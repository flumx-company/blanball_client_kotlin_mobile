package com.example.blanball.utils.di

import com.example.blanball.utils.NavigationManagerImpl
import com.example.data.backend.*
import com.example.data.backend.models.AuthApiService
import com.example.data.datastore.remembermemanager.RememberMeManager
import com.example.data.datastore.remembermemanager.RememberMeManagerImpl
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.data.datastore.tokenmanager.TokenManagerImpl
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManager
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManagerImpl
import com.example.data.datastore.useremailmanager.UserEmailManager
import com.example.data.datastore.useremailmanager.UserEmailManagerImpl
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.usernamemanager.UserNameManagerImpl
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.data.datastore.userphonemanager.UserPhoneManagerImpl
import com.example.data.datastore.verifycodemanager.ResetPassVerifyCodeManager
import com.example.data.datastore.verifycodemanager.ResetPassVerifyCodeManagerImpl
import com.example.domain.repository.AppRepository
import com.example.domain.utils.Endpoints
import com.example.domain.utils.NavigationManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [DataStoreModule::class])
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .authenticator(authAuthenticator)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManagerImpl): AuthInterceptor {
        return AuthInterceptor(tokenManager)
    }

    @Singleton
    @Provides
    fun provideAuthAuthenticator(
        tokenManager: TokenManager,
        navigationManager: NavigationManager
    ): AuthAuthenticator {
        return AuthAuthenticator(tokenManager, navigationManager)
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Endpoints.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())

    @Singleton
    @Provides
    fun provideAuthAPIService(retrofit: Retrofit.Builder): AuthApiService =
        retrofit
            .build()
            .create(AuthApiService::class.java)

    @Singleton
    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): MainApiService = retrofit
        .client(okHttpClient)
        .build()
        .create(MainApiService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAppRepository(appRepository: AppRepositoryImpl): AppRepository

    @Binds
    fun bindTokenManager(tokenManager: TokenManagerImpl): TokenManager

    @Binds
    fun bindResetPassVerifyCodeManager(resetPassVerifyCodeManager: ResetPassVerifyCodeManagerImpl): ResetPassVerifyCodeManager

    @Binds
    fun bindUserPhoneManager(userPhoneManager: UserPhoneManagerImpl): UserPhoneManager

    @Binds
    fun bindUserNameManager(userNameManager: UserNameManagerImpl): UserNameManager

    @Binds
    fun bindRememberMeManager (rememberMeManager: RememberMeManagerImpl): RememberMeManager

    @Binds
    fun bindNavigationManager(navigationManager: NavigationManagerImpl): NavigationManager

    @Binds
    fun bindUserAvatarUrlManager(userAvatarUrlManager: UserAvatarUrlManagerImpl): UserAvatarUrlManager

    @Binds
    fun bindUserEmailManager(userEmailManager: UserEmailManagerImpl): UserEmailManager
}