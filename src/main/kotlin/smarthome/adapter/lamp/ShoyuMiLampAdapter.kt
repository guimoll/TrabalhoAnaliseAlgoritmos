package smarthome.adapter.lamp

import br.furb.analise.algoritmos.LampadaShoyuMi
import smarthome.device.Lamp

class ShoyuMiLampAdapter(
    private val lamp: LampadaShoyuMi
) : Lamp {
    override fun ligar() {
        lamp.ligar()
    }

    override fun desligar() {
        lamp.desligar()
    }

    override fun isOn(): Boolean {
        return lamp.estaLigada()
    }
}
