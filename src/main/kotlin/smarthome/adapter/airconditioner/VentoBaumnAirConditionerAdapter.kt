package smarthome.adapter.airconditioner

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn
import smarthome.device.AirConditioner

class VentoBaumnAirConditionerAdapter(
    private val airConditioner: ArCondicionadoVentoBaumn
) : AirConditioner {
    private var turnedOn = false

    override fun turnOn() {
        airConditioner.ligar()
        turnedOn = true
    }

    override fun turnOff() {
        airConditioner.desligar()
        turnedOn = false
    }

    override fun increaseTemperature() {
        setTemperature(getTemperature() + 1)
    }

    override fun decreaseTemperature() {
        setTemperature(getTemperature() - 1)
    }

    override fun setTemperature(temperature: Int) {
        airConditioner.definirTemperatura(temperature)
    }

    override fun getTemperature(): Int {
        return airConditioner.getTemperatura()
    }

    override fun isOn(): Boolean {
        return turnedOn
    }
}
