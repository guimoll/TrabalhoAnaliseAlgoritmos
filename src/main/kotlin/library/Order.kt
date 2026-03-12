package library
import library.delivery.DeliveryType
import java.math.BigDecimal

class Order(
    products: List<Product> = emptyList(),
    private var deliveryType: DeliveryType
) {
    private val products: MutableList<Product> = products.toMutableList()

    fun getTotalWeight(): Int {
        return products.sumOf { it.weight }
    }

    fun getProductsPrice(): BigDecimal {
        return products.fold(BigDecimal.ZERO) { acc, product ->
            acc + product.price
        }
    }

    fun getDeliveryPrice(): BigDecimal {
        return deliveryType.calculateDeliveryPrice(getTotalWeight())
    }

    fun changeDeliveryType(deliveryType: DeliveryType) {
        this.deliveryType = deliveryType
    }

    fun addProduct(product: Product) {
        products.add(product)
    }
}