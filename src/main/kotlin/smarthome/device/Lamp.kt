package smarthome.device

interface Lamp {
    fun turnOn()

    fun turnOff()

    fun isOn(): Boolean
}
