package com.example.blanball.utils.di

import android.content.Context
import com.example.data.backend.ApiService
import com.example.data.backend.AuthInterceptor
import com.example.data.backend.LoginRepositoryImpl
import com.example.data.backend.TokenAuthenticator
import com.example.data.datastore.TokenManager
import com.example.data.datastore.TokenManagerImpl
import com.example.domain.BuildConfig
import com.example.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class DataModule(val context: Context) {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideLoginRepository(
        service: ApiService,
        tokenManager: TokenManagerImpl,
    ): LoginRepository {
        return LoginRepositoryImpl(service, tokenManager)
    }

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideTokenManager(): TokenManager {
        return TokenManagerImpl(context)
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
                .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManagerImpl): AuthInterceptor {
        return AuthInterceptor(tokenManager)
    }
}