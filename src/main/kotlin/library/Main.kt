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

    println("Products price: ${amazonOrder.productsPrice}")
    println("Products weight: ${amazonOrder.totalWeight}g")
    println("PAC delivery price: ${amazonOrder.deliveryPrice}")
    println()

    println("===== TEST 2: UPGRADE TO SEDEX =====")

    amazonOrder.changeDeliveryType(Sedex)

    println("Products price: ${amazonOrder.productsPrice}")
    println("Products weight: ${amazonOrder.totalWeight}g")
    println("Sedex delivery price: ${amazonOrder.deliveryPrice}")
    println()

    println("===== TEST 3: ADD PRODUCT AND RECALCULATE (SEDEX) =====")

    amazonOrder.addProduct(domainDrivenDesign)
    amazonOrder.changeDeliveryType(Sedex)

    println("Products price after adding product: ${amazonOrder.productsPrice}")
    println("Products weight after adding product: ${amazonOrder.totalWeight}g")
    println("Sedex delivery price after adding product: ${amazonOrder.deliveryPrice}")
    println()

    println("===== TEST 4: PAC EXCEPTION ABOVE 2KG =====")

    try {
        amazonOrder.changeDeliveryType(Pac)
        println("PAC delivery price: ${amazonOrder.deliveryPrice}")
    } catch (exception: IllegalArgumentException) {
        println("Expected exception: ${exception.message}")
    }
    println()

    println("===== TEST 5: IN-STORE PICKUP - NO SHIPPING COST =====")

    val storePickupOrder = Order(
        products = listOf(cleanCode),
        deliveryType = PickUp
    )

    println("Products price: ${storePickupOrder.productsPrice}")
    println("Products weight: ${storePickupOrder.totalWeight}g")
    println("Pickup delivery price: ${storePickupOrder.deliveryPrice}")
}