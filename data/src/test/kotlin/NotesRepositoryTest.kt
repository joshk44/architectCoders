import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.notes.NotesLocalDataSource
import com.joseferreyra.data.notes.NotesRemoteDataSource
import com.joseferreyra.data.notes.NotesRepository
import com.joseferreyra.data.vehicle.VehicleLocalDataSource
import com.joseferreyra.data.vehicle.VehicleRemoteDataSource
import com.joseferreyra.data.vehicle.VehicleRepository
import com.joseferreyra.domain.Note
import com.joseferreyra.domain.Vehicle
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

class NotesRepositoryTest {

    private val localDataSource: NotesLocalDataSource = mock()
    private val remoteDataSource: NotesRemoteDataSource = mock()

    private lateinit var notesRepository: NotesRepository.NotesRepositoryImpl

    @Before
    fun setup() {
        notesRepository = NotesRepository.NotesRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `Repository should return values when success`() {
        whenever(remoteDataSource.requestNotes()).thenReturn(Either.Right(mockedNotes))
        assert((notesRepository.requestNotes() as Either.Right).b == mockedNotes)
    }

    @Test
    fun `repository should return failure network connection on failed`() {
        whenever(remoteDataSource.requestNotes()).thenReturn(Either.Left(Failure.NetworkFailure))
        assert((notesRepository.requestNotes() as Either.Left).a == Failure.NetworkFailure)
    }
}

val mockedNotes = listOf<Note>( Note ( 1, "good note"))
