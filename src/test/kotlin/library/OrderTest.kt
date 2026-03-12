package library

import Order
import Product
import io.mockk.every
import io.mockk.mockk
import library.delivery.DeliveryType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class OrderTest {

    private val lightProduct = mockk<Product>()
    private val heavyProduct = mockk<Product>()

    private val freeDelivery = mockk<DeliveryType>()
    private val paidDelivery = mockk<DeliveryType>()

    @BeforeEach
    fun setUp() {
        every { lightProduct.weight } returns 10
        every { lightProduct.price } returns BigDecimal("45.00")

        every { heavyProduct.weight } returns 100
        every { heavyProduct.price } returns BigDecimal("80.00")

        every { paidDelivery.calculateDeliveryPrice(any()) } returns BigDecimal("10.00")
    }

    @Test
    fun testTotalWeightWithMultipleProducts() {
        val order = Order(products = listOf(lightProduct, heavyProduct), deliveryType = freeDelivery)
        assertEquals(110, order.totalWeight)
    }

    @Test
    fun testProductsPriceWithMultipleProducts() {
        val order = Order(products = listOf(lightProduct, heavyProduct), deliveryType = freeDelivery)
        assertEquals(BigDecimal("125.00"), order.productsPrice)
    }

    @Test
    fun testAddProductUpdatesTotalWeight() {
        val order = Order(products = listOf(lightProduct), deliveryType = freeDelivery)
        order.addProduct(heavyProduct)
        assertEquals(110, order.totalWeight)
    }

    @Test
    fun testAddProductUpdatesProductsPrice() {
        val order = Order(products = listOf(lightProduct), deliveryType = freeDelivery)
        order.addProduct(heavyProduct)
        assertEquals(BigDecimal("125.00"), order.productsPrice)
    }

    @Test
    fun testDeliveryPriceWithPaidDelivery() {
        val order = Order(products = listOf(lightProduct), deliveryType = paidDelivery)
        assertEquals(BigDecimal("10.00"), order.deliveryPrice)
    }
}
