package org.xgo.fetcher.adapters

import org.xgo.fetcher.model.StockPrice
import java.time.Duration

interface StockRepository {
    fun search(symbol: String, timespan: Duration): List<StockPrice>
    fun store(prices: List<StockPrice>)

}
