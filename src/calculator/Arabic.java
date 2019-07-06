package calculator;

public interface Arabic {

    default boolean checkArabic(String operands[]) {
        int check = 0;
        for (String operand : operands) {
            switch (operand.length()) {
                case 1:
                    check += Character.isDigit(operand.charAt(0)) ? 1 : 0;
                    break;
                case 2:
                    check += operand.equals("10") ? 1 : 0;
                    break;
            }
        }
        return check == 2;
    }

    default int parseArabic(String operand) {
        return Integer.parseInt(operand);
    }
}
