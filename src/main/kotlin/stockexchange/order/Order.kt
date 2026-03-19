package stockexchange.order

import stockexchange.investor.Investor
import java.math.BigDecimal

data class Order(
    override val investor: Investor,
    override val type: OrderType,
    override val price: BigDecimal
) : OrderRequest
 