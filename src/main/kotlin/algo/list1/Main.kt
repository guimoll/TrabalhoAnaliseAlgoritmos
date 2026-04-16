package algo.list1

import java.util.LinkedList
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.measureTime

private const val RUNS_20 = 20
private const val RUNS_10 = 10

fun main() {
    questao1()
}

fun questao1() {
    println("=== Questão 1: Inserção ao final ===\n")

    val arrayList = ArrayList<Int>()
    val linkedList = LinkedList<Int>()

    val arrayListAvg = measureAverage(RUNS_20) { arrayList.add(Random.nextInt()) }
    val linkedListAvg = measureAverage(RUNS_20) { linkedList.add(Random.nextInt()) }

    val ratio =
        maxOf(arrayListAvg, linkedListAvg).inWholeNanoseconds.toDouble() /
            minOf(arrayListAvg, linkedListAvg).inWholeNanoseconds
    val faster = if (arrayListAvg < linkedListAvg) "ArrayList" else "LinkedList"

    println("Parte 1: $RUNS_20 execuções, 1 inserção por execução")
    println("  ArrayList  : $arrayListAvg")
    println("  LinkedList : $linkedListAvg")
    println("  Mais rápida: $faster (${"%.2f".format(ratio)}x)\n")

    println("Parte 2: Capacidade inicial do ArrayList ($RUNS_10 execuções, 1 inserção por execução)")
    listOf(10, 1_000, 100_000).forEach { capacity ->
        val list = ArrayList<Int>(capacity).also { l -> repeat(capacity) { l.add(Random.nextInt()) } }
        val avg = measureAverage(RUNS_10) { list.add(Random.nextInt()) }
        println("  initialCapacity = ${"%-7d".format(capacity)}: $avg (tamanho final: ${list.size})")
    }
}

private fun measureAverage(
    runs: Int,
    block: () -> Unit,
): Duration {
    var total = Duration.ZERO
    repeat(runs) { total += measureTime(block) }
    return total / runs
}
