package calculator;

interface Roman {

    default boolean checkRoman(String[] operands) {
        for (String operand : operands) {
            for (RomanNumerals numeral : RomanNumerals.values()) {
                if (!numeral.name().equals(operand)) {
                    return false;
                }
            }
        }
        return operands.length == 2;
    }

    default int parseRoman(String operand) {
        return RomanNumerals.valueOf(operand).integer;
    }

    default String resultToRoman(Object resultInt) {
        String result = String.valueOf(resultInt);
        switch (result.length()) {
            case 1:
                result = digitToRoman((int) resultInt);
                break;
            case 2:
                if (result.charAt(0) == '-') {
                    result = null;
                } else {
                    int digit1 = Character.getNumericValue(result.charAt(0));
                    int digit2 = Character.getNumericValue(result.charAt(1));
                    result = concat(digitToRoman(digit1), digitToRoman(digit2));
                }

                break;
            case 3:
                result = "C";
                break;
        }
        return result;
    }

    static String digitToRoman(int digit) {
        for (RomanNumerals numeral : RomanNumerals.values()) {
            if (numeral.integer == digit) {
                return numeral.name();
            }
        }
        return null;
    }

    static String concat(String digit1, String digit2) {
        digit1 = digit1.replace('X', 'C');
        digit1 = digit1.replace('V', 'L');
        digit1 = digit1.replace('I', 'X');
        digit2 = (digit2 == null) ? "" : digit2;
        return digit1.concat(digit2);
    }
}
