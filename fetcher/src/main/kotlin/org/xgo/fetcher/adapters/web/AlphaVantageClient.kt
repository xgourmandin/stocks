package org.xgo.fetcher.adapters.web

import com.fasterxml.jackson.annotation.JsonProperty
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.logging.Logger
import org.xgo.fetcher.adapters.StockClient
import org.xgo.fetcher.converters.toStockPrice
import org.xgo.fetcher.model.StockPrice
import java.time.Duration
import java.time.Instant
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.Response.Status


class DailyTimeSeries {
    @JsonProperty("Time Series (Daily)")
    lateinit var series: Map<String, StockSeries>
}

class StockSeries {
    @JsonProperty("1. open")
    var open: Double = 0.0
    @JsonProperty("2. high")
    var high: Double = 0.0
    @JsonProperty("3. low")
    var low: Double = 0.0
    @JsonProperty("4. close")
    var close: Double = 0.0
    @JsonProperty("5. volume")
    var volume: Long = 0
}

@ApplicationScoped
class AlphaVantageClient(@RestClient val api: AlphaVantageApi, @ConfigProperty(name = "apikey") val apiKey: String) :
    StockClient {

    private val LOG: Logger = Logger.getLogger(AlphaVantageClient::class.java)

    override fun getHistoricStocks(symbol: String, timespan: Duration): List<StockPrice> {
        val response = api.getHistoricData(symbol, OutputSize.FULL, apiKey)
        return if (response.status == Status.OK.statusCode) {
            val timeSeries = response.readEntity(DailyTimeSeries::class.java)
            timeSeries.toStockPrice(symbol).filter { it.timestamp >= Instant.now().minus(timespan) }
        }else {
            LOG.error("Error retrieving stocks for ${symbol}. Request returned status ${response.status}")
            listOf()
        }
    }

}
