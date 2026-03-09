package library

import library.delivery.DeliveryType
import java.math.BigDecimal

class Order(
    products: List<Product> = emptyList(),
    private var deliveryType: DeliveryType
) {
    private val products: MutableList<Product> = products.toMutableList()

    fun changeDeliveryType(deliveryType: DeliveryType) {
        this.deliveryType = deliveryType
    }

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun calculateTotalWeight(): Int {
        return products.fold(0) { total, product ->
            total + product.weight
        }
    }

    fun calculateProductsPrice(): BigDecimal {
        return products.fold(BigDecimal.ZERO) { total, product ->
            total + product.price
        }
    }

    fun getDeliveryPrice(): BigDecimal {
        return deliveryType.calculateDeliveryPrice(calculateTotalWeight())
    }
}