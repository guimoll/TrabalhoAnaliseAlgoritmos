package smarthome.adapter.airconditioner

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza
import smarthome.device.AirConditioner

class GellaKazaAirConditionerAdapter(
    private val airConditioner: ArCondicionadoGellaKaza
) : AirConditioner {
    override fun ligar() {
        airConditioner.ativar()
    }

    override fun desligar() {
        airConditioner.desativar()
    }

    override fun aumentarTemperatura() {
        airConditioner.aumentarTemperatura()
    }

    override fun diminuirTemperatura() {
        airConditioner.diminuirTemperatura()
    }

    override fun definirTemperatura(temperature: Int) {
        while (getTemperature() < temperature) {
            aumentarTemperatura()
        }

        while (getTemperature() > temperature) {
            diminuirTemperatura()
        }
    }

    override fun getTemperature(): Int {
        return airConditioner.getTemperatura()
    }

    override fun isOn(): Boolean {
        return airConditioner.estaLigado()
    }
}
