package smarthome.adapter.blind

import br.furb.analise.algoritmos.PersianaNatLight
import smarthome.device.Blind

class NatLightBlindAdapter(
    private val blind: PersianaNatLight
) : Blind {
    fun descerPalhetas() {
        blind.descerPalheta()
    }

    fun subirPalhetas() {
        require(blind.estaPalhetaAberta()) { "Nao e possivel subir a palheta com as palhetas fechadas" }
        if (!blind.estaPalhetaErguida()) {
            blind.subirPalheta()
        }
    }

    fun abrirPalhetas() {
        if (!blind.estaPalhetaAberta()) {
            blind.abrirPalheta()
        }
    }

    fun fecharPalhetas() {
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

    override fun isOpen(): Boolean {
        return blind.estaPalhetaAberta() && blind.estaPalhetaErguida()
    }
}
