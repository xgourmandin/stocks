package org.xgo.fetcher.model

import java.time.Instant

data class StockPrice(val symbol: String, val timestamp: Instant, val open: Double, val high: Double, val low: Double, val close: Double, val volume: Long) {

}
