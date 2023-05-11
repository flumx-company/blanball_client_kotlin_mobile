package com.example.blanball.utils.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, DataStoreModule::class])
interface AppComponent {
}