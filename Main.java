import java.util.*;;

class Main {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        String expression = "3+6*(x-y)+x^2";
        char [] postfixNotation = getPostFixNotation(expression);
        binaryTree.construct(postfixNotation);
        new TreeGUI(binaryTree);
        for (char c : postfixNotation) {
            System.out.println(getFormat(c));
        }
        System.out.println("Pop result");
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

    private static char [] getPostFixNotation(String expression) {
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
        return result.toString().toCharArray();
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