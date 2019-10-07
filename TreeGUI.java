import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TreeGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DrawTree drawer;

    public TreeGUI(BinaryTree tree) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        drawer = new DrawTree(tree);

        contentPane.add(drawer);
        setContentPane(contentPane);
        setVisible(true);
    }
}

class DrawTree extends JPanel {
    private static final long serialVersionUID = 1L;
    public BinaryTree tree;

    public DrawTree(BinaryTree tree) {
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.setFont(new Font("Tahoma", Font.BOLD, 18));
        Draw(graphics, 0, getWidth(), 0, getHeight() / tree.getheight(tree.root), tree.root);
    }

    public Point Draw(Graphics graphics, int startWidth, int endWidth, int startHeight, int level, BinaryTreeNode binaryTreeNode) {
        String value = String.valueOf(binaryTreeNode.value);
        int dataWidth = graphics.getFontMetrics().stringWidth(value);
        drawColoredCircle(graphics, startWidth, endWidth, startHeight, level, dataWidth);

        Point rootNode = new Point((((startWidth + endWidth) / 2) - (dataWidth / 2)) + 7,(startHeight + level / 2) + 18);
        insertText(graphics, value, rootNode);

        graphics.setColor(Color.red);
        if (binaryTreeNode.left != null) {
            Point leftChild = Draw(graphics, startWidth, (startWidth + endWidth) / 2, startHeight + level, level, binaryTreeNode.left);
            drawLine(graphics, rootNode, leftChild);
        }

        if (binaryTreeNode.right != null) {
            Point rightChild = Draw(graphics, (startWidth + endWidth) / 2, endWidth, startHeight + level, level, binaryTreeNode.right);
            drawLine(graphics, rootNode, rightChild);
        }

        return rootNode;
    }

    private void drawColoredCircle(Graphics graphics, int startWidth, int endWidth, int startHeight, int level, int dataWidth) {
        graphics.setColor(Color.red);
        graphics.fillOval((startWidth + endWidth) / 2 - dataWidth / 2, startHeight + level / 2, 25, 25);
    }

    private void insertText(Graphics graphics, String value, Point rootNode) {
        graphics.setColor(Color.white);
        graphics.drawString(value, rootNode.x, rootNode.y);
    }

    private void drawLine(Graphics graphics, Point root, Point child) {
        graphics.drawLine(root.x, root.y, child.x - 3, child.y - 10);
    }
}