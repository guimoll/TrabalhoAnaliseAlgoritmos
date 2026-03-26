package smarthome.device

interface AirConditioner {
    fun ligar()

    fun desligar()

    fun aumentarTemperatura()

    fun diminuirTemperatura()

    fun definirTemperatura(temperatura: Int)

    val temperatura: Int

    fun estaLigado(): Boolean
}
