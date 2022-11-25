package domain

type StockProfile struct {
	Symbol  string         `json:"symbol"`
	Profile TradingProfile `json:"profile"`
}
