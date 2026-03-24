package smarthome.adapter.lamp

import br.furb.analise.algoritmos.LampadaPhellipes
import smarthome.device.Lamp

class PhellipesLampAdapter(
    private val lamp: LampadaPhellipes
) : Lamp {
    override fun ligar() {
        lamp.intensidade = MAX_INTENSITY
    }

    override fun desligar() {
        lamp.intensidade = MIN_INTENSITY
    }

    override fun isOn(): Boolean {
        return lamp.intensidade > MIN_INTENSITY
    }

    companion object {
        private const val MIN_INTENSITY = 0
        private const val MAX_INTENSITY = 100
    }
}
