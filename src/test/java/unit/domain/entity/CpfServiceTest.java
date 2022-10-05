package unit.domain.entity;

import domain.entity.CpfService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfServiceTest {
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
    static void initTests() {
        cpfService = new CpfService();
    }


    @Test
    @DisplayName("Deve validar o tamanho do CPF")
    void shouldAssureLength() {
        assertTrue(cpfService.validateLength(CPF1));
    }

    @Test
    @DisplayName("Não deve validar um cpf com mais de 11 caracteres")
    void shouldAssureLengthIsLessThan11() {
        assertNotEquals(true, cpfService.validateLength(INVALID_LENGTH_CPF));
    }

    @Test
    @DisplayName("Deve remover caracteres especiais do CPF")
    void shouldCleanCPF() {
        assertEquals(CPF1, cpfService.cleanCpf(RAW_CPF));
    }

    @Test
    @DisplayName("Deve verificar se todos os d[igitos são iguais")
    void shouldVerifySameDigits() {
        assertTrue(cpfService.hasAllSameDigits("111111111"));
    }


    @Test
    @DisplayName("Não deve ter todos os dígitos repetidos")
    void shouldNotHasSameDigits() {
        assertFalse(cpfService.hasAllSameDigits("11111511"));
    }

    @Test
    @DisplayName("Deve calcular o primeiro dígito")
    void shouldCalcFirstDigit() {
        assertEquals(3, cpfService.calculateDigit(CPF1, 10));
        assertEquals(7, cpfService.calculateDigit(CPF2, 10));
    }

    @Test
    @DisplayName("Deve calcular o segundo dígito")
    void shouldCalcSecondDigit() {
        assertEquals(5, cpfService.calculateDigit(CPF1WithFirstDigitCalculated, 11));
        assertEquals(9, cpfService.calculateDigit(CPF2WithFirstDigitCalculated, 11));
    }

    @Test
    @DisplayName("Deve comparar os digitos")
    void shouldCompareDigits() {
        assertTrue(cpfService.compareDigits(3, 5, CORRECT_CPF1));
        assertTrue(cpfService.compareDigits(7, 9, CORRECT_CPF2));
    }
}
