package org.xgo.fetcher.adapters

import org.xgo.fetcher.model.StockPrice
import java.time.Duration

interface StockClient {
    fun getHistoricStocks(symbol: String, timespan: Duration): List<StockPrice>

}
