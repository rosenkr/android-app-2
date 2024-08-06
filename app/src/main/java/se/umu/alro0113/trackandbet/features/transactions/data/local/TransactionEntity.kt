package se.umu.alro0113.trackandbet.features.transactions.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val tId: Int = 0, // auto-generated transaction id
    val asset: String, // tickers such as AAPL, or derivatives
    val date: String, // date of transaction completion
    val amount: Int, // amount of units in the transaction
    val price: Float, // price per unit
    val transactionType: String,  // buy or sell
    val isCompleted: Boolean // active on the market or sold/bought
)