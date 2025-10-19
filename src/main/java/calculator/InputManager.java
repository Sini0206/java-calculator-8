package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputManager {

    public enum DefaultSeparator {
        COMMA(","),
        COLON(":");
        final private String value;
        DefaultSeparator(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public enum CustomSeparatorWrapper {
        DOUBLE_SLASH("//"),
        NEW_LINE("\\n");
        final private String value;
        CustomSeparatorWrapper(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    
    private String buildCustomSeparatorRegex() {
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("^");
        regexBuilder.append(CustomSeparatorWrapper.DOUBLE_SLASH.getValue());
        regexBuilder.append("(.)\\");
        regexBuilder.append(CustomSeparatorWrapper.NEW_LINE.getValue());

        return regexBuilder.toString();
    }
    
    private String extractCustomSeparator(String validatedStr) {
        // 커스텀 구분자 추출용 정규식 생성 (문자열 앞 //와 \n 사이 문자)
        String regexForCustomSeparator = buildCustomSeparatorRegex();
        // 정규식에 해당하는 커스텀 구분자 추출
        Pattern pattern = Pattern.compile(regexForCustomSeparator);
        Matcher matcher = pattern.matcher(validatedStr);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    private String buildRegexToSplit(String customSeparator) {
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("[");

        for (InputManager.DefaultSeparator separator : InputManager.DefaultSeparator.values())  {
            regexBuilder.append(separator.getValue());
        }
        regexBuilder.append(customSeparator);
        regexBuilder.append("]+"); // 구분자가 여러 개 있어도 구분 e.g. 3::4 -> [3, 4]

        return regexBuilder.toString();
    }

    public List<Integer> parseNumbers(String validatedStr) {
        List<Integer> numbers = new ArrayList<>();
        // 구분용 정규식 생성
        String customSeparator = extractCustomSeparator(validatedStr);
        String regex = buildRegexToSplit(customSeparator);

        for (String str : validatedStr.split(regex)) {
            if (str.matches("\\d+")) {   // 양의 정수인 경우만
                int num = Integer.parseInt(str);
                if (num > 0)
                    numbers.add(num);
            }
        }
        return numbers;
    }
}
