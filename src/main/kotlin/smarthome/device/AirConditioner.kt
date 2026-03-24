package smarthome.device

interface AirConditioner {
    fun ligar()

    fun desligar()

    fun aumentarTemperatura()

    fun diminuirTemperatura()

    fun definirTemperatura(temperature: Int)

    fun getTemperature(): Int

    fun isOn(): Boolean
}
