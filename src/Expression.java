import java.util.HashMap;
import java.util.Map;

public class Expression {
    private String input;
    private Boolean isArabic;
    private Integer a, b, result;
    private Operation oper;
    private static  String [] romanNums =
            new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};


    public Expression(String inputStr) throws Exception {
        input = inputStr;
        String [] inputSplit =inputStr.split(" ");
        //Проверяем, что в выражении 3 слова (а + b)
        if (inputSplit.length != 3) {
            throw new Exception("Выражение составлено не корректно.");
        }
        try {
            a = Integer.parseInt(inputSplit[0]);
            b = Integer.parseInt(inputSplit[2]);
            isArabic = Boolean.TRUE;
        } catch (NumberFormatException e) {
            isArabic = Boolean.FALSE;
        }
        if (!isArabic) {
            a = romanToArabic(inputSplit[0]);
            b = romanToArabic(inputSplit[2]);
        }
        oper = recognitionOperation(inputSplit[1]);
    }
    public String getResult() throws Exception {
        Integer result;
        switch (oper) {
            case ADDITION:
                result = a + b;
                break;
            case DIVISION:
                result = a / b;
                break;
            case SUBTRACTION:
                result = a - b;
                break;
            case MULTIPLICTION:
                result = a * b;
                break;
            default:
                throw new Exception("Во время вычисления что-то пошло не так.");
        }
        if (isArabic) {
            return result.toString();
        } else {
            return arabicToRoman(result);
        }
    }

    private static Integer romanToArabic(String romanStr) throws Exception{
        Map <Character, Integer> romanNumerals = new HashMap<Character,Integer>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);

        char [] romanChars = romanStr.toCharArray();
        Integer result = romanNumerals.get(romanChars[romanChars.length-1]);

        for (int i = romanChars.length-2; i>=0; i--){
            Integer arabian = romanNumerals.get(romanChars[i]);
            if (arabian < romanNumerals.get(romanChars[i+1])){
                result -= arabian;
            } else {
                result += arabian;
            }
        }
        if (result > 10)
            throw new Exception("Римское число больше X.");

        return result;
    }
    private static String arabicToRoman (Integer num) throws Exception{
        if (num == 100)
            return "C";
        if (num >= 50)
            if (num >= 90)
                return "XC" + arabicToRoman(num -90);
            else
                return "L" + arabicToRoman(num - 50);
        if (num >= 10)
            if (num >= 40)
                return "XL" + arabicToRoman(num - 40);
            else
                return "X" + arabicToRoman(num - 10);
        if (num >= 5)
            if (num == 9)
                return "IX";
            else
                return "V" + arabicToRoman(num - 5);
        if (num > 0)
            if (num == 4)
                return "IV";
            else
                return "I" + arabicToRoman(num -1);
        return "";
    }
    private static Operation recognitionOperation(String strOper) throws Exception {
        Operation oper = null;
        for (Operation operation:Operation.values()){
            if (strOper.equals(operation.getTitle())){
                oper = operation;
                break;
            }
        }
        if (oper == null) {
            throw new Exception("Оператор записан не корректно.");
        }
        return oper;
    }
}
