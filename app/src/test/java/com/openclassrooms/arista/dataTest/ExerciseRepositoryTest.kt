package com.openclassrooms.arista.dataTest

import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.data.entity.ExerciseDto
import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class ExerciseRepositoryTest {
    @Mock
    private lateinit var exerciseDao: ExerciseDtoDao

    private lateinit var exerciseRepository: ExerciseRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        exerciseRepository = ExerciseRepository(exerciseDao)
    }

    @Test
    fun `getAllExercises should return list of exercises`() = runBlocking {
        // Arrange
        val exerciseDtos = listOf(
            ExerciseDto(id = 1, startTime = 123456789, duration = 30, category = "Running", intensity = 5),
            ExerciseDto(id = 2, startTime = 987654321, duration = 45, category = "Walking", intensity = 7)
        )
        Mockito.`when`(exerciseDao.getAllExercises()).thenReturn(flowOf(exerciseDtos))

        // Act
        val result = exerciseRepository.getAllExercises()

        // Assert
        assertEquals(2, result.size)
        assertEquals(1L, result[0].id)
        assertEquals("Running", result[0].category.name)
        assertEquals(2L, result[1].id)
        assertEquals("Walking", result[1].category.name)
    }

    @Test
    fun `addExercise should call insertExercise on DAO`(): Unit = runBlocking {
        // Arrange
        val exercise = Exercise(
            id = 1L,
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 5
        )
        val exerciseDto = exercise.toDto()

        // Act
        exerciseRepository.addExercise(exercise)

        // Assert
        Mockito.verify(exerciseDao).insertExercise(exerciseDto)
    }

    @Test
    fun `deleteExercise should call deleteExerciseById on DAO`() = runBlocking {
        // Arrange
        val exercise = Exercise(
            id = 1L,
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 5
        )

        // Act
        exerciseRepository.deleteExercise(exercise)

        // Assert
        Mockito.verify(exerciseDao).deleteExerciseById(exercise.id!!)
    }

    @Test
    fun `deleteExercise should not call deleteExerciseById if id is null`() = runBlocking {
        // Arrange
        val exercise = Exercise(
            id = null, // Pas d'ID
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 5
        )

        // Act
        exerciseRepository.deleteExercise(exercise)

        // Assert
        Mockito.verify(exerciseDao, Mockito.never()).deleteExerciseById(Mockito.anyLong())
    }
}