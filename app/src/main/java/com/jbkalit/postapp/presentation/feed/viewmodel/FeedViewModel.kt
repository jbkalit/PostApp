package com.jbkalit.postapp.presentation.feed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jbkalit.domain.model.Feed
import com.jbkalit.domain.usecase.feed.FeedUseCaseContract
import com.jbkalit.postapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val feedUseCase: FeedUseCaseContract)
    : BaseViewModel() {

    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    private val _feed = MutableLiveData<List<Feed>>()
    val feed : LiveData<List<Feed>>
        get() = _feed

    fun fetchFeed() {
        isLoading.value = true
        disposable = feedUseCase.getAllPosts()
            .subscribeWith(object : DisposableSingleObserver<List<Feed>>(){
                override fun onSuccess(feeds: List<Feed>) {
                    _feed.value = feeds
                    isLoading.value = false
                    isError.value = false
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    isError.value = true
                }
            })
    }

}
