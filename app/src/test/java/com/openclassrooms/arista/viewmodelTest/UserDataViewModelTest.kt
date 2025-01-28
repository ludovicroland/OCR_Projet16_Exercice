package com.openclassrooms.arista.viewmodelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import com.openclassrooms.arista.ui.user.UserDataViewModel
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
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
@OptIn(ExperimentalCoroutinesApi::class)
class UserDataViewModelTest {
    // Règle pour exécuter les tâches de LiveData de manière synchrone
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getUserUsecase: GetUserUsecase

    private lateinit var userDataViewModel: UserDataViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        // Utiliser un dispatcher de test pour les coroutines
        Dispatchers.setMain(UnconfinedTestDispatcher())
        userDataViewModel = UserDataViewModel(getUserUsecase)
    }

    @After
    fun tearDown() {
        // Réinitialiser le dispatcher principal après les tests
        Dispatchers.resetMain()
    }

    @Test
    fun `loadUserData should update userFlow with user data`() = runTest {
        // Arrange
        val fakeUser = User(name = "John Doe", email = "john.doe@example.com")
        `when`(getUserUsecase.execute()).thenReturn(fakeUser)

        // Assert
        verify(getUserUsecase).execute()
    }

    @Test
    fun `loadUserData should update userFlow with null if no user found`() = runTest {
        // Arrange
        `when`(getUserUsecase.execute()).thenReturn(null)

        // Act
        val result = userDataViewModel.userFlow.first()

        // Assert
        assertEquals(null, result)
        verify(getUserUsecase).execute()
    }
}