import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {

    @DisplayName("Test functions on lists")
    @Nested
    inner class UtilFunctions {

        @Test
        internal fun canTakeHeadOfNoneEmptyList() {
            assertThat(listOf(1,2,3,4,5).head(), `is`(1))

            assertThat(listOf(1,2).head(), `is`(1))
        }

        @Test
        internal fun headOfEmptyListThrowsException() {
            assertThrows<NoSuchElementException> { emptyList<Any>().head() }
        }

        @Test
        internal fun canTakeTailOfNoneEmptyList() {
            assertThat(listOf(1,2,3,4,5).tail(), `is`(listOf(2,3,4,5)))

            assertThat(listOf(1,2).tail(), `is`(listOf(2)))
        }

        @Test
        internal fun tailOfEmptyListIsEmptyList() {
            assertThat(emptyList<Any>().tail(), `is`(emptyList()))
        }

        @Test
        internal fun canAppendAnyElementToAList() {
            assertThat(1 append listOf(2,3,4,5), `is`(listOf(1,2,3,4,5)))

            assertThat('a' append listOf(2,3,4,5), `is`(listOf('a', 2,3,4,5)))
        }

        @Test
        internal fun canTransposeListOfListOfAnyLength() {
            assertThat(listOf(listOf(1,2), listOf(3,4)).transpose(),
                `is`(listOf(listOf(1,3), listOf(2,4))))

            assertThat(listOf(listOf(1,2), listOf(3)).transpose(),
                `is`(listOf(listOf(1,3), listOf(2))))

            assertThat(listOf(listOf(1,2)).transpose(),
                `is`(listOf(listOf(1), listOf(2))))

            assertThat(listOf(listOf(1,2,3), listOf(4,5)).transpose(),
                `is`(listOf(listOf(1,4), listOf(2,5), listOf(3))))
        }
    }
}