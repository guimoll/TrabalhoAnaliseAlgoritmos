package stockexchange.exchange

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import stockexchange.investor.Investor
import stockexchange.notification.NotificationService
import stockexchange.stock.Stock
import java.math.BigDecimal
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class StockMarketTest {

    private val stockMarket = StockMarket()

    @BeforeTest
    fun setup() {
        mockkObject(NotificationService)
        every { NotificationService.subscribed(any(), any(), any()) } returns Unit
        every { NotificationService.unsubscribed(any(), any()) } returns Unit
        every { NotificationService.matchExecuted(any(), any(), any(), any()) } returns Unit
        every { NotificationService.priceUpdated(any(), any(), any()) } returns Unit
        every { NotificationService.orderPlaced(any(), any()) } returns Unit
    }

    @Test
    fun testRegisterStockReturnsOrderBook() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val orderBook = stockMarket.registerStock(stock)
        assertNotNull(orderBook)
    }

    @Test
    fun testGetOrderBookReturnsCorrectOrderBook() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val orderBook = stockMarket.registerStock(stock)
        assertEquals(orderBook, stockMarket.getOrderBook("MCD"))
    }

    @Test
    fun testGetOrderBookThrowsForUnknownSymbol() {
        assertFailsWith<IllegalStateException> {
            stockMarket.getOrderBook("UNKNOWN")
        }
    }

    @Test
    fun testGetStockReturnsCorrectStock() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        stockMarket.registerStock(stock)
        assertEquals(stock, stockMarket.getStock("MCD"))
    }

    @Test
    fun testGetStockThrowsForUnknownSymbol() {
        assertFailsWith<IllegalStateException> {
            stockMarket.getStock("UNKNOWN")
        }
    }

    @Test
    fun testSubscribeToStockAttachesObserver() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        stockMarket.registerStock(stock)
        val investor = mockk<Investor>()
        every { investor.name } returns "Mariana"

        stockMarket.subscribeToStock("MCD", investor)

        verify { investor.name }
    }

    @Test
    fun testUnsubscribeFromStockDetachesObserver() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        stockMarket.registerStock(stock)
        val investor = mockk<Investor>()
        every { investor.name } returns "Mariana"

        stockMarket.subscribeToStock("MCD", investor)
        stockMarket.unsubscribeFromStock("MCD", investor)

        verify { NotificationService.unsubscribed("Mariana", "MCD") }
    }

    @Test
    fun testProcessOrdersExecutesMatchAndUpdatePrice() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val orderBook = stockMarket.registerStock(stock)
        val buyer = mockk<Investor>(relaxed = true)
        val seller = mockk<Investor>(relaxed = true)

        orderBook.placeBuyOrder(buyer, BigDecimal("50.00"))
        orderBook.placeSellOrder(seller, BigDecimal("50.00"))
        stockMarket.processOrders("MCD")

        assertEquals(BigDecimal("50.00"), stock.currentPrice)
    }

    @Test
    fun testProcessOrdersNotifiesMatchExecuted() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val orderBook = stockMarket.registerStock(stock)
        val buyer = mockk<Investor>(relaxed = true)
        val seller = mockk<Investor>(relaxed = true)

        orderBook.placeBuyOrder(buyer, BigDecimal("50.00"))
        orderBook.placeSellOrder(seller, BigDecimal("50.00"))
        stockMarket.processOrders("MCD")

        verify { NotificationService.matchExecuted(any(), any(), "MCD", BigDecimal("50.00")) }
    }

    @Test
    fun testProcessOrdersDoesNothingWhenNoMatch() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val orderBook = stockMarket.registerStock(stock)
        val buyer = mockk<Investor>(relaxed = true)
        val seller = mockk<Investor>(relaxed = true)

        orderBook.placeBuyOrder(buyer, BigDecimal("49.00"))
        orderBook.placeSellOrder(seller, BigDecimal("50.00"))
        stockMarket.processOrders("MCD")

        verify(exactly = 0) { NotificationService.matchExecuted(any(), any(), any(), any()) }
    }

    @Test
    fun testProcessOrdersRemovesBothOrdersAfterMatch() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val orderBook = stockMarket.registerStock(stock)
        val buyer = mockk<Investor>(relaxed = true)
        val seller = mockk<Investor>(relaxed = true)

        orderBook.placeBuyOrder(buyer, BigDecimal("50.00"))
        orderBook.placeSellOrder(seller, BigDecimal("50.00"))
        stockMarket.processOrders("MCD")

        assertEquals(0, stock.currentOrders.size)
    }

    @Test
    fun testProcessOrdersHandlesMultipleMatches() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val orderBook = stockMarket.registerStock(stock)
        val buyer = mockk<Investor>(relaxed = true)
        val seller = mockk<Investor>(relaxed = true)

        orderBook.placeBuyOrder(buyer, BigDecimal("50.00"))
        orderBook.placeBuyOrder(buyer, BigDecimal("50.00"))
        orderBook.placeSellOrder(seller, BigDecimal("50.00"))
        orderBook.placeSellOrder(seller, BigDecimal("50.00"))
        stockMarket.processOrders("MCD")

        verify(exactly = 2) { NotificationService.matchExecuted(any(), any(), "MCD", BigDecimal("50.00")) }
        assertEquals(0, stock.currentOrders.size)
    }
}