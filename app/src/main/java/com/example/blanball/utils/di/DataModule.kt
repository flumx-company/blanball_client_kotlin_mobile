//package com.example.blanball.utils.di
//
//import com.example.data.AppRepositoryImpl
//import com.example.data.backend.ApiService
//import com.example.domain.repository.AppRepository
//import dagger.Module
//import dagger.Provides
//
//@Module
//class DataModule {
//
//    @Provides
//    fun provideApiService(): ApiService {
//        return ApiService.ApiObject.retrofitService
//    }
//
//    @Provides
//    fun provideAppRepository(service: ApiService): AppRepository {
//        return AppRepositoryImpl(service = service)
//    }
//
//}