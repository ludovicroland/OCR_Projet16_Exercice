package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.User

class UserRepository(private val userDtoDao: UserDtoDao) {

    suspend fun getCurrentUser(): User? {
        return userDtoDao.getUserById(1)?.let { User.fromDto(it) }
    }

    suspend fun setUser(user: User) {
        val userDto = user.toDto()
        userDtoDao.updateUser(userDto)
    }
}