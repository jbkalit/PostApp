package com.jbkalit.postapp.presentation.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jbkalit.domain.model.Album
import com.jbkalit.domain.model.User
import com.jbkalit.domain.usecase.album.AlbumUseCaseContract
import com.jbkalit.domain.usecase.user.UserUseCaseContract
import com.jbkalit.postapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userUseCase: UserUseCaseContract,
                                           private val albumUseCase: AlbumUseCaseContract)
    : BaseViewModel() {

    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    fun fetchProfile(userId: Int) {
        disposable = userUseCase.getUserById(userId)
            .subscribeWith(object : DisposableSingleObserver<User>(){
                override fun onSuccess(user: User) {
                    _user.value = user
                    isError.value = false
                }

                override fun onError(e: Throwable) {
                    isError.value = true
                }
            })
    }

    fun fetchAlbums(userId: Int) {
        isLoading.value = true
        disposable = albumUseCase.getAlbumByUserId(userId)
            .subscribeWith(object : DisposableSingleObserver<List<Album>>(){
                override fun onSuccess(album: List<Album>) {
                    _albums.value = album
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
