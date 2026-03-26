package smarthome.adapter.airconditioner

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza
import smarthome.device.AirConditioner

class GellaKazaAirConditionerAdapter(
    private val airConditioner: ArCondicionadoGellaKaza
) : AirConditioner {

    override fun ligar() {
        airConditioner.ativar()
        definirTemperatura(DEFAULT_TEMPERATURE)
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

    override fun definirTemperatura(temperatura: Int) {
        require(temperatura in MIN_TEMPERATURE..MAX_TEMPERATURE) {
            if (temperatura < MIN_TEMPERATURE) "Limite de temperatura atingido $MIN_TEMPERATURE"
            else "Limite de temperatura atingido $MAX_TEMPERATURE"
        }
        while (this.temperatura < temperatura) {
            aumentarTemperatura()
        }
        while (this.temperatura > temperatura) {
            diminuirTemperatura()
        }
    }

    override val temperatura: Int
        get() = airConditioner.getTemperatura()

    override fun estaLigado(): Boolean {
        return airConditioner.estaLigado()
    }

    companion object {
        private const val DEFAULT_TEMPERATURE = 28
        private const val MIN_TEMPERATURE = 15
        private const val MAX_TEMPERATURE = 35
    }
}
