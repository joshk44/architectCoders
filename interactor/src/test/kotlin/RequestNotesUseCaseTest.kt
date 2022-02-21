@file:JvmName("RequestNoteUseCaseTestKt")

package com.joseferreyra.interactor

import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.notes.NotesRepository
import com.joseferreyra.domain.Note
import com.joseferreyra.interactor.main.RequestNotesUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Test

class RequestNotesUseCaseTest {

    private val repository: NotesRepository = mock()

    private lateinit var requestNotesUseCase: RequestNotesUseCase

    private lateinit var coroutineScope: CoroutineScope


    @Before
    fun setup() {
        coroutineScope = TestCoroutineScope()
        requestNotesUseCase =
            RequestNotesUseCase(repository, coroutineScope, Dispatchers.Unconfined)

    }

    @Test
    fun `Repository should be call with params`() {
        requestNotesUseCase.invoke(Unit, {})
        verify(repository).requestNotes()
    }

    @Test
    fun `Repository should response value`() {
        val response: (List<Note>) -> Unit = mock()
        whenever(repository.requestNotes()).thenReturn(Either.Right(mockedNotes))
        requestNotesUseCase.invoke(Unit) { it.either({}, response) }
        verify(response).invoke(mockedNotes)
    }

    @Test
    fun `Repository should response failure`() {
        val response: (Failure) -> Unit = mock()
        whenever(repository.requestNotes()).thenReturn(Either.Left(Failure.FeatureFailure))
        requestNotesUseCase.invoke(Unit) { it.either(response, {}) }
        verify(response).invoke(Failure.FeatureFailure)
    }
}

val mockedNotes = listOf<Note>( Note ( 1, "good note"))
