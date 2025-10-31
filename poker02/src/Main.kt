
import kotlin.random.Random
open class Carta(val valor: String, val palo: String) {
    override fun toString(): String = "$valor de $palo"
}
fun crearBaraja(): MutableList<Carta> {
    val palos = listOf("Corazones", "Diamantes", "Treboles", "Picas")
    val valores = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
    val baraja = mutableListOf<Carta>()
    for (palo in palos) {
        for (valor in valores) {
            baraja.add(Carta(valor, palo))
        }
    }
    baraja.shuffle(Random(System.currentTimeMillis()))
    return baraja
}
fun repartirMano(baraja: MutableList<Carta>): Array<Carta> {
    return Array(5) { baraja.removeAt(0) }
}
fun valorNumerico(carta: Carta): Int {
    return when (carta.valor) {
        "A" -> 14
        "K" -> 13
        "Q" -> 12
        "J" -> 11
        else -> carta.valor.toInt()
    }
}
fun evaluarMano(mano: Array<Carta>): String {
    val valores = mano.map { valorNumerico(it) }.sorted()
    val palos = mano.map { it.palo }
    val esColor = palos.distinct().size == 1
    val esEscalera = valores.zipWithNext().all { (a, b) -> b == a + 1 }
    val grupos = valores.groupingBy { it }.eachCount()
    val cantidades = grupos.values.sortedDescending()
    val esGenerala = valores.distinct().size == 1
    return when {
        esEscalera && esColor && valores.last() == 14 -> "Escalera Real"
        esEscalera && esColor -> "Escalera de Color"
        cantidades == listOf(4, 1) -> "Poker"
        cantidades == listOf(3, 2) -> "Full"
        esColor -> "Color"
        esEscalera -> "Escalera"
        cantidades == listOf(3, 1, 1) -> "Trio"
        cantidades == listOf(2, 2, 1) -> "Doble Par"
        cantidades == listOf(2, 1, 1, 1) -> "Par"
        esGenerala -> "Generala"


        else -> "Carta Alta"
    }
}
fun cartaMasAlta(mano: Array<Carta>): Carta {
    return mano.maxByOrNull { valorNumerico(it) }!!
}
fun main() {
    val baraja = crearBaraja()
    val jugador = repartirMano(baraja)
    val computadora = repartirMano(baraja)
    println("Tus cartas:")
    jugador.forEach { println(it) }
    println("\nCartas de la computadora:")
    computadora.forEach { println(it) }
    val jugadaJugador = evaluarMano(jugador)
    val jugadaComputadora = evaluarMano(computadora)
    println("\nTu jugada: $jugadaJugador")
    println("Jugada de la computadora: $jugadaComputadora")
    val ranking = listOf(
        "Carta Alta", "Par", "Doble Par", "Trio", "Escalera",
        "Color", "Full", "Poker","Generala", "Escalera de Color", "Escalera Real"
    )
    val indiceJugador = ranking.indexOf(jugadaJugador)
    val indiceComputadora = ranking.indexOf(jugadaComputadora)
    val ganador = when {
        indiceJugador > indiceComputadora -> "Ganaste"
        indiceJugador < indiceComputadora -> "Perdiste"
        else -> {
            val altaJugador = cartaMasAlta(jugador)
            val altaComputadora = cartaMasAlta(computadora)
            val valorJugador = valorNumerico(altaJugador)
            val valorComputadora = valorNumerico(altaComputadora)
            when {
                valorJugador > valorComputadora -> "Ganaste por carta mas alta: ${altaJugador} vs ${altaComputadora}"
                valorJugador < valorComputadora -> "Perdiste por carta mas alta: ${altaJugador} vs ${altaComputadora}"
                else -> "Empate absoluto con la misma carta alta: ${altaJugador}"
            }
        }
    }
    println("\nResultado: $ganador")
}