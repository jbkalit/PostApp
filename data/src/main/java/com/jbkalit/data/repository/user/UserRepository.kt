package com.jbkalit.data.repository.user


import com.jbkalit.data.source.user.remote.UserRemoteDataSourceContract
import com.jbkalit.domain.model.*
import com.jbkalit.domain.repository.user.UserRepositoryContract
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject
constructor(private val userRemoteDataSource: UserRemoteDataSourceContract)
    : UserRepositoryContract {

    override fun getUserById(userId: Int): Single<User> {
        return userRemoteDataSource.getUserById(userId)
    }

}
