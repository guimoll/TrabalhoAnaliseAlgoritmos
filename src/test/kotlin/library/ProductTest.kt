package library

import Product
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ProductTest {

    @Test
    fun testCreateProductWithValidData() {
        assertDoesNotThrow {
            Product(name = "Clean Code", weight = 300, price = BigDecimal("45.00"))
        }
    }

    @Test
    fun testThrowExceptionWhenWeightIsZero() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Product(name = "Clean Code", weight = 0, price = BigDecimal("45.00"))
        }
        assertEquals("Weight must be greater than 0", exception.message)
    }

    @Test
    fun testThrowExceptionWhenWeightIsNegative() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Product(name = "Clean Code", weight = -1, price = BigDecimal("45.00"))
        }
        assertEquals("Weight must be greater than 0", exception.message)
    }

        @Test
    fun testThrowExceptionWhenPriceIsNegative() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Product(name = "Clean Code", weight = 300, price = BigDecimal("-0.01"))
        }
        assertEquals("Price must be non-negative", exception.message)
    }

    @Test
    fun testCreateProductWithZeroPrice() {
        assertDoesNotThrow {
            Product(name = "Free Book", weight = 300, price = BigDecimal.ZERO)
        }
    }
}

