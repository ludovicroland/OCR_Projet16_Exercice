package com.openclassrooms.arista.domainTest.modelTest

import com.openclassrooms.arista.data.entity.ExerciseDto
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class ExerciseTest {

    @Test
    fun `toDto should convert Exercise to ExerciseDto`() {
        // Arrange
        val exercise = Exercise(
            id = 1L,
            startTime = LocalDateTime.of(2025, 1, 1, 10, 0),
            duration = 60,
            category = ExerciseCategory.Running,
            intensity = 8
        )

        // Act
        val exerciseDto = exercise.toDto()

        // Assert
        assertEquals(exercise.startTime.toEpochSecond(ZoneOffset.UTC), exerciseDto.startTime)
        assertEquals(60, exerciseDto.duration)
        assertEquals("Running", exerciseDto.category)
        assertEquals(8, exerciseDto.intensity)
    }

    @Test
    fun `fromDto should convert ExerciseDto to Exercise correctly`() {
        // Arrange
        val exerciseDto = ExerciseDto(
            id = 1L,
            startTime = Instant.parse("2023-10-01T12:00:00Z").toEpochMilli(), // 1st October 2023, 12:00 PM UTC
            duration = 30,
            category = "Running",
            intensity = 5
        )

        // Act
        val result = Exercise.fromDto(exerciseDto)

        // Assert
        assertEquals(exerciseDto.id, result.id)
        assertEquals(
            LocalDateTime.ofInstant(Instant.ofEpochMilli(exerciseDto.startTime), ZoneId.systemDefault()),
            result.startTime
        )
        assertEquals(exerciseDto.duration, result.duration)
        assertEquals(ExerciseCategory.valueOf(exerciseDto.category), result.category)
        assertEquals(exerciseDto.intensity, result.intensity)
    }


    @Test
    fun `setStartTime should correctly update startTime`() {
        // Arrange
        val exercise = Exercise(
            id = 1L,
            startTime = LocalDateTime.of(2025, 1, 1, 10, 0),
            duration = 60,
            category = ExerciseCategory.Running,
            intensity = 8
        )

        // Act
        exercise.startTime = LocalDateTime.of(2025, 1, 2, 12, 0)

        // Assert
        assertEquals(LocalDateTime.of(2025, 1, 2, 12, 0), exercise.startTime)
    }

    @Test
    fun `setDuration should correctly update duration`() {
        // Arrange
        val exercise = Exercise(
            id = 1L,
            startTime = LocalDateTime.of(2025, 1, 1, 10, 0),
            duration = 60,
            category = ExerciseCategory.Running,
            intensity = 8
        )

        // Act
        exercise.duration = 90

        // Assert
        assertEquals(90, exercise.duration)
    }

    @Test
    fun `setCategory should correctly update category`() {
        // Arrange
        val exercise = Exercise(
            id = 1L,
            startTime = LocalDateTime.of(2025, 1, 1, 10, 0),
            duration = 60,
            category = ExerciseCategory.Running,
            intensity = 8
        )

        // Act
        exercise.category = ExerciseCategory.Running

        // Assert
        assertEquals(ExerciseCategory.Running, exercise.category)
    }

    @Test
    fun `setIntensity should correctly update intensity`() {
        // Arrange
        val exercise = Exercise(
            id = 1L,
            startTime = LocalDateTime.of(2025, 1, 1, 10, 0),
            duration = 60,
            category = ExerciseCategory.Running,
            intensity = 8
        )

        // Act
        exercise.intensity = 10

        // Assert
        assertEquals(10, exercise.intensity)
    }


}