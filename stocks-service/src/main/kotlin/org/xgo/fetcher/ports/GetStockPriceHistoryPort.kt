package org.xgo.fetcher.ports

import org.xgo.fetcher.model.StockPrice

interface GetStockPriceHistoryPort {
    fun getStockPricesHistory(symbol: String): List<StockPrice>

}
