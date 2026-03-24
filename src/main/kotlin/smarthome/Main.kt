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
        lamps = listOf(shoyuMiLamp, phellipesLamp),
        blinds = listOf(solariusBlind, natLightBlind),
        airConditioners = listOf(ventoBaumnAirConditioner, gellaKazaAirConditioner)
    )

    smartHome.ligarLampadas()
    smartHome.abrirPersianas()
    smartHome.ligarArCondicionados()

    phellipesLamp.setIntensidade(1)
    println("Phellipes ligada com intensidade 1: ${phellipesLamp.isOn()}")
    phellipesLamp.setIntensidade(4)
    println("Phellipes ligada com intensidade 4: ${phellipesLamp.isOn()}")
    phellipesLamp.setIntensidade(99)
    println("Phellipes ligada com intensidade 99: ${phellipesLamp.isOn()}")

    solariusBlind.descerPersiana()
    println("Solarius aberta apos descerPersiana: ${solariusBlind.isOpen()}")
    solariusBlind.subirPersiana()
    println("Solarius aberta apos subirPersiana: ${solariusBlind.isOpen()}")

    natLightBlind.abrirPalhetas()
    println("NatLight aberta apos abrirPalheta: ${natLightBlind.isOpen()}")
    natLightBlind.subirPalhetas()
    println("NatLight aberta apos subirPalheta: ${natLightBlind.isOpen()}")
    natLightBlind.descerPalhetas()
    println("NatLight aberta apos descerPalheta: ${natLightBlind.isOpen()}")
    natLightBlind.fecharPalhetas()
    println("NatLight aberta apos fecharPalheta: ${natLightBlind.isOpen()}")
    natLightBlind.abrir()

    println("ShoyuMi lamp on: ${shoyuMiLamp.isOn()}")
    println("Phellipes lamp on: ${phellipesLamp.isOn()}")
    println("Solarius blind open: ${solariusBlind.isOpen()}")
    println("NatLight blind open: ${natLightBlind.isOpen()}")
    println("VentoBaumn air conditioner on: ${ventoBaumnAirConditioner.isOn()}")
    println("GellaKaza air conditioner on: ${gellaKazaAirConditioner.isOn()}")
    println("VentoBaumn temperature after ligar: ${ventoBaumnAirConditioner.getTemperature()}")
    println("GellaKaza temperature after ligar: ${gellaKazaAirConditioner.getTemperature()}")

    smartHome.definirTemperaturaArCondicionados(25)
    println("VentoBaumn temperature after definir 25: ${ventoBaumnAirConditioner.getTemperature()}")
    println("GellaKaza temperature after definir 25: ${gellaKazaAirConditioner.getTemperature()}")

    smartHome.modoSono()

    println("ShoyuMi lamp on after modo sono: ${shoyuMiLamp.isOn()}")
    println("Phellipes lamp on after modo sono: ${phellipesLamp.isOn()}")
    println("Solarius blind open after modo sono: ${solariusBlind.isOpen()}")
    println("NatLight blind open after modo sono: ${natLightBlind.isOpen()}")
    println("VentoBaumn air conditioner on after modo sono: ${ventoBaumnAirConditioner.isOn()}")
    println("GellaKaza air conditioner on after modo sono: ${gellaKazaAirConditioner.isOn()}")
    println("VentoBaumn temperature after modo sono: ${ventoBaumnAirConditioner.getTemperature()}")
    println("GellaKaza temperature after modo sono: ${gellaKazaAirConditioner.getTemperature()}")

    smartHome.modoTrabalho()

    println("ShoyuMi lamp on after modo trabalho: ${shoyuMiLamp.isOn()}")
    println("Phellipes lamp on after modo trabalho: ${phellipesLamp.isOn()}")
    println("Solarius blind open after modo trabalho: ${solariusBlind.isOpen()}")
    println("NatLight blind open after modo trabalho: ${natLightBlind.isOpen()}")
    println("VentoBaumn air conditioner on after modo trabalho: ${ventoBaumnAirConditioner.isOn()}")
    println("GellaKaza air conditioner on after modo trabalho: ${gellaKazaAirConditioner.isOn()}")
    println("VentoBaumn temperature after modo trabalho: ${ventoBaumnAirConditioner.getTemperature()}")
    println("GellaKaza temperature after modo trabalho: ${gellaKazaAirConditioner.getTemperature()}")
}
