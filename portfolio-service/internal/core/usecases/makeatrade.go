package usecases

import (
	"portfolio-service/internal/core/domain"
	"portfolio-service/internal/handlers"
)

type MakeATradeUseCase struct {
	StockBuyerAdapter handlers.StockBuyer
}

func NewMakeATrade() *MakeATradeUseCase {
	return &MakeATradeUseCase{}
}

func (mat MakeATradeUseCase) BuyStock(portfolio domain.Portfolio, symbol string) domain.Portfolio {
	if canBuyStock(portfolio, symbol) {
		value, amount, fees := mat.StockBuyerAdapter.BuyStock(symbol, buyableAmount(symbol, portfolio))
		portfolio = portfolio.OpenPosition(symbol, value, amount, fees)
	}
	return portfolio
}

func canBuyStock(portfolio domain.Portfolio, symbol string) bool {
	return portfolio.StockRatio(symbol) < 0.33333333
}

func buyableAmount(symbol string, portfolio domain.Portfolio) float32 {
	return portfolio.AllocatedFunds*0.33333333 - portfolio.StockVolume(symbol)
}
