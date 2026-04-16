package algo.list1

import kotlin.random.Random
import kotlin.time.measureTime

fun main() {
    questao1()
}

private fun questao1() {
    val arrList = arrayListOf<Int>()

    val arrayListList10 = createArrayList(10)
    val arrayList1000 = createArrayList(1000)
    val arrayList100000 = createArrayList(100000)

    val elapsed =
        measureTime {
            repeat(1000) {
                insertIntoArrayList(arrList, 10)
            }
        }
}

private fun createArrayList(amount: Int): ArrayList<Int> {
    val list = arrayListOf<Int>()
    repeat(amount) {
        list.add(Random.nextInt(0, 99))
    }
    return list
}

private fun insertIntoArrayList(
    arrayList: ArrayList<Int>,
    amount: Int,
) {
    repeat(amount) {
        arrayList.addLast(Random.nextInt(0, 99))
    }
}
