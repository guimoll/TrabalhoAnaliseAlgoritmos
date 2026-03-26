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
        definirTemperatura(temperatura + 1)
    }

    override fun diminuirTemperatura() {
        definirTemperatura(temperatura - 1)
    }

    override fun definirTemperatura(temperatura: Int) {
        require(ligado) { "Para definir a temperatura o aparelho deve estar ligado" }
        airConditioner.definirTemperatura(temperatura)
    }

    override val temperatura: Int
        get() = airConditioner.temperatura

    override fun estaLigado(): Boolean {
        return ligado
    }

    companion object {
        private const val DEFAULT_TEMPERATURE = 24
    }
}
