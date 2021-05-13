package com.jbkalit.data.source.user.remote

import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.service.UserService
import com.jbkalit.domain.model.User
import io.reactivex.Single
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userService: UserService,
                                               private val schedulerProvider: BaseSchedulerProvider)
    : UserRemoteDataSourceContract {

    override fun getUserById(userId: Int): Single<User> {
        return userService.getUserById(userId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

}
