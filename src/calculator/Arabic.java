package calculator;

public interface Arabic {

    default boolean checkArabic(String operand) {
        switch (operand.length()) {
            case 1:
                return Character.isDigit(operand.charAt(0));
            case 2:
                return operand.equals("10");
        }
        return false;
    }

    default int parseArabic(String operand) {
        return Integer.parseInt(operand);
    }
}
