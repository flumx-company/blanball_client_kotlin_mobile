package com.example.blanball.utils.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.data.backend.ApiService
import com.example.data.backend.AppRepositoryImpl
import com.example.data.backend.AuthInterceptor
import com.example.data.backend.TokenAuthenticator
import com.example.data.tokenmanager.TokenManager
import com.example.data.tokenmanager.TokenManagerImpl
import com.example.data.verifycodemanager.VerifyCodeManager
import com.example.data.verifycodemanager.VerifyCodeManagerImpl
import com.example.domain.repository.AppRepository
import com.example.domain.utils.Endpoints
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
        verifyCodeManager: VerifyCodeManager,
    ): AppRepository {
        return AppRepositoryImpl(service, tokenManager, verifyCodeManager)
    }

    @Provides
    fun provideVerifyCodeManager(dataStore: DataStore<Preferences>): VerifyCodeManager {
        return VerifyCodeManagerImpl(dataStore)
    }

    @Provides
    fun provideTokenManager(dataStore: DataStore<Preferences>): TokenManager {
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