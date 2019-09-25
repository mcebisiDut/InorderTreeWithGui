import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Main {
    private static String sign;

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        String expression = "3+6*(x-y)+x^2";
        char[] postfixNotation = getPostFixNotation(expression);
        binaryTree.construct(postfixNotation);
        new TreeGUI(binaryTree);
        //List<Character> items = new ArrayList<Character>(postfixNotation);
        for(char c : postfixNotation){
            System.out.println(getFormat(c));
            System.out.println("Pop result");
        }
        // items.forEach(x -> System.out.println(x));
    }

    private static String getFormat(char character) {
        switch (character) {
        case '+':
            return "Add";
        case '-':
            return "Sub";
        case '/':
            return "Div";
        case '*':
            return "Mul";
        case '^':
            return "Pow";
        }
        return "Push " + character;
    }

    private static char[] getPostFixNotation(String expression) {
        String result = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (precedence(c) > 0) {
                while (stack.isEmpty() == false && precedence(stack.peek()) >= precedence(c)) {
                    result += stack.pop();
                }
                stack.push(c);
            } else if (c == ')') {
                char x = stack.pop();
                while (x != '(') {
                    result += x;
                    x = stack.pop();
                }
            } else if (c == '(') {
                stack.push(c);
            } else {
                result += c;
            }
        }
        for (int i = 0; i <= stack.size(); i++) {
            result += stack.pop();
        }

        return result.toCharArray();
    }

    private static int precedence(char value) {
        switch (value) {
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