package com.jbkalit.domain.repository.user

import com.jbkalit.domain.model.*
import io.reactivex.Single

interface UserRepositoryContract {

    fun getUserById(userId: Int): Single<User>

}
