package com.openclassrooms.arista.domainTest.usecaseTest

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetUserUsecaseTest {
    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var getUserUsecase: GetUserUsecase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getUserUsecase = GetUserUsecase(userRepository)
    }

    @Test
    fun `execute should return user when repository returns user`() = runBlocking {
        // Arrange
        val fakeUser = User(name = "John Doe", email = "john.doe@example.com")
        Mockito.`when`(userRepository.getCurrentUser()).thenReturn(fakeUser)

        // Act
        val result = getUserUsecase.execute()

        // Assert
        assertEquals(fakeUser, result)
    }

    @Test
    fun `execute should return null when repository returns null`() = runBlocking {
        // Arrange
        Mockito.`when`(userRepository.getCurrentUser()).thenReturn(null)

        // Act
        val result = getUserUsecase.execute()

        // Assert
        assertNull(result)
    }
}