import java.util.Stack;

class BinaryTree {
    BinaryTreeNode root;
    BinaryTreeNode rightChild;
    BinaryTreeNode leftChild;

    boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
            return true;
        }
        return false;
    }

    void construct(char postfix[]) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();

        for (int i = 0; i < postfix.length; i++) {

            if (!isOperator(postfix[i])) {
                root = new BinaryTreeNode(postfix[i]);
                stack.push(root);
            } else {
                root = new BinaryTreeNode(postfix[i]);

                rightChild = stack.pop();
                leftChild = stack.pop();

                root.right = rightChild;
                root.left = leftChild;

                stack.push(root);
            }
        }

        root = stack.peek();
        stack.pop();

        // return parent;
    }

    public int getheight(BinaryTreeNode root) {
        if (root == null)
            return 0;
        return Math.max(getheight(root.left), getheight(root.right)) + 1;
    }
}