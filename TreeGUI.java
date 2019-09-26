import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

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
        graphics.setColor(Color.red);
        Draw(graphics, 0, getWidth(), 0, getHeight() / tree.getheight(tree.root), tree.root, 0);
    }

    public void Draw(Graphics graphics, int startWidth, int endWidth, int startHeight, int level,
            BinaryTreeNode binaryTreeNode, int i) {
        String data = String.valueOf(binaryTreeNode.value);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int dataWidth = fontMetrics.stringWidth(data);
        int xCoordinate = (startWidth + endWidth) / 2 - dataWidth / 2;
        int yCoordinate = startHeight + level / 2;
        graphics.fillOval(xCoordinate, yCoordinate, 25, 25);
        graphics.setColor(Color.white);
        graphics.drawString("" + i, xCoordinate + 7, yCoordinate + 18);
        i++;
        graphics.setColor(Color.red);
        graphics.drawLine(xCoordinate, yCoordinate, 20, 20);

        if (binaryTreeNode.left != null) {
            Draw(graphics, startWidth, (startWidth + endWidth) / 2, startHeight + level, level, binaryTreeNode.left, i);
            i++;
        }

        if (binaryTreeNode.right != null) {
            Draw(graphics, (startWidth + endWidth) / 2, endWidth, startHeight + level, level, binaryTreeNode.right, i);
            i++;
        }

    }
}
