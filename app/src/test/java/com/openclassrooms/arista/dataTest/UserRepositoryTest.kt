package com.openclassrooms.arista.dataTest

import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.data.entity.UserDto
import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserRepositoryTest {

    @Mock
    private lateinit var userDtoDao: UserDtoDao

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(userDtoDao)
    }

    @Test
    fun `getCurrentUser should return user when DAO returns user DTO`() = runBlocking {
        // Arrange
        val userDto = UserDto(id = 1, name = "John Doe", email = "john.doe@example.com")
        Mockito.`when`(userDtoDao.getUserById(1)).thenReturn(userDto)

        // Act
        val result = userRepository.getCurrentUser()

        // Assert
        assertEquals("John Doe", result?.name)
        assertEquals("john.doe@example.com", result?.email)
    }

    @Test
    fun `getCurrentUser should return null when DAO returns null`() = runBlocking {
        // Arrange
        Mockito.`when`(userDtoDao.getUserById(1)).thenReturn(null)

        // Act
        val result = userRepository.getCurrentUser()

        // Assert
        assertNull(result)
    }

    @Test
    fun `setUser should call updateUser on DAO with correct DTO`() = runBlocking {
        // Arrange
        val user = User(name = "Jane Doe", email = "jane.doe@example.com")
        val userDto = user.toDto()

        // Act
        userRepository.setUser(user)

        // Assert
        Mockito.verify(userDtoDao).updateUser(userDto)
    }
}