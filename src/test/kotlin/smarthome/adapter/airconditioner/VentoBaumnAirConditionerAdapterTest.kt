package smarthome.adapter.airconditioner

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VentoBaumnAirConditionerAdapterTest {

    @Test
    fun testAirConditionerStartsTurnedOffAtDefaultTemperature() {
        val adapter = VentoBaumnAirConditionerAdapter(ArCondicionadoVentoBaumn())

        assertFalse(adapter.estaLigado())
        assertEquals(24, adapter.temperatura)
    }

    @Test
    fun testLigarTurnsAirConditionerOnAndResetsTemperatureToDefault() {
        val adapter = VentoBaumnAirConditionerAdapter(ArCondicionadoVentoBaumn())
        adapter.ligar()
        adapter.definirTemperatura(35)
        adapter.desligar()

        adapter.ligar()

        assertTrue(adapter.estaLigado())
        assertEquals(24, adapter.temperatura)
    }

    @Test
    fun testDesligarTurnsAirConditionerOff() {
        val adapter = VentoBaumnAirConditionerAdapter(ArCondicionadoVentoBaumn())
        adapter.ligar()

        adapter.desligar()

        assertFalse(adapter.estaLigado())
    }

    @ParameterizedTest
    @ValueSource(ints = [15, 16, 24, 34, 35])
    fun testDefinirTemperaturaAcceptsSupportedValues(temperatura: Int) {
        val adapter = VentoBaumnAirConditionerAdapter(ArCondicionadoVentoBaumn())
        adapter.ligar()

        adapter.definirTemperatura(temperatura)

        assertEquals(temperatura, adapter.temperatura)
    }

    @ParameterizedTest
    @ValueSource(ints = [14, 36])
    fun testDefinirTemperaturaRejectsUnsupportedValues(temperatura: Int) {
        val adapter = VentoBaumnAirConditionerAdapter(ArCondicionadoVentoBaumn())
        adapter.ligar()

        val exception = assertThrows(IllegalArgumentException::class.java) {
            adapter.definirTemperatura(temperatura)
        }

        assertEquals("Temperatura deve ser entre 15 e 35", exception.message)
    }

    @Test
    fun testDefinirTemperaturaThrowsWhenAirConditionerIsOff() {
        val adapter = VentoBaumnAirConditionerAdapter(ArCondicionadoVentoBaumn())

        val exception = assertThrows(IllegalArgumentException::class.java) {
            adapter.definirTemperatura(24)
        }

        assertEquals("Para definir a temperatura o aparelho deve estar ligado", exception.message)
    }

    @Test
    fun testAumentarTemperaturaIncrementsByOne() {
        val adapter = VentoBaumnAirConditionerAdapter(ArCondicionadoVentoBaumn())
        adapter.ligar()

        adapter.aumentarTemperatura()

        assertEquals(25, adapter.temperatura)
    }

    @Test
    fun testDiminuirTemperaturaDecrementsByOne() {
        val adapter = VentoBaumnAirConditionerAdapter(ArCondicionadoVentoBaumn())
        adapter.ligar()

        adapter.diminuirTemperatura()

        assertEquals(23, adapter.temperatura)
    }
}
