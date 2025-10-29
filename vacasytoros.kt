import kotlin.random.Random

fun main() {
    val secretNumber = generateSecretNumber()


    println("¡Bienvenido al juego de Toros y Vacas!")
    println("Intenta adivinar el número de 4 dígitos. Los dígitos no se repiten.")

    while (true) {
        print("Ingrese su intento: ")
        val userInput = readLine() ?: ""


        if (!isValidInput(userInput)) {
            println("Entrada inválida. Asegúrese de ingresar 4 dígitos sin repetir.")
            continue
        }

        val (bulls, cows) = calculateBullsAndCows(secretNumber, userInput)

        if (bulls == 4) {
            println("¡Felicidades! El número secreto era: $secretNumber")
            break
        } else {
            println("$cows vacas, $bulls toros.")
        }
    }
}


fun generateSecretNumber(): String {
    val digits = (0..9).toMutableList()
    digits.shuffle()

    if (digits[0] == 0) {
        val nonZeroIndex = digits.indexOfFirst { it != 0 }
        digits[0] = digits[nonZeroIndex].also { digits[nonZeroIndex] = digits[0] }
    }
    return digits.take(4).joinToString("")
}


fun isValidInput(input: String): Boolean {
    if (input.length != 4) return false
    if (!input.all { it.isDigit() }) return false
    if (input.toSet().size != 4) return false
    return true
}


fun calculateBullsAndCows(secret: String, guess: String): Pair<Int, Int> {
    var bulls = 0
    var cows = 0

    for (i in 0..3) {
        if (guess[i] == secret[i]) {
            bulls++
        } else if (guess[i] in secret) {
            cows++
        }
    }

    return Pair(bulls, cows)
}
