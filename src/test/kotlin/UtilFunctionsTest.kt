import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Test util functions on lists")
class UtilFunctionsTest {

    @Test
    internal fun canTakeHeadOfNoneEmptyList() {
        assertThat(
            listOf(0, 1, 2, 3, 4, 5).head(),
            `is`(0)
        )

        assertThat(
            listOf(1, 2).head(),
            `is`(1)
        )
    }

    @Test
    internal fun headOfEmptyListThrowsException() {
        assertThrows<NoSuchElementException> { emptyList<Any>().head() }
    }

    @Test
    internal fun canTakeTailOfNoneEmptyList() {
        assertThat(
            listOf(0, 1, 2, 3, 4, 5).tail(),
            `is`(listOf(1, 2, 3, 4, 5))
        )

        assertThat(
            listOf(1, 2).tail(),
            `is`(listOf(2))
        )
    }

    @Test
    internal fun tailOfEmptyListIsEmptyList() {
        assertThat(
            emptyList<Any>().tail(),
            `is`(emptyList())
        )
    }

    @Test
    internal fun canAppendAnyElementToAList() {
        assertThat(
            0 append listOf(1, 2, 3, 4, 5),
            `is`(listOf(0, 1, 2, 3, 4, 5))
        )

        assertThat(
            'a' append listOf(2, 3, 4, 5),
            `is`(listOf('a', 2, 3, 4, 5))
        )
    }

    @Test
    internal fun canTransposeListOfListOfAnyLength() {
        assertThat(
            listOf(listOf(0, 1), listOf(2, 3)).transpose(),
            `is`(listOf(listOf(0, 2), listOf(1, 3)))
        )

        assertThat(
            listOf(listOf(0, 1), listOf(2)).transpose(),
            `is`(listOf(listOf(0, 2), listOf(1)))
        )

        assertThat(
            listOf(listOf(1, 2)).transpose(),
            `is`(listOf(listOf(1), listOf(2)))
        )

        assertThat(
            listOf(listOf(1, 2, 3), listOf(4, 5)).transpose(),
            `is`(listOf(listOf(1, 4), listOf(2, 5), listOf(3)))
        )

        assertThat(
            listOf(listOf(0, 1), listOf(), listOf(3)).transpose(),
            `is`(listOf(listOf(0, 3), listOf(1)))
        )
    }
}