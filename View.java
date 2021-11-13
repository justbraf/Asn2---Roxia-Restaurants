import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * View
 */
public class View extends JFrame implements ActionListener {
    protected JFrame appFrame;    

    View() {
        ImageIcon thmb = new ImageIcon("logo.png");
        this.setLayout(new FlowLayout());
        this.setSize(1200, 750);
        this.setTitle("Roxia Restaurant Training System - Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setIconImage(thmb.getImage());
        this.setResizable(false);
        centerFrame(this);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //event handlers
        // if (e.getSource() == object) {
        //     //TODO
        // }
    }

    public void setupZoneAPanel(String name) {
        JPanel zoneAPanel = new JPanel();
        JLabel secLabel = new JLabel(name);
        Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 3);
        Border panelBorder = BorderFactory.createTitledBorder(blueBorder, "Zone A - Seating Area", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 24));

        // zoneAPanel.setBackground(new Color(123,145,228));
        zoneAPanel.setPreferredSize(new Dimension(500, 150));
        zoneAPanel.setBorder(panelBorder);
        // zoneAPanel.setLayout(new GridLayout());
        // zoneAPanel.setLayout(new BorderLayout());
        zoneAPanel.add(secLabel);
        this.add(zoneAPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private static void centerFrame(JFrame fr) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = fr.getSize().width;
        int h = fr.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        fr.setLocation(x, y);
    }
}
