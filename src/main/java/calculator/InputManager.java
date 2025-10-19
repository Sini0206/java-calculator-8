package calculator;

import java.util.List;

public class InputManager {

    public enum DefaultSeparator {
        COMMA(","),
        COLONE(":");
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
        NEW_LINE("\n");
        final private String value;
        CustomSeparatorWrapper(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
}
