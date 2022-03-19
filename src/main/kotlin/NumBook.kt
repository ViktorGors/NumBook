
class NumBook {

    var NumBook = HashMap<String, MutableSet<String>>()

    fun СorrectIncorrectNum(num: String) {
        if (!num.matches(Regex("[0-9+*\\-#]+"))) throw IllegalArgumentException("Incorrect number! Please check the data entry.")
    }
    fun СorrectIncorrectName(name: String) {
        if (!name.matches(Regex("[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+"))) throw IllegalArgumentException("Incorrect name! Please check the data entry.")
    }

    // Добавить имя.
    // true - имя добавлено.
    // false - такое имя уже сеть.

    fun addName(name: String): Boolean {
        СorrectIncorrectName(name) // проверочка
        return if (NumBook.containsKey(name))
            false
        else {
            NumBook[name] = mutableSetOf()
            true
        }
    }

    // Добавить номер.
    // true - номер был добавлен.
    // false - такой номер уже существует || человек с таким именем отсутствовал || у него уже был такой номер.

    fun addNum(name: String, num: String): Boolean {

        СorrectIncorrectNum(num) // проверочка
        СorrectIncorrectName(name) // проверочка

        if (!NumBook.containsKey(name)) return false
        val Number = NumBook[name]
        if (Number!!.contains(num)) return false //проверка на наличие номера в телефонной книге
        for (personNum in NumBook.values) { //проверка на наличие этого номера у других людей
            if (personNum.contains(num)) return false
        }
        Number.add(num)
        return true
    }

    // Удалить имя.
    // true - имя удалено.
    // false - человека с таким именем не существует

    fun delName(name: String): Boolean {
        СorrectIncorrectName(name) // проверочка
        return if (!NumBook.containsKey(name)) false
        else {
            NumBook.remove(name)
            true
        }
    }

    // Удалить номер.
    // true - номер удалён.
    // false - человек с таким именем не существует (либо у него не было такого номера).

    fun delNum(name: String, num: String): Boolean {
        СorrectIncorrectNum(num) // проверочка
        СorrectIncorrectName(name) // проверочка
        if (!NumBook.containsKey(name)) return false
        val phones = NumBook[name]
        if (!phones!!.contains(num)) return false
        phones.remove(num)
        return true
    }

    // Вернуть все номера телефона заданного по имени человека.
    // В ином случае, вернуть пустой список

    fun numbers(name: String): Set<String> {
        СorrectIncorrectName(name) // проверочка
        return NumBook[name] ?: setOf() //пустое множество при null
    }

    // Вернуть имя по номеру.
    // В ином случае, вернуть null.

    fun nameByNum(num: String): String? {
        СorrectIncorrectNum(num) // проверочка
        for ((name, phones) in NumBook) {
            if (phones.contains(num)) return name
        }
        return null
    }

    // Две книги равны, если в них находится одинаковый набор имен,
    // одинаковый набор телефонов. Порядок в книге не иметь значения.

    override fun equals(other: Any?): Boolean {
        if (other is NumBook) {
            if (other.NumBook.size != this.NumBook.size) return false
            for ((name, num) in NumBook) {
                if (other.NumBook[name] != num) return false
            }
        }
        return true
    }

    override fun hashCode(): Int = NumBook.hashCode()

}


