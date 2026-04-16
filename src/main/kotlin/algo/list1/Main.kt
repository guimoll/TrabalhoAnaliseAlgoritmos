package algo.list1

import java.util.LinkedList
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.measureTime

private const val ELEMENTS = 100_000
private const val ELEMENTS_QUESTION2 = 10_000
private const val ACCESSES = 10_000
private const val RUNS_20 = 20
private const val RUNS_10 = 10

fun main() {
    questao1()
    questao2()
    questao3()
    questao4()
    questao5()
    questao6()
}

fun questao1() {
    println("=== Questão 1: Inserção ao final ===\n")

    val arrayListAvg =
        measureAverage(RUNS_20) {
            val list = ArrayList<Int>()
            repeat(ELEMENTS) { list.add(Random.nextInt()) }
        }

    val linkedListAvg =
        measureAverage(RUNS_20) {
            val list = LinkedList<Int>()
            repeat(ELEMENTS) { list.add(Random.nextInt()) }
        }

    val ratio =
        maxOf(arrayListAvg, linkedListAvg).inWholeNanoseconds.toDouble() /
            minOf(arrayListAvg, linkedListAvg).inWholeNanoseconds
    val fastest = getFastest(arrayListAvg, linkedListAvg)

    println("Parte 1: $RUNS_20 testes, $ELEMENTS inserções por teste")
    println("  ArrayList  : $arrayListAvg")
    println("  LinkedList : $linkedListAvg")
    println("  Mais rápida: $fastest (${"%.2f".format(ratio)}x)\n")

    println("Parte 2: Capacidade inicial do ArrayList ($RUNS_10 testes, $ELEMENTS inserções por teste)")

    listOf(10, 1_000, 100_000).forEach { capacity ->
        val avg =
            measureAverage(RUNS_10) {
                val list = ArrayList<Int>(capacity)
                repeat(ELEMENTS) { list.add(Random.nextInt()) }
            }
        println("  initialCapacity = ${"%-7d".format(capacity)}: $avg")
    }

    println(
        "  R: A velocidade de cada lista está apresentada acima na parte 1. Criando a arraylist com tamanho suficiente para as inserções, " +
            "evita o tempo que seria gasto redimensionando o array interno (criando um maior e copiando dentro)",
    )
}

fun questao2() {
    println("=== Questão 2: Inserção em posição aleatória ===\n")

    val arrayListAvg =
        measureAverage(RUNS_20) {
            val list = ArrayList<Int>()
            repeat(ELEMENTS_QUESTION2) { list.add(Random.nextInt(list.size + 1), Random.nextInt()) }
        }

    val linkedListAvg =
        measureAverage(RUNS_20) {
            val list = LinkedList<Int>()
            repeat(ELEMENTS_QUESTION2) { list.add(Random.nextInt(list.size + 1), Random.nextInt()) }
        }

    val ratio =
        maxOf(arrayListAvg, linkedListAvg).inWholeNanoseconds.toDouble() /
            minOf(arrayListAvg, linkedListAvg).inWholeNanoseconds

    val fastest = getFastest(arrayListAvg, linkedListAvg)

    println("$RUNS_20 testes, $ELEMENTS_QUESTION2 inserções por teste")
    println("  ArrayList  : $arrayListAvg")
    println("  LinkedList : $linkedListAvg")
    println("  Mais rápida: $fastest (${"%.2f".format(ratio)}x)")
}

fun questao3() {
    println("=== Questão 3: Remoção de elementos ===\n")

    val arrayListFirstAvg = measureRemoval(RUNS_20, { createFullArrayList() }) { while (it.isNotEmpty()) it.removeFirst() }

    val linkedListFirstAvg = measureRemoval(RUNS_20, { createFullLinkedList() }) { while (it.isNotEmpty()) it.removeFirst() }

    val ratio1 =
        maxOf(arrayListFirstAvg, linkedListFirstAvg).inWholeNanoseconds.toDouble() /
            minOf(arrayListFirstAvg, linkedListFirstAvg).inWholeNanoseconds

    val fastestRemoveFirst = getFastest(arrayListFirstAvg, linkedListFirstAvg)

    println("Parte 1: $RUNS_20 testes, remover sempre o primeiro")
    println("  ArrayList  : $arrayListFirstAvg")
    println("  LinkedList : $linkedListFirstAvg")
    println("  Mais rápida: $fastestRemoveFirst (${"%.2f".format(ratio1)}x)\n")

    val arrayListLastAvg = measureRemoval(RUNS_20, { createFullArrayList() }) { while (it.isNotEmpty()) it.removeLast() }

    val linkedListLastAvg = measureRemoval(RUNS_20, { createFullLinkedList() }) { while (it.isNotEmpty()) it.removeLast() }

    val ratio2 =
        maxOf(arrayListLastAvg, linkedListLastAvg).inWholeNanoseconds.toDouble() /
            minOf(arrayListLastAvg, linkedListLastAvg).inWholeNanoseconds

    val fastestRemoveLast = if (arrayListLastAvg < linkedListLastAvg) "ArrayList" else "LinkedList"

    println("Parte 2: $RUNS_20 testes, remover sempre o último")
    println("  ArrayList  : $arrayListLastAvg")
    println("  LinkedList : $linkedListLastAvg")
    println("  Mais rápida: $fastestRemoveLast (${"%.2f".format(ratio2)}x)")
}

