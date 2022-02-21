import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.vehicle.VehicleLocalDataSource
import com.joseferreyra.data.vehicle.VehicleRemoteDataSource
import com.joseferreyra.data.vehicle.VehicleRepository
import com.joseferreyra.domain.Vehicle
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

class VehicleRepositoryTest {

    private val localDataSource: VehicleLocalDataSource = mock()
    private val remoteDataSource: VehicleRemoteDataSource = mock()

    private lateinit var vehicleRepository: VehicleRepository.VehicleRepositoryImpl

    @Before
    fun setup() {
        vehicleRepository = VehicleRepository.VehicleRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `Repository should return values when success`() {
        whenever(remoteDataSource.requestVehicles()).thenReturn(Either.Right(mockedVehicles ))
        assert((vehicleRepository.requestVehicles() as Either.Right).b == mockedVehicles)
    }

    @Test
    fun `repository should return failure network connection on failed`() {
        whenever(remoteDataSource.requestVehicles()).thenReturn(Either.Left(Failure.NetworkFailure))
        assert((vehicleRepository.requestVehicles() as Either.Left).a == Failure.NetworkFailure)
    }
}

val mockedVehicles = listOf<Vehicle>( Vehicle(1,
    "BMW" ,
    "316i",
    13000,
    "Gasoline",
    listOf("https://loremflickr.com/g/320/240/bmw", "https://loremflickr.com/g/640/480/bmw")),
    Vehicle(2,
        "BMW" ,
        "316i",
        13000,
        "Gasoline",
        listOf("https://loremflickr.com/g/320/240/bmw", "https://loremflickr.com/g/640/480/bmw"))
)