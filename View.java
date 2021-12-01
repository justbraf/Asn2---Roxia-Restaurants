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

    private Scoraxian[] scorax; //Scoraxian table seats
    private Zoraxian[] zorax; //Zoraxian table seats
    private Trainee trainee;
    private Food[] dish;
    private int whichDishToServe = 999; // Track the dish that is to be served
    private int totalDishesServed = 0;
    private int roundNumber = 0;
    

    View(Diner[] sc, Diner[] zo, Trainee tn) {
        scorax = (Scoraxian[])sc;
        zorax = (Zoraxian[])zo;
        trainee = tn;
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
        this.updateZoneAPanel();
        this.updateFoodList();
        this.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        // check if a dish was selected to be served
        for (int idx = 0; idx < serveReturnButtons.length; idx++) {
            if (e.getSource() == serveReturnButtons[idx][0]) {
                whichDishToServe = idx;
                totalDishesServed++;
            }
        }
        // check if a dish has been returned
        for (int idx = 0; idx < serveReturnButtons.length; idx++) {
            if (e.getSource() == serveReturnButtons[idx][1]) {
                dishLabels[idx].setText("<html><strike>" + dishLabels[idx].getText() + "</strike><html>");
                serveReturnButtons[idx][0].setEnabled(false);
                serveReturnButtons[idx][1].setEnabled(false);
                trainee.incDishesReturned();
                updateUserSummary();
                totalDishesServed++;
                resetRound();
            }
        }
        if (whichDishToServe != 999) {
            //Iterate over the table seat buttons to see which was pressed
            for (int btn = 0; btn < scoraSeats.length; btn++) {
                // Check which seated Scoraxian is being served
                if (e.getSource() == scoraSeats[btn]) {
                    if (scoraSeats[btn].getText() != "0") {
                        try {
                            scorax[btn].FeedMe(dish[whichDishToServe]);
                        }
                        catch (GreedyGutsException errorMsg) {
                            scoraSeats[btn].setText(String.valueOf(scorax[btn].getEnergyLevel())); //update button before error message
                            trainee.setIncidentRecords("A " + errorMsg.getMessage() + " has occured for a " + scorax[btn].getSpecies() + " in seat " + (btn + 1));
                            JOptionPane.showMessageDialog(null, "A " + errorMsg.getMessage() + " has occured for a " + scorax[btn].getSpecies() + " in seat " + (btn + 1), "Poor Service", JOptionPane.WARNING_MESSAGE);
                            scorax[btn] = null;
                            trainee.setServicePoints(trainee.getServicePoints() - 3);
                            trainee.setNumDeaths(trainee.getNumDeaths() + 1);
                        }
                        catch (AllergyException errorMsg) {
                            scoraSeats[btn].setText(String.valueOf(scorax[btn].getEnergyLevel()));
                            trainee.setIncidentRecords("A " + errorMsg.getMessage() + " has occured for a " + scorax[btn].getSpecies() + " in seat " + (btn + 1));
                            JOptionPane.showMessageDialog(null, "A " + errorMsg.getMessage() + " has occured for a " + scorax[btn].getSpecies() + " in seat " + (btn + 1), "Poor Service", JOptionPane.WARNING_MESSAGE);
                            scorax[btn] = null;
                            trainee.setServicePoints(trainee.getServicePoints() - 3);
                            trainee.setNumDeaths(trainee.getNumDeaths() + 1);
                        }
                        catch (FullyFedException errorMsg) {
                            scoraSeats[btn].setText(String.valueOf(scorax[btn].getEnergyLevel()));
                            trainee.setIncidentRecords("There is a " + errorMsg.getMessage() + " " + scorax[btn].getSpecies() + " in seat " + (btn + 1));
                            JOptionPane.showMessageDialog(null, "There is a " + errorMsg.getMessage() + " " + scorax[btn].getSpecies() + " in seat " + (btn + 1), "Good Service", JOptionPane.INFORMATION_MESSAGE);
                            scorax[btn] = null;
                            trainee.setServicePoints(trainee.getServicePoints() + 2);
                            trainee.setNumFed(trainee.getNumFed() + 1);
                        }
                        serveReturnButtons[whichDishToServe][0].setEnabled(false);
                        serveReturnButtons[whichDishToServe][1].setEnabled(false);
                        trainee.incDishesServed();
                        updateUserSummary(); 
                        whichDishToServe = 999; // Reset dish tracker
                        resetRound();
                    }
                }
                // Check which seated Zoraxian is being served
                if (e.getSource() == zoraSeats[btn]) {
                    // Prevent empty seat from being served
                    if (zoraSeats[btn].getText() != "0") {
                        try {
                            zorax[btn].FeedMe(dish[whichDishToServe]);
                        }
                        catch (GreedyGutsException errorMsg) {
                            zoraSeats[btn].setText(String.valueOf(zorax[btn].getEnergyLevel()));
                            trainee.setIncidentRecords("A " + errorMsg.getMessage() + " has occured for a " + zorax[btn].getSpecies() + " in seat " + (btn + 1));
                            JOptionPane.showMessageDialog(null, "A " + errorMsg.getMessage() + " has occured for a " + zorax[btn].getSpecies() + " in seat " + (btn + 1), "Poor Service", JOptionPane.WARNING_MESSAGE);
                            zorax[btn] = null;
                            trainee.setServicePoints(trainee.getServicePoints() - 3);
                            trainee.setNumDeaths(trainee.getNumDeaths() + 1);
                        }
                        catch (FullyFedException errorMsg) {
                            zoraSeats[btn].setText(String.valueOf(zorax[btn].getEnergyLevel()));
                            trainee.setIncidentRecords("There is a " + errorMsg.getMessage() + " " + zorax[btn].getSpecies() + " in seat " + (btn + 1));
                            JOptionPane.showMessageDialog(null, "There is a " + errorMsg.getMessage() + " " + zorax[btn].getSpecies() + " in seat " + (btn + 1), "Good Service", JOptionPane.INFORMATION_MESSAGE);
                            zorax[btn] = null;
                            trainee.setServicePoints(trainee.getServicePoints() + 2);
                            trainee.setNumFed(trainee.getNumFed() + 1);
                        }
                        serveReturnButtons[whichDishToServe][0].setEnabled(false);
                        serveReturnButtons[whichDishToServe][1].setEnabled(false);
                        trainee.incDishesServed();
                        updateUserSummary(); 
                        whichDishToServe = 999; // Reset dish tracker
                        resetRound();
                    }
                }
                updateZoneAPanel();
            }
        }
        // System.out.println(trainee.getIncidentRecords()); //delete this!!!!!*********************************
        System.out.println("round: "+roundNumber+"\tDish Total: "+totalDishesServed);
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
       Border fwblueBorder = BorderFactory.createLineBorder(Color.BLUE, 2);
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
                serveReturnButtons[idx][idx2].addActionListener(this);
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

    public void updateZoneAPanel() {
        for (int i = 0; i < scoraSeats.length; i++) {
            scoraSeats[i].setText(String.valueOf(scorax[i]!=null?scorax[i].getEnergyLevel():"0"));
    }
        for (int i = 0; i < zoraSeats.length; i++) {
            zoraSeats[i].setText(String.valueOf(zorax[i]!=null?zorax[i].getEnergyLevel():"0"));
        }
    }

    public void updateUserSummary() {
        usernameDpl.setText(trainee.getTraineeName());
        servicePointsDpl.setText(String.valueOf(trainee.getServicePoints()));
        fullyFedDpl.setText(String.valueOf(trainee.getNumFed()));
        dishesServedDpl.setText(String.valueOf(trainee.getDishesServed()));
        dishesReturnedDpl.setText(String.valueOf(trainee.getDishesReturned()));
        poorServiceDeathsDpl.setText(String.valueOf(trainee.getNumDeaths()));
        curiousAmbroxiansDpl.setText(String.valueOf(trainee.getCuriousAmbroxians()));
    }
    
    public void updateFoodList() {
        dish = new Food[6]; // Paceholder for six dishes
        // Loop through indexes and generate six dishes
        // indexes 0 to 2 will create Ambron, Scoron & Zoron dishes respectively
        // indexes 3 to 5 will create one of four random dishes
        for (int idx = 0; idx < dish.length; idx++) {
            dish[idx] = new Food(idx);
        }
        for (int idx = 0; idx < dish.length; idx++) {
            dishLabels[idx].setText(String.valueOf(dish[idx].getDishName() + " (" + dish[idx].getDishEnergy() + ")"));
            // renabled buttons
        }
        // Reset serve and return buttons to enable state
        for (int idx = 0; idx < serveReturnButtons.length; idx++) {
            serveReturnButtons[idx][0].setEnabled(true);
            serveReturnButtons[idx][1].setEnabled(true);
        }
    }

    private void resetRound() {
        // Reset for next round if all dishes were used
        if (totalDishesServed == 6) {
            totalDishesServed = 0;
            roundNumber++;
            JOptionPane.showMessageDialog(null, "Round " + roundNumber + " completed");
            this.updateFoodList();
            if (roundNumber > 0) {
                // updateCustomerWaiting
                if (roundNumber % 2 == 0) { //Check every even round 2, 4 or 6
                    for (int idx = 0; idx < scorax.length; idx++) {
                        if (scorax[idx] != null)
                            scorax[idx].setEnergyLevel(scorax[idx].getEnergyLevel() - 1);
                        if (zorax[idx] != null)
                            zorax[idx].setEnergyLevel(zorax[idx].getEnergyLevel() - 1);
                    }
                    updateZoneAPanel();
                }
            }
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