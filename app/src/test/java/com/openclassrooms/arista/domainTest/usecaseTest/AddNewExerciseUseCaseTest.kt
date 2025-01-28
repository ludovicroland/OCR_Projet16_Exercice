package com.openclassrooms.arista.domainTest.usecaseTest

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class AddNewExerciseUseCaseTest {

    @Mock
    private lateinit var exerciseRepository: ExerciseRepository

    private lateinit var addNewExerciseUseCase: AddNewExerciseUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        addNewExerciseUseCase = AddNewExerciseUseCase(exerciseRepository)
    }

    @Test
    fun `execute should call repository addExercise with correct exercise`() = runBlocking {
        // Arrange
        val exercise = Exercise(
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 5
        )

        // Simuler le comportement de la méthode suspend
        Mockito.doAnswer {
            // Ne rien faire (équivalent de doNothing pour les méthodes suspend)
            Unit
        }.`when`(exerciseRepository).addExercise(exercise)

        // Act
        addNewExerciseUseCase.execute(exercise)

        // Assert
        Mockito.verify(exerciseRepository).addExercise(exercise)
    }

    @Test
    fun `execute should not throw exception when repository succeeds`() = runBlocking {
        // Arrange
        val exercise = Exercise(
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 5
        )

        // Simuler le comportement de la méthode suspend
        Mockito.doAnswer {
            // Ne rien faire (équivalent de doNothing pour les méthodes suspend)
            Unit
        }.`when`(exerciseRepository).addExercise(exercise)

        // Act & Assert
        try {
            addNewExerciseUseCase.execute(exercise)
            assertTrue(true) // Si on arrive ici, c'est que tout s'est bien passé
        } catch (e: Exception) {
            assertTrue(false) // Si une exception est levée, le test échoue
        }
    }
}