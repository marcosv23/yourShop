package unit.domain.entity;

import domain.entity.CpfService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CpfServiceTest {
    private static final String RAW_CPF = "111.444.777-05";
    private static final String INVALID_LENGTH_CPF = "415215515911";
    private static final String CPF1 = "11144477705";
    private static final String CPF1WithFirstDigitCalculated = "1114447773";
    private static final String CORRECT_CPF1 = "11144477735";
    private static final String CPF2 = "46679181855";
    private static final String CPF2WithFirstDigitCalculated = "4667918187";
    private static final String CORRECT_CPF2 = "46679181879";
    private static CpfService cpfService;

    @BeforeAll
    public static void initTests() {
        cpfService = new CpfService();
    }

    @Test
    public void shouldAssureGetIntFromString() {
        assertEquals(1, cpfService.getIntFromString('1'));
    }


    @Test
    public void shouldAssureLength() {
        assertTrue(cpfService.validateLength(CPF1));
    }

    @Test
    public void shouldAssureLengthIsLessThan11() {
        assertNotEquals(true, cpfService.validateLength(INVALID_LENGTH_CPF));
    }

    @Test
    public void shouldCleanCPF() {
        assertEquals(CPF1, cpfService.cleanCpf(RAW_CPF));
    }

    @Test
    public void shouldVerifySameDigits() {
        assertTrue(cpfService.hasAllSameDigits("111111111"));
    }


    @Test
    public void shouldNotHasSameDigits() {
        assertFalse(cpfService.hasAllSameDigits("11111511"));
    }

    @Test
    public void shouldCalcFirstDigit() {
        assertEquals(3, cpfService.calculateDigit(CPF1, 10));
        assertEquals(7, cpfService.calculateDigit(CPF2, 10));
    }

    @Test
    public void shouldCalcSecondDigit() {
        assertEquals(5, cpfService.calculateDigit(CPF1WithFirstDigitCalculated, 11));
        assertEquals(9, cpfService.calculateDigit(CPF2WithFirstDigitCalculated, 11));
    }

    @Test
    public void shouldCompareDigits() {
        assertTrue(cpfService.compareDigits(3, 5, CORRECT_CPF1));
        assertTrue(cpfService.compareDigits(7, 9, CORRECT_CPF2));
    }
}
