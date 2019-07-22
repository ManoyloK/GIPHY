package com.manoilo.giphy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.manoilo.giphy.data.GifSearchResponse
import com.manoilo.giphy.entity.Gif
import com.manoilo.giphy.mappers.GifMapper
import com.manoilo.giphy.repository.GifRepository
import com.manoilo.giphy.ui.SearchViewModel
import com.manoilo.giphy.util.RxImmediateSchedulerRule
import com.manoilo.giphy.util.mock
import com.manoilo.giphy.utils.TestUtil
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import retrofit2.Response


@RunWith(JUnit4::class)
class SearchViewModelTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private val repository = mock(GifRepository::class.java)
    private val gifMapper = GifMapper()

    private lateinit var viewModel: SearchViewModel
    private lateinit var sourceData: Observable<Response<GifSearchResponse>>

    @Before
    fun init() {
        viewModel = SearchViewModel(repository, gifMapper)
        val gifSearchResponse = TestUtil.getGifSearchResponse()
        sourceData = TestUtil.createSuccesCall(gifSearchResponse)
    }

    @Test
    fun empty() {
        val result = mock<Observer<List<Gif>>>()
        viewModel.results.observeForever(result)
        viewModel.searchGifs("")
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun basic() {
        val result = mock<Observer<List<Gif>>>()
        viewModel.results.observeForever(result)
        `when`(repository.searchGifs("cats")).thenReturn(sourceData)
        viewModel.searchGifs("cats")
        Mockito.verify(repository).searchGifs("cats")
    }

    @Test
    fun sameQuery() {
        viewModel.results.observeForever(mock())
        `when`(repository.searchGifs("cats")).thenReturn(sourceData)
        `when`(repository.searchGifs("dogs")).thenReturn(sourceData)
        viewModel.searchGifs("cats")
        Mockito.verify(repository).searchGifs("cats")

        viewModel.searchGifs("cats")
        verifyNoMoreInteractions(repository)
        viewModel.searchGifs("dogs")
        Mockito.verify(repository).searchGifs("dogs")
    }

}