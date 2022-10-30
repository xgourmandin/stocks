package org.xgo.fetcher.adapters.storage

import com.mongodb.client.MongoClient
import com.mongodb.client.model.Filters
import org.bson.Document
import org.xgo.fetcher.adapters.StockRepository
import org.xgo.fetcher.model.StockPrice
import java.time.Duration
import java.time.Instant
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MongoStockRepository(val client: MongoClient) : StockRepository {

    override fun search(symbol: String, timespan: Duration): List<StockPrice> {
        val minDate = Instant.now().minus(timespan)
        val documents = getCollection().find(
            Filters.and(
                listOf(
                    Filters.eq("symbol", symbol),
                    Filters.gte("timestamp", minDate)
                )
            )
        ).toList()
        return documents.map { StockPrice(
            it.getString("symbol"),
            it.getDate("timestamp").toInstant(),
            it.getDouble("open"),
            it.getDouble("high"),
            it.getDouble("low"),
            it.getDouble("close"),
            it.getLong("volume")
        ) }
    }

    override fun store(prices: List<StockPrice>) {
        val documents = prices.map {
            Document().append("symbol", it.symbol)
                .append("timestamp", it.timestamp)
                .append("open", it.open)
                .append("high", it.high)
                .append("low", it.low)
                .append("close", it.close)
                .append("volume", it.volume)
        }
        getCollection().insertMany(documents)
    }


    private fun getCollection() = client.getDatabase("stocks").getCollection("stocks")


}
