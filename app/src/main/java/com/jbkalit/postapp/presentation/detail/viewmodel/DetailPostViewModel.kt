package com.jbkalit.postapp.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jbkalit.domain.model.PostDetail
import com.jbkalit.domain.usecase.post.PostUseCaseContract
import com.jbkalit.postapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(private val postUseCase: PostUseCaseContract)
    : BaseViewModel() {

    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    private val _post = MutableLiveData<PostDetail>()
    val post : LiveData<PostDetail>
        get() = _post

    fun fetchDetailPost(postId: Int) {
        isLoading.value = true
        disposable = postUseCase.getPostById(postId)
            .subscribeWith(object : DisposableSingleObserver<PostDetail>(){
                override fun onSuccess(postDetail: PostDetail) {
                    _post.value = postDetail
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
