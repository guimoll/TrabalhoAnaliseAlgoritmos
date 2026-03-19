package stockexchange.exchange

import stockexchange.notification.NotificationService
import stockexchange.order.OrderBook
import stockexchange.stock.Stock
import stockexchange.stock.StockObserver

class StockMarket {
    private val stocks = mutableMapOf<String, Stock>()
    private val orderBooks = mutableMapOf<String, OrderBook>()

    fun registerStock(stock: Stock): OrderBook {
        require(!stocks.containsKey(stock.symbol)) {
            "A stock with symbol '${stock.symbol}' is already registered"
        }
        stocks[stock.symbol] = stock
        val orderBook = OrderBook(stock)
        orderBooks[stock.symbol] = orderBook
        return orderBook
    }

    fun getOrderBook(symbol: String): OrderBook {
        return orderBooks[symbol] ?: error("No order book found for symbol $symbol")
    }

    fun getStock(symbol: String): Stock {
        return stocks[symbol] ?: error("No stock found for symbol $symbol")
    }

    fun subscribeToStock(symbol: String, observer: StockObserver) {
        getStock(symbol).attach(observer)
    }

    fun unsubscribeFromStock(symbol: String, observer: StockObserver) {
        getStock(symbol).detach(observer)
    }

    fun processOrders(symbol: String) {
        val orderBook = getOrderBook(symbol)
        var match = TradeMatcher.findMatch(orderBook)
        while (match != null) {
            val (buyOrder, sellOrder) = match
            orderBook.removeOrder(buyOrder)
            orderBook.removeOrder(sellOrder)
            val tradedPrice = sellOrder.price
            getStock(symbol).updatePrice(tradedPrice)
            NotificationService.matchExecuted(buyOrder, sellOrder, symbol, tradedPrice)
            match = TradeMatcher.findMatch(orderBook)
        }
    }
}