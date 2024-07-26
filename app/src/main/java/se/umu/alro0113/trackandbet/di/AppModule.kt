package se.umu.alro0113.trackandbet.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import se.umu.alro0113.trackandbet.marketdata.data.remote.DetailsApi
import se.umu.alro0113.trackandbet.marketdata.data.remote.TickersApi
import se.umu.alro0113.trackandbet.marketdata.data.remote.createDetailsApi
import se.umu.alro0113.trackandbet.marketdata.data.remote.createTickersApi
import se.umu.alro0113.trackandbet.onboarding.data.datastore.DataStoreRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            defaultRequest { url("http://api.marketstack.com/v1/") }
            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
        }
    }

    @Provides
    @Singleton
    fun provideKtorfit(httpClient: HttpClient): Ktorfit {
        return Ktorfit.Builder()
            .httpClient(httpClient)
            .baseUrl("http://api.marketstack.com/v1/")
            .build()
    }

    @Provides
    @Singleton
    fun provideTickersApi(ktorfit: Ktorfit): TickersApi {
        return ktorfit.createTickersApi()
    }

    @Provides
    @Singleton
    fun provideDetailsApi(ktorfit: Ktorfit): DetailsApi {
        return ktorfit.createDetailsApi()
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)
}