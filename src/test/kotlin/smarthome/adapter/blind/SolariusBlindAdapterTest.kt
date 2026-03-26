package smarthome.adapter.blind

import br.furb.analise.algoritmos.PersianaSolarius
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SolariusBlindAdapterTest {

    @Test
    fun testBlindStartsOpen() {
        val adapter = SolariusBlindAdapter(PersianaSolarius())

        assertTrue(adapter.estaAberta())
    }

    @Test
    fun testFecharClosesBlind() {
        val adapter = SolariusBlindAdapter(PersianaSolarius())

        adapter.fechar()

        assertFalse(adapter.estaAberta())
    }

    @Test
    fun testAbrirOpensBlind() {
        val adapter = SolariusBlindAdapter(PersianaSolarius())
        adapter.fechar()

        adapter.abrir()

        assertTrue(adapter.estaAberta())
    }
}
