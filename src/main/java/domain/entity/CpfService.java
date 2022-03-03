package domain.entity;

import jakarta.validation.constraints.NotNull;

public class CpfService {
    private static final int CPF_LENGTH = 11;
    private static final int ELEVEN_CONSTANT = 11;


    public boolean validate(@NotNull String rawCPF) {
        String cpf = cleanCpf(rawCPF);
        if (cpf.length() != CPF_LENGTH || hasAllSameDigits(cpf)) return false;
        var firstDigit = calculateDigit(rawCPF, 10);
        var secondDigit = calculateDigit(rawCPF, 11);
        return compareDigits(firstDigit, secondDigit, cpf);
    }

    public boolean validateLength(String rawCPF) {
        return rawCPF.length() == CPF_LENGTH;
    }

    public boolean compareDigits(int dg1, int dg2, String cpf) {
        var digit1 = String.valueOf(dg1);
        var digit2 = String.valueOf(dg2);
        var verifierDigits = digit1.concat(digit2);
        return cpf.substring(9, 11).equals(verifierDigits);
    }

    public String cleanCpf(String rawCPF) {
        return rawCPF.replace(".", "").replace("-", "");
    }

    public boolean hasAllSameDigits(String cpf) {
        var counter = 0;
        for (int i = 0; i < cpf.length(); i++) {
            char firstDigit = cpf.charAt(0);
            char comparingDigit = cpf.charAt(i);
            if (comparingDigit == firstDigit && i != 0) counter++;
        }
        return counter == cpf.length() - 1;
    }

    public int getIntFromString(char c) {
        return Character.getNumericValue(c);
    }

    public int calculateDigit(String rawCpf, int factor) {
        var cpf = cleanCpf(rawCpf);
        var total = 0;
        for (char digit : cpf.toCharArray()) {
            if (factor > 1) total += getIntFromString(digit) * factor--;
        }
        var divisionRest = total % 11;
        return divisionRest < 2 ? 0 : 11 - divisionRest;
    }
}
