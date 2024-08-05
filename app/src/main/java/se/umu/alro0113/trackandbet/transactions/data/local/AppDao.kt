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

    // TODO make sure this is ok
    // Search for a specific transaction from TransactionEntity table, or if no query is provided, find all transactions
    //@Query("SELECT * FROM transactions WHERE (:query IS NULL OR asset = :query)")
    // TODO go to video see his search call. change my int related stuff to String everywhere, actually
    // TODO even remove nullability, set to String not String?
    // TODO add , allow to also look for transactiontype, isCompleted?
    // add as a dropdown menu with options "All, Buy/Sell toggle side by side highlighted, and if completed o not"
    // TODO so would be a new query
}