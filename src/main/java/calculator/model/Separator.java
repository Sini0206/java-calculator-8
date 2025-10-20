package calculator.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {

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

    public static String buildCustomSeparatorRegex() {
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("^");
        regexBuilder.append(CustomSeparatorWrapper.DOUBLE_SLASH.getValue());
        regexBuilder.append("(.)\\");
        regexBuilder.append(CustomSeparatorWrapper.NEW_LINE.getValue());

        return regexBuilder.toString();
    }

    public static String extractCustomSeparator(String validatedFullStr) {
        // 커스텀 구분자 추출용 정규식 생성 (문자열 앞 //와 \n 사이 한 문자)
        String regexForCustomSeparator = buildCustomSeparatorRegex();
        // 정규식에 해당하는 커스텀 구분자 추출
        Pattern pattern = Pattern.compile(regexForCustomSeparator);
        Matcher matcher = pattern.matcher(validatedFullStr);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
