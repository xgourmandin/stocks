package ports

import "portfolio-service/internal/core/domain"

type MakeATradePort interface {
	BuyStock(portfolio domain.Portfolio, symbol string)
}
