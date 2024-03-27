package com.example.blanball.utils.di

import com.example.blanball.utils.errorshandler.DetailMessageHandler
import com.example.blanball.utils.errorshandler.DetailMessageHandlerImpl
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
    fun bindDetailMessageHandler(errorsHandler: DetailMessageHandlerImpl): DetailMessageHandler
}