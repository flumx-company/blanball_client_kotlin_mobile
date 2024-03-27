package com.example.blanball.utils.di

import com.example.blanball.utils.errorshandler.ErrorsHandler
import com.example.blanball.utils.errorshandler.ErrorsHandlerImpl
import com.example.blanball.utils.workers.LoadUsersWorker
import com.example.blanball.utils.workers.LoadUsersWorkerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    fun bindLoadUsersWorker(loadUsersWorker: LoadUsersWorkerImpl): LoadUsersWorker

    @Binds
    fun bindErrorsHandler(errorsHandler: ErrorsHandlerImpl): ErrorsHandler
}