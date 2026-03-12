
import library.delivery.DeliveryType
import java.math.BigDecimal

class Order(
    products: List<Product> = emptyList(),
    private var deliveryType: DeliveryType
) {
    private val products: MutableList<Product> = products.toMutableList()

    val totalWeight: Int
        get() = products.sumOf { it.weight }

    val productsPrice: BigDecimal
        get() = products.fold(BigDecimal.ZERO) { acc, product -> acc + product.price }

    val deliveryPrice: BigDecimal
        get() = deliveryType.calculateDeliveryPrice(totalWeight)

    fun changeDeliveryType(deliveryType: DeliveryType) {
        this.deliveryType = deliveryType
    }

    fun addProduct(product: Product) {
        products.add(product)
    }
}