package smarthome.adapter.lamp

import br.furb.analise.algoritmos.LampadaPhellipes
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PhellipesLampAdapterTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 4, 11, 50, 99, 100])
    fun testDefinirIntensidadeAcceptsSupportedValues(intensidade: Int) {
        val lamp = LampadaPhellipes()
        val adapter = PhellipesLampAdapter(lamp)

        adapter.definirIntensidade(intensidade)

        assertEquals(intensidade, lamp.intensidade)
        assertEquals(intensidade > 0, adapter.estaLigada())
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 101])
    fun testDefinirIntensidadeRejectsUnsupportedValues(intensidade: Int) {
        val adapter = PhellipesLampAdapter(LampadaPhellipes())

        val exception = assertThrows(IllegalArgumentException::class.java) {
            adapter.definirIntensidade(intensidade)
        }

        assertEquals("O valor da intensidade deve ser entre 0 e 100", exception.message)
    }

    @Test
    fun testLigarSetsMaximumIntensity() {
        val lamp = LampadaPhellipes()
        val adapter = PhellipesLampAdapter(lamp)

        adapter.ligar()

        assertEquals(100, lamp.intensidade)
        assertTrue(adapter.estaLigada())
    }

    @Test
    fun testDesligarSetsMinimumIntensity() {
        val lamp = LampadaPhellipes()
        val adapter = PhellipesLampAdapter(lamp)
        adapter.definirIntensidade(50)

        adapter.desligar()

        assertEquals(0, lamp.intensidade)
        assertFalse(adapter.estaLigada())
    }
}
