package se.umu.alro0113.trackandbet.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import se.umu.alro0113.trackandbet.features.transactions.data.local.TransactionEntity

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(
        transactionEntities: List<TransactionEntity>
    )

    @Query("DELETE FROM transactions")
    suspend fun clearTransactions()

    @Query(
        """
            SELECT *
            FROM transactions
            WHERE LOWER(asset) LIKE '%' || LOWER(:query) || '%'
            """
    )

    suspend fun searchTransaction(query: String?): List<TransactionEntity>
}