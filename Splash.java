import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Splash
 */
public class Splash extends JFrame {

    Splash() {
        ImageIcon thmb = new ImageIcon("logo.png");  // App thumbnail
        ImageIcon splashImg = new ImageIcon(thmb.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT)); // resize image for label logo
        JPanel splashPanel = new JPanel();
        JLabel splashLabel = new JLabel();
        JLabel devLabel = new JLabel("Developed by Jason Griffith & Dwayne Brathwaite");
        
        this.setLayout(new FlowLayout());
        this.setSize(520, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setIconImage(thmb.getImage());
        this.setResizable(false);

        splashPanel.setBackground(new Color(127,191,242));
        splashPanel.setBounds(10, 10, 500, 380);
        splashLabel.setText("Welcome to Roxia Training Systems");
        splashLabel.setFont(new Font("Serif", Font.ITALIC, 30));
        splashLabel.setIcon(splashImg);
        splashLabel.setIconTextGap(20);
        splashLabel.setHorizontalTextPosition(JLabel.CENTER);
        splashLabel.setVerticalTextPosition(JLabel.TOP);
        splashPanel.add(splashLabel);
        devLabel.setFont(new Font("Serif", Font.ITALIC, 22));
        splashPanel.add(devLabel);
        this.add(splashPanel);
        this.setUndecorated(true);  // Remove window title bar and controls
        centerFrame(this);
        this.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Thread is interrupted");
        }
        this.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        //event handlers
        // if (e.getSource() == object) {
        //     //TODO
        // }
    }

    public void setup() {
        
        // Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 3);
        // Border panelBorder = BorderFactory.createTitledBorder(blueBorder, "Zone A - Seating Area", TitledBorder.CENTER, TitledBorder.TOP);

        // zoneAPanel.setBackground(new Color(123,145,228));
        // zoneAPanel.setPreferredSize(new Dimension(500, 500));
        // zoneAPanel.setBorder(blueBorder);
        // zoneAPanel.setLayout(new GridLayout());
        // zoneAPanel.setLayout(new BorderLayout());
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
