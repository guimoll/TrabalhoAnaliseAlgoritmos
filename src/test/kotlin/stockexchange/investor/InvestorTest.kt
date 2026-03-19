package stockexchange.investor

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import stockexchange.notification.NotificationService
import stockexchange.stock.Stock
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotSame

class InvestorTest {

    @Test
    fun testCreateInvestorWithValidName() {
        val investor = Investor("Mariana")
        assertEquals("Mariana", investor.name)
    }

    @Test
    fun testCreateInvestorWithBlankNameThrows() {
        assertFailsWith<IllegalArgumentException> {
            Investor("")
        }
    }

    @Test
    fun testCreateInvestorWithWhitespaceNameThrows() {
        assertFailsWith<IllegalArgumentException> {
            Investor("   ")
        }
    }

    @Test
    fun testUpdateNotifiesWithCorrectStockInfo() {
        mockkObject(NotificationService)
        every { NotificationService.priceUpdated(any(), any(), any()) } returns Unit

        val investor = Investor("Mariana")
        val stock = mockk<Stock>()
        every { stock.symbol } returns "MCD"
        every { stock.currentPrice } returns BigDecimal("50.00")

        investor.update(stock)

        verify { NotificationService.priceUpdated("Mariana", "MCD", BigDecimal("50.00")) }
    }

    @Test
    fun testInvestorsWithSameNameAreNotEqual() {
        val a = Investor("Mariana")
        val b = Investor("Mariana")
        assertNotSame(a, b)
    }
}