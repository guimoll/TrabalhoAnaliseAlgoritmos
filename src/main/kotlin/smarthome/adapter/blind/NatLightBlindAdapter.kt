package smarthome.adapter.blind

import br.furb.analise.algoritmos.PersianaNatLight
import smarthome.device.Blind

class NatLightBlindAdapter(
    private val blind: PersianaNatLight
) : Blind {
    private fun descerPalhetas() {
        blind.descerPalheta()
    }

    private fun subirPalhetas() {
        require(blind.estaPalhetaAberta()) { "Nao e possivel subir a palheta com as palhetas fechadas" }
        if (!blind.estaPalhetaErguida()) {
            blind.subirPalheta()
        }
    }

    private fun abrirPalhetas() {
        if (!blind.estaPalhetaAberta()) {
            blind.abrirPalheta()
        }
    }

    private fun fecharPalhetas() {
        require(!blind.estaPalhetaErguida()) { "Nao e possivel fechar a palheta com a persiana erguida" }
        if (blind.estaPalhetaAberta()) {
            blind.fecharPalheta()
        }
    }

    override fun abrir() {
        abrirPalhetas()
        subirPalhetas()
    }

    override fun fechar() {
        if (blind.estaPalhetaErguida()) {
            descerPalhetas()
        }
        fecharPalhetas()
    }

    override fun estaAberta(): Boolean {
        return blind.estaPalhetaAberta() && blind.estaPalhetaErguida()
    }
}
