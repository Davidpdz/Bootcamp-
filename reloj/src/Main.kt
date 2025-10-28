import java.util.Arrays

class Reloj {
    private var horas: Int = 12
    private var minutos: Int = 0
    private var segundos: Int = 0

    // Constructor por defecto
    constructor()

    // Constructor con horas, minutos y segundos
    constructor(h: Int, m: Int, s: Int) {
        setHoras(h)
        setMinutos(m)
        setSegundos(s)
    }

    // Constructor con segundos desde la medianoche
    constructor(segundosDesdeMedianoche: Int) {
        setReloj(segundosDesdeMedianoche)
    }

    // Métodos set
    fun setHoras(h: Int) {
        if (h in 0..23) horas = h
    }

    fun setMinutos(m: Int) {
        if (m in 0..59) minutos = m
    }

    fun setSegundos(s: Int) {
        if (s in 0..59) segundos = s
    }

    fun setReloj(segundosDesdeMedianoche: Int) {
        val total = ((segundosDesdeMedianoche % 86400) + 86400) % 86400
        horas = total / 3600
        minutos = (total % 3600) / 60
        segundos = total % 60
    }

    // Métodos get
    fun getHoras(): Int = horas
    fun getMinutos(): Int = minutos
    fun getSegundos(): Int = segundos

    // Método toString
    override fun toString(): String {
        return "[%02d:%02d:%02d]".format(horas, minutos, segundos)
    }

    // Método tick (incrementar 1 segundo)
    fun tick() {
        setReloj(getTotalSegundos() + 1)
    }

    // Método tickDecrement (decrementar 1 segundo)
    fun tickDecrement() {
        setReloj(getTotalSegundos() - 1)
    }

    // Método addReloj (sumar otro reloj)
    fun addReloj(otro: Reloj) {
        val suma = this.getTotalSegundos() + otro.getTotalSegundos()
        setReloj(suma)
    }

    // Método restaReloj (restar otro reloj, retorna nuevo Reloj)
    fun restaReloj(otro: Reloj): Reloj {
        val resta = this.getTotalSegundos() - otro.getTotalSegundos()
        return Reloj(resta)
    }

    // Método privado para obtener el total de segundos desde medianoche
    private fun getTotalSegundos(): Int {
        return horas * 3600 + minutos * 60 + segundos
    }
}


