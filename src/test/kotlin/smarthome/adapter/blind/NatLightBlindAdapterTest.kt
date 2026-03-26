package smarthome.adapter.blind

import br.furb.analise.algoritmos.PersianaNatLight
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NatLightBlindAdapterTest {

    @Test
    fun testBlindStartsOpen() {
        val adapter = NatLightBlindAdapter(PersianaNatLight())

        assertTrue(adapter.estaAberta())
    }

    @Test
    fun testAbrirOpensAndRaisesBlind() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)
        blind.descerPalheta()
        blind.fecharPalheta()

        adapter.abrir()

        assertTrue(blind.estaPalhetaAberta())
        assertTrue(blind.estaPalhetaErguida())
        assertTrue(adapter.estaAberta())
    }

    @Test
    fun testFecharClosesRaisedBlind() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)

        adapter.fechar()

        assertFalse(blind.estaPalhetaAberta())
        assertFalse(blind.estaPalhetaErguida())
        assertFalse(adapter.estaAberta())
    }

    @Test
    fun testFecharClosesLoweredOpenBlind() {
        val blind = PersianaNatLight()
        val adapter = NatLightBlindAdapter(blind)
        blind.descerPalheta()

        adapter.fechar()

        assertFalse(blind.estaPalhetaAberta())
        assertFalse(blind.estaPalhetaErguida())
        assertFalse(adapter.estaAberta())
    }
}
