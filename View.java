import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * View
 */
public class View extends JFrame implements ActionListener {
    protected JFrame appFrame;

    View() {
        this.setLayout(new FlowLayout());
        this.setSize(1000, 750);
        this.setTitle("Roxia Restaurant Training System - Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //event handlers
    }
}
