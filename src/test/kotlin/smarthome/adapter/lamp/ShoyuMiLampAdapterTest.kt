package smarthome.adapter.lamp

import br.furb.analise.algoritmos.LampadaShoyuMi
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ShoyuMiLampAdapterTest {

    @Test
    fun testLampStartsTurnedOff() {
        val adapter = ShoyuMiLampAdapter(LampadaShoyuMi())

        assertFalse(adapter.isOn())
    }

    @Test
    fun testLigarTurnsLampOn() {
        val adapter = ShoyuMiLampAdapter(LampadaShoyuMi())

        adapter.ligar()

        assertTrue(adapter.isOn())
    }

    @Test
    fun testDesligarTurnsLampOff() {
        val adapter = ShoyuMiLampAdapter(LampadaShoyuMi())
        adapter.ligar()

        adapter.desligar()

        assertFalse(adapter.isOn())
    }
}
