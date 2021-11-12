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
        this.setLayout(null);
        // this.setResizable(false);
        // setupZoneAPanel();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //event handlers
    }

    public void setupZoneAPanel(String name) {
        JPanel zoneAPanel = new JPanel();
        JLabel secLabel = new JLabel(name);

        zoneAPanel.setBackground(new Color(23,45,228));
        zoneAPanel.setBounds(25, 25, 500, 350);
        // zoneAPanel.setLayout(new GridLayout());
        // zoneAPanel.setLayout(new BorderLayout());
        zoneAPanel.add(secLabel);
        this.add(zoneAPanel);
        this.setVisible(true);
    }
}
