import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
        graphics.setFont(new Font("Tahoma", Font.BOLD, 20));
        Draw(graphics, 0, getWidth(), 0, getHeight() / tree.getheight(tree.root), tree.root);
    }

    public void Draw(Graphics graphics, int startWidth, int endWidth, int startHeight, int level, BinaryTreeNode binaryTreeNode) {
        String data = String.valueOf(binaryTreeNode.value);
        graphics.setFont(new Font("Tahoma", Font.BOLD, 18));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int dataWidth = fontMetrics.stringWidth(data);
        // graphics.drawString(data, (startWidth + endWidth) / 2 - dataWidth / 2,
        // startHeight + level / 2);
        graphics.drawOval((startWidth + endWidth) / 2 - dataWidth / 2, startHeight + level / 2, 25, 25);
        graphics.drawString(data, ((startWidth + endWidth) / 2 - dataWidth / 2) + 7, (startHeight + level / 2) + 18);
        graphics.drawLine(10, 10, 60, 60);

        if (binaryTreeNode.left != null) {
            Draw(graphics, startWidth, (startWidth + endWidth) / 2, startHeight + level, level, binaryTreeNode.left);
        }

        if (binaryTreeNode.right != null) {
            Draw(graphics, (startWidth + endWidth) / 2, endWidth, startHeight + level, level, binaryTreeNode.right);
        }
    }
}
