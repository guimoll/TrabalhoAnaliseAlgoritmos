package stockexchange

import java.math.BigDecimal

class Stock(
    val name: String,
    currentPrice: BigDecimal
) {
    init {
        require(name.isNotBlank()) { "Name must not be blank" }
        require(currentPrice > BigDecimal.ZERO) { "Current price must be greater than 0" }
    }

    var currentPrice: BigDecimal = currentPrice
        private set

    fun updatePrice(newPrice: BigDecimal) {
        require(newPrice > BigDecimal.ZERO) { "New price must be greater than 0" }
        currentPrice = newPrice
    }
}