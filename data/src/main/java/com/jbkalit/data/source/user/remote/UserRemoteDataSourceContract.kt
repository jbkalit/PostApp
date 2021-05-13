package com.jbkalit.data.source.user.remote

import com.jbkalit.domain.model.User
import io.reactivex.Single

interface UserRemoteDataSourceContract {

    fun getUserById(userId: Int): Single<User>

}
