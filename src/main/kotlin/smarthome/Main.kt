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

    smartHome.turnOnLights()
    smartHome.openBlinds()
    smartHome.turnOnAirConditioners()
    smartHome.setAirConditionerTemperature(25)
    smartHome.increaseAirConditionerTemperature()
    smartHome.decreaseAirConditionerTemperature()

    println("ShoyuMi lamp on: ${shoyuMiLamp.isOn()}")
    println("Phellipes lamp on: ${phellipesLamp.isOn()}")
    println("Solarius blind open: ${solariusBlind.isOpen()}")
    println("NatLight blind open: ${natLightBlind.isOpen()}")
    println("VentoBaumn air conditioner on: ${ventoBaumnAirConditioner.isOn()}")
    println("GellaKaza air conditioner on: ${gellaKazaAirConditioner.isOn()}")
    println("VentoBaumn temperature: ${ventoBaumnAirConditioner.getTemperature()}")
    println("GellaKaza temperature: ${gellaKazaAirConditioner.getTemperature()}")

    smartHome.turnOffLights()
    smartHome.closeBlinds()
    smartHome.turnOffAirConditioners()

    println("ShoyuMi lamp on after shutdown: ${shoyuMiLamp.isOn()}")
    println("Phellipes lamp on after shutdown: ${phellipesLamp.isOn()}")
    println("Solarius blind open after closing: ${solariusBlind.isOpen()}")
    println("NatLight blind open after closing: ${natLightBlind.isOpen()}")
    println("VentoBaumn air conditioner on after shutdown: ${ventoBaumnAirConditioner.isOn()}")
    println("GellaKaza air conditioner on after shutdown: ${gellaKazaAirConditioner.isOn()}")
    println("VentoBaumn temperature after shutdown: ${ventoBaumnAirConditioner.getTemperature()}")
    println("GellaKaza temperature after shutdown: ${gellaKazaAirConditioner.getTemperature()}")
}
