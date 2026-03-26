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
        assertTrue(printedText.contains("Solarius aberta apos fechar: false"))
        assertTrue(printedText.contains("NatLight aberta apos fechar: false"))
        assertTrue(printedText.contains("VentoBaumn temperatura apos ligar: 24"))
        assertTrue(printedText.contains("GellaKaza temperatura apos ligar: 28"))
        assertTrue(printedText.contains("ShoyuMi ligada apos modo sono: false"))
        assertTrue(printedText.contains("Solarius aberta apos modo trabalho: true"))
        assertTrue(printedText.contains("GellaKaza temperatura apos modo trabalho: 25"))
    }
}
