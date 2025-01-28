package com.openclassrooms.arista.domainTest.modelTest

import com.openclassrooms.arista.data.entity.SleepDto
import com.openclassrooms.arista.domain.model.Sleep
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class SleepTest {

    @Test
    fun `fromDto should convert SleepDto to Sleep correctly`() {
        // Arrange
        val sleepDto = SleepDto(
            startTime = Instant.parse("2023-10-01T22:00:00Z").toEpochMilli(), // 1st October 2023, 10:00 PM UTC
            duration = 480, // 8 hours
            quality = 85 // Quality score
        )

        // Act
        val result = Sleep.fromDto(sleepDto)

        // Assert
        assertEquals(
            LocalDateTime.ofInstant(Instant.ofEpochMilli(sleepDto.startTime), ZoneId.systemDefault()),
            result.startTime
        )
        assertEquals(sleepDto.duration, result.duration)
        assertEquals(sleepDto.quality, result.quality)
    }


    @Test
    fun `setDuration should correctly update the duration`() {
        // Arrange
        val sleep = Sleep(
            startTime = LocalDateTime.now(),
            duration = 300, // Initial duration: 5 hours
            quality = 70
        )

        // Act
        sleep.duration = 480 // Update to 8 hours

        // Assert
        assertEquals(480, sleep.duration)
    }

    @Test
    fun `setQuality should correctly update the quality`() {
        // Arrange
        val sleep = Sleep(
            startTime = LocalDateTime.now(),
            duration = 300, // Initial duration: 5 hours
            quality = 70
        )

        // Act
        sleep.quality = 90 // Update to quality score of 90

        // Assert
        assertEquals(90, sleep.quality)
    }



}