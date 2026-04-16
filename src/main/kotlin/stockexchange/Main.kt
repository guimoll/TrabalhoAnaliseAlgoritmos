package stockexchange

import stockexchange.exchange.StockMarket
import stockexchange.investor.Investor
import stockexchange.order.ConditionalOrder
import stockexchange.order.OrderType
import stockexchange.stock.Stock
import stockexchange.trigger.PriceTriggerType
import java.math.BigDecimal

fun main() {
    val stockMarket = StockMarket()

    val mcdonalds = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
    val burgerKing = Stock(symbol = "BK", initialPrice = BigDecimal("30.00"))
    val kfc = Stock(symbol = "KFC", initialPrice = BigDecimal("20.00"))

    val mcdBook = stockMarket.registerStock(mcdonalds)
    val bkBook = stockMarket.registerStock(burgerKing)
    val kfcBook = stockMarket.registerStock(kfc)
    val mariana = Investor("Mariana")
    val joaquim = Investor("Joaquim")
    stockMarket.subscribeToStock("MCD", mariana)
    stockMarket.subscribeToStock("BK", mariana)
    stockMarket.subscribeToStock("KFC", joaquim)

    println("\n--- Placing Orders ---\n")

    mcdBook.placeSellOrder(mariana, BigDecimal("50.00"))
    mcdBook.placeBuyOrder(joaquim, BigDecimal("50.00"))
    stockMarket.processOrders("MCD")

    println("\n--- Burger King Orders ---\n")

    bkBook.placeSellOrder(joaquim, BigDecimal("31.00"))
    bkBook.placeBuyOrder(mariana, BigDecimal("30.00"))
    stockMarket.processOrders("BK")

    println("\n--- KFC Conditional Order ---\n")

    kfcBook.scheduleConditionalOrder(
        ConditionalOrder(
            investor = joaquim,
            type = OrderType.BUY,
            price = BigDecimal("18.00"),
            triggerPrice = BigDecimal("18.00"),
            triggerType = PriceTriggerType.LESS_OR_EQUAL
        )
    )

    kfcBook.placeSellOrder(mariana, BigDecimal("19.00"))
    kfcBook.placeBuyOrder(joaquim, BigDecimal("19.00"))
    stockMarket.processOrders("KFC")

    kfcBook.placeSellOrder(mariana, BigDecimal("18.00"))
    kfcBook.placeBuyOrder(joaquim, BigDecimal("18.00"))
    stockMarket.processOrders("KFC")
    stockMarket.processOrders("KFC")
}