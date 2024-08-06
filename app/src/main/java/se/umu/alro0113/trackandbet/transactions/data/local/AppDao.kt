package se.umu.alro0113.trackandbet.transactions.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// TODO break out of specific transactions feature hierarchy, this is a general interface
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