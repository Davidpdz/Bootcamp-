import java.util.Random

fun main() {
    val rollDice = { Random().nextInt(6)}

    val diceRolls = mutableListOf<Int>()
    repeat(5) {
        diceRolls.add(rollDice())
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
        println("You Lost")
    }

}




