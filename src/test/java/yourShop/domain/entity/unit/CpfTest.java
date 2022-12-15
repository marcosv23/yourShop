package yourShop.domain.entity.unit;

import domain.entity.order.Cpf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfTest {
    private static final String RAW_CPF = "111.444.777-05";
    private static final String INVALID_LENGTH_CPF = "415215515911";
    private static final String CPF1 = "11144477705";
    private static final String CPF1WithFirstDigitCalculated = "1114447773";
    private static final String CORRECT_CPF1 = "11144477735";
    private static final String CPF2 = "46679181855";
    private static final String CPF2WithFirstDigitCalculated = "4667918187";
    private static final String CORRECT_CPF2 = "46679181879";
    private static Cpf cpf;

    @BeforeAll
    static void initTests() {
        cpf = new Cpf();
    }


    @Test
    @DisplayName("Deve validar o tamanho do CPF")
    void shouldAssureLength() {
        assertTrue(cpf.validateLength(CPF1));
    }

    @Test
    @DisplayName("Não deve validar um cpf com mais de 11 caracteres")
    void shouldAssureLengthIsLessThan11() {
        assertNotEquals(true, cpf.validateLength(INVALID_LENGTH_CPF));
    }

    @Test
    @DisplayName("Deve remover caracteres especiais do CPF")
    void shouldCleanCPF() {
        assertEquals(CPF1, cpf.cleanCpf(RAW_CPF));
    }

    @Test
    @DisplayName("Deve verificar se todos os d[igitos são iguais")
    void shouldVerifySameDigits() {
        assertTrue(cpf.hasAllSameDigits("111111111"));
    }


    @Test
    @DisplayName("Não deve ter todos os dígitos repetidos")
    void shouldNotHasSameDigits() {
        assertFalse(cpf.hasAllSameDigits("11111511"));
    }

    @Test
    @DisplayName("Deve calcular o primeiro dígito")
    void shouldCalcFirstDigit() {
        assertEquals(3, cpf.calculateDigit(CPF1, 10));
        assertEquals(7, cpf.calculateDigit(CPF2, 10));
    }

    @Test
    @DisplayName("Deve calcular o segundo dígito")
    void shouldCalcSecondDigit() {
        assertEquals(5, cpf.calculateDigit(CPF1WithFirstDigitCalculated, 11));
        assertEquals(9, cpf.calculateDigit(CPF2WithFirstDigitCalculated, 11));
    }

    @Test
    @DisplayName("Deve comparar os digitos")
    void shouldCompareDigits() {
        assertTrue(cpf.compareDigits(3, 5, CORRECT_CPF1));
        assertTrue(cpf.compareDigits(7, 9, CORRECT_CPF2));
    }
}
