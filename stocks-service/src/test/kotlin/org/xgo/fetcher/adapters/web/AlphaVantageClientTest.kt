package org.xgo.fetcher.adapters.web

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.xgo.fetcher.adapters.StockClient
import java.time.Duration
import java.time.Instant
import javax.inject.Inject

@QuarkusTest
class AlphaVantageClientTest {

    @Inject
    lateinit var apiClient: StockClient

    @Test
    fun getHistoricStocks() {
        val oneYear = Duration.ofDays(365)
        val ibmStocks = apiClient.getHistoricStocks("IBM", oneYear)
        assertTrue(ibmStocks.all { it.timestamp >= Instant.now().minus(oneYear) })
    }
}
