package com.openclassrooms.arista.viewmodelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import com.openclassrooms.arista.ui.exercise.ExerciseViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

@ExperimentalCoroutinesApi
class ExerciseViewModelTest {

    // Règle pour exécuter les tâches de LiveData de manière synchrone
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getAllExercisesUseCase: GetAllExercisesUseCase

    @Mock
    private lateinit var addNewExerciseUseCase: AddNewExerciseUseCase

    @Mock
    private lateinit var deleteExerciseUseCase: DeleteExerciseUseCase

    private lateinit var exerciseViewModel: ExerciseViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        // Utiliser un dispatcher de test pour les coroutines
        Dispatchers.setMain(UnconfinedTestDispatcher())
        exerciseViewModel = ExerciseViewModel(
            getAllExercisesUseCase,
            addNewExerciseUseCase,
            deleteExerciseUseCase
        )
    }

    @After
    fun tearDown() {
        // Réinitialiser le dispatcher principal après les tests
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch exercise should update exercise with empty list if no sleeps found`() = runTest {
        // Arrange
        Mockito.`when`(getAllExercisesUseCase.execute()).thenReturn(emptyList())

        // Act
        val result = exerciseViewModel.exercisesFlow.first()

        // Assert
        assertEquals(null, result)
        Mockito.verify(getAllExercisesUseCase).execute()
    }
}