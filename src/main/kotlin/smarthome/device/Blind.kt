package smarthome.device

interface Blind {
    fun open()

    fun close()

    fun isOpen(): Boolean
}
