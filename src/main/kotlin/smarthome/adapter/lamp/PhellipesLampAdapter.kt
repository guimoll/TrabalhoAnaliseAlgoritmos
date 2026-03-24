package smarthome.adapter.lamp

import br.furb.analise.algoritmos.LampadaPhellipes
import smarthome.device.Lamp

class PhellipesLampAdapter(
    private val lamp: LampadaPhellipes
) : Lamp {

    fun setIntensidade(intensidade: Int) {
        lamp.intensidade = intensidade
    }

    override fun ligar() {
        setIntensidade(MAX_INTENSITY)
    }

    override fun desligar() {
        setIntensidade(MIN_INTENSITY)
    }

    override fun isOn(): Boolean {
        return lamp.intensidade > MIN_INTENSITY
    }

    companion object {
        private const val MIN_INTENSITY = 0
        private const val MAX_INTENSITY = 100
    }
}
