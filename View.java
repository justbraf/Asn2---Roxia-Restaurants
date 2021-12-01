import java.awt.*;
import java.awt.event.*;
import java.util.Random;

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

    // updatable elements for Zone A
    private JButton[] scoraSeats; // An array of buttons for each of the Scoraxian seats at the table
    private JButton[] zoraSeats; // An array of buttons for each of the Zoraxian seats at the table

    // updatable elements for Customer Waiting Panel
    JLabel[] seatCWLbl;
    JButton seat1CWBtn;
    JButton seat2CWBtn;
    JButton ambroxCWBtn;

    private Restaurant diner;
    private Trainee trainee;
    private Food[] dish;
    private int whichDishToServe = 999; // Track the dish that is to be served
    private int totalDishesServed = 0;
    private int roundNumber = 0;
    private Diner[] dinerType;
    private int whoToSeat = 999;
    

    View(Restaurant rest, Trainee tn) {
        diner = rest;
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
            for (int btn = 0; btn < 15; btn++) {
                // Check which seated Scoraxian is being served
                if (e.getSource() == scoraSeats[btn]) {
                    if (scoraSeats[btn].getText() != "0") {
                        totalDishesServed++;
                        try {
                            diner.scoraxTableZoneA[btn].FeedMe(dish[whichDishToServe]);
                        }
                        catch (GreedyGutsException errorMsg) {
                            scoraSeats[btn].setText(String.valueOf(diner.scoraxTableZoneA[btn].getEnergyLevel())); //update button before error message
                            trainee.setIncidentRecords("A " + errorMsg.getMessage() + " has occured for a " + diner.scoraxTableZoneA[btn].getSpecies() + " in seat " + (btn + 1));
                            JOptionPane.showMessageDialog(null, "A " + errorMsg.getMessage() + " has occured for a " + diner.scoraxTableZoneA[btn].getSpecies() + " in seat " + (btn + 1), "Poor Service", JOptionPane.WARNING_MESSAGE);
                            diner.clearSeat(btn, diner.scoraxTableZoneA[btn].getSpeciesCode());
                            trainee.setServicePoints(trainee.getServicePoints() - 3);
                            trainee.setNumDeaths(trainee.getNumDeaths() + 1);
                        }
                        catch (AllergyException errorMsg) {
                            scoraSeats[btn].setText(String.valueOf(diner.scoraxTableZoneA[btn].getEnergyLevel()));
                            trainee.setIncidentRecords("A " + errorMsg.getMessage() + " has occured for a " + diner.scoraxTableZoneA[btn].getSpecies() + " in seat " + (btn + 1));
                            JOptionPane.showMessageDialog(null, "A " + errorMsg.getMessage() + " has occured for a " + diner.scoraxTableZoneA[btn].getSpecies() + " in seat " + (btn + 1), "Poor Service", JOptionPane.WARNING_MESSAGE);
                            diner.clearSeat(btn, diner.scoraxTableZoneA[btn].getSpeciesCode());
                            trainee.setServicePoints(trainee.getServicePoints() - 3);
                            trainee.setNumDeaths(trainee.getNumDeaths() + 1);
                        }
                        catch (FullyFedException errorMsg) {
                            scoraSeats[btn].setText(String.valueOf(diner.scoraxTableZoneA[btn].getEnergyLevel()));
                            trainee.setIncidentRecords("There is a " + errorMsg.getMessage() + " " + diner.scoraxTableZoneA[btn].getSpecies() + " in seat " + (btn + 1));
                            JOptionPane.showMessageDialog(null, "There is a " + errorMsg.getMessage() + " " + diner.scoraxTableZoneA[btn].getSpecies() + " in seat " + (btn + 1), "Good Service", JOptionPane.INFORMATION_MESSAGE);
                            diner.clearSeat(btn, diner.scoraxTableZoneA[btn].getSpeciesCode());
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
                        totalDishesServed++;
                        try {
                            diner.zoraxTableZoneA[btn].FeedMe(dish[whichDishToServe]);
                        }
                        catch (GreedyGutsException errorMsg) {
                            zoraSeats[btn].setText(String.valueOf(diner.zoraxTableZoneA[btn].getEnergyLevel()));
                            trainee.setIncidentRecords("A " + errorMsg.getMessage() + " has occured for a " + diner.zoraxTableZoneA[btn].getSpecies() + " in seat " + (btn + 1));
                            JOptionPane.showMessageDialog(null, "A " + errorMsg.getMessage() + " has occured for a " + diner.zoraxTableZoneA[btn].getSpecies() + " in seat " + (btn + 1), "Poor Service", JOptionPane.WARNING_MESSAGE);
                            diner.clearSeat(btn, diner.zoraxTableZoneA[btn].getSpeciesCode());
                            trainee.setServicePoints(trainee.getServicePoints() - 3);
                            trainee.setNumDeaths(trainee.getNumDeaths() + 1);
                        }
                        catch (FullyFedException errorMsg) {
                            zoraSeats[btn].setText(String.valueOf(diner.zoraxTableZoneA[btn].getEnergyLevel()));
                            trainee.setIncidentRecords("There is a " + errorMsg.getMessage() + " " + diner.zoraxTableZoneA[btn].getSpecies() + " in seat " + (btn + 1));
                            JOptionPane.showMessageDialog(null, "There is a " + errorMsg.getMessage() + " " + diner.zoraxTableZoneA[btn].getSpecies() + " in seat " + (btn + 1), "Good Service", JOptionPane.INFORMATION_MESSAGE);
                            diner.clearSeat(btn, diner.zoraxTableZoneA[btn].getSpeciesCode());
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
        if (whoToSeat != 999) {
            for (int btn = 0; btn < 15; btn++) {
                // Check which Scoraxian button was pressed
                if (e.getSource() == scoraSeats[btn]) {
                    // Ensure seat is empty
                    if (scoraSeats[btn].getText() == "0") {
                        // Check that the diner is a Scoraxian
                        if (dinerType[whoToSeat].getSpeciesCode() == 's') {
                            if (!diner.fillSeat(btn, dinerType[whoToSeat])){ // Check if unable to seat them
                                // Check if the table is full
                                if (diner.getNumScoraxians() == 15) {
                                    JOptionPane.showConfirmDialog(null, "The Scoraxian table is full.", "Service", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            else { // Seated successfully
                                dinerType[whoToSeat] = null; // Move customer to seat
                                seatCWLbl[whoToSeat].setText("Empty Queue");
                                if (whoToSeat == 0)
                                    seat1CWBtn.setEnabled(false);
                                else
                                    seat2CWBtn.setEnabled(false);
                                whoToSeat = 999;
                            }
                        }
                        else { // Warn about seating the wrong species at the table
                            JOptionPane.showConfirmDialog(null, "A Zoraxian cannot sit here!", "Service", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else { // Warn about seating in an occupied space
                        JOptionPane.showConfirmDialog(null, "Seat is occupied", "Service", JOptionPane.WARNING_MESSAGE);
                    }
                }
                // Check which Zoraxian button was pressed
                if (e.getSource() == zoraSeats[btn]) {
                    // Ensure seat is empty
                    if (zoraSeats[btn].getText() == "0") {
                        // Check that the diner is a Zoraxian
                        if (dinerType[whoToSeat].getSpeciesCode() == 'z') {
                            if (!diner.fillSeat(btn, dinerType[whoToSeat])){ // Check if unable to seat them
                                // Check if the table is full
                                if (diner.getNumZoraxians() == 15) {
                                    JOptionPane.showConfirmDialog(null, "The Zoraxian table is full.", "Service", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            else { // Seated successfully
                                dinerType[whoToSeat] = null; // Move customer to seat
                                seatCWLbl[whoToSeat].setText("Empty Queue");
                                if (whoToSeat == 0)
                                    seat1CWBtn.setEnabled(false);
                                else
                                    seat2CWBtn.setEnabled(false);
                                whoToSeat = 999;
                            }
                        }
                        else { // Warn about seating the wrong species at the table
                            JOptionPane.showConfirmDialog(null, "A Scoraxian cannot sit here!", "Service", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else { // Warn about seating in an occupied space
                        JOptionPane.showConfirmDialog(null, "Seat is occupied", "Service", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } //end for loop
        }
        // System.out.println(trainee.getIncidentRecords()); //delete this!!!!!*********************************
        System.out.println("round: "+roundNumber+"\tDish Total: "+totalDishesServed);
        
        if (e.getSource() == seat1CWBtn) {
            whoToSeat = 0;
            
        }
        if (e.getSource() == seat2CWBtn) {
            whoToSeat = 1;
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
        Border cwblueBorder = BorderFactory.createLineBorder(Color.BLUE, 2);
        Border cwpanelBorder = BorderFactory.createTitledBorder(cwblueBorder, "Customer Waiting", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 18));
        seatCWLbl = new JLabel[2];
        seatCWLbl[0] = new JLabel("Empty Queue");
        seatCWLbl[1] = new JLabel("Empty Queue");
        JLabel ambroxLbl = new JLabel("Curious Ambroxain?");
        seat1CWBtn = new JButton("Seat");
        seat1CWBtn.setEnabled(false);
        seat2CWBtn = new JButton("Seat");
        seat2CWBtn.setEnabled(false);
        ambroxCWBtn = new JButton("No");
        ambroxCWBtn.setEnabled(false);

        // Set up Customer Waiting Section
        cwPanel.setPreferredSize(new Dimension(350, 400));
        cwPanel.setBorder(cwpanelBorder);
        cwPanel.setLayout(new GridLayout(3,2));

        seatCWLbl[0].setPreferredSize(new Dimension(150, 50));
        seatCWLbl[0].setFont(new Font("Arial", Font.PLAIN, 12));
        seatCWLbl[1].setPreferredSize(new Dimension(150, 50));
        seatCWLbl[1].setFont(new Font("Arial", Font.PLAIN, 12));
        ambroxLbl.setPreferredSize(new Dimension(150, 50));
        ambroxLbl.setFont(new Font("Arial", Font.PLAIN, 12));

        //Customer waiting right grid
        //seat1CWBtn.setPreferredSize(new Dimension(100, 30));
        seat1CWBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        seat1CWBtn.setSize(50, 50);
        seat1CWBtn.addActionListener(this);
        seat2CWBtn.setPreferredSize(new Dimension(275, 50));
        seat2CWBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        seat2CWBtn.setSize(50, 50);
        seat2CWBtn.addActionListener(this);
        ambroxCWBtn.setPreferredSize(new Dimension(275, 50));
        ambroxCWBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        ambroxCWBtn.setSize(50, 50);

        //Adding components to Customer Waiting panel
        cwPanel.add(seatCWLbl[0]);
        cwPanel.add(seat1CWBtn);
        cwPanel.add(seatCWLbl[1]);
        cwPanel.add(seat2CWBtn);
        cwPanel.add(ambroxLbl);
        cwPanel.add(ambroxCWBtn);

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
            scoraSeats[i].setText(String.valueOf(diner.scoraxTableZoneA[i]!=null?diner.scoraxTableZoneA[i].getEnergyLevel():"0"));
    }
        for (int i = 0; i < zoraSeats.length; i++) {
            zoraSeats[i].setText(String.valueOf(diner.zoraxTableZoneA[i]!=null?diner.zoraxTableZoneA[i].getEnergyLevel():"0"));
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

    public void updateCustomerWaiting() {
        Random cwType = new Random();
        dinerType = new Diner[2];
        for (int idx = 0; idx < dinerType.length; idx++) {
            if (cwType.nextBoolean() && dinerType[idx] == null) {
                dinerType[idx] = new Zoraxian("Zora");
                seatCWLbl[idx].setText(String.valueOf((dinerType[idx].getSpecies() + " (" + dinerType[idx].getEnergyLevel() +")")));
            }
            else {
                dinerType[idx] = new Scoraxian("Scora");
                seatCWLbl[idx].setText(String.valueOf((dinerType[idx].getSpecies() + " (" + dinerType[idx].getEnergyLevel() +")")));
            }
        }
        seat1CWBtn.setEnabled(true);
        seat2CWBtn.setEnabled(true);
    }

    private void resetRound() {
        // Reset for next round if all dishes were used
        if (totalDishesServed == 6) {
            totalDishesServed = 0;
            roundNumber++;
            JOptionPane.showMessageDialog(null, "Round " + roundNumber + " completed");
            this.updateFoodList();
            if (roundNumber > 0) {
                if (roundNumber % 2 == 0) { //Check every even round 2, 4 or 6
                    for (int idx = 0; idx < diner.scoraxTableZoneA.length; idx++) {
                        if (diner.scoraxTableZoneA[idx] != null)
                            diner.scoraxTableZoneA[idx].setEnergyLevel(diner.scoraxTableZoneA[idx].getEnergyLevel() - 1);
                        if (diner.zoraxTableZoneA[idx] != null)
                            diner.zoraxTableZoneA[idx].setEnergyLevel(diner.zoraxTableZoneA[idx].getEnergyLevel() - 1);
                    }
                    updateZoneAPanel();
                    String val = null;
                    do {
                        val = diner.zoraxianEnergyCheck(); // Returns incidents of weak Zoraxians and their outcome
                        if (val != null && val != "max")
                            JOptionPane.showConfirmDialog(null, val, "Poor Service", JOptionPane.WARNING_MESSAGE);
                            updateZoneAPanel();
                            trainee.setIncidentRecords(val);
                            trainee.setServicePoints(trainee.getServicePoints() - 3);
                    } while (val != "max");
                    updateCustomerWaiting();
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