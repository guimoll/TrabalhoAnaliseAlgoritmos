package smarthome.adapter.airconditioner

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn
import smarthome.device.AirConditioner

class VentoBaumnAirConditionerAdapter(
    private val airConditioner: ArCondicionadoVentoBaumn
) : AirConditioner {
    private var turnedOn = false

    override fun ligar() {
        airConditioner.ligar()
        turnedOn = true
    }

    override fun desligar() {
        airConditioner.desligar()
        turnedOn = false
    }

    override fun aumentarTemperatura() {
        definirTemperatura(getTemperature() + 1)
    }

    override fun diminuirTemperatura() {
        definirTemperatura(getTemperature() - 1)
    }

    override fun definirTemperatura(temperature: Int) {
        airConditioner.definirTemperatura(temperature)
    }

    override fun getTemperature(): Int {
        return airConditioner.getTemperatura()
    }

    override fun isOn(): Boolean {
        return turnedOn
    }
}
