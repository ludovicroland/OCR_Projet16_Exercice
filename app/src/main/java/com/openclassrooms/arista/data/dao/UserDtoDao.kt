package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.openclassrooms.arista.data.entity.UserDto

@Dao
interface UserDtoDao {
    @Insert
    suspend fun insertUser(user: UserDto): Long

    @Update
    suspend fun updateUser(user: UserDto)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Long): UserDto?

}
