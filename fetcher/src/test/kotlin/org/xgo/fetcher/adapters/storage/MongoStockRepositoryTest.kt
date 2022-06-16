package org.xgo.fetcher.adapters.storage

import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.mongodb.MongoTestResource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS
import org.xgo.fetcher.adapters.StockRepository
import org.xgo.fetcher.model.StockPrice
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject
import kotlin.random.Random

@QuarkusTest
@QuarkusTestResource(MongoTestResource::class)
@DisabledOnOs(OS.WINDOWS)
class MongoStockRepositoryTest {

    @Inject
    lateinit var repository: StockRepository

    @Test
    fun testMongoStocks() {
        val prices = getStockPrices("IBM")
        assertEquals(50, prices.size)
        repository.store(prices)
        val stockPrices = repository.search("IBM", Duration.ofDays(25))
        assertEquals(25, stockPrices.size)
    }

    private fun getStockPrices(symbol: String): List<StockPrice> {
        var timestamp = Instant.from(LocalDate.now().atStartOfDay(ZoneId.of("UTC")))
        val prices = (1..50).map {
            val price = StockPrice(
                symbol,
                timestamp,
                Random.nextDouble(5.0, 10.0),
                Random.nextDouble(15.0, 20.0),
                Random.nextDouble(5.0, 10.0),
                Random.nextDouble(15.0, 20.0),
                Random.nextLong(100, 10000)
            )
            timestamp = timestamp.minus(Duration.ofDays(1))
            return@map price
        }
        return prices
    }

}
