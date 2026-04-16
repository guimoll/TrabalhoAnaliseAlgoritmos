package stockexchange.order

import stockexchange.investor.Investor
import java.math.BigDecimal

interface OrderRequest {
    val investor: Investor
    val type: OrderType
    val price: BigDecimal
}
