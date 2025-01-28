package com.openclassrooms.arista.domainTest.usecaseTest

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class GetAllSleepsUseCaseTest {
    @Mock
    private lateinit var sleepRepository: SleepRepository

    private lateinit var getAllSleepsUseCase: GetAllSleepsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAllSleepsUseCase = GetAllSleepsUseCase(sleepRepository)
    }

    @Test
    fun `execute should return sleeps when repository returns sleeps`() = runBlocking {
        // Arrange
        val fakeSleeps = listOf(
            Sleep(
                startTime = LocalDateTime.now(),
                duration = 480, // 8 hours
                quality = 85
            ),
            Sleep(
                startTime = LocalDateTime.now().minusDays(1),
                duration = 360, // 6 hours
                quality = 70
            )
        )
        Mockito.`when`(sleepRepository.getAllSleeps()).thenReturn(fakeSleeps)

        // Act
        val result = getAllSleepsUseCase.execute()

        // Assert
        assertEquals(fakeSleeps, result)
    }

    @Test
    fun `execute should return empty list when repository returns empty list`() = runBlocking {
        // Arrange
        Mockito.`when`(sleepRepository.getAllSleeps()).thenReturn(emptyList())

        // Act
        val result = getAllSleepsUseCase.execute()

        // Assert
        assertTrue(result.isEmpty())
    }
}