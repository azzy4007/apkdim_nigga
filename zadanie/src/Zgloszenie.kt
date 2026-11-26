class Zgloszenie(
    val id: Int,
    val klient: String,
    val telefon: String,
    val sprzet: String,
    val model: String,
    val usterka: String,
    var status: Status = Status.PRZYJETE,
    var koszt: Double
)