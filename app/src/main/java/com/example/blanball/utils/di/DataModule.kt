package com.example.blanball.utils.di

import com.example.data.backend.*
import com.example.data.tokenmanager.TokenManager
import com.example.data.tokenmanager.TokenManagerImpl
import com.example.data.usernamemanager.UserNameManager
import com.example.data.usernamemanager.UserNameManagerImpl
import com.example.data.userphonemanager.UserPhoneManager
import com.example.data.userphonemanager.UserPhoneManagerImpl
import com.example.data.verifycodemanager.VerifyCodeManager
import com.example.data.verifycodemanager.VerifyCodeManagerImpl
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

@Module(includes = [DataStoreModule::class])
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideOkHttpClient(tokenManager: TokenManager, authenticator: TokenAuthenticator? = null): OkHttpClient {
        return OkHttpClient.Builder()
            .also { client ->
                authenticator?.let { client.authenticator(TokenAuthenticator(tokenManager)) }
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build()
    }

    @Provides
    fun provideTokenAuthenticator(tokenManager: TokenManager): TokenAuthenticator{
        return TokenAuthenticator(tokenManager)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManagerImpl): AuthInterceptor {
        return AuthInterceptor(tokenManager)
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