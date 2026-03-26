package smarthome

import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertTrue

class MainTest {

    @Test
    fun testMainPrintsExpectedFlow() {
        val originalOut = System.out
        val output = ByteArrayOutputStream()

        try {
            System.setOut(PrintStream(output, true))
            main()
        } finally {
            System.setOut(originalOut)
        }

        val printedText = output.toString()

        assertTrue(printedText.contains("Phellipes ligada com intensidade 99: true"))
        assertTrue(printedText.contains("Solarius aberta apos descerPersiana: false"))
        assertTrue(printedText.contains("NatLight aberta apos fecharPalheta: false"))
        assertTrue(printedText.contains("VentoBaumn temperature after ligar: 24"))
        assertTrue(printedText.contains("GellaKaza temperature after ligar: 28"))
        assertTrue(printedText.contains("ShoyuMi lamp on after modo sono: false"))
        assertTrue(printedText.contains("Solarius blind open after modo trabalho: true"))
        assertTrue(printedText.contains("GellaKaza temperature after modo trabalho: 25"))
    }
}
