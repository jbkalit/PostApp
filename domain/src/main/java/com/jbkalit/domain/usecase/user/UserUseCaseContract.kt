package com.jbkalit.domain.usecase.user

import com.jbkalit.domain.model.User
import io.reactivex.Single

interface UserUseCaseContract {

    fun getUserById(userId: Int): Single<User>

}
