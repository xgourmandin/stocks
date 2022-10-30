package org.xgo.fetcher.converters

import org.xgo.fetcher.adapters.web.DailyTimeSeries
import org.xgo.fetcher.model.StockPrice
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun DailyTimeSeries.toStockPrice(symbol: String): List<StockPrice> {
    return series.map { (t, s) ->
        StockPrice(
            symbol,
            Instant.from(LocalDate.from(DateTimeFormatter.ISO_DATE.parse(t)).atStartOfDay(ZoneId.of("UTC"))),
            s.open,
            s.high,
            s.low,
            s.close,
            s.volume
        )
    }
}
