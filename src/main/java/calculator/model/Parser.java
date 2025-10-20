package calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static String buildRegexToSplit(String customSeparator) {
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("[");

        for (Separator.DefaultSeparator separator : Separator.DefaultSeparator.values())  {
            regexBuilder.append(separator.getValue());
        }
        regexBuilder.append(customSeparator);
        regexBuilder.append("]+"); // 구분자가 여러 개 있어도 구분 e.g. 3::4 -> [3, 4]

        return regexBuilder.toString();
    }

    public static List<Integer> parseNumbers(String validatedStr, String customSeperator) {
        List<Integer> numbers = new ArrayList<>();
        String regex = buildRegexToSplit(customSeperator);
        for (String str : validatedStr.split(regex)) {
            if (str.isEmpty()) continue;
            int num = Integer.parseInt(str);
            if (num == 0)
                throw new IllegalArgumentException("잘못된 입력: 0은 입력할 수 없습니다");
            numbers.add(num);
        }
        return numbers;
    }
}
