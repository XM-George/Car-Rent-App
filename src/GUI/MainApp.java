package GUI;

import API.Customer;
import API.User;
import API.Vehicle;
import util.I18n;

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

        JMenu userMenu = new JMenu(I18n.getString("users"));

        JMenuItem currentUser = new JMenuItem(I18n.getString("user.current"));
        JMenuItem addUser = new JMenuItem(I18n.getString("user.add"));
        JMenuItem removeUser = new JMenuItem(I18n.getString("user.delete"));
        JMenuItem signOut = new JMenuItem(I18n.getString("disconnect"));

        JMenu customerMenu = new JMenu(I18n.getString("customers"));

        JMenuItem addCustomer = new JMenuItem(I18n.getString("customer.add"));
        JMenuItem editCustomer = new JMenuItem(I18n.getString("customer.edit.info"));
        JMenuItem searchCustomer = new JMenuItem(I18n.getString("customer.search"));
        JMenuItem customerHistory = new JMenuItem(I18n.getString("customer.history.see"));

        JMenu vehicleMenu = new JMenu(I18n.getString("vehicles"));

        JMenuItem addVehicle = new JMenuItem(I18n.getString("vehicle.add"));
        JMenuItem editVehicle = new JMenuItem(I18n.getString("vehicle.edit.info"));
        JMenuItem searchVehicle = new JMenuItem(I18n.getString("vehicle.search"));
        JMenuItem vehicleHistory = new JMenuItem(I18n.getString("vehicle.history.see"));

        JMenu rentMenu = new JMenu(I18n.getString("rent"));

        JMenuItem rentVehicle = new JMenuItem(I18n.getString("vehicle.rent"));
        JMenuItem returnVehicle = new JMenuItem(I18n.getString("vehicle.return"));


        userMenu.setMnemonic(KeyEvent.VK_U);

        userMenu.add(currentUser);
        currentUser.setText(I18n.getString("user.current") + ":  " + username);
        currentUser.setEnabled(false);

        userMenu.add(addUser);
        addUser.setMnemonic(KeyEvent.VK_A);
        addUser.setActionCommand("ADD_USER");
        addUser.addActionListener(this);

        userMenu.add(removeUser);
        removeUser.setMnemonic(KeyEvent.VK_R);
        removeUser.setActionCommand("REMOVE_USER");
        removeUser.addActionListener(this);

        userMenu.add(signOut);
        signOut.setMnemonic(KeyEvent.VK_S);
        signOut.setActionCommand("SIGN_OUT");
        signOut.addActionListener(this);


        customerMenu.setMnemonic(KeyEvent.VK_C);

        customerMenu.add(addCustomer);
        addCustomer.setMnemonic(KeyEvent.VK_A);
        addCustomer.setActionCommand("ADD_CUSTOMER");
        addCustomer.addActionListener(this);

        customerMenu.add(editCustomer);
        editCustomer.setMnemonic(KeyEvent.VK_E);
        editCustomer.setActionCommand("EDIT_CUSTOMER");
        editCustomer.addActionListener(this);

        customerMenu.add(searchCustomer);
        searchCustomer.setMnemonic(KeyEvent.VK_S);
        searchCustomer.setActionCommand("SEARCH_CUSTOMER");
        searchCustomer.addActionListener(this);

        customerMenu.add(customerHistory);
        customerHistory.setMnemonic(KeyEvent.VK_H);
        customerHistory.setActionCommand("HISTORY_CUSTOMER");
        customerHistory.addActionListener(this);


        vehicleMenu.setMnemonic(KeyEvent.VK_V);

        vehicleMenu.add(addVehicle);
        addVehicle.setMnemonic(KeyEvent.VK_A);
        addVehicle.setActionCommand("ADD_VEHICLE");
        addVehicle.addActionListener(this);

        vehicleMenu.add(editVehicle);
        editVehicle.setMnemonic(KeyEvent.VK_E);
        editVehicle.setActionCommand("EDIT_VEHICLE");
        editVehicle.addActionListener(this);

        vehicleMenu.add(searchVehicle);
        searchVehicle.setMnemonic(KeyEvent.VK_S);
        searchVehicle.setActionCommand("SEARCH_VEHICLE");
        searchVehicle.addActionListener(this);

        vehicleMenu.add(vehicleHistory);
        vehicleHistory.setMnemonic(KeyEvent.VK_H);
        vehicleHistory.setActionCommand("HISTORY_VEHICLE");
        vehicleHistory.addActionListener(this);


        rentMenu.setMnemonic(KeyEvent.VK_R);

        rentMenu.add(rentVehicle);
        rentVehicle.setMnemonic(KeyEvent.VK_R);
        rentVehicle.setActionCommand("RENT_VEHICLE");
        rentVehicle.addActionListener(this);

        rentMenu.add(returnVehicle);
        returnVehicle.setMnemonic(KeyEvent.VK_T);
        returnVehicle.setActionCommand("RETURN_VEHICLE");
        returnVehicle.addActionListener(this);


        menuBar.add(userMenu);
        menuBar.add(customerMenu);
        menuBar.add(vehicleMenu);
        menuBar.add(rentMenu);
        frame.setJMenuBar(menuBar);

        JButton userButton = buttonForMainApp(I18n.getString("users.show"),"/icons/userUI.png");
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

        JButton customerButton = buttonForMainApp(I18n.getString("customers.show"), "/icons/customerUI.png");
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

        JButton vehicleButton = buttonForMainApp(I18n.getString("vehicles.show"), "/icons/carUI.png");
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
        JButton rentButton = buttonForMainApp(I18n.getString("vehicle.rent"), "/icons/car_rentUI.png");
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RentDialogUI r = new RentDialogUI();
                r.RentVehicleUI("Rent", username);
            }
        });
        JButton returnButton = buttonForMainApp(I18n.getString("vehicle.return"), "/icons/car_rentUI.png");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RentDialogUI r = new RentDialogUI();
                r.RentVehicleUI("Return", username);
            }
        });




        JPanel gridPanel = new JPanel(new GridLayout(2, 0, 40, 40));
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
            case "ADD_USER":
                u.AddUser();
                break;
            case "REMOVE_USER":
                u.RemoveUser(username);
                break;
            case "SIGN_OUT":
                u.SignOut(frame);
                break;
            case "ADD_CUSTOMER":
                c.AddCustomer();
                break;
            case "EDIT_CUSTOMER":
                c.EditCustomerDetails();
                break;
            case "SEARCH_CUSTOMER":
                c.SearchCustomer();
                break;
            case "HISTORY_CUSTOMER":
                c.SeeCustomerHistory();
                break;
            case "ADD_VEHICLE":
                v.AddVehicle();
                break;
            case "EDIT_VEHICLE":
                v.EditVehicleDetails();
                break;
            case "SEARCH_VEHICLE":
                v.SearchVehicle();
                break;
            case "HISTORY_VEHICLE":
                v.SeeVehicleHistory();
                break;
            case "RENT_VEHICLE":
                r.RentVehicleUI("Rent", username);
                break;
            case "RETURN_VEHICLE":
                r.ReturnVehicleUI();
                break;
        }


    }
}
