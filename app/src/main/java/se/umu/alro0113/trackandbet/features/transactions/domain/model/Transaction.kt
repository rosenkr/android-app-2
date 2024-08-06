package se.umu.alro0113.trackandbet.features.transactions.domain.model

import kotlinx.serialization.Serializable

// Data class placed in domain layer to be accessed by Viewmodels, to decouple data logic in the data layer from the domain layer
@Serializable // must be serializable for dto list of transactions that is serilizable
data class Transaction(
    val asset: String, // tickers such as AAPL, or derivatives
    val date: String, // date of transaction completion
    val amount: Int, // amount of units in the transaction
    val price: Float, // price per unit
    val transactionType: String,  // buy or sell
    val isCompleted: Boolean // active on the market or sold/bought
)