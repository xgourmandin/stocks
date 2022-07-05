package org.xgo.fetcher.adapters.rpc

import io.quarkus.grpc.GrpcService
import io.smallrye.mutiny.Uni
import org.xgo.fetcher.model.StockPrice
import org.xgo.fetcher.ports.GetStockPriceHistoryPort

@GrpcService
class HistoricStocksRpc(private val historyPort: GetStockPriceHistoryPort) : HistoricStocks {

    override fun initStock(request: HistoricStockRequest): Uni<HistoricStockResponse> {
        val stockPricesHistory = historyPort.getStockPricesHistory(request.symbol)
        return Uni.createFrom().item {
            HistoricStockResponse.newBuilder().setSymbol(request.symbol)
                .addAllPrices(stockPricesHistory.map { it.convertToProto() }).build()
        }
    }
}

fun StockPrice.convertToProto(): HistoricStockResponse.StockPrice =
    HistoricStockResponse.StockPrice.newBuilder().setTimestamp(timestamp.epochSecond)
        .setOpen(open).setHigh(high).setLow(low).setClose(close).setVolume(volume).build()

