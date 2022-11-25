package test

import (
	"portfolio-service/internal/core/domain"
	"testing"
)

func TestTradingPositionOpeningValue(t *testing.T) {
	p := domain.TradingPosition{
		Symbol:     "GOOG",
		BuyValue:   100,
		StockCount: 5,
		FeesValue:  5,
	}
	var openingValue = p.OpeningValue()
	if openingValue != 495.0 {
		t.Errorf("Opening value for trading position should have been %d, %f found.", 495, openingValue)
	}
}

func TestPortfolioStockVolume(t *testing.T) {
	p := domain.Portfolio{
		Name:    "Portfolio",
		User:    "uid",
		Symbols: []string{},
		Positions: []domain.TradingPosition{
			{
				Symbol:     "GOOG",
				BuyValue:   100,
				StockCount: 2,
				FeesValue:  2.5,
			},
			{
				Symbol:     "GOOG",
				BuyValue:   100,
				StockCount: 3,
				FeesValue:  2.5,
			},
			{
				Symbol:     "FBK",
				BuyValue:   20,
				StockCount: 10,
				FeesValue:  5,
			},
		},
		Profile:        domain.BALANCED,
		AllocatedFunds: 5000.0,
	}
	stockVolume := p.StockVolume("GOOG")
	if stockVolume != 495.0 {
		t.Errorf("Stock volume for portfolio should have been %.2f, %.2f found", 495.0, stockVolume)
	}
}

func TestPortfolioStockRatio(t *testing.T) {
	p := domain.Portfolio{
		Name:    "Portfolio",
		User:    "uid",
		Symbols: []string{},
		Positions: []domain.TradingPosition{
			{
				Symbol:     "GOOG",
				BuyValue:   100,
				StockCount: 2,
				FeesValue:  2.5,
			},
			{
				Symbol:     "GOOG",
				BuyValue:   100,
				StockCount: 3,
				FeesValue:  2.5,
			},
			{
				Symbol:     "FBK",
				BuyValue:   20,
				StockCount: 10,
				FeesValue:  5,
			},
		},
		Profile:        domain.BALANCED,
		AllocatedFunds: 990.0,
	}
	stockRatio := p.StockRatio("GOOG")
	if stockRatio != 0.5 {
		t.Errorf("Stock ratio for portfolio should have been %.2f, %.2f found", 0.5, stockRatio)
	}
}
