package stockexchange.stock

interface StockSubject {
    fun attach(observer: StockObserver)
    fun detach(observer: StockObserver)
}