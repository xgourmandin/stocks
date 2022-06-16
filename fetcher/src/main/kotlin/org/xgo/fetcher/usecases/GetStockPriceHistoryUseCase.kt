package org.xgo.fetcher.usecases

import org.eclipse.microprofile.config.inject.ConfigProperty
import org.xgo.fetcher.adapters.StockClient
import org.xgo.fetcher.adapters.StockRepository
import org.xgo.fetcher.model.StockPrice
import org.xgo.fetcher.ports.GetStockPriceHistoryPort
import java.time.Duration
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetStockPriceHistoryUseCase(
    val stockClient: StockClient,
    val stockRepository: StockRepository,
    @ConfigProperty(name = "history.timespan.days") val timespan: Long
) :
    GetStockPriceHistoryPort {

    override fun getStockPricesHistory(symbol: String): List<StockPrice> {
        val timespanDuration = Duration.ofDays(timespan)
        val stockPrices = stockRepository.search(symbol, timespanDuration)
        return stockPrices.ifEmpty {
            val historicStocks = stockClient.getHistoricStocks(symbol, timespanDuration)
            stockRepository.store(historicStocks)
            historicStocks
        }
    }
}
