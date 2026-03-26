package smarthome.adapter.blind

import br.furb.analise.algoritmos.PersianaSolarius
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SolariusBlindAdapterTest {

    @Test
    fun testBlindStartsOpen() {
        val adapter = SolariusBlindAdapter(PersianaSolarius())

        assertTrue(adapter.isOpen())
    }

    @Test
    fun testDescerPersianaClosesBlind() {
        val adapter = SolariusBlindAdapter(PersianaSolarius())

        adapter.descerPersiana()

        assertFalse(adapter.isOpen())
    }

    @Test
    fun testSubirPersianaOpensBlind() {
        val adapter = SolariusBlindAdapter(PersianaSolarius())
        adapter.descerPersiana()

        adapter.subirPersiana()

        assertTrue(adapter.isOpen())
    }

    @Test
    fun testFecharClosesBlind() {
        val adapter = SolariusBlindAdapter(PersianaSolarius())

        adapter.fechar()

        assertFalse(adapter.isOpen())
    }

    @Test
    fun testAbrirOpensBlind() {
        val adapter = SolariusBlindAdapter(PersianaSolarius())
        adapter.fechar()

        adapter.abrir()

        assertTrue(adapter.isOpen())
    }
}
