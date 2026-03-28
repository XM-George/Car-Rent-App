package GUI;

import API.Customer;
import API.User;
import API.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;

public class MainApp implements ActionListener
{

    private String username;

    public MainApp(String username)
    {
        this.username = username;
        startMainApp(username);
    }

    JFrame frame = new JFrame();

    protected void startMainApp(String username)
    {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(screenSize.width/2,screenSize.height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setTitle("CarRent");
        frame.setLocationRelativeTo(null);
        ImageIcon carRentImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/car_rent_main.png")));
        frame.setIconImage(carRentImage.getImage());

        JMenuBar menuBar = new JMenuBar();

        JMenu userMenu = new JMenu("Χρήστες");

        JMenuItem currentUser = new JMenuItem("Τρέχων Χρήστης");
        JMenuItem addUser = new JMenuItem("Προσθήκη Χρήστη");
        JMenuItem removerUser = new JMenuItem("Διαγραφή Χρήστη");
        JMenuItem signOut = new JMenuItem("Αποσύνδεση");

        JMenu customerMenu = new JMenu("Πελάτες");

        JMenuItem addCustomer = new JMenuItem("Προσθήκη Πελάτη");
        JMenuItem editCustomer = new JMenuItem("Επεξεργασία Πληροφοριών Πελάτη");
        JMenuItem searchCustomer = new JMenuItem("Αναζήτηση Πελάτη");
        JMenuItem customerHistory = new JMenuItem("Προβολή Ιστορικού Πελάτη");

        JMenu vehicleMenu = new JMenu("Οχήματα");

        JMenuItem addVehicle = new JMenuItem("Προσθήκη Οχήματος");
        JMenuItem editVehicle = new JMenuItem("Επεξεργασία Πληροφοριών Οχήματος");
        JMenuItem searchVehicle = new JMenuItem("Αναζήτηση Οχήματος");
        JMenuItem vehicleHistory = new JMenuItem("Προβολή Ιστορικού Οχήματος");

        JMenu rentMenu = new JMenu("Ενοικίαση");

        JMenuItem rentVehicle = new JMenuItem("Ενοικίαση Οχήματος");
        JMenuItem returnVehicle = new JMenuItem("Επιστροφή Οχήματος");


        userMenu.setMnemonic(KeyEvent.VK_U);

        userMenu.add(currentUser);
        currentUser.setText("Τρέχων Χρήστης :  " + username);
        currentUser.setEnabled(false);

        userMenu.add(addUser);
        addUser.setMnemonic(KeyEvent.VK_A);
        addUser.addActionListener(this);

        userMenu.add(removerUser);
        removerUser.setMnemonic(KeyEvent.VK_R);
        removerUser.addActionListener(this);

        userMenu.add(signOut);
        signOut.setMnemonic(KeyEvent.VK_S);
        signOut.addActionListener(this);


        customerMenu.setMnemonic(KeyEvent.VK_C);

        customerMenu.add(addCustomer);
        addCustomer.setMnemonic(KeyEvent.VK_A);
        addCustomer.addActionListener(this);

        customerMenu.add(editCustomer);
        editCustomer.setMnemonic(KeyEvent.VK_E);
        editCustomer.addActionListener(this);

        customerMenu.add(searchCustomer);
        searchCustomer.setMnemonic(KeyEvent.VK_S);
        searchCustomer.addActionListener(this);

        customerMenu.add(customerHistory);
        customerHistory.setMnemonic(KeyEvent.VK_H);
        customerHistory.addActionListener(this);


        vehicleMenu.setMnemonic(KeyEvent.VK_V);

        vehicleMenu.add(addVehicle);
        addVehicle.addActionListener(this);
        addVehicle.setMnemonic(KeyEvent.VK_A);

        vehicleMenu.add(editVehicle);
        editVehicle.addActionListener(this);
        editVehicle.setMnemonic(KeyEvent.VK_E);

        vehicleMenu.add(searchVehicle);
        searchVehicle.addActionListener(this);
        searchVehicle.setMnemonic(KeyEvent.VK_S);

        vehicleMenu.add(vehicleHistory);
        vehicleHistory.addActionListener(this);
        vehicleHistory.setMnemonic(KeyEvent.VK_H);


        rentMenu.setMnemonic(KeyEvent.VK_R);

        rentMenu.add(rentVehicle);
        rentVehicle.setMnemonic(KeyEvent.VK_R);
        rentVehicle.addActionListener(this);

        rentMenu.add(returnVehicle);
        returnVehicle.setMnemonic(KeyEvent.VK_T);
        returnVehicle.addActionListener(this);


        menuBar.add(userMenu);
        menuBar.add(customerMenu);
        menuBar.add(vehicleMenu);
        menuBar.add(rentMenu);
        frame.setJMenuBar(menuBar);

        JButton userButton = buttonForMainApp("Προβολή Όλων Των Χρηστών","/icons/userUI.png");
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> u = User.getUsers();
                BaseDialogUI baseDialog = new BaseDialogUI();
                if(!u.isEmpty()){
                    baseDialog.TableSearchResult("user", "Search", u);
                }
                else
                {
                    baseDialog.emptyArray("user");
                }
            }
        });

        JButton customerButton = buttonForMainApp("Προβολή Όλων Των Πελατών", "/icons/customerUI.png");
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Customer> c = Customer.getCustomers();
                CustomerDialogUI customerDialog = new CustomerDialogUI();
                BaseDialogUI baseDialog = new BaseDialogUI();
                if(!c.isEmpty()){
                    int selection = baseDialog.TableSearchResult("customer", "All", c);
                    if (selection >= 0)
                    {
                        int confirm;
                        if(selection < Customer.getCustomers().size())
                        {
                            confirm = baseDialog.ConfirmUI(c.get(selection), "Edit", "customer");
                            if (confirm == 1)
                            {
                                customerDialog.EditCustomerDialog(c.get(selection));
                            }
                        }
                        else
                        {
                            confirm = baseDialog.ConfirmUI(c.get(selection - Customer.getCustomers().size()), "History", "customer");
                            if (confirm == 1)
                            {
                                if(!c.get(selection - Customer.getCustomers().size()).getRents().isEmpty())
                                {
                                    baseDialog.TableSearchResult("history", "Search", c.get(selection - Customer.getCustomers().size()).getRents());
                                }
                                else
                                {
                                    baseDialog.emptyArray("history");
                                }
                            }
                        }
                    }
                }
                else{
                    baseDialog.emptyArray("customer");
                }
            }
        });

        JButton vehicleButton = buttonForMainApp("Προβολή Όλων Των Οχημάτων", "/icons/carUI.png");
        vehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Vehicle> v = Vehicle.getCars();
                VehicleDialogUI vehicleDialog = new VehicleDialogUI();
                BaseDialogUI baseDialog = new BaseDialogUI();
                if(!v.isEmpty()){
                    int selection = baseDialog.TableSearchResult("vehicle", "All", v);
                    if (selection >= 0)
                    {
                        int confirm;
                        if(selection < Vehicle.getCars().size())
                        {
                            confirm = baseDialog.ConfirmUI(v.get(selection), "Edit", "vehicle");
                            if (confirm == 1)
                            {
                                vehicleDialog.EditVehicleDialog(v.get(selection));
                            }
                        }
                        else
                        {
                            confirm = baseDialog.ConfirmUI(v.get(selection - Vehicle.getCars().size()), "History", "vehicle");
                            if (confirm == 1)
                            {
                                if(!v.get(selection - Vehicle.getCars().size()).getRents().isEmpty())
                                {
                                    baseDialog.TableSearchResult("history",  "Search", v.get(selection - Vehicle.getCars().size()).getRents());
                                }
                                else
                                {
                                    baseDialog.emptyArray("history");
                                }
                            }
                        }
                    }
                }
                else{
                    baseDialog.emptyArray("vehicle");
                }
            }
        });
        JButton rentButton = buttonForMainApp("Ενοικίαση Οχήματος", "/icons/car_rentUI.png");
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RentDialogUI r = new RentDialogUI();
                r.RentVehicleUI("Rent", username);
            }
        });
        JButton returnButton = buttonForMainApp("Επιστροφή Οχήματος", "/icons/car_rentUI.png");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RentDialogUI r = new RentDialogUI();
                r.RentVehicleUI("Return", username);
            }
        });




        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 40, 40));
        gridPanel.add(userButton);
        gridPanel.add(customerButton);
        gridPanel.add(vehicleButton);
        gridPanel.add(rentButton);
        gridPanel.add(returnButton);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add(gridPanel);

        frame.setLayout(new BorderLayout());
        frame.add(wrapper, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    protected JButton buttonForMainApp(String text, String filename)
    {
        JButton button = new JButton(text);
        ImageIcon rawIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        Image scaledImage = rawIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        button.setFocusable(false);
        button.setIconTextGap(5);

        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setHorizontalAlignment(SwingConstants.CENTER);

        return button;

    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        UserDialogUI u = new UserDialogUI();
        CustomerDialogUI c = new CustomerDialogUI();
        VehicleDialogUI v = new VehicleDialogUI();
        RentDialogUI r = new RentDialogUI();
        switch (e.getActionCommand()){
            case "Προσθήκη Χρήστη":
                u.AddUser();
                break;
            case "Διαγραφή Χρήστη":
                u.RemoveUser(username);
                break;
            case "Αποσύνδεση":
                u.SignOut(frame);
                break;
            case "Προσθήκη Πελάτη":
                c.AddCustomer();
                break;
            case "Επεξεργασία Πληροφοριών Πελάτη":
                c.EditCustomerDetails();
                break;
            case "Αναζήτηση Πελάτη":
                c.SearchCustomer();
                break;
            case "Προβολή Ιστορικού Πελάτη":
                c.SeeCustomerHistory();
                break;
            case "Προσθήκη Οχήματος":
                v.AddVehicle();
                break;
            case "Επεξεργασία Πληροφοριών Οχήματος":
                v.EditVehicleDetails();
                break;
            case "Αναζήτηση Οχήματος":
                v.SearchVehicle();
                break;
            case "Προβολή Ιστορικού Οχήματος":
                v.SeeVehicleHistory();
                break;
            case "Ενοικίαση Οχήματος":
                r.RentVehicleUI("Rent", username);
                break;
            case "Επιστροφή Οχήματος":
                r.ReturnVehicleUI();
                break;
        }


    }
}
