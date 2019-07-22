package com.manoilo.giphy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manoilo.giphy.entity.Gif
import com.manoilo.giphy.mappers.GifMapper
import com.manoilo.giphy.repository.GifRepository
import com.manoilo.giphy.testing.OpenForTesting
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@OpenForTesting
class SearchViewModel @Inject constructor(
    val gifRepository: GifRepository,
    val gifMapper: GifMapper
) : ViewModel() {
    private val _results = MutableLiveData<List<Gif>>()
    val results: LiveData<List<Gif>>
        get() = _results

    private val _query = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    fun searchGifs(query: String) {
        if (query.isNotBlank() && query != _query.value) {
            _query.value = query
            compositeDisposable.add(
                gifRepository.searchGifs(query).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        if (response.isSuccessful && response.body() != null)
                            _results.postValue(response.body()!!.gifs.map { gifMapper.map(it) })
                        else _results.postValue(emptyList())
                    },
                    {
                        _results.postValue(emptyList())
                    })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
