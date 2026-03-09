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

    println("===== TEST 1: AMAZON ORDER WITH PAC =====")

    val amazonOrder = Order(
        products = listOf(cleanCode, refactoring),
        deliveryType = Pac
    )

    println("Total price: ${amazonOrder.calculateTotalPrice()}")
    println("Total weight: ${amazonOrder.calculateProductsPrice()}g")
    println("PAC delivery price: ${amazonOrder.calculateDeliveryPrice()}")
    println()

    println("===== TEST 2: UPGRADE TO SEDEX =====")

    amazonOrder.changeDeliveryType(Sedex)

    println("Total price: ${amazonOrder.calculateTotalPrice()}")
    println("Total weight: ${amazonOrder.calculateProductsPrice()}g")
    println("Sedex delivery price: ${amazonOrder.calculateDeliveryPrice()}")
    println()

    println("===== TEST 3: ADD PRODUCT AND RECALCULATE (SEDEX) =====")

    amazonOrder.addProduct(domainDrivenDesign)
    amazonOrder.changeDeliveryType(Sedex)

    println("Total price after adding product: ${amazonOrder.calculateTotalPrice()}")
    println("Total weight after adding product: ${amazonOrder.calculateProductsPrice()}g")
    println("Sedex delivery price after adding product: ${amazonOrder.calculateDeliveryPrice()}")
    println()

    println("===== TEST 4: PAC EXCEPTION ABOVE 2KG =====")

    try {
        amazonOrder.changeDeliveryType(Pac)
        println("PAC delivery price: ${amazonOrder.calculateDeliveryPrice()}")
    } catch (exception: IllegalArgumentException) {
        println("Expected exception: ${exception.message}")
    }
    println()

    println("===== TEST 5: IN-STORE PICKUP - NO SHIPPING COST =====")

    val storePickupOrder = Order(
        products = listOf(cleanCode),
        deliveryType = PickUp
    )

    println("Total price: ${storePickupOrder.calculateTotalPrice()}")
    println("Total weight: ${storePickupOrder.calculateProductsPrice()}g")
    println("Pickup delivery price: ${storePickupOrder.calculateDeliveryPrice()}")
}