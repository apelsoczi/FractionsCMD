import model.Command
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import usecase.CalculationFunction
import usecase.ErrorFunction
import usecase.ProcessInputFunction
import usecase.ShutdownFunction


class CalculatorTest {

    private val numberSerializer = Mockito.mock<NumberSerializer>()
    private val processInputFunction = Mockito.mock<ProcessInputFunction>()
    private val calculationFunction = Mockito.mock<CalculationFunction>()
    private val errorFunction = Mockito.mock<ErrorFunction>()
    private val shutdownFunction = Mockito.mock<ShutdownFunction>()

    private lateinit var calculator: Calculator

    @BeforeEach
    fun setup() {
        calculator = Calculator(
            numberSerializer = numberSerializer,
            processInputFunction = processInputFunction,
            calculationFunction = calculationFunction,
            errorFunction = errorFunction,
            shutdownFunction = shutdownFunction,
        )
    }

    @Test
    fun `process addition of fractions`() {
        // given
        val firstFraction = "1&2/3"
        val firstMixedNumber = MixedNumber(1, 2, 3)
        val secondFraction = "6&7/8"
        val secondMixedNumber = MixedNumber(6,7,8)
        Mockito.`when`(processInputFunction.invoke())
            .thenReturn(Command.Calculation(firstFraction, "+", secondFraction))
            .thenReturn(Command.ExitProcess)
        Mockito.`when`(numberSerializer.deserialize(firstFraction)).thenReturn(firstMixedNumber)
        Mockito.`when`(numberSerializer.deserialize(secondFraction)).thenReturn(secondMixedNumber)
        // when
        calculator.process()
        // then
        verify(processInputFunction, times(2)).invoke()
        verify(numberSerializer).deserialize(firstFraction)
        verify(numberSerializer).deserialize(secondFraction)
        verify(calculationFunction).add(firstMixedNumber, secondMixedNumber)
    }

    @Test
    fun `process subtraction of fractions`() {
        // given
        val firstFraction = "1&2/3"
        val firstMixedNumber = MixedNumber(1, 2, 3)
        val secondFraction = "6&7/8"
        val secondMixedNumber = MixedNumber(6,7,8)
        Mockito.`when`(processInputFunction.invoke())
            .thenReturn(Command.Calculation(firstFraction, "-", secondFraction))
            .thenReturn(Command.ExitProcess)
        Mockito.`when`(numberSerializer.deserialize(firstFraction)).thenReturn(firstMixedNumber)
        Mockito.`when`(numberSerializer.deserialize(secondFraction)).thenReturn(secondMixedNumber)
        // when
        calculator.process()
        // then
        verify(processInputFunction, times(2)).invoke()
        verify(numberSerializer).deserialize(firstFraction)
        verify(numberSerializer).deserialize(secondFraction)
        verify(calculationFunction).subtract(firstMixedNumber, secondMixedNumber)
    }

    @Test
    fun `process multiplication of fractions`() {
        // given
        val firstFraction = "1&2/3"
        val firstMixedNumber = MixedNumber(1, 2, 3)
        val secondFraction = "6&7/8"
        val secondMixedNumber = MixedNumber(6,7,8)
        Mockito.`when`(processInputFunction.invoke())
            .thenReturn(Command.Calculation(firstFraction, "*", secondFraction))
            .thenReturn(Command.ExitProcess)
        Mockito.`when`(numberSerializer.deserialize(firstFraction)).thenReturn(firstMixedNumber)
        Mockito.`when`(numberSerializer.deserialize(secondFraction)).thenReturn(secondMixedNumber)
        // when
        calculator.process()
        // then
        verify(processInputFunction, times(2)).invoke()
        verify(numberSerializer).deserialize(firstFraction)
        verify(numberSerializer).deserialize(secondFraction)
        verify(calculationFunction).multiply(firstMixedNumber, secondMixedNumber)
    }

    @Test
    fun `process division of fractions`() {
        // given
        val firstFraction = "1&2/3"
        val firstMixedNumber = MixedNumber(1, 2, 3)
        val secondFraction = "6&7/8"
        val secondMixedNumber = MixedNumber(6,7,8)
        Mockito.`when`(processInputFunction.invoke())
            .thenReturn(Command.Calculation(firstFraction, "/", secondFraction))
            .thenReturn(Command.ExitProcess)
        Mockito.`when`(numberSerializer.deserialize(firstFraction)).thenReturn(firstMixedNumber)
        Mockito.`when`(numberSerializer.deserialize(secondFraction)).thenReturn(secondMixedNumber)
        // when
        calculator.process()
        // then
        verify(processInputFunction, times(2)).invoke()
        verify(numberSerializer).deserialize(firstFraction)
        verify(numberSerializer).deserialize(secondFraction)
        verify(calculationFunction).divide(firstMixedNumber, secondMixedNumber)
    }

    @Test
    fun `error processing input from user`() {
        // given
        val exception = CommandException.InvalidInputException("")
        Mockito.`when`(processInputFunction.invoke())
            .thenReturn(Command.Invalid(exception))
            .thenReturn(Command.ExitProcess)
        // when
        calculator.process()
        // then
        verify(processInputFunction, times(2)).invoke()
        verify(errorFunction).invoke(exception)
    }

    @Test
    fun `clear command receives input from user`() {
        // given
        Mockito.`when`(processInputFunction.invoke()).thenReturn(Command.ExitProcess)
        // when
        calculator.process()
        // then
        verify(processInputFunction).invoke()
        verify(shutdownFunction).invoke()
    }

}