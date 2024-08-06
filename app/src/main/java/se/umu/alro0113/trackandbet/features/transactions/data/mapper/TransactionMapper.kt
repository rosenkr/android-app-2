package se.umu.alro0113.trackandbet.features.transactions.data.mapper

import se.umu.alro0113.trackandbet.features.transactions.data.local.TransactionEntity
import se.umu.alro0113.trackandbet.features.transactions.domain.model.Transaction

// Map TransactionEntity to Transaction
fun TransactionEntity.toTransaction(): Transaction {
    return Transaction(
        asset = asset,
        date = date,
        amount = amount,
        price = price,
        transactionType = transactionType,
        isCompleted = isCompleted
    )
}

// Map Transaction to TransactionEntity
fun Transaction.toTransaction(): TransactionEntity {
    return TransactionEntity(
        asset = asset,
        date = date,
        amount = amount,
        price = price,
        transactionType = transactionType,
        isCompleted = isCompleted
    )
}