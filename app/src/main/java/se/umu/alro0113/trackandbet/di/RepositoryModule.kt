package se.umu.alro0113.trackandbet.di
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import se.umu.alro0113.trackandbet.marketdata.data.repository.DetailsRepositoryImpl
import se.umu.alro0113.trackandbet.marketdata.data.repository.TickersRepositoryImpl
import se.umu.alro0113.trackandbet.marketdata.domain.repository.DetailsRepository
import se.umu.alro0113.trackandbet.marketdata.domain.repository.TickersRepository
import javax.inject.Singleton

/*
Bind APIs to their implementations
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class TickersRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTickersRepository(impl: TickersRepositoryImpl): TickersRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailsRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDetailsRepository(impl: DetailsRepositoryImpl): DetailsRepository
}