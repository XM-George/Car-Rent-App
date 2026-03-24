package GUI;

import API.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class CustomerDialogUI extends BaseDialogUI {

    String[] customerOptions = {"Όνομα", "Επίθετο", "Τηλέφωνο", "Email", "ΑΦΜ"};
    String typeOf = "Πελάτης";
    String filename = "/icons/customerUI.png";
    EntitySearch search = new EntitySearch();

    public void AddCustomer() {
        String[] result = UIConstructor("Προσθήκη Πελάτη", typeOf, customerOptions, "Add", filename);
        if (!result[customerOptions.length].equals("Cancel")) {
            int check = CustomerHelp.checkCustomerDetails(result);
            if (check == -1) {
                new Customer(result);
            }
            CheckerUI(check, typeOf, "Add");
        }
    }

    public void EditCustomerDetails() {
        String[] result = UIConstructor("Επεξεργασία Πληροφοριών Πελάτη", typeOf, customerOptions, "Find", filename);
        if (!result[customerOptions.length].equals("Cancel")) {
            int s = search.searchEntity(result, typeOf);
            int selection;
            if (s < 0) {
                entityNotFound(400, 200, typeOf, s);
            } else if (s > 0) {
                ArrayList<Customer> c = CustomerHelp.SearchCustomer(result);
                selection = TableSearchResult(typeOf, "Edit", c);
                if (selection >= 0) {
                    int confirm = ConfirmUI(c.get(selection), "Edit", typeOf);
                    if (confirm == 1) {
                        EditCustomerDialog(c.get(selection));
                    }
                }
            }
        }

    }

    public void SearchCustomer() {
        String[] result = UIConstructor("Αναζήτηση Πελάτη", typeOf, customerOptions, "Find", filename);
        if (!result[customerOptions.length].equals("Cancel")) {
            int s = search.searchEntity(result, typeOf);
            if (s < 0) {
                entityNotFound(400, 200, typeOf, s);
            } else if (s > 0) {
                ArrayList<Customer> c = CustomerHelp.SearchCustomer(result);
                TableSearchResult(typeOf, "Search", c);
            }
        }
    }

    public void SeeCustomerHistory() {
        String[] result = UIConstructor("Προβολή Ιστορικού Πελάτη", typeOf, customerOptions, "Find", filename);
        if (!result[customerOptions.length].equals("Cancel")) {
            int s = search.searchEntity(result, typeOf);
            int selection;
            if (s < 0) {
                entityNotFound(400, 200, typeOf, s);
            } else if (s > 0) {
                ArrayList<Customer> c = CustomerHelp.SearchCustomer(result);
                selection = TableSearchResult(typeOf, "History", c);
                if (selection >= 0)
                {
                    int confirm = new BaseDialogUI().ConfirmUI(c.get(selection), "History", typeOf);
                    if(confirm == 1)
                    {
                        if(!c.get(selection).getRents().isEmpty())
                        {
                            TableSearchResult("Ιστορικό", "Search", c.get(selection).getRents());
                        }
                        else
                        {
                            emptyArray("Ιστορικό");
                        }
                    }
                }
            }
        }
    }

    protected void EditCustomerDialog(Customer c) {
        String[] fields = {c.getName(), c.getSurname(), c.getPhone(), c.getEmail(), c.getAfm()};
        String[] result = EditCustomerUIConstructor("Επεξεργασία Πληροφοριών Πελάτη", customerOptions, filename, fields);
        if (!result[customerOptions.length].equals("Cancel")) {
            Customer.getCustomerAfm().remove(c.getAfm());
            int check = CustomerHelp.checkCustomerDetails(result);
            if (check == -1) {
                c.EditAll(result);
                Customer.getCustomerAfm().add(result[4]);
            } else {
                Customer.getCustomerAfm().add(c.getAfm());
            }
            CheckerUI(check, typeOf, "Edit");
        }
    }

    protected String[] EditCustomerUIConstructor(String title, String[] types, String filename, String[] fields) {
        int times = types.length;
        JDialog dialog = createDialog(title, 400, 100 * (times + 1));
        int j = 10;
        if (filename != null) {
            ImageIcon userUIImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
            dialog.setIconImage(userUIImage.getImage());
        }
        String[] returnValues = new String[times + 1];
        JTextField[] textFields = new JTextField[times];
        for (int i = 0; i < times; i++) {
            JLabel label = createLabel(types[i] + ":", 50, j, 300, 30);

            JTextField field;

            dialog.add(label);

            int k = j + 30;

            field = createTextField(50, k, 300, 40);
            field.setText(fields[i]);
            textFields[i] = field;
            dialog.add(field);


            j += 90;
        }


        JButton editButton = createButton("Επεξεργασία Πελάτη", 15, times * 100, 260, 50);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < times; i++) {
                    returnValues[i] = textFields[i].getText();
                }
                returnValues[times] = "";
                dialog.dispose();
            }
        });
        dialog.add(editButton);

        JButton cancelButton = createButton("Άκυρο", 290, times * 100, 95, 50);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arrays.fill(returnValues, null);
                returnValues[times] = "Cancel";
                dialog.dispose();
            }
        });
        dialog.add(cancelButton);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Arrays.fill(returnValues, null);
                returnValues[times] = "Cancel";
            }
        });

        dialog.setVisible(true);

        return returnValues;
    }
}

