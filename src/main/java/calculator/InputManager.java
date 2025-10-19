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
}
