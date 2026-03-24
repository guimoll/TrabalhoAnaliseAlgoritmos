package smarthome.adapter.blind

import br.furb.analise.algoritmos.PersianaNatLight
import smarthome.device.Blind

class NatLightBlindAdapter(
    private val blind: PersianaNatLight
) : Blind {
    override fun open() {
        ensurePalhetasAreOpen()
        raiseBlindIfNeeded()
    }

    override fun close() {
        lowerBlindIfNeeded()
        closeSlatsIfNeeded()
    }

    override fun isOpen(): Boolean {
        return blind.estaPalhetaAberta() && blind.estaPalhetaErguida()
    }

    private fun ensurePalhetasAreOpen() {
        if (!blind.estaPalhetaAberta()) {
            blind.abrirPalheta()
        }
    }

    private fun raiseBlindIfNeeded() {
        if (!blind.estaPalhetaErguida()) {
            blind.subirPalheta()
        }
    }

    private fun lowerBlindIfNeeded() {
        if (blind.estaPalhetaErguida()) {
            blind.descerPalheta()
        }
    }

    private fun closeSlatsIfNeeded() {
        if (blind.estaPalhetaAberta()) {
            blind.fecharPalheta()
        }
    }
}
