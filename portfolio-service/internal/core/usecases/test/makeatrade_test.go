package test

import (
	"portfolio-service/internal/core/domain"
	"portfolio-service/internal/core/usecases"
	"testing"
)

func TestBuyStock(t *testing.T) {
	usecase := usecases.MakeATradeUseCase{
		StockBuyerAdapter: struct{}{},
	}

	p := domain.Portfolio{
		Name:           "test-portfolio",
		User:           "uidv4",
		Symbols:        []string{},
		Positions:      []domain.TradingPosition{},
		Profile:        domain.BALANCED,
		AllocatedFunds: 3000,
	}

	p = usecase.BuyStock(p, "GOOG")

	if len(p.Positions) != 1 {
		t.Errorf("Portfolio openned positions has not been updated after stock buy operation")
	}
	if p.Positions[0].BuyValue != 1000.0 {
		t.Errorf("Portfolio openned positions buy value should have been %.2f. %.2f found.", 1000.0, p.Positions[0].BuyValue)
	}
}

func TestDoNotBuyStockIfCapIsReached(t *testing.T) {
	usecase := usecases.MakeATradeUseCase{
		StockBuyerAdapter: struct{}{},
	}

	p := domain.Portfolio{
		Name:           "test-portfolio",
		User:           "uidv4",
		Symbols:        []string{},
		Positions:      []domain.TradingPosition{},
		Profile:        domain.BALANCED,
		AllocatedFunds: 3000,
	}

	p = usecase.BuyStock(p, "GOOG")
	p = usecase.BuyStock(p, "GOOG")

	if len(p.Positions) != 1 {
		t.Errorf("Portfolio has an incorrect number of openned position. %d expected, %d found", 1, len(p.Positions))
	}
}
