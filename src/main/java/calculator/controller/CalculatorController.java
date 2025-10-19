package calculator.controller;

import calculator.model.Calculator;
import calculator.model.Parser;
import calculator.model.Separator;
import calculator.model.Validator;
import calculator.view.InputView;
import calculator.view.OutputView;

import java.util.List;

public class CalculatorController {
    public void run() {
        String input = InputView.readExpression();
        String validatedString = Validator.validateInput(input);
        String customSeperator = Separator.extractCustomSeparator(validatedString);
        List<Integer> numbers = Parser.parseNumbers(validatedString, customSeperator);
        int result = Calculator.sum(numbers);
        OutputView.printResult(result);
    }
}
