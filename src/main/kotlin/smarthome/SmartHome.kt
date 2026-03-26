package smarthome

import smarthome.device.AirConditioner
import smarthome.device.Blind
import smarthome.device.Lamp

class SmartHome(
    lampadas: List<Lamp> = emptyList(),
    persianas: List<Blind> = emptyList(),
    arCondicionados: List<AirConditioner> = emptyList()
) {
    private val _lampadas: MutableList<Lamp> = lampadas.toMutableList()
    private val _persianas: MutableList<Blind> = persianas.toMutableList()
    private val _arCondicionados: MutableList<AirConditioner> = arCondicionados.toMutableList()

    val lamps: List<Lamp>
        get() = _lampadas.toList()

    val blinds: List<Blind>
        get() = _persianas.toList()

    val airConditioners: List<AirConditioner>
        get() = _arCondicionados.toList()

    fun addLamp(lamp: Lamp) {
        _lampadas.add(lamp)
    }

    fun addBlind(blind: Blind) {
        _persianas.add(blind)
    }

    fun addAirConditioner(airConditioner: AirConditioner) {
        _arCondicionados.add(airConditioner)
    }

    fun ligarLampadas() {
        _lampadas.forEach { lamp -> lamp.ligar() }
    }

    fun desligarLampadas() {
        _lampadas.forEach { lamp -> lamp.desligar() }
    }

    fun abrirPersianas() {
        _persianas.forEach { persiana -> persiana.abrir() }
    }

    fun fecharPersianas() {
        _persianas.forEach { persiana -> persiana.fechar() }
    }

    fun ligarArCondicionados() {
        _arCondicionados.forEach { arCondicionado -> arCondicionado.ligar() }
    }

    fun desligarArCondicionados() {
        _arCondicionados.forEach { arCondicionado -> arCondicionado.desligar() }
    }

    fun aumentarTemperaturaArCondicionados() {
        _arCondicionados.forEach { arCondicionado -> arCondicionado.aumentarTemperatura() }
    }

    fun diminuirTemperaturaArCondicionados() {
        _arCondicionados.forEach { arCondicionado -> arCondicionado.diminuirTemperatura() }
    }

    fun definirTemperaturaArCondicionados(temperatura: Int) {
        _arCondicionados.forEach { arCondicionado -> arCondicionado.definirTemperatura(temperatura) }
    }

    fun modoSono() {
        desligarArCondicionados()
        desligarLampadas()
        fecharPersianas()
    }

    fun modoTrabalho() {
        ligarLampadas()
        ligarArCondicionados()
        definirTemperaturaArCondicionados(TEMPERATURA_MODO_TRABALHO)
        abrirPersianas()
    }

    companion object {
        private const val TEMPERATURA_MODO_TRABALHO = 25
    }
}
