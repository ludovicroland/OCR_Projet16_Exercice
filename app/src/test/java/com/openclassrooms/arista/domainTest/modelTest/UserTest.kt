package com.openclassrooms.arista.domainTest.modelTest

import com.openclassrooms.arista.data.entity.UserDto
import com.openclassrooms.arista.domain.model.User
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserTest {

    @Test
    fun `toDto should convert User to UserDto`() {
        // Arrange
        val user = User(name = "John Doe", email = "john.doe@example.com")

        // Act
        val userDto = user.toDto()

        // Assert
        assertEquals("John Doe", userDto.name)
        assertEquals("john.doe@example.com", userDto.email)
    }

    @Test
    fun `fromDto should convert UserDto to User`() {
        // Arrange
        val userDto = UserDto(name = "Jane Doe", email = "jane.doe@example.com")

        // Act
        val user = User.fromDto(userDto)

        // Assert
        assertEquals("Jane Doe", user.name)
        assertEquals("jane.doe@example.com", user.email)
    }

    @Test
    fun `setName should correctly update the name`() {
        // Arrange
        val user = User(name = "John Doe", email = "john.doe@example.com")

        // Act
        user.name = "Jane Doe"

        // Assert
        assertEquals("Jane Doe", user.name)
    }

    @Test
    fun `setEmail should correctly update the email`() {
        // Arrange
        val user = User(name = "John Doe", email = "john.doe@example.com")

        // Act
        user.email = "jane.doe@example.com"

        // Assert
        assertEquals("jane.doe@example.com", user.email)
    }

}