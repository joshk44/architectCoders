package com.joseferreyra.architectcoders.di

import com.joseferreyra.architectcoders.main.framework.note.NotesLocalDataSourceImpl
import com.joseferreyra.architectcoders.main.framework.note.NotesRemoteDataSourceImpl
import com.joseferreyra.architectcoders.main.framework.note.NotesService
import com.joseferreyra.architectcoders.main.framework.vehicle.VehicleLocalDataSourceImpl
import com.joseferreyra.architectcoders.main.framework.vehicle.VehicleRemoteDataSourceImpl
import com.joseferreyra.architectcoders.main.framework.vehicle.VehicleService
import com.joseferreyra.architectcoders.main.ui.MainViewModel
import com.joseferreyra.data.notes.NotesLocalDataSource
import com.joseferreyra.data.notes.NotesRemoteDataSource
import com.joseferreyra.data.notes.NotesRepository
import com.joseferreyra.data.vehicle.VehicleLocalDataSource
import com.joseferreyra.data.vehicle.VehicleRemoteDataSource
import com.joseferreyra.data.vehicle.VehicleRepository
import com.joseferreyra.interactor.base.UseCaseScope
import com.joseferreyra.interactor.main.RequestNotesUseCase
import com.joseferreyra.interactor.main.RequestVehiclesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule: Module = module {

    factory<CoroutineScope> { UseCaseScope() }
    factory { Dispatchers.IO }

    factory<VehicleLocalDataSource> { VehicleLocalDataSourceImpl() }
    factory<VehicleRemoteDataSource> { VehicleRemoteDataSourceImpl(get()) }
    factory<VehicleRepository> { VehicleRepository.VehicleRepositoryImpl(get(), get()) }
    single<VehicleService> { get<Retrofit>().create(VehicleService::class.java) }
    factory<RequestVehiclesUseCase> { RequestVehiclesUseCase(get(), get(), get()) }

    factory<NotesLocalDataSource> { NotesLocalDataSourceImpl() }
    factory<NotesRemoteDataSource> { NotesRemoteDataSourceImpl(get()) }
    factory<NotesRepository> { NotesRepository.NotesRepositoryImpl(get(), get()) }
    single<NotesService> { get<Retrofit>().create(NotesService::class.java) }
    factory<RequestNotesUseCase> { RequestNotesUseCase(get(), get(), get()) }

    viewModel { MainViewModel(get(), get()) }
}


