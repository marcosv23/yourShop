package service;

public class Cpf {
    private static final int CPF_LENGTH = 11;
    private static final int ELEVEN_CONSTANT = 11;


    public boolean validate(String rawCPF) {
        if (rawCPF == null) return false;
        String cpf = unmaskCPF(rawCPF);
        return cpf.length() == CPF_LENGTH && !hasAllSameDigits(cpf);
    }

    public boolean validateLength(String rawCPF) {
        return rawCPF.length() == CPF_LENGTH;
    }

    public String unmaskCPF(String rawCPF) {
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
        var cpf = unmaskCPF(rawCpf);
        var total = 0;
        for (char digit : cpf.toCharArray()) {
            if (factor > 1) total += getIntFromString(digit) * factor--;
        }
        var divisionRest = total % 11;
        return divisionRest < 2 ? 0 : 11 - divisionRest;
    }
}
