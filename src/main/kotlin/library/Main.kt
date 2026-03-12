package library

import library.delivery.Pac
import library.delivery.PickUp
import library.delivery.Sedex

fun main() {

    val cleanCode = Product(
        name = "Clean Code",
        price = "89.90".toBigDecimal(),
        weight = 400
    )

    val refactoring = Product(
        name = "Refactoring",
        price = "120.00".toBigDecimal(),
        weight = 900
    )

    val domainDrivenDesign = Product(
        name = "Domain-Driven Design",
        price = "150.00".toBigDecimal(),
        weight = 850
    )

    println("===== TEST 1: ORDER WITH PAC =====")

    val order = Order(
        products = listOf(cleanCode, refactoring),
        deliveryType = Pac
    )

    println("Products price: ${order.getProductsPrice()}")
    println("Products weight: ${order.getTotalWeight()}g")
    println("PAC delivery price: ${order.getDeliveryPrice()}")
    println("Total price (products + delivery): ${order.getTotalPrice()}")
    println()

    println("===== TEST 2: CHANGE DELIVERY TYPE TO SEDEX =====")

    order.changeDeliveryType(Sedex)

    println("Products price: ${order.getProductsPrice()}")
    println("Products weight: ${order.getTotalWeight()}g")
    println("Sedex delivery price: ${order.getDeliveryPrice()}")
    println("Total price (products + delivery): ${order.getTotalPrice()}")
    println()

    println("===== TEST 3: ADD PRODUCT AND RECALCULATE =====")

    order.addProduct(domainDrivenDesign)

    println("Products price after adding product: ${order.getProductsPrice()}")
    println("Products weight after adding product: ${order.getTotalWeight()}g")
    println("Sedex delivery price after adding product: ${order.getDeliveryPrice()}")
    println("Total price (products + delivery): ${order.getTotalPrice()}")
    println()

    println("===== TEST 4: PAC EXCEPTION ABOVE 2KG =====")

    try {
        order.changeDeliveryType(Pac)
        println("PAC delivery price: ${order.getDeliveryPrice()}")
        println("Total price (products + delivery): ${order.getTotalPrice()}")
    } catch (exception: IllegalArgumentException) {
        println("Expected exception: ${exception.message}")
    }

    println()
    println("===== TEST 5: ORDER WITH PICKUP =====")

    val pickupOrder = Order(
        products = listOf(cleanCode),
        deliveryType = PickUp
    )

    println("Products price: ${pickupOrder.getProductsPrice()}")
    println("Products weight: ${pickupOrder.getTotalWeight()}g")
    println("Pickup delivery price: ${pickupOrder.getDeliveryPrice()}")
    println("Total price (products + delivery): ${pickupOrder.getTotalPrice()}")
}