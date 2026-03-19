package stockexchange.stock

interface StockObserver {
    fun update(stock: Stock)
}