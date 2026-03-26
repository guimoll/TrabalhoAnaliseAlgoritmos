package smarthome

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import smarthome.device.AirConditioner
import smarthome.device.Blind
import smarthome.device.Lamp
import kotlin.test.assertEquals
import kotlin.test.assertNotSame

class SmartHomeTest {

    private lateinit var lamp1: Lamp
    private lateinit var lamp2: Lamp
    private lateinit var blind1: Blind
    private lateinit var blind2: Blind
    private lateinit var airConditioner1: AirConditioner
    private lateinit var airConditioner2: AirConditioner

    @BeforeEach
    fun setUp() {
        lamp1 = mockk(relaxed = true)
        lamp2 = mockk(relaxed = true)
        blind1 = mockk(relaxed = true)
        blind2 = mockk(relaxed = true)
        airConditioner1 = mockk(relaxed = true)
        airConditioner2 = mockk(relaxed = true)
    }

    @Test
    fun testConstructorCopiesProvidedCollections() {
        val sourceLamps = mutableListOf(lamp1)
        val sourceBlinds = mutableListOf(blind1)
        val sourceAirConditioners = mutableListOf(airConditioner1)

        val smartHome = SmartHome(sourceLamps, sourceBlinds, sourceAirConditioners)

        sourceLamps.add(lamp2)
        sourceBlinds.add(blind2)
        sourceAirConditioners.add(airConditioner2)

        assertEquals(listOf(lamp1), smartHome.getLamps())
        assertEquals(listOf(blind1), smartHome.getBlinds())
        assertEquals(listOf(airConditioner1), smartHome.getAirConditioners())
    }

    @Test
    fun testAddDevicesStoresThemInSmartHome() {
        val smartHome = SmartHome()

        smartHome.addLamp(lamp1)
        smartHome.addBlind(blind1)
        smartHome.addAirConditioner(airConditioner1)

        assertEquals(listOf(lamp1), smartHome.getLamps())
        assertEquals(listOf(blind1), smartHome.getBlinds())
        assertEquals(listOf(airConditioner1), smartHome.getAirConditioners())
    }

    @Test
    fun testGettersReturnCopies() {
        val smartHome = SmartHome(listOf(lamp1), listOf(blind1), listOf(airConditioner1))

        val firstLampSnapshot = smartHome.getLamps()
        val secondLampSnapshot = smartHome.getLamps()
        val firstBlindSnapshot = smartHome.getBlinds()
        val secondBlindSnapshot = smartHome.getBlinds()
        val firstAirConditionerSnapshot = smartHome.getAirConditioners()
        val secondAirConditionerSnapshot = smartHome.getAirConditioners()

        assertNotSame(firstLampSnapshot, secondLampSnapshot)
        assertNotSame(firstBlindSnapshot, secondBlindSnapshot)
        assertNotSame(firstAirConditionerSnapshot, secondAirConditionerSnapshot)
    }

    @Test
    fun testLigarLampadasCallsAllLamps() {
        SmartHome(listOf(lamp1, lamp2)).ligarLampadas()

        verify(exactly = 1) { lamp1.ligar() }
        verify(exactly = 1) { lamp2.ligar() }
        confirmVerified(lamp1, lamp2)
    }

    @Test
    fun testDesligarLampadasCallsAllLamps() {
        SmartHome(listOf(lamp1, lamp2)).desligarLampadas()

        verify(exactly = 1) { lamp1.desligar() }
        verify(exactly = 1) { lamp2.desligar() }
        confirmVerified(lamp1, lamp2)
    }

    @Test
    fun testAbrirPersianasCallsAllBlinds() {
        SmartHome(blinds = listOf(blind1, blind2)).abrirPersianas()

        verify(exactly = 1) { blind1.abrir() }
        verify(exactly = 1) { blind2.abrir() }
        confirmVerified(blind1, blind2)
    }

    @Test
    fun testFecharPersianasCallsAllBlinds() {
        SmartHome(blinds = listOf(blind1, blind2)).fecharPersianas()

        verify(exactly = 1) { blind1.fechar() }
        verify(exactly = 1) { blind2.fechar() }
        confirmVerified(blind1, blind2)
    }

    @Test
    fun testLigarArCondicionadosCallsAllAirConditioners() {
        SmartHome(airConditioners = listOf(airConditioner1, airConditioner2)).ligarArCondicionados()

        verify(exactly = 1) { airConditioner1.ligar() }
        verify(exactly = 1) { airConditioner2.ligar() }
        confirmVerified(airConditioner1, airConditioner2)
    }

    @Test
    fun testDesligarArCondicionadosCallsAllAirConditioners() {
        SmartHome(airConditioners = listOf(airConditioner1, airConditioner2)).desligarArCondicionados()

        verify(exactly = 1) { airConditioner1.desligar() }
        verify(exactly = 1) { airConditioner2.desligar() }
        confirmVerified(airConditioner1, airConditioner2)
    }

    @Test
    fun testAumentarTemperaturaArCondicionadosCallsAllAirConditioners() {
        SmartHome(airConditioners = listOf(airConditioner1, airConditioner2)).aumentarTemperaturaArCondicionados()

        verify(exactly = 1) { airConditioner1.aumentarTemperatura() }
        verify(exactly = 1) { airConditioner2.aumentarTemperatura() }
        confirmVerified(airConditioner1, airConditioner2)
    }

    @Test
    fun testDiminuirTemperaturaArCondicionadosCallsAllAirConditioners() {
        SmartHome(airConditioners = listOf(airConditioner1, airConditioner2)).diminuirTemperaturaArCondicionados()

        verify(exactly = 1) { airConditioner1.diminuirTemperatura() }
        verify(exactly = 1) { airConditioner2.diminuirTemperatura() }
        confirmVerified(airConditioner1, airConditioner2)
    }

    @Test
    fun testDefinirTemperaturaArCondicionadosCallsAllAirConditionersWithGivenTemperature() {
        SmartHome(airConditioners = listOf(airConditioner1, airConditioner2)).definirTemperaturaArCondicionados(25)

        verify(exactly = 1) { airConditioner1.definirTemperatura(25) }
        verify(exactly = 1) { airConditioner2.definirTemperatura(25) }
        confirmVerified(airConditioner1, airConditioner2)
    }

    @Test
    fun testModoSonoExecutesExpectedSequence() {
        val smartHome = SmartHome(
            lamps = listOf(lamp1, lamp2),
            blinds = listOf(blind1, blind2),
            airConditioners = listOf(airConditioner1, airConditioner2)
        )

        smartHome.modoSono()

        verifySequence {
            airConditioner1.desligar()
            airConditioner2.desligar()
            lamp1.desligar()
            lamp2.desligar()
            blind1.fechar()
            blind2.fechar()
        }
    }

    @Test
    fun testModoTrabalhoExecutesExpectedSequence() {
        val smartHome = SmartHome(
            lamps = listOf(lamp1, lamp2),
            blinds = listOf(blind1, blind2),
            airConditioners = listOf(airConditioner1, airConditioner2)
        )

        smartHome.modoTrabalho()

        verifySequence {
            lamp1.ligar()
            lamp2.ligar()
            airConditioner1.ligar()
            airConditioner2.ligar()
            airConditioner1.definirTemperatura(25)
            airConditioner2.definirTemperatura(25)
            blind1.abrir()
            blind2.abrir()
        }
    }
}
