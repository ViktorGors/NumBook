import org.junit.Test

import org.junit.Assert.*

class NumBookTest {

    @Test
    fun addName() {
        val book = NumBook()
        assertTrue(book.addName("Иосиф Сталин"))
        assertTrue(book.addName("Васильев Дмитрий"))
        assertFalse(book.addName("Иосиф Сталин"))

    }





    @Test
    fun addNum() {
        val book = NumBook()
        assertTrue(book.addName("Ильич Ленин"))
        assertTrue(book.addName("Бобрышев Дмитрий"))
        assertTrue(book.addNum("Ильич Ленин", "+79212345678"))
        assertTrue(book.addNum("Ильич Ленин", "+78121234567"))
        assertFalse(book.addNum("Ильич Ленин", "+79212345678"))
        assertFalse(book.addNum("Бобрышев Дмитрий", "+79212345678"))
        assertTrue(book.addNum("Бобрышев Дмитрий", "+79217654321"))
        assertTrue(book.addNum("Ильич Ленин", "+7+9+123434567"))
        assertTrue(book.addNum("Ильич Ленин", "+792*1123#456-7"))
        //assertTrue(book.addNum("Ильич Ленин", "+792*1123=456-7"))
        //assertThrows(IllegalArgumentException::class.java) { book.addName("+7923211=1234") }
    }

    @Test
    fun delName() {
        val book = NumBook()
        assertTrue(book.addName("Ильич Ленин"))
        assertTrue(book.addName("Иосиф Виссарионович"))
        assertTrue(book.addName("Берия Лаврентий"))
        assertTrue(book.delName("Ильич Ленин"))
        assertFalse(book.delName("Троцкий Лев"))
    }

    @Test
    fun delNum() {
        val book = NumBook()
        assertTrue(book.addName("Ильич Ленин"))
        assertTrue(book.addName("Иосиф Виссарионович"))
        assertTrue(book.addNum("Ильич Ленин", "+79212121212"))
        assertTrue(book.addNum("Ильич Ленин", "+78187654321"))
        assertTrue(book.addNum("Иосиф Виссарионович", "+79222222222"))
        assertTrue(book.delNum("Ильич Ленин", "+78187654321"))
        assertFalse(book.delNum("Ильич Ленин", "+78187654321"))
        assertTrue(book.delNum("Иосиф Виссарионович", "+79222222222"))
    }

    @Test
    fun numbers() {
        val book = NumBook()
        assertTrue(book.addName("Ильич Ленин"))
        assertTrue(book.addName("Иосиф Виссарионович"))
        assertTrue(book.addNum("Ильич Ленин", "+79212345678"))
        assertTrue(book.addNum("Ильич Ленин", "+78187654321"))
        assertEquals(setOf("+79212345678", "+78187654321"), book.numbers("Ильич Ленин"))
    }

    @Test
    fun nameByNum() {
        val book = NumBook()
        assertTrue(book.addName("Ильич Ленин"))
        assertTrue(book.addName("Иосиф Виссарионович"))
        assertTrue(book.addNum("Ильич Ленин", "+79211110000"))
        assertTrue(book.addNum("Ильич Ленин", "+79276192492"))
        assertTrue(book.addNum("Иосиф Виссарионович", "+79217654321"))
        assertEquals("Иосиф Виссарионович", book.nameByNum("+79217654321"))
        assertEquals("Ильич Ленин", book.nameByNum("+79276192492"))
        assertNull(book.nameByNum("+79204030201"))
    }

    @Test
    fun testEquals() {
        val bookA = NumBook()
        assertTrue(bookA.addName("Иосиф Виссарионович"))
        assertTrue(bookA.addName("Ильич Ленин"))
        assertTrue(bookA.addNum("Ильич Ленин", "+79276192492"))
        assertTrue(bookA.addNum("Иосиф Виссарионович", "+79217654321"))
        assertTrue(bookA.addNum("Ильич Ленин", "+79212345678"))

        val bookB = NumBook()
        assertTrue(bookB.addName("Ильич Ленин"))
        assertTrue(bookB.addName("Иосиф Виссарионович"))
        assertTrue(bookB.addNum("Ильич Ленин", "+79212345678"))
        assertTrue(bookB.addNum("Ильич Ленин", "+79276192492"))
        assertTrue(bookB.addNum("Иосиф Виссарионович", "+79217654321"))
        assertTrue(bookB == bookA)

        val bookC = NumBook()
        assertTrue(bookC.addName("Иосиф Виссарионович"))
        assertTrue(bookC.addName("Ильич Ленин"))
        assertTrue(bookC.addNum("Ильич Ленин", "+79217654321"))
        assertTrue(bookC.addNum("Иосиф Виссарионович", "+79276192492"))
        assertTrue(bookC.addNum("Ильич Ленин", "+79212345678"))
        assertFalse(bookA == bookC)

        val bookD = NumBook()
        assertTrue(bookD.addName("Иосиф Виссарионович"))
        assertTrue(bookD.addName("Ильич Ленин"))
        assertFalse(bookC == bookD)
    }

    @Test
    fun testHashCode() {
        val bookA = NumBook()
        assertTrue(bookA.addName("Ильич Ленин")) //1
        assertTrue(bookA.addName("Иосиф Виссарионович")) //3
        assertTrue(bookA.addNum("Ильич Ленин", "+79212345678")) //5
        assertTrue(bookA.addNum("Ильич Ленин", "+79276192492")) //6
        assertTrue(bookA.addNum("Ильич Ленин", "+78187654321")) //2
        assertTrue(bookA.addNum("Иосиф Виссарионович", "+79217654321")) //4

        val bookB = NumBook()
        assertTrue(bookB.addName("Иосиф Виссарионович")) //3
        assertTrue(bookB.addName("Ильич Ленин")) //1
        assertTrue(bookB.addNum("Ильич Ленин", "+78187654321")) //2
        assertTrue(bookB.addNum("Иосиф Виссарионович", "+79217654321")) //4
        assertTrue(bookB.addNum("Ильич Ленин", "+79212345678")) //5
        assertTrue(bookB.addNum("Ильич Ленин", "+79276192492")) //6
        assertTrue(bookA.hashCode() == bookB.hashCode())

        val bookC = NumBook()
        assertTrue(bookC.addName("Иосиф Виссарионович"))
        assertTrue(bookC.addName("Ильич Ленин"))
        assertTrue(bookC.addNum("Ильич Ленин", "+79217654321"))
        assertTrue(bookC.addNum("Иосиф Виссарионович", "+78187654321"))
        assertTrue(bookC.addNum("Ильич Ленин", "+79212345678"))
        assertFalse(bookA.hashCode() == bookC.hashCode())

        val bookD = NumBook()
        assertTrue(bookD.addName("Иосиф Виссарионович"))
        assertTrue(bookD.addName("Ильич Ленин"))
        assertTrue(bookD.addName("Троцкий Лев"))
        assertTrue(bookD.addNum("Ильич Ленин", "+79276192492"))
        assertTrue(bookD.addNum("Троцкий Лев", "+79206669990"))
        assertFalse(bookC.hashCode() == bookD.hashCode())
    }

    }