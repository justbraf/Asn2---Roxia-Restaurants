import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * View
 */
public class View extends JFrame implements ActionListener {
    // updatable elements for Food Waiting section
    JLabel[] dishLabels;
    JButton[][] serveReturnButtons;
    
    // updatable elements for the User Summary
    private JLabel usernameDpl;
    JLabel servicePointsDpl;
    JLabel fullyFedDpl;
    JLabel dishesServedDpl;
    JLabel dishesReturnedDpl;
    JLabel poorServiceDeathsDpl;
    JLabel curiousAmbroxiansDpl;

    // updatabl elements for Zone A
    private JButton[] scoraSeats; // An array of buttons for each of the Scoraxian seats at the table
    private JButton[] zoraSeats; // An array of buttons for each of the Zoraxian seats at the table   
    

    View() {
        ImageIcon thmb = new ImageIcon("logo.png");
        // this.setLayout(new FlowLayout());
        this.setSize(1100, 450);
        this.setTitle("Roxia Restaurant Training System - Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setIconImage(thmb.getImage());
        this.setResizable(false);
        centerFrame(this);
        this.ZoneAPanel();
        this.CustomerWaitingPanel();
        this.FoodWaitingPanel();
        this.UserSummaryPanel();
        this.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        //event handlers
        //for loop to iterate over the buttons to see which was pressed
        if (e.getSource() == scoraSeats[0]) {
            scoraSeats[0].setText(String.valueOf(Integer.valueOf(scoraSeats[0].getText()) + 1));
        }
    }

    public void ZoneAPanel() {
        JPanel zoneAPanel = new JPanel();
        JPanel zoneALeftPanel = new JPanel();
        JPanel zoneARightPanel = new JPanel();
        JLabel scoraxLabel = new JLabel("Scoraxian Table -->", JLabel.RIGHT);
        JLabel zoraxLabel = new JLabel("Zoraxian Table -->", JLabel.RIGHT);
        scoraSeats = new JButton[15];
        zoraSeats = new JButton[15]; 
        Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 2);
        Border panelBorder = BorderFactory.createTitledBorder(blueBorder, "Zone A - Seating Area", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 18));
        
        
        // Set up zone A section
        zoneAPanel.setPreferredSize(new Dimension(500, 150)); // Set the height of the south layout section
        zoneAPanel.setBorder(panelBorder);
        zoneAPanel.setLayout(new BorderLayout());
        
        // Set up left half of zone A with labels
        zoneALeftPanel.setPreferredSize(new Dimension(200, 50));
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
        // zoneARightPanel.setPreferredSize(new Dimension(900, 50));
        // zoneARightPanel.setBackground(new Color(123,145,228));
        
        for (int i = 0; i < scoraSeats.length; i++) {
            scoraSeats[i] = new JButton();
            scoraSeats[i].addActionListener(this);
            zoneARightPanel.add(scoraSeats[i]);
        }
        for (int i = 0; i < zoraSeats.length; i++) {
            zoraSeats[i] = new JButton();
            zoraSeats[i].addActionListener(this);
            zoneARightPanel.add(zoraSeats[i]);
        }
        zoneAPanel.add(zoneARightPanel, BorderLayout.CENTER);

