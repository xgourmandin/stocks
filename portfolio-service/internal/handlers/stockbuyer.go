package handlers

type StockBuyer struct {
}

// BuyStock Buy the given amount of symbol.
// Due to trade platform latency and fees, it returns the real buy price, the number of stocks bought and the total fees applied during the trade
func (sb StockBuyer) BuyStock(symbol string, amount float32) (float32, int, float32) {
	return amount, 4, 2.5
}

// SellStock sells the amount of stocks designated by the given symbol.
// It returns the true price earnt by this sell (that may differ to the estimated price due to network latency)
func (sb StockBuyer) SellStock(symbol string, amount int) float32 {
	return float32(50.0 * amount)
}
