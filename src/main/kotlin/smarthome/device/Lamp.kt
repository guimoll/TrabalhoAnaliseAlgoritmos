package smarthome.device

interface Lamp {
    fun ligar()

    fun desligar()

    fun isOn(): Boolean
}
