import java.util.*;

class Main {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        String expression = "3+6*(x-y)+x^2";
        TreeExpression treeExpression = new TreeExpression();
        String postfixNotation = treeExpression.getPostFixNotation(expression);
        String prefixNotation = treeExpression.getPreFixNotation(expression);
        binaryTree.construct(postfixNotation);
        System.out.println("Postfix notation: " + postfixNotation);
        System.out.println();
        System.out.println("Prefix notation: " + prefixNotation);
        System.out.println();
        new TreeGUI(binaryTree);
        for (char c : postfixNotation.toCharArray()) {
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
}