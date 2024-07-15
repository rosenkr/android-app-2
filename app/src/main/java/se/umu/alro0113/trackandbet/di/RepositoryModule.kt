package se.umu.alro0113.trackandbet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import se.umu.alro0113.trackandbet.marketdata.data.repository.TickersRepositoryImpl
import se.umu.alro0113.trackandbet.marketdata.domain.repository.TickersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTickersRepository(impl: TickersRepositoryImpl): TickersRepository
}