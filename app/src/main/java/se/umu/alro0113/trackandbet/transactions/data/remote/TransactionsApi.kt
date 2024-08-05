package se.umu.alro0113.trackandbet.transactions.data.remote

import de.jensklingenberg.ktorfit.http.GET
import se.umu.alro0113.trackandbet.transactions.data.remote.dto.ResponseBody


interface TransactionsApi {
    @GET("transactions")
    suspend fun getResponseBody(): ResponseBody
}