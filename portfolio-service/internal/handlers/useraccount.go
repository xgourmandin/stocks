package handlers

type UserAccount struct {
	balance float32
}

func (ua UserAccount) BuyStock(symbol string, amount float32) {
	ua.balance -= amount
}

func (ua UserAccount) SellStock(symbol string, amount float32) {
	ua.balance += amount
}

func (ua UserAccount) Balance() float32 {
	return ua.balance
}
