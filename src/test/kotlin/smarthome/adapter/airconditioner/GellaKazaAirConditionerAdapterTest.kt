package smarthome.adapter.airconditioner

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GellaKazaAirConditionerAdapterTest {

    @Test
    fun testAirConditionerStartsTurnedOffAtDefaultTemperature() {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())

        assertFalse(adapter.isOn())
        assertEquals(28, adapter.getTemperature())
    }

    @Test
    fun testLigarTurnsAirConditionerOnAndResetsTemperatureToDefault() {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())
        adapter.ligar()
        adapter.definirTemperatura(35)
        adapter.desligar()

        adapter.ligar()

        assertTrue(adapter.isOn())
        assertEquals(28, adapter.getTemperature())
    }

    @Test
    fun testDesligarTurnsAirConditionerOff() {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())
        adapter.ligar()

        adapter.desligar()

        assertFalse(adapter.isOn())
    }

    @Test
    fun testAumentarTemperaturaIncrementsByOne() {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())

        adapter.aumentarTemperatura()

        assertEquals(29, adapter.getTemperature())
    }

    @Test
    fun testAumentarTemperaturaRejectsTemperatureAboveMaximum() {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())
        adapter.definirTemperatura(35)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            adapter.aumentarTemperatura()
        }

        assertEquals("Limite de temperatura atingido 35", exception.message)
    }

    @Test
    fun testDiminuirTemperaturaDecrementsByOne() {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())

        adapter.diminuirTemperatura()

        assertEquals(27, adapter.getTemperature())
    }

    @Test
    fun testDiminuirTemperaturaRejectsTemperatureBelowMinimum() {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())
        adapter.definirTemperatura(15)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            adapter.diminuirTemperatura()
        }

        assertEquals("Limite de temperatura atingido 15", exception.message)
    }

    @ParameterizedTest
    @ValueSource(ints = [15, 16, 27, 28, 29, 34, 35])
    fun testDefinirTemperaturaAcceptsSupportedValues(temperature: Int) {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())

        adapter.definirTemperatura(temperature)

        assertEquals(temperature, adapter.getTemperature())
    }

    @ParameterizedTest
    @ValueSource(ints = [14, 36])
    fun testDefinirTemperaturaRejectsUnsupportedValues(temperature: Int) {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())

        val exception = assertThrows(IllegalArgumentException::class.java) {
            adapter.definirTemperatura(temperature)
        }

        assertEquals(if (temperature < 15) "Limite de temperatura atingido 15" else "Limite de temperatura atingido 35", exception.message)
    }
}
