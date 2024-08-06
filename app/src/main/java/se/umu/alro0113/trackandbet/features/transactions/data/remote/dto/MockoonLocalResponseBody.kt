package se.umu.alro0113.trackandbet.features.transactions.data.remote.dto

import kotlinx.serialization.Serializable
import se.umu.alro0113.trackandbet.features.transactions.domain.model.Transaction

@Serializable
data class ResponseBody(
    val transactions: List<Transaction>
)
