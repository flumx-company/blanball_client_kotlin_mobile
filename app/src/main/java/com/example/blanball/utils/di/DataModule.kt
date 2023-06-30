package com.example.blanball.utils.di

import com.example.data.backend.*
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.data.datastore.tokenmanager.TokenManagerImpl
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.usernamemanager.UserNameManagerImpl
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.data.datastore.userphonemanager.UserPhoneManagerImpl
import com.example.data.datastore.verifycodemanager.VerifyCodeManager
import com.example.data.datastore.verifycodemanager.VerifyCodeManagerImpl
import com.example.domain.repository.AppRepository
import com.example.domain.utils.Endpoints
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

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    @Provides
    fun provideAuthAuthenticator(tokenManager: TokenManager): AuthAuthenticator {
        return AuthAuthenticator(tokenManager)
    }

    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManagerImpl): AuthInterceptor {
        return AuthInterceptor(tokenManager)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAppRepository(appRepository: AppRepositoryImpl): AppRepository

    @Binds
    fun bindTokenManager(tokenManager: TokenManagerImpl): TokenManager

    @Binds
    fun bindVerifyCodeManager(verifyCodeManager: VerifyCodeManagerImpl): VerifyCodeManager

    @Binds
    fun bindUserPhoneManager(userPhoneManager: UserPhoneManagerImpl): UserPhoneManager

    @Binds
    fun bindUserNameManager(userNameManager: UserNameManagerImpl): UserNameManager

}