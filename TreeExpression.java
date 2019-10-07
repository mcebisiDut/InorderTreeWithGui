import java.util.*;

public class TreeExpression {

    public String getPostFixNotation(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if (precedence(character) > 0) {
                while (stack.isEmpty() == false && precedence(stack.peek()) >= precedence(character)) {
                    result.append(stack.pop());
                }
                stack.push(character);
            } else if (character == ')') {
                char element = stack.pop();
                while (element != '(') {
                    result.append(element);
                    element = stack.pop();
                }
            } else if (character == '(') {
                stack.push(character);
            } else {
                result.append(character);
            }
        }
        for (int index = 0; index <= stack.size(); index++) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    public String getPreFixNotation(String expression) {

        StringBuilder result = new StringBuilder();
        StringBuilder input = new StringBuilder(expression);
        input.reverse();
        Stack<Character> stack = new Stack<Character>();

        char[] expressionCharArray = new String(input).toCharArray();
        for (int index = 0; index < expressionCharArray.length; index++) {

            if (expressionCharArray[index] == '(') {
                expressionCharArray[index] = ')';
                index++;
            } else if (expressionCharArray[index] == ')') {
                expressionCharArray[index] = '(';
                index++;
            }
        }
        for (int index = 0; index < expressionCharArray.length; index++) {
            char character = expressionCharArray[index];

            if (precedence(character) > 0) {
                while (stack.isEmpty() == false && precedence(stack.peek()) >= precedence(character)) {
                    result.append(stack.pop());
                }
                stack.push(character);
            } else if (character == ')') {
                char element = stack.pop();
                while (element != '(') {
                    result.append(element);
                    element = stack.pop();
                }
            } else if (character == '(') {
                stack.push(character);
            } else {
                result.append(character);
            }
        }

        for (int i = 0; i <= stack.size(); i++) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    private static int precedence(char character) {
        switch (character) {
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        case '^':
            return 3;
        }
        return -1;
    }
}