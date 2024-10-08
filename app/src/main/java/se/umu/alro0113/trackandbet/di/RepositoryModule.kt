package se.umu.alro0113.trackandbet.di
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import se.umu.alro0113.trackandbet.features.marketdata.data.repository.DetailsRepositoryImpl
import se.umu.alro0113.trackandbet.features.marketdata.data.repository.TickersRepositoryImpl
import se.umu.alro0113.trackandbet.features.marketdata.domain.repository.DetailsRepository
import se.umu.alro0113.trackandbet.features.marketdata.domain.repository.TickersRepository
import se.umu.alro0113.trackandbet.features.transactions.data.repository.TransactionRepositoryImpl
import se.umu.alro0113.trackandbet.features.transactions.domain.repository.TransactionRepository
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

@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionsRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTransactionsRepository(impl: TransactionRepositoryImpl): TransactionRepository
}