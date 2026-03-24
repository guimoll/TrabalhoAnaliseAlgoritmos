package smarthome.adapter.blind

import br.furb.analise.algoritmos.PersianaSolarius
import smarthome.device.Blind

class SolariusBlindAdapter(
    private val blind: PersianaSolarius
) : Blind {
    fun subirPersiana() {
        blind.subirPersiana()
    }

    fun descerPersiana() {
        blind.descerPersiana()
    }

    override fun abrir() {
        subirPersiana()
    }

    override fun fechar() {
        descerPersiana()
    }

    override fun isOpen(): Boolean {
        return blind.estaAberta()
    }
}
