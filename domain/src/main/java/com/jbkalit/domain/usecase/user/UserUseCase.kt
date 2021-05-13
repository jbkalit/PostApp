package com.jbkalit.domain.usecase.user

import com.jbkalit.domain.repository.user.UserRepositoryContract
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepositoryContract)
    : UserUseCaseContract {

    override fun getUserById(userId: Int) = userRepository.getUserById(userId)

}
