package usecases

import "portfolio-service/internal/core/domain"
import "golang.org/x/exp/slices"

type MakeATradeUseCase struct {
}

func NewMakeATrade() *MakeATradeUseCase {
	return &MakeATradeUseCase{}
}

func (mat MakeATradeUseCase) BuyStock(portfolio domain.Portfolio, symbol string) {
	if canBuyStock(portfolio, symbol) {

	}
}

func canBuyStock(portfolio domain.Portfolio, symbol string) bool {
	return slices.Contains(portfolio.Symbols, symbol)
}
