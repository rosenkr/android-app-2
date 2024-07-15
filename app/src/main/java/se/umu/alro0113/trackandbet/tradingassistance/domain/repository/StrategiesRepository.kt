package se.umu.alro0113.trackandbet.tradingassistance.domain.repository

/* we create an interface so we depend on abstractions not concretions
if for example I change from Ktor to Retrofit or vice versa, then other classes that depend on this interface
will NOT need to be changed
 */
interface StrategiesRepository {
}