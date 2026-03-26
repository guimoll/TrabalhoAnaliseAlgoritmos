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

        assertFalse(adapter.estaLigado())
        assertEquals(28, adapter.temperatura)
    }

    @Test
    fun testLigarTurnsAirConditionerOnAndResetsTemperatureToDefault() {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())
        adapter.ligar()
        adapter.definirTemperatura(35)
        adapter.desligar()

        adapter.ligar()

        assertTrue(adapter.estaLigado())
        assertEquals(28, adapter.temperatura)
    }

    @Test
    fun testDesligarTurnsAirConditionerOff() {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())
        adapter.ligar()

        adapter.desligar()

        assertFalse(adapter.estaLigado())
    }

    @Test
    fun testAumentarTemperaturaIncrementsByOne() {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())

        adapter.aumentarTemperatura()

        assertEquals(29, adapter.temperatura)
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

        assertEquals(27, adapter.temperatura)
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
    fun testDefinirTemperaturaAcceptsSupportedValues(temperatura: Int) {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())

        adapter.definirTemperatura(temperatura)

        assertEquals(temperatura, adapter.temperatura)
    }

    @ParameterizedTest
    @ValueSource(ints = [14, 36])
    fun testDefinirTemperaturaRejectsUnsupportedValues(temperatura: Int) {
        val adapter = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())

        val exception = assertThrows(IllegalArgumentException::class.java) {
            adapter.definirTemperatura(temperatura)
        }

        assertEquals(
            if (temperatura < 15) "Limite de temperatura atingido 15" else "Limite de temperatura atingido 35",
            exception.message
        )
    }
}