fun questao4() {
    println("=== Questão 4: Remoção em índice aleatório ===\n")

    val arrayListAvg = measureRemoval(RUNS_20, { createFullArrayList() }) { while (it.isNotEmpty()) it.removeAt(Random.nextInt(it.size)) }
    val linkedListAvg = measureRemoval(RUNS_20, { createFullLinkedList() }) { while (it.isNotEmpty()) it.removeAt(Random.nextInt(it.size)) }

    val ratio =
        maxOf(arrayListAvg, linkedListAvg).inWholeNanoseconds.toDouble() /
            minOf(arrayListAvg, linkedListAvg).inWholeNanoseconds
    val fastest = getFastest(arrayListAvg, linkedListAvg)

    println("$RUNS_20 testes, remoções até esvaziar")
    println("  ArrayList  : $arrayListAvg")
    println("  LinkedList : $linkedListAvg")
    println("  Mais rápida: $fastest (${"%.2f".format(ratio)}x)")
}

fun questao5() {
    println("=== Questão 5: Acesso por índice aleatório ===\n")

    val arrayListAvg = measureRemoval(RUNS_20, { createFullArrayList() }) { list -> repeat(ACCESSES) { list[Random.nextInt(list.size)] } }
    val linkedListAvg = measureRemoval(RUNS_20, { createFullLinkedList() }) { list -> repeat(ACCESSES) { list[Random.nextInt(list.size)] } }

    val ratio =
        maxOf(arrayListAvg, linkedListAvg).inWholeNanoseconds.toDouble() /
            minOf(arrayListAvg, linkedListAvg).inWholeNanoseconds
    val fastest = getFastest(arrayListAvg, linkedListAvg)

    println("$RUNS_20 testes, $ACCESSES acessos por teste")
    println("  ArrayList  : $arrayListAvg")
    println("  LinkedList : $linkedListAvg")
    println("  Mais rápida: $fastest (${"%.2f".format(ratio)}x)")
}

fun questao6() {
    println("=== Questão 6: Análise Comparativa ===\n")
    println(
        """
        ArrayList foi mais vantajoso em:
           Inserção em posição aleatória: acesso O(1) ao índice permite deslocar elementos via System.arraycopy, que é altamente otimizado. A LinkedList precisa percorrer a lista até o índice antes de inserir.
           Remoção do último elemento: remoção no final do array é O(1) sem deslocamento; na LinkedList tem a alocação de nós.
           Acesso aleatório por índice: ArrayList é mais vantajoso pois a linkedList precisa ir de nó em nó até o indice

        LinkedList foi mais vantajoso em:
           Inserção ao final em sequência: diferença pequena, mas a LinkedList evita realocações periódicas do array interno.
           Remoção do primeiro elemento: O(1) ao remover a cabeça da lista encadeada; ArrayList desloca todos os elementos restantes a cada remoção.

        Os resultados experimentais confirmaram a teoria esperada?
          Sim. As diferenças de desempenho batem com as complexidades teóricas:
           O(1) vs O(n) explica a vantagem da LinkedList na remoção do primeiro elemento
           O(1) de acesso direto explica a vantagem do ArrayList em inserção aleatória e acesso por índice.
           A vantagem da LinkedList na inserção ao final foi pequena , pois o custo de realocação do ArrayList é baixo na prática.

        Fatores práticos que podem ter influenciado os resultados:
           JIT e warm-up da JVM: devido ao jit, pode demorar mais no início, justificando a necessidade de executar varias vezes e tirar a média
           Overhead de alocação de objetos: cada nó da LinkedList é uma alocação separada no heap, o que pode aumentar o tempo de inserção e remoção.
           System.arraycopy nativo: operações em bloco no ArrayList são executadas em código nativo, reduzindo o custo de deslocamento de elementos em comparação ao que a complexidade O(n) sugere isoladamente.
        """.trimIndent(),
    )
}

private fun createFullArrayList() = ArrayList<Int>(ELEMENTS).also { l -> repeat(ELEMENTS) { l.add(Random.nextInt()) } }

private fun createFullLinkedList() = LinkedList<Int>().also { l -> repeat(ELEMENTS) { l.add(Random.nextInt()) } }

private fun measureRemoval(
    runs: Int,
    setup: () -> MutableList<Int>,
    remove: (MutableList<Int>) -> Unit,
): Duration {
    var total = Duration.ZERO
    repeat(runs) {
        val list = setup()
        total += measureTime { remove(list) }
    }
    return total / runs
}

private fun measureAverage(
    runs: Int,
    block: () -> Unit,
): Duration {
    var total = Duration.ZERO
    repeat(runs) { total += measureTime(block) }
    return total / runs
}

private fun getFastest(
    arrayDuration: Duration,
    linkedDuration: Duration,
): String = if (arrayDuration < linkedDuration) "ArrayList" else "LinkedList"
