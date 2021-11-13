import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Splash
 */
public class UserForm extends JFrame implements ActionListener {
    private String name;
    private JTextField nameTextField;
    private JButton loginButton;
    private Trainee trainee;

    UserForm(Trainee tn) {
        trainee = tn;
        ImageIcon thmb = new ImageIcon("logo.png");  // App thumbnail
        JPanel titlePanel = new JPanel();
        JPanel loginPanel = new JPanel();
        JLabel nameLabel = new JLabel("Name");
        nameTextField = new JTextField();
        loginButton = new JButton("Login");
        
        this.setLayout(new BorderLayout());
        this.setSize(400, 125);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(thmb.getImage());
        this.setResizable(false);

        nameLabel.setText("Welcome to Roxia Training Systems");
        nameLabel.setFont(new Font("Serif", Font.ITALIC, 20));
        titlePanel.add(nameLabel);
        this.add(titlePanel, BorderLayout.NORTH);
        nameTextField.setPreferredSize(new Dimension(150, 30));
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameTextField.setAlignmentY(CENTER_ALIGNMENT);
        loginPanel.add(nameTextField);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);
        this.add(loginPanel, BorderLayout.CENTER);
        centerFrame(this);
        // this.pack();
        nameTextField.requestFocusInWindow();
        this.setVisible(true);
        this.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {
        //event handlers
        if (e.getSource() == loginButton) {
            this.setUserName(nameTextField.getText());
            trainee.setTraineeName(nameTextField.getText());
            // this.dispose();
        }
    }

    private static void centerFrame(JFrame fr) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = fr.getSize().width;
        int h = fr.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        fr.setLocation(x, y);
    }
    
    public String getUserName() {
        return name;
    }
    public void setUserName(String nam) {
        name = nam;
    }
}
