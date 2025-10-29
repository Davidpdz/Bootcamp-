import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val diceRolls = mutableListOf<Int>()

    println("Enter 5 dice rolls (values between 1 and 6):")

    repeat(5) {
        var input: Int
        do {
            println("Enter a roll (1-6):")
            input = scanner.nextInt()
        } while (input !in 1..6)
        diceRolls.add(input)
    }

    println("Dice rolls: $diceRolls")

    var isLadderFound = false
    var isFullHouseFound = false
    var isGeneralaFound = false
    var isPokerFound = false

    if (diceRolls.sorted().zipWithNext { a, b -> b - a == 1 }.all { it }) {
        println("It's a ladder")
        isLadderFound = true
    }


    if (diceRolls.groupBy { it }.values.map { it.size }.sorted() == listOf(2, 3)) {
        println("It's a full house")
        isFullHouseFound = true
    }


    if (diceRolls.distinct().size == 1) {
        println("It's Generala")
        isGeneralaFound = true
    }


    if (diceRolls.groupBy { it }.values.any { it.size == 4 }) {
        println("It's Poker")
        isPokerFound = true
    }


    if (!isLadderFound && !isFullHouseFound && !isGeneralaFound && !isPokerFound) {
        println("NADA")
    }
}









