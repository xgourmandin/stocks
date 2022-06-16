package org.xgo.fetcher.usecases

import org.xgo.fetcher.adapters.StockClient
import org.xgo.fetcher.ports.GetStockPriceHistoryPort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetStockPriceHistoryUseCase(val stockClient: StockClient): GetStockPriceHistoryPort {
}