        this.add(zoneAPanel, BorderLayout.SOUTH);
    }

    public void CustomerWaitingPanel(){

        //Customer waiting panel
        JPanel cwPanel = new JPanel();

        //Customer waiting panel componets
        Border cwblueBorder = BorderFactory.createLineBorder(Color.BLUE);
        Border cwpanelBorder = BorderFactory.createTitledBorder(cwblueBorder, "Customer Waiting", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 18));
        JLabel scorLbl = new JLabel("Scoraxian");
        JLabel zorLbl = new JLabel("Zoraxian");
        JLabel ambroxLbl = new JLabel("Curious Ambroxain?");
        JButton scoraxSeat = new JButton("Seat");
        JButton zoraxSeat = new JButton("Seat");
        JButton ambroxBTN = new JButton("Yes. Click here!");

        // Set up Customer Waiting Section
        cwPanel.setPreferredSize(new Dimension(350, 400));
        cwPanel.setBorder(cwpanelBorder);
        cwPanel.setLayout(new GridLayout(3,2));

        scorLbl.setPreferredSize(new Dimension(150, 50));
        scorLbl.setFont(new Font("Arial", Font.PLAIN, 12));
        zorLbl.setPreferredSize(new Dimension(150, 50));
        zorLbl.setFont(new Font("Arial", Font.PLAIN, 12));
        ambroxLbl.setPreferredSize(new Dimension(150, 50));
        ambroxLbl.setFont(new Font("Arial", Font.PLAIN, 12));

        //Customer waiting right grid
        //scoraxSeat.setPreferredSize(new Dimension(100, 30));
        scoraxSeat.setFont(new Font("Arial", Font.PLAIN, 14));
        scoraxSeat.setSize(50, 50);
        zoraxSeat.setPreferredSize(new Dimension(275, 50));
        zoraxSeat.setFont(new Font("Arial", Font.PLAIN, 14));
        zoraxSeat.setSize(50, 50);
        ambroxBTN.setPreferredSize(new Dimension(275, 50));
        ambroxBTN.setFont(new Font("Arial", Font.PLAIN, 14));
        ambroxBTN.setSize(50, 50);

        //Adding components to Customer Waiting panel
        cwPanel.add(scorLbl);
        cwPanel.add(scoraxSeat);
        cwPanel.add(zorLbl);
        cwPanel.add(zoraxSeat);
        cwPanel.add(ambroxLbl);
        cwPanel.add(ambroxBTN);

        this.add(cwPanel, BorderLayout.WEST);

    }

    public void UserSummaryPanel(){
        
        //User summary panel
        JPanel usPanel = new JPanel();

        //User Summary panel components
        Border usBorder = BorderFactory.createLineBorder(Color.BLUE);
        Border usblueBorder = BorderFactory.createLineBorder(Color.BLUE, 2);
        Border uspanelBorder = BorderFactory.createTitledBorder(usblueBorder, "User Summary", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 18));
        JLabel usernameLbl = new JLabel("User Name:");
        JLabel servicePointsLbl = new JLabel("Service Points:");
        JLabel fullyFedLbl = new JLabel("Fully Fed:");
        JLabel dishesServedLbl = new JLabel("Dishes Served:");
        JLabel dishesReturnedLbl = new JLabel("Dishes Returned:");
        JLabel poorServiceDeathsLbl = new JLabel("'Poor Service' Deaths:");
        JLabel curiousAmbroxiansLbl = new JLabel("Curious Ambroxain:");
        usernameDpl = new JLabel();
        servicePointsDpl = new JLabel();
        fullyFedDpl = new JLabel();
        dishesServedDpl = new JLabel();
        dishesReturnedDpl = new JLabel();
        poorServiceDeathsDpl = new JLabel();
        curiousAmbroxiansDpl = new JLabel();

         // Set up User Summery Section
         usPanel.setPreferredSize(new Dimension(450, 400));
         usPanel.setBorder(uspanelBorder);
         usPanel.setLayout(new GridLayout(7,2));

        //User summary left grid
        usernameLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        servicePointsLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        fullyFedLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        dishesServedLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        dishesReturnedLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        poorServiceDeathsLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        curiousAmbroxiansLbl.setFont(new Font("Arial", Font.PLAIN, 14));

        //User summary right grid
        usernameDpl.setBorder(usBorder);
        servicePointsDpl.setBorder(usBorder);
        fullyFedDpl.setBorder(usBorder);
        dishesServedDpl.setBorder(usBorder);
        dishesReturnedDpl.setBorder(usBorder);
        poorServiceDeathsDpl.setBorder(usBorder);
        curiousAmbroxiansDpl.setBorder(usBorder);

        //Adding components to User summary
        usPanel.add(usernameLbl);
        usPanel.add(usernameDpl);
        usPanel.add(servicePointsLbl);
        usPanel.add(servicePointsDpl);
        usPanel.add(fullyFedLbl);
        usPanel.add(fullyFedDpl);
        usPanel.add(dishesServedLbl);
        usPanel.add(dishesServedDpl);
        usPanel.add(dishesReturnedLbl);
        usPanel.add(dishesReturnedDpl);
        usPanel.add(poorServiceDeathsLbl);
        usPanel.add(poorServiceDeathsDpl);
        usPanel.add(curiousAmbroxiansLbl);
        usPanel.add(curiousAmbroxiansDpl);

        this.add(usPanel, BorderLayout.EAST);

    }

    public void FoodWaitingPanel(){
        //Food waiting panel
        JPanel fwPanel = new JPanel();
        dishLabels = new JLabel[6];
        serveReturnButtons = new JButton[6][2];

       //Food waiting panel components
       Border fwblueBorder = BorderFactory.createLineBorder(Color.BLUE);
       Border fwpanelBorder = BorderFactory.createTitledBorder(fwblueBorder, "Food Waiting", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 18));
       

        // Set up Food Waiting Section
        // fwPanel.setPreferredSize(new Dimension(450, 400));
        fwPanel.setBorder(fwpanelBorder);
        fwPanel.setLayout(new GridLayout(6,3));

        //Food waiting left column
        for (int idx = 0; idx < dishLabels.length; idx++) {
            dishLabels[idx] = new JLabel();
            dishLabels[idx].setFont(new Font("Arial", Font.PLAIN, 14));
        }

        //Food waiting 2nd column
        for (int idx = 0; idx < serveReturnButtons.length; idx++) {
            for (int idx2 = 0; idx2 < serveReturnButtons[idx].length; idx2++) {
                if (idx2 < 1)
                    serveReturnButtons[idx][idx2] = new JButton("Serve");
                else
                    serveReturnButtons[idx][idx2] = new JButton("Return");
                serveReturnButtons[idx][idx2].setSize(50, 50);
            }
        }

        //Adding components to Food waiting
        for (int idx = 0; idx < dishLabels.length; idx++) {
            fwPanel.add(dishLabels[idx]);
            fwPanel.add(serveReturnButtons[idx][0]);
            fwPanel.add(serveReturnButtons[idx][1]);
        }
        this.add(fwPanel, BorderLayout.CENTER);

    }

    public void updateZoneAPanel(Diner[] sc, Diner[] zo) {
        for (int i = 0; i < scoraSeats.length; i++) {
            scoraSeats[i].setText(String.valueOf(sc[i]!=null?sc[i].getEnergyLevel():"0"));
        }
        for (int i = 0; i < zoraSeats.length; i++) {
            zoraSeats[i].setText(String.valueOf(String.valueOf(zo[i]!=null?zo[i].getEnergyLevel():"0")));
        }
    }

    public void updateUserSummary(Trainee tn) {
        usernameDpl.setText(tn.getTraineeName());
        servicePointsDpl.setText(String.valueOf(tn.getServicePoints()));
        fullyFedDpl.setText(String.valueOf(tn.getNumFed()));
        dishesServedDpl.setText(String.valueOf(tn.getDishesServed()));
        dishesReturnedDpl.setText(String.valueOf(tn.getDishesReturned()));
        poorServiceDeathsDpl.setText(String.valueOf(tn.getNumDeaths()));
        curiousAmbroxiansDpl.setText(String.valueOf(tn.getCuriousAmbroxians()));
    }
    
    public void updateFoodList(Food[] dish) {
        for (int idx = 0; idx < dish.length; idx++) {
            dishLabels[idx].setText(String.valueOf(dish[idx].getDishName() + " (" + dish[idx].getDishEnergy() + ")"));
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
}