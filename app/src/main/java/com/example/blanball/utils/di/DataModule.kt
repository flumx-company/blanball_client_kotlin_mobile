package com.example.blanball.utils.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.data.backend.ApiService
import com.example.data.backend.AuthInterceptor
import com.example.data.backend.LoginRepositoryImpl
import com.example.data.backend.TokenAuthenticator
import com.example.data.tokenmanager.TokenManager
import com.example.data.tokenmanager.TokenManagerImpl
import com.example.domain.repository.LoginRepository
import com.example.domain.utils.Const
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [DataStoreModule::class])
class DataModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

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
    fun provideTokenManager(dataStore: DataStore<Preferences> ): TokenManager {
        return TokenManagerImpl(dataStore)
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
                .baseUrl(Const.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManagerImpl): AuthInterceptor {
        return AuthInterceptor(tokenManager)
    }
}