class RelojDemo {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            print("Ingrese  los segundos desde la medianoche")
            val entrada = readLine()?.toIntOrNull()
            if ((entrada == null) || (entrada < 0)) {
                println("Entrada invalida.")
                return
            }
            val reloj1 = Reloj(entrada)
            println("Hora inicial:${reloj1}")

            for (i in 1 .. 10) {
                reloj1.tick()
                println("Tick $i :$reloj1")
            }

            val reloj2 = Reloj(1,0,0)
            println("Segundo reloj: $reloj2")

            val diferencia = reloj1. restaReloj(reloj2)
            println("Diferencia entre reloj1 y reloj2: ${diferencia}")
        }
    }
}