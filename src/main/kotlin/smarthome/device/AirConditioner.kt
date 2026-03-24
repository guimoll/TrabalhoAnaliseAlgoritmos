package smarthome.device

interface AirConditioner {
    fun turnOn()

    fun turnOff()

    fun increaseTemperature()

    fun decreaseTemperature()

    fun setTemperature(temperature: Int)

    fun getTemperature(): Int

    fun isOn(): Boolean
}
