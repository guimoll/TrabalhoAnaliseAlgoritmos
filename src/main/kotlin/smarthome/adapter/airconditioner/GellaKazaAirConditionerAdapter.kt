package smarthome.adapter.airconditioner

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza
import smarthome.device.AirConditioner

class GellaKazaAirConditionerAdapter(
    private val airConditioner: ArCondicionadoGellaKaza
) : AirConditioner {
    override fun turnOn() {
        airConditioner.ativar()
    }

    override fun turnOff() {
        airConditioner.desativar()
    }

    override fun increaseTemperature() {
        airConditioner.aumentarTemperatura()
    }

    override fun decreaseTemperature() {
        airConditioner.diminuirTemperatura()
    }

    override fun setTemperature(temperature: Int) {
        while (getTemperature() < temperature) {
            increaseTemperature()
        }

        while (getTemperature() > temperature) {
            decreaseTemperature()
        }
    }

    override fun getTemperature(): Int {
        return airConditioner.getTemperatura()
    }

    override fun isOn(): Boolean {
        return airConditioner.estaLigado()
    }
}
