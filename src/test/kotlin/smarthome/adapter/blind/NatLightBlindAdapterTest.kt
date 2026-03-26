package smarthome.adapter.blind

import br.furb.analise.algoritmos.PersianaNatLight
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NatLightBlindAdapterTest {

    @Test
    fun testBlindStartsOpen() {
        val adapter = NatLightBlindAdapter(PersianaNatLight())

        assertTrue(adapter.isOpen())
    }

    @Test
    fun testDescerPalhetasLowersBlindWithoutClosingSlats() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)

        adapter.descerPalhetas()

        assertTrue(blind.estaPalhetaAberta())
        assertFalse(blind.estaPalhetaErguida())
        assertFalse(adapter.isOpen())
    }

    @Test
    fun testSubirPalhetasRaisesBlindWhenSlatsAreOpenAndBlindIsLowered() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)
        adapter.descerPalhetas()

        adapter.subirPalhetas()

        assertTrue(blind.estaPalhetaAberta())
        assertTrue(blind.estaPalhetaErguida())
        assertTrue(adapter.isOpen())
    }

    @Test
    fun testSubirPalhetasDoesNothingWhenBlindIsAlreadyRaised() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)

        adapter.subirPalhetas()

        assertTrue(blind.estaPalhetaAberta())
        assertTrue(blind.estaPalhetaErguida())
    }

    @Test
    fun testSubirPalhetasThrowsWhenSlatsAreClosed() {
        val adapter = NatLightBlindAdapter(PersianaNatLight())
        adapter.descerPalhetas()
        adapter.fecharPalhetas()

        val exception = assertThrows(IllegalArgumentException::class.java) {
            adapter.subirPalhetas()
        }

        assertEquals("Nao e possivel subir a palheta com as palhetas fechadas", exception.message)
    }

    @Test
    fun testAbrirPalhetasOpensClosedSlats() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)
        adapter.descerPalhetas()
        adapter.fecharPalhetas()

        adapter.abrirPalhetas()

        assertTrue(blind.estaPalhetaAberta())
        assertFalse(blind.estaPalhetaErguida())
        assertFalse(adapter.isOpen())
    }

    @Test
    fun testAbrirPalhetasDoesNothingWhenSlatsAreAlreadyOpen() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)

        adapter.abrirPalhetas()

        assertTrue(blind.estaPalhetaAberta())
        assertTrue(blind.estaPalhetaErguida())
    }

    @Test
    fun testFecharPalhetasThrowsWhenBlindIsRaised() {
        val adapter = NatLightBlindAdapter(PersianaNatLight())

        val exception = assertThrows(IllegalArgumentException::class.java) {
            adapter.fecharPalhetas()
        }

        assertEquals("Nao e possivel fechar a palheta com a persiana erguida", exception.message)
    }

    @Test
    fun testFecharPalhetasClosesLoweredOpenBlind() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)
        adapter.descerPalhetas()

        adapter.fecharPalhetas()

        assertFalse(blind.estaPalhetaAberta())
        assertFalse(blind.estaPalhetaErguida())
        assertFalse(adapter.isOpen())
    }

    @Test
    fun testFecharPalhetasDoesNothingWhenBlindIsAlreadyClosedAndLowered() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)
        adapter.descerPalhetas()
        adapter.fecharPalhetas()

        adapter.fecharPalhetas()

        assertFalse(blind.estaPalhetaAberta())
        assertFalse(blind.estaPalhetaErguida())
    }

    @Test
    fun testAbrirOpensAndRaisesBlind() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)
        adapter.descerPalhetas()
        adapter.fecharPalhetas()

        adapter.abrir()

        assertTrue(blind.estaPalhetaAberta())
        assertTrue(blind.estaPalhetaErguida())
        assertTrue(adapter.isOpen())
    }

    @Test
    fun testFecharClosesRaisedBlind() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)

        adapter.fechar()

        assertFalse(blind.estaPalhetaAberta())
        assertFalse(blind.estaPalhetaErguida())
        assertFalse(adapter.isOpen())
    }

    @Test
    fun testFecharClosesLoweredOpenBlind() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)
        adapter.descerPalhetas()

        adapter.fechar()

        assertFalse(blind.estaPalhetaAberta())
        assertFalse(blind.estaPalhetaErguida())
        assertFalse(adapter.isOpen())
    }
}
