package se.umu.alro0113.trackandbet.features.onboarding.domain

import kotlinx.serialization.Serializable

// TODO move this elsewhere later
@Serializable
data class TickerData(
    val c : Float, // price
    val d : Float, // change
    val dp : Float, // percent change
    val h : Float, // high
    val l : Float, // low
    val o : Float, // open
    val pc : Float, // previous close
    val t : Long // time in ms
)