package se.umu.alro0113.trackandbet.transactions.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
// TODO break out of specific transactions feature hierarchy, this is a general database

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val dao: AppDao
}