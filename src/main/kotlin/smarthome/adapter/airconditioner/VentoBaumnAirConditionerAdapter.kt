package smarthome.adapter.airconditioner

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn
import smarthome.device.AirConditioner

class VentoBaumnAirConditionerAdapter(
    private val airConditioner: ArCondicionadoVentoBaumn
) : AirConditioner {
    private var ligado = false

    override fun ligar() {
        airConditioner.ligar()
        airConditioner.definirTemperatura(DEFAULT_TEMPERATURE)
        ligado = true
    }

    override fun desligar() {
        airConditioner.desligar()
        ligado = false
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
        return airConditioner.temperatura
    }

    override fun isOn(): Boolean {
        return ligado
    }

    companion object {
        private const val DEFAULT_TEMPERATURE = 24
    }
}
