package service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CPFTest {
    private static final Integer CPF_LENGTH = 11;
    private static final String RAW_CPF = "415.215.515-79";
    private static final String INVALID_LENGTH_CPF = "415215515911";
    private static final String ANY_CPF = "41521551579";
    private Cpf cpfService;

    @Before
    public void init() {
        cpfService = new Cpf();
    }

    @Test
    public void assureGetIntFromString() {
        assertEquals(1, cpfService.getIntFromString('1'));
    }


    @Test
    public void assureLength() {
        assertTrue(cpfService.validateLength(ANY_CPF));
    }

    @Test
    public void assureLengthIsLessThan11() {
        assertNotEquals(true, cpfService.validateLength(INVALID_LENGTH_CPF));
    }

    @Test
    public void ensureUnmask() {
        assertEquals(ANY_CPF, cpfService.unmaskCPF(RAW_CPF));
    }

    @Test
    public void verifySameDigits() {
        assertTrue(cpfService.hasAllSameDigits("111111111"));
    }


    @Test
    public void VerifyDoesNotHasSameDigits() {
        assertFalse(cpfService.hasAllSameDigits("11111511"));
    }

    @Test
    public void calcFirstDigit() {
        assertEquals(7, cpfService.calculateDigit("46679181879", 10));
    }

    @Test
    public void calcSecondDigit() {
        assertEquals(9, cpfService.calculateDigit("466791818797", 11));
    }
}
