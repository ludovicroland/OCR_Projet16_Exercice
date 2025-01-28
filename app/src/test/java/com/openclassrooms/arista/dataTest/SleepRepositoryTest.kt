package com.openclassrooms.arista.dataTest

import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.data.entity.SleepDto
import com.openclassrooms.arista.data.repository.SleepRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SleepRepositoryTest {
    @Mock
    private lateinit var sleepDao: SleepDtoDao

    private lateinit var sleepRepository: SleepRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sleepRepository = SleepRepository(sleepDao)
    }

    @Test
    fun `getAllSleeps should return list of sleeps`() = runBlocking {
        // Arrange
        val sleepDtos = listOf(
            SleepDto(startTime = 123456789, duration = 480, quality = 85),
            SleepDto(startTime = 987654321, duration = 360, quality = 70)
        )
        Mockito.`when`(sleepDao.getAllSleeps()).thenReturn(sleepDtos)

        // Act
        val result = sleepRepository.getAllSleeps()

        // Assert
        assertEquals(2, result.size)
        assertEquals(480, result[0].duration)
        assertEquals(85, result[0].quality)
        assertEquals(360, result[1].duration)
        assertEquals(70, result[1].quality)
    }

    @Test
    fun `getAllSleeps should return empty list when DAO returns empty list`() = runBlocking {
        // Arrange
        Mockito.`when`(sleepDao.getAllSleeps()).thenReturn(emptyList())

        // Act
        val result = sleepRepository.getAllSleeps()

        // Assert
        assertTrue(result.isEmpty())
    }
}