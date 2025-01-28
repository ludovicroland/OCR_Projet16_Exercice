package com.openclassrooms.arista.viewmodelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import com.openclassrooms.arista.ui.sleep.SleepViewModel
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

@ExperimentalCoroutinesApi
class SleepViewModelTest {

    // Règle pour exécuter les tâches de LiveData de manière synchrone
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getAllSleepsUseCase: GetAllSleepsUseCase

    private lateinit var sleepViewModel: SleepViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        // Utiliser un dispatcher de test pour les coroutines
        Dispatchers.setMain(UnconfinedTestDispatcher())
        sleepViewModel = SleepViewModel(getAllSleepsUseCase)
    }

    @After
    fun tearDown() {
        // Réinitialiser le dispatcher principal après les tests
        Dispatchers.resetMain()
    }




}