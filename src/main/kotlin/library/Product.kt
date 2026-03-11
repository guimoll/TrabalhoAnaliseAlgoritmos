package library

import java.math.BigDecimal

data class Product (
    val name: String,
    val weight: Int,
    val price: BigDecimal
) {
    init {
        require(weight > 0) { "Weight must be greater than 0" }
        require(price >= BigDecimal.ZERO) { "Price must be non-negative" }
    }
}
