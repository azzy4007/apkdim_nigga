fun main() {
    val zgloszenia = mutableListOf<Zgloszenie>()
    var nastepneId = 1
    var dziala = true

    while (dziala) {
        println("\n=== SERWIS ===")
        println("1. Dodaj zgłoszenie")
        println("2. Wyświetl zgłoszenia")
        println("3. Wyszukaj zgłoszenie")
        println("4. Zmień status")
        println("5. Wpisz koszt")
        println("0. Wyjdź")
        print("Wybierz opcję: ")

        when (readLine()?.trim()) {
            "1" -> {
                print("Klient: ")
                val klient = readLine() ?: ""
                print("Telefon: ")
                val telefon = readLine() ?: ""
                print("Sprzęt: ")
                val sprzet = readLine() ?: ""
                print("Model: ")
                val model = readLine() ?: ""
                print("Usterka: ")
                val usterka = readLine() ?: ""

                if (klient.isBlank() || telefon.isBlank() || sprzet.isBlank() || model.isBlank() || usterka.isBlank()) {
                    println("Błąd: Żadne pole nie może być puste!")
                } else {
                    zgloszenia.add(Zgloszenie(nastepneId, klient, telefon, sprzet, model, usterka))
                    println("Dodano! Twoje ID to: $nastepneId")
                    nastepneId++
                }
            }
            "2" -> {
                if (zgloszenia.isEmpty()) {
                    println("Brak zgłoszeń.")
                } else {
                    for (z in zgloszenia) {
                        val k = if (z.koszt == null) "nie ustalono" else "${z.koszt} zł"
                        println("ID: ${z.id} | ${z.klient} | ${z.sprzet} | ${z.status} | Koszt: $k")
                        println("   Usterka: ${z.usterka}")
                    }
                }
            }
            "3" -> {
                print("Szukaj (ID lub nazwisko): ")
                val szukaj = readLine()?.trim()?.lowercase() ?: ""
                val wynik = zgloszenia.filter { it.id.toString() == szukaj || it.klient.lowercase().contains(szukaj) }

                if (wynik.isEmpty()) {
                    println("Nic nie znaleziono.")
                } else {
                    for (z in wynik) {
                        println("ID: ${z.id} | ${z.klient} | ${z.sprzet} | Status: ${z.status}")
                    }
                }
            }
            "4" -> {
                print("Podaj ID zgłoszenia: ")
                val id = readLine()?.toIntOrNull()
                val z = zgloszenia.find { it.id == id }
                if (z == null) {
                    println("Nie ma takiego ID!")
                } else {
                    println("Wybierz status: 1. PRZYJETE, 2. W_NAPRAWIE, 3. GOTOWE")
                    when (readLine()?.trim()) {
                        "1" -> z.status = Status.PRZYJETE
                        "2" -> z.status = Status.W_NAPRAWIE
                        "3" -> z.status = Status.GOTOWE_DO_ODBIORU
                        else -> println("Nie ma takiego statusu!")
                    }
                }
            }
            "5" -> {
                print("Podaj ID zgłoszenia: ")
                val id = readLine()?.toIntOrNull()
                val z = zgloszenia.find { it.id == id }
                if (z == null) {
                    println("Nie ma takiego ID!")
                } else {
                    print("Podaj koszt (lub wpisz 'brak'): ")
                    val w = readLine()?.trim()?.replace(",", ".")
                    if (w == "brak") {
                        z.koszt = null
                        println("Koszt usunięty.")
                    } else {
                        val kwota = w?.toDoubleOrNull()
                        if (kwota == null || kwota < 0) {
                            println("Błąd: Niepoprawna kwota!")
                        } else {
                            z.koszt = kwota
                            println("Zapisano koszt: $kwota zł")
                        }
                    }
                }
            }
            "0" -> {
                println("Koniec programu.")
                dziala = false
            }
            else -> println("Nieznana opcja!")
        }
    }
}