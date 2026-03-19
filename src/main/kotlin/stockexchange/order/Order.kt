package stockexchange.order

import stockexchange.investor.Investor
import java.math.BigDecimal

data class Order(
    val investor: Investor,
    val type: OrderType,
    val price: BigDecimal
)