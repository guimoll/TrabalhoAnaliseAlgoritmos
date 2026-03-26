package smarthome

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza
import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn
import br.furb.analise.algoritmos.LampadaPhellipes
import br.furb.analise.algoritmos.LampadaShoyuMi
import br.furb.analise.algoritmos.PersianaNatLight
import br.furb.analise.algoritmos.PersianaSolarius
import smarthome.adapter.airconditioner.GellaKazaAirConditionerAdapter
import smarthome.adapter.airconditioner.VentoBaumnAirConditionerAdapter
import smarthome.adapter.blind.NatLightBlindAdapter
import smarthome.adapter.blind.SolariusBlindAdapter
import smarthome.adapter.lamp.PhellipesLampAdapter
import smarthome.adapter.lamp.ShoyuMiLampAdapter

fun main() {
    val shoyuMiLamp = ShoyuMiLampAdapter(LampadaShoyuMi())
    val phellipesLamp = PhellipesLampAdapter(LampadaPhellipes())
    val solariusBlind = SolariusBlindAdapter(PersianaSolarius())
    val natLightBlind = NatLightBlindAdapter(PersianaNatLight())
    val ventoBaumnAirConditioner = VentoBaumnAirConditionerAdapter(ArCondicionadoVentoBaumn())
    val gellaKazaAirConditioner = GellaKazaAirConditionerAdapter(ArCondicionadoGellaKaza())
    val smartHome = SmartHome(
        lampadas = listOf(shoyuMiLamp, phellipesLamp),
        persianas = listOf(solariusBlind, natLightBlind),
        arCondicionados = listOf(ventoBaumnAirConditioner, gellaKazaAirConditioner)
    )

    println("===== SETUP =====")
    smartHome.ligarLampadas()
    smartHome.abrirPersianas()
    smartHome.ligarArCondicionados()
    println()

    println("===== CONTROLE DE INTENSIDADE - PHELLIPES =====")
    phellipesLamp.definirIntensidade(1)
    println("Phellipes ligada com intensidade 1: ${phellipesLamp.estaLigada()}")
    phellipesLamp.definirIntensidade(4)
    println("Phellipes ligada com intensidade 4: ${phellipesLamp.estaLigada()}")
    phellipesLamp.definirIntensidade(99)
    println("Phellipes ligada com intensidade 99: ${phellipesLamp.estaLigada()}")
    println()

    println("===== CONTROLE DE PERSIANA - SOLARIUS =====")
    solariusBlind.fechar()
    println("Solarius aberta apos fechar: ${solariusBlind.estaAberta()}")
    solariusBlind.abrir()
    println("Solarius aberta apos abrir: ${solariusBlind.estaAberta()}")
    println()

    println("===== CONTROLE DE PERSIANA - NATLIGHT =====")
    natLightBlind.fechar()
    println("NatLight aberta apos fechar: ${natLightBlind.estaAberta()}")
    natLightBlind.abrir()
    println("NatLight aberta apos abrir: ${natLightBlind.estaAberta()}")
    println()

    println("===== ESTADO INICIAL DOS DISPOSITIVOS =====")
    println("ShoyuMi ligada: ${shoyuMiLamp.estaLigada()}")
    println("Phellipes ligada: ${phellipesLamp.estaLigada()}")
    println("Solarius aberta: ${solariusBlind.estaAberta()}")
    println("NatLight aberta: ${natLightBlind.estaAberta()}")
    println("VentoBaumn ligado: ${ventoBaumnAirConditioner.estaLigado()}")
    println("GellaKaza ligado: ${gellaKazaAirConditioner.estaLigado()}")
    println("VentoBaumn temperatura apos ligar: ${ventoBaumnAirConditioner.temperatura}")
    println("GellaKaza temperatura apos ligar: ${gellaKazaAirConditioner.temperatura}")
    println()

    println("===== DEFINIR TEMPERATURA =====")
    smartHome.definirTemperaturaArCondicionados(25)
    println("VentoBaumn temperatura apos definir 25: ${ventoBaumnAirConditioner.temperatura}")
    println("GellaKaza temperatura apos definir 25: ${gellaKazaAirConditioner.temperatura}")
    println()

    println("===== MODO SONO =====")
    smartHome.modoSono()
    println("ShoyuMi ligada apos modo sono: ${shoyuMiLamp.estaLigada()}")
    println("Phellipes ligada apos modo sono: ${phellipesLamp.estaLigada()}")
    println("Solarius aberta apos modo sono: ${solariusBlind.estaAberta()}")
    println("NatLight aberta apos modo sono: ${natLightBlind.estaAberta()}")
    println("VentoBaumn ligado apos modo sono: ${ventoBaumnAirConditioner.estaLigado()}")
    println("GellaKaza ligado apos modo sono: ${gellaKazaAirConditioner.estaLigado()}")
    println("VentoBaumn temperatura apos modo sono: ${ventoBaumnAirConditioner.temperatura}")
    println("GellaKaza temperatura apos modo sono: ${gellaKazaAirConditioner.temperatura}")
    println()

    println("===== MODO TRABALHO =====")
    smartHome.modoTrabalho()
    println("ShoyuMi ligada apos modo trabalho: ${shoyuMiLamp.estaLigada()}")
    println("Phellipes ligada apos modo trabalho: ${phellipesLamp.estaLigada()}")
    println("Solarius aberta apos modo trabalho: ${solariusBlind.estaAberta()}")
    println("NatLight aberta apos modo trabalho: ${natLightBlind.estaAberta()}")
    println("VentoBaumn ligado apos modo trabalho: ${ventoBaumnAirConditioner.estaLigado()}")
    println("GellaKaza ligado apos modo trabalho: ${gellaKazaAirConditioner.estaLigado()}")
    println("VentoBaumn temperatura apos modo trabalho: ${ventoBaumnAirConditioner.temperatura}")
    println("GellaKaza temperatura apos modo trabalho: ${gellaKazaAirConditioner.temperatura}")
}
