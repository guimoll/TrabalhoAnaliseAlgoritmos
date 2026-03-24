package smarthome

import smarthome.device.AirConditioner
import smarthome.device.Blind
import smarthome.device.Lamp

class SmartHome(
    lamps: List<Lamp> = emptyList(),
    blinds: List<Blind> = emptyList(),
    airConditioners: List<AirConditioner> = emptyList()
) {
    private val lamps: MutableList<Lamp> = lamps.toMutableList()
    private val blinds: MutableList<Blind> = blinds.toMutableList()
    private val airConditioners: MutableList<AirConditioner> = airConditioners.toMutableList()

    fun addLamp(lamp: Lamp) {
        lamps.add(lamp)
    }

    fun addBlind(blind: Blind) {
        blinds.add(blind)
    }

    fun addAirConditioner(airConditioner: AirConditioner) {
        airConditioners.add(airConditioner)
    }

    fun getLamps(): List<Lamp> {
        return lamps.toList()
    }

    fun getBlinds(): List<Blind> {
        return blinds.toList()
    }

    fun getAirConditioners(): List<AirConditioner> {
        return airConditioners.toList()
    }

    fun ligarLampadas() {
        lamps.forEach { lamp -> lamp.ligar() }
    }

    fun desligarLampadas() {
        lamps.forEach { lamp -> lamp.desligar() }
    }

    fun abrirPersianas() {
        blinds.forEach { blind -> blind.abrir() }
    }

    fun fecharPersianas() {
        blinds.forEach { blind -> blind.fechar() }
    }

    fun ligarArCondicionados() {
        airConditioners.forEach { airConditioner -> airConditioner.ligar() }
    }

    fun desligarArCondicionados() {
        airConditioners.forEach { airConditioner -> airConditioner.desligar() }
    }

    fun aumentarTemperaturaArCondicionados() {
        airConditioners.forEach { airConditioner -> airConditioner.aumentarTemperatura() }
    }

    fun diminuirTemperaturaArCondicionados() {
        airConditioners.forEach { airConditioner -> airConditioner.diminuirTemperatura() }
    }

    fun definirTemperaturaArCondicionados(temperature: Int) {
        airConditioners.forEach { airConditioner -> airConditioner.definirTemperatura(temperature) }
    }

    fun modoSono() {
        desligarArCondicionados()
        desligarLampadas()
        fecharPersianas()
    }

    fun modoTrabalho() {
        ligarLampadas()
        ligarArCondicionados()
        definirTemperaturaArCondicionados(25)
        abrirPersianas()
    }
}
