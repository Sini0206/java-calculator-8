package calculator.model;

import java.util.List;

public class Calculator {
    public static int sum(List<Integer> numbers) {
        int total = 0;
        for (int num: numbers) {
            total += num;
        }
        return total;
    }
}
