package calculator.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static calculator.model.Separator.buildCustomSeparatorRegex;

public class Validator {
    public static String validateInput(String input) {
        boolean hasCustomSeperator = checkCustomSeperatorExistence(input);
        String firstWrapper = Separator.CustomSeparatorWrapper.DOUBLE_SLASH.getValue();
        String secondWrapper = Separator.CustomSeparatorWrapper.NEW_LINE.getValue();
        String firstDefault = Separator.DefaultSeparator.COLON.getValue();
        String secondDefault = Separator.DefaultSeparator.COMMA.getValue();

        if (!hasCustomSeperator) {
            if (input.contains(firstWrapper) || input.contains(secondWrapper))
                throw new IllegalArgumentException("잘못된 입력: 커스텀 구분자를 추출할 수 있는 패턴이 아닙니다.");

            if (!input.contains(firstDefault) && !input.contains(secondDefault))
                throw new IllegalArgumentException("잘못된 입력: 구분자가 존재하지 않습니다.");

            if (!input.matches(String.format("[\\d%s%s]+", firstDefault, secondDefault)))
                throw new IllegalArgumentException("잘못된 입력: 양수와 구분자를 제외한 문자 입력은 불가합니다.");
        }

        if (input.matches(".*-\\d+.*"))
            throw new IllegalArgumentException("잘못된 입력: 음수는 입력할 수 없습니다.");

        if (hasCustomSeperator) {
            String customRegex = buildCustomSeparatorRegex();
            return input.replaceFirst(customRegex, "");
        }

        return input;
    }

    private static boolean checkCustomSeperatorExistence(String input) {
        // 커스텀 구분자 추출용 정규식 생성 (문자열 앞 //와 \n 사이 한 문자)
        String regexForCustomSeparator = buildCustomSeparatorRegex();
        // 정규식에 해당하는 커스텀 구분자 추출
        Pattern pattern = Pattern.compile(regexForCustomSeparator);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        } else
            return false;
    }
}
