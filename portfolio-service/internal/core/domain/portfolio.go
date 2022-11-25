package domain

import "portfolio-service/internal/utils"

type TradingPosition struct {
	Symbol     string  `json:"symbol"`
	BuyValue   float32 `json:"buyValue"`
	StockCount int     `json:"stockCount"`
	FeesValue  float32 `json:"feesValue"`
}

func (tp TradingPosition) OpeningValue() float32 {
	return float32(tp.StockCount)*tp.BuyValue - tp.FeesValue
}

type TradingProfile string

const (
	RISKY        TradingProfile = "risky"
	BALANCED                    = "balanced"
	CONSERVATIVE                = "conservative"
)

type Portfolio struct {
	Name           string            `json:"name"`
	User           string            `json:"user"`
	Symbols        []string          `json:"symbols"`
	Positions      []TradingPosition `json:"positions"`
	Profile        TradingProfile    `json:"profile"`
	AllocatedFunds float32           `json:"allocatedFunds"`
}

func (p Portfolio) StockVolume(symbol string) float32 {
	stockPositions := utils.Filter(p.Positions, func(p TradingPosition) bool { return p.Symbol == symbol })
	return utils.Reduce(stockPositions, func(p TradingPosition, acc float32) float32 { return p.OpeningValue() + acc }, 0.0)
}

// StockRatio Return a value between 0.0 and 1.0, giving the percentage of money allocated to the given symbol in the portfolio
func (p Portfolio) StockRatio(symbol string) float32 {
	return p.StockVolume(symbol) / p.AllocatedFunds
}

func (p Portfolio) OpenPosition(symbol string, value float32, amount int, fees float32) Portfolio {
	position := TradingPosition{
		Symbol:     symbol,
		BuyValue:   value,
		StockCount: amount,
		FeesValue:  fees,
	}
	p.Positions = append(p.Positions, position)
	return p
}
