package domain

type TradingPosition struct {
	Symbol     string  `json:"symbol"`
	BuyValue   float32 `json:"buyValue"`
	StockCount float32 `json:"stockCount"`
	FeesValue  float32 `json:"feesValue"`
}

func (tp TradingPosition) OpeningValue() float32 {
	return tp.StockCount*tp.BuyValue - tp.FeesValue
}

type TradingProfile string

const (
	RISKY        TradingProfile = "risky"
	BALANCED                    = "balanced"
	CONSERVATIVE                = "conservative"
)

type Portfolio struct {
	Name      string            `json:"name"`
	User      string            `json:"user"`
	Symbols   []string          `json:"symbols"`
	Positions []TradingPosition `json:"positions"`
	Profile   TradingProfile    `json:"profile"`
}
