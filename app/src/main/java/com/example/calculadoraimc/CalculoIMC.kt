package com.example.calculadoraimc
import kotlin.math.pow

fun calcularIMC(altura: Double, peso: Double): Double {
    return peso / (altura / 100).pow(2.0)
}
