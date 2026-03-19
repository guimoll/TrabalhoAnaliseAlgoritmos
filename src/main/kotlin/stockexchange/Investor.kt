package stockexchange

import java.math.BigDecimal

data class Investor(
    val name: String
) {
    init {
        require(name.isNotBlank()) { "Name must not be blank" }
    }

    fun notifyPriceChange(stockSymbol: String, newPrice: BigDecimal) =
        println("$name was notified: $stockSymbol changed to $newPrice")
}