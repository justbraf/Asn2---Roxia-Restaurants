import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * View
 */
public class View extends JFrame implements ActionListener {
    // protected JFrame appFrame;    

    View() {
        ImageIcon thmb = new ImageIcon("logo.png");
        this.setLayout(new FlowLayout());
        this.setSize(1200, 750);
        this.setTitle("Roxia Restaurant Training System - Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setIconImage(thmb.getImage());
        // this.setResizable(false);
        centerFrame(this);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //event handlers
        // if (e.getSource() == object) {
        //     //TODO
        // }
    }

    public void setupZoneAPanel(Diner[] sc, Diner[] zo) {
        JPanel zoneAPanel = new JPanel();
        JPanel zoneALeftPanel = new JPanel();
        JPanel zoneARightPanel = new JPanel();
        JLabel scoraxLabel = new JLabel("Scoraxian Table -->", JLabel.RIGHT);
        JLabel zoraxLabel = new JLabel("Zoraxian Table -->", JLabel.RIGHT);
        Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 3);
        Border panelBorder = BorderFactory.createTitledBorder(blueBorder, "Zone A - Seating Area", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 24));
        JButton[] scoraSeats = new JButton[15];
        JButton[] zoraSeats = new JButton[15];
        
        // Set up zone A section
        zoneAPanel.setPreferredSize(new Dimension(500, 150)); // Set the height of the south layout section
        zoneAPanel.setBorder(panelBorder);
        zoneAPanel.setLayout(new BorderLayout());
        
        // Set up left half of zone A with labels
        zoneALeftPanel.setPreferredSize(new Dimension(250, 50));
        scoraxLabel.setPreferredSize(new Dimension(250, 50));
        scoraxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        zoraxLabel.setPreferredSize(new Dimension(250, 50));
        zoraxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        zoneALeftPanel.add(scoraxLabel);
        zoneALeftPanel.add(zoraxLabel);
        zoneAPanel.add(zoneALeftPanel, BorderLayout.WEST);

        // Set up right half of zone A with buttons
        zoneARightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 100));
        zoneARightPanel.setLayout(new GridLayout(2, 15));
        // zoneARightPanel.setBackground(new Color(123,145,228));
        
        for (int i = 0; i < scoraSeats.length; i++) {
            scoraSeats[i] = new JButton(String.valueOf(sc[i]!=null?sc[i].getEnergyLevel():""));
            zoneARightPanel.add(scoraSeats[i]);
        }
        for (int i = 0; i < zoraSeats.length; i++) {
            zoraSeats[i] = new JButton(String.valueOf(String.valueOf(zo[i]!=null?zo[i].getEnergyLevel():"")));
            zoneARightPanel.add(zoraSeats[i]);
        }
        zoneAPanel.add(zoneARightPanel, BorderLayout.CENTER);

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
