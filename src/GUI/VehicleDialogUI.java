package GUI;

import API.EntitySearch;
import API.Vehicle;
import API.VehicleHelp;
import util.I18n;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class VehicleDialogUI extends BaseDialogUI {
    String[] vehicleOptions = {"ID", "Πινακίδα", "Μάρκα", "Τύπος", "Μοντέλο", "Έτος", "Χρώμα", "Κατάσταση"};
    String typeOf = "vehicle";
    String filename = "/icons/carUI.png";
    EntitySearch search = new EntitySearch();

    public void AddVehicle() {

        String[] result = UIConstructor("Προσθήκη Οχήματος", typeOf, vehicleOptions, "Add", filename);
        if (!result[vehicleOptions.length].equals("Cancel")) {
            int check = VehicleHelp.checkVehicleDetails(result, null);
            if (check == -1) {
                new Vehicle(result);
            }
            CheckerUI(check, typeOf, "Add");
        }
    }

    public void EditVehicleDetails() {
        String[] result = UIConstructor("Επεξεργασία Πληροφοριών Οχήματος", typeOf, vehicleOptions, "Find", filename);
        if (!result[vehicleOptions.length].equals("Cancel")) {
            int s = search.searchEntity(result, typeOf);
            int selection;
            if (s < 0) {
                entityNotFound(400, 200, typeOf, s);
            } else if (s > 0) {
                ArrayList<Vehicle> v = VehicleHelp.SearchVehicles(result);
                selection = TableSearchResult(typeOf, "Edit", v);
                if (selection >= 0) {
                    int confirm = ConfirmUI(v.get(selection), "Edit", typeOf);
                    if (confirm == 1) {
                        EditVehicleDialog(v.get(selection));
                    }
                }
            }
        }
    }

    public void SearchVehicle() {
        String[] result = UIConstructor("Αναζήτηση Οχήματος", typeOf, vehicleOptions, "Find", filename);
        if (!result[vehicleOptions.length].equals("Cancel")) {
            int s = search.searchEntity(result, typeOf);
            if (s < 0) {
                entityNotFound(400, 200, typeOf, s);
            } else if (s > 0) {
                ArrayList<Vehicle> v = VehicleHelp.SearchVehicles(result);
                TableSearchResult(typeOf, "Search", v);
            }
        }
    }

    public void SeeVehicleHistory() {
        String[] result = UIConstructor("Προβολή Ιστορικού Οχήματος", typeOf, vehicleOptions, "Find", filename);
        if (!result[vehicleOptions.length].equals("Cancel")) {
            int s = search.searchEntity(result, typeOf);
            int selection;
            if (s < 0) {
                entityNotFound(400, 200, typeOf, s);
            } else if (s > 0) {
                ArrayList<Vehicle> v = VehicleHelp.SearchVehicles(result);
                selection = TableSearchResult(typeOf, "History", v);
                if (selection >= 0)
                {
                    int confirm =ConfirmUI(v.get(selection), "History", typeOf);
                    if(confirm == 1)
                     {
                         if(!v.get(selection).getRents().isEmpty())
                         {
                             TableSearchResult("history", "Search", v.get(selection).getRents());
                         }
                         else
                         {
                             emptyArray("history");
                         }
                     }
                }
            }
        }
    }

    protected void EditVehicleDialog(Vehicle v) {
        String[] fields = {v.getId(), v.getPlate(), v.getBrand(), v.getType(), v.getModel(), v.getYearOfProduction(), v.getColor(), v.getState()};
        String[] result = EditVehicleUIConstructor("Επεξεργασία Πληροφοριών Οχήματος", vehicleOptions, filename, fields);
        if (!result[vehicleOptions.length].equals("Cancel")) {
            Vehicle.getIDs().remove(v.getId());
            Vehicle.getNPs().remove(v.getPlate());
            int check = VehicleHelp.checkVehicleDetails(result, v);
            if (check == -1) {
                v.EditAll(result);
                Vehicle.getIDs().add(result[0]);
                Vehicle.getNPs().add(result[1]);
            } else {
                Vehicle.getIDs().add(v.getId());
                Vehicle.getNPs().add(v.getPlate());
            }
            CheckerUI(check, typeOf, "Edit");
        }
    }

    protected String[] EditVehicleUIConstructor(String title, String[] types, String filename, String[] fields) {
        int times = types.length;
        JDialog dialog = createDialog(title, 400, 100 * (times + 1));
        int j = 10;
        if (filename != null) {
            ImageIcon userUIImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
            dialog.setIconImage(userUIImage.getImage());
        }
        String[] returnValues = new String[times + 1];
        JTextField[] textFields = new JTextField[times];
        @SuppressWarnings("unchecked")
        JComboBox<String>[] comboBoxes = new JComboBox[times];
        for (int i = 0; i < times; i++) {
            JLabel label = createLabel(types[i] + ":", 50, j, 300, 30);

            JTextField field;

            dialog.add(label);

            int k = j + 30;

            if (types[i].equals("Κατάσταση")) {
                String[] a = {"Διαθέσιμο", "Ενοικιασμένο"};
                JComboBox<String> comboBox = createComboBox(a, 50, k, 300, 40);
                comboBox.setSelectedItem(fields[i]);
                comboBoxes[i] = comboBox;
                dialog.add(comboBox);
            } else {
                field = createTextField(50, k, 300, 40);
                field.setText(fields[i]);
                textFields[i] = field;
                dialog.add(field);
            }

            j += 90;
        }


        JButton editButton = createButton("Επεξεργασία Οχήματος", 15, times * 100, 260, 50);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < times; i++) {
                    if (!types[i].equals("Κατάσταση")) {
                        returnValues[i] = textFields[i].getText();
                    } else {
                        returnValues[i] = (String) comboBoxes[i].getSelectedItem();
                    }
                }
                returnValues[times] = "";
                dialog.dispose();
            }
        });
        dialog.add(editButton);

        JButton cancelButton = createButton(I18n.getString("cancel"), 290, times * 100, 95, 50);
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