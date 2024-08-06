package se.umu.alro0113.trackandbet.database

import androidx.room.Database
import androidx.room.RoomDatabase
import se.umu.alro0113.trackandbet.features.transactions.data.local.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val dao: AppDao
}