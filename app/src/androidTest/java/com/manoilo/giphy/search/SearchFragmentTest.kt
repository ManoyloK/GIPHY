package com.manoilo.giphy.search

import android.view.KeyEvent
import androidx.databinding.DataBindingComponent
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.manoilo.giphy.R
import com.manoilo.giphy.binding.FragmentBindingAdapters
import com.manoilo.giphy.entity.Gif
import com.manoilo.giphy.testing.SingleFragmentActivity
import com.manoilo.giphy.ui.SearchFragment
import com.manoilo.giphy.ui.SearchViewModel
import com.manoilo.giphy.util.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)
    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()
    @Rule
    @JvmField
    val countingAppExecutors = CountingAppExecutorsRule()
    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    private lateinit var mockBindingAdapter: FragmentBindingAdapters
    private lateinit var viewModel: SearchViewModel
    private val results = MutableLiveData<List<Gif>>()
    private val searchFragment = SearchFragment.newInstance()

    @Before
    fun init() {
        viewModel = mock(SearchViewModel::class.java)
        `when`(viewModel.results).thenReturn(results)

        mockBindingAdapter = mock(FragmentBindingAdapters::class.java)

        searchFragment.appExecutors = countingAppExecutors.appExecutors
        searchFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        searchFragment.dataBindingComponent = object : DataBindingComponent {
            override fun getFragmentBindingAdapters(): FragmentBindingAdapters {
                return mockBindingAdapter
            }
        }
        activityRule.activity.setFragment(searchFragment)
    }

    @Test
    fun search() {
        onView(withId(R.id.input)).perform(
            typeText("cats"),
            pressKey(KeyEvent.KEYCODE_ENTER)
        )
        verify(viewModel).searchGifs("cats")
    }

    @Test
    fun loadResults() {
        val gisf = Gif("testUrl")
        results.postValue(arrayListOf(gisf))
        onView(listMatcher().atPosition(0)).check(matches(isDisplayed()))
    }


    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.photo_list)
    }

}