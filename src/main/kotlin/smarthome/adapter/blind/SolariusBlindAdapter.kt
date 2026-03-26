package smarthome.adapter.blind

import br.furb.analise.algoritmos.PersianaSolarius
import smarthome.device.Blind

class SolariusBlindAdapter(
    private val blind: PersianaSolarius
) : Blind {
    override fun abrir() {
        blind.subirPersiana()
    }

    override fun fechar() {
        blind.descerPersiana()
    }

    override fun estaAberta(): Boolean {
        return blind.estaAberta()
    }
}
