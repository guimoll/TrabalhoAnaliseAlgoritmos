package smarthome.device

interface Blind {
    fun abrir()

    fun fechar()

    fun estaAberta(): Boolean
}
