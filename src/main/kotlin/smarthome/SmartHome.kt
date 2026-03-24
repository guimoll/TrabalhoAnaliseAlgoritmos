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

    fun turnOnLights() {
        lamps.forEach { lamp -> lamp.turnOn() }
    }

    fun turnOffLights() {
        lamps.forEach { lamp -> lamp.turnOff() }
    }

    fun openBlinds() {
        blinds.forEach { blind -> blind.open() }
    }

    fun closeBlinds() {
        blinds.forEach { blind -> blind.close() }
    }

    fun turnOnAirConditioners() {
        airConditioners.forEach { airConditioner -> airConditioner.turnOn() }
    }

    fun turnOffAirConditioners() {
        airConditioners.forEach { airConditioner -> airConditioner.turnOff() }
    }

    fun increaseAirConditionerTemperature() {
        airConditioners.forEach { airConditioner -> airConditioner.increaseTemperature() }
    }

    fun decreaseAirConditionerTemperature() {
        airConditioners.forEach { airConditioner -> airConditioner.decreaseTemperature() }
    }

    fun setAirConditionerTemperature(temperature: Int) {
        airConditioners.forEach { airConditioner -> airConditioner.setTemperature(temperature) }
    }
}
