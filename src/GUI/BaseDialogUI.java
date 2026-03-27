package GUI;

import API.Customer;
import API.Rent;
import API.User;
import API.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BaseDialogUI {


    private final Font FONT = new Font(null, Font.BOLD, 20);
    int screenWidth  = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;





    protected JDialog createDialog(String title, int width, int height)
    {
        JDialog dialog = new JDialog();
        dialog.getContentPane().setPreferredSize(new Dimension(width, height));
        dialog.pack();
        dialog.setTitle(title);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(null);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        return dialog;
    }





    protected JLabel createLabel(String text, int WBound, int HBound, int width, int height)
    {
        JLabel label = new JLabel(text);
        label.setBounds(WBound, HBound, width, height);
        label.setFont(FONT);
        return label;
    }





    protected JLabel addIconToLabel(JLabel label, String filename)
    {
        ImageIcon rawIcon =  new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        Image scaledImage = rawIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));
        return label;
    }





    protected JTextField createTextField(int WBound, int HBound, int width, int height)
    {
        JTextField field = new JTextField();
        field.setBounds(WBound, HBound, width, height);
        field.setFont(FONT);
        return field;
    }





    protected JPasswordField createPasswordField(int WBound, int HBound, int width, int height)
    {
        JPasswordField field = new JPasswordField();
        field.setBounds(WBound, HBound, width, height);
        field.setFont(FONT);
        return field;
    }





    protected JButton createButton(String text, int WBound, int HBound, int width, int height)
    {
        JButton button = new JButton(text);
        button.setBounds(WBound, HBound, width, height);
        button.setFont(FONT);
        button.setFocusable(false);
        return button;
    }





    protected JComboBox<String> createComboBox(String[] text, int WBound, int HBound, int width, int height)
    {
        JComboBox<String> comboBox = new JComboBox<>();
        if(text!=null)
        {
             comboBox = new JComboBox<>(text);
        }
        comboBox.setFont(FONT);
        comboBox.setBounds(WBound, HBound, width, height);
        comboBox.setFocusable(false);
        return comboBox;

    }





    protected JTable createTable(DefaultTableModel model)
    {
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setRowHeight(24);
        table.setFillsViewportHeight(true);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.setDefaultRenderer(Object.class, leftRenderer);
        table.setDefaultRenderer(String.class, leftRenderer);
        table.setDefaultRenderer(Number.class, leftRenderer);
        table.setDefaultRenderer(Integer.class, leftRenderer);
        table.setDefaultRenderer(java.util.Date.class, leftRenderer);
        return table;
    }





    protected JTextArea createTextArea(String text, int WBound, int HBound, int width, int height)
    {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(FONT);
        textArea.setBounds(WBound, HBound, width, height);
        textArea.setEditable(false);
        return textArea;
    }





    protected String[] UIConstructor(String title, String typeOf, String[] types, String use, String filename)
    {
        int times = types.length;

        int dialogHeight = 400;
        String buttonText = "";
        switch(typeOf)
        {
            case ("Χρήστης"):
                switch(use)
                {
                    case ("Add"):
                        buttonText = "Προσθήκη Χρήστη";
                        break;
                    case ("Find"):
                        buttonText = "Αναζήτηση Χρήστη";
                        break;
                }
                dialogHeight = 450;
                break;
            case ("Πελάτης"):
                switch(use)
                {
                    case ("Add"):
                        buttonText = "Προσθήκη Πελάτη";
                        break;
                    case ("Find"):
                        buttonText = "Αναζήτηση Πελάτη";
                        break;
                }
                dialogHeight = 450;
                break;
            case ("Όχημα"):
                switch(use)
                {
                    case ("Add"):
                        buttonText = "Προσθήκη Οχήματος";
                        break;
                    case ("Find"):
                        buttonText = "Αναζήτηση Οχήματος";
                        break;
                }
                dialogHeight = 650;
                break;
        }

        JDialog dialog= createDialog(title, 400,dialogHeight);
        int j=10;
        if(filename != null)
        {
            ImageIcon userUIImage =  new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
            dialog.setIconImage(userUIImage.getImage());
        }
        String[] returnValues = new String[times+1];
        JTextField[] textFields = new JTextField[times];
        @SuppressWarnings("unchecked")
        JComboBox<String>[] comboBoxes = new JComboBox[times];
        for(int i=0;i<times;i++)
        {
            JLabel label = createLabel(types[i] + ":",50, j, 300,30);

            JTextField field;
            JPasswordField passField;


            dialog.add(label);
            int k=j+30;
            if(types[i].equals("Password"))
            {
                passField = createPasswordField(50, k, 300, 35);
                textFields[i] = passField;
                dialog.add(passField);
            }
            else if(types[i].equals("Κατάσταση"))
            {
                String[] a={"Διαθέσιμο", "Ενοικιασμένο"};
                JComboBox<String> comboBox = createComboBox(a, 50, k,300,35);
                if(use.equals("Add"))
                {
                    comboBox.setEnabled(false);
                }
                if(title.equals("Επιστροφή Οχήματος"))
                {
                    comboBox.setSelectedIndex(1);
                }
                comboBoxes[i] = comboBox;
                dialog.add(comboBox);
            }
            else
            {
                field = createTextField(50, k, 300, 35);
                textFields[i] = field;
                dialog.add(field);
            }

            j+=70;
        }



        JButton findButton = createButton(buttonText, 20, dialogHeight - 70, 240, 40);
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for(int i=0;i<times;i++)
                {
                    if(!types[i].equals("Κατάσταση"))
                    {
                        returnValues[i] = textFields[i].getText();
                    }
                    else
                    {
                        returnValues[i] = (String) comboBoxes[i].getSelectedItem();
                    }
                }
                returnValues[times] ="";
                dialog.dispose();
            }
        });
        dialog.add(findButton);

        JButton cancelButton = createButton("Άκυρο", 280, dialogHeight - 70, 100, 40);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
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





    JDialog entityNotFoundDialog;
    protected void entityNotFound(int width, int height,String entity, int use)
    {
        String filename;
        JLabel label = new JLabel();

        if(use == -1)
        {
            filename = "/icons/errorNotFound.png";
            entityNotFoundDialog = createDialog("Δεν Βρέθηκε " + entity, width, height);
            label = createLabel("Δεν Βρέθηκε " + entity, 70,40, 260, 40);
            addIconToLabel(label, filename);
        }
        else if(use == -2)
        {
            filename = "/icons/warningEmptySearch.png";
            entityNotFoundDialog = createDialog("Δεν Βρέθηκαν Παράμετροι", width, height);
            label = createLabel("Εισάγετε Κάποιες Παραμέτρους", 20,40, 360, 40);
            addIconToLabel(label, filename);
        }


        JButton button = createButton("OK", 150, 120, 100, 40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                entityNotFoundDialog.dispose();
            }
        });


        entityNotFoundDialog.add(label);
        entityNotFoundDialog.add(button);
        entityNotFoundDialog.setVisible(true);
    }




    @SuppressWarnings("unchecked")
    protected int TableSearchResult(String entity, String use, ArrayList<?> a)
    {
        String filename="/icons/car_rent_main.png";
        String[] columnNames = null;
        int[] selected = new int[1];

        JButton configurableButton = new JButton();
        JButton cancelButton = createButton("Άκυρο", 0, 0, 0, 0);
        String dialogText= "";
        String entityNaming = "";


        switch(entity)
        {
            case ("Χρήστης"):
                columnNames = new String[]{"Όνομα", "Επίθετο", "Username", "Email"};
                dialogText = "Χρήστες που βρέθηκαν";
                entityNaming = "Χρήστη";
                break;
            case ("Πελάτης"):
                columnNames = new String[]{"Όνομα", "Επίθετο", "Τηλέφωνο", "Email", "ΑΦΜ"};
                dialogText = "Πελάτες που βρέθηκαν";
                entityNaming = "Πελάτη";
                break;
            case ("Όχημα"):
                columnNames = new String[]{"ID", "Πινακίδα", "Μάρκα", "Τύπος", "Μοντέλο", "Έτος", "Χρώμα", "Κατάσταση"};
                dialogText = "Οχήματα που βρέθηκαν";
                entityNaming = "Οχήματος";
                break;
            case ("Ιστορικό"):
                columnNames = new String[]{"ID Ενοικίασης", "Χρήστης", "ΑΦΜ Πελάτη","ID Οχήματος", "Πινακίδα Οχήματος", "Ημερομηνία Ενοικίασης","Ημερομηνία Επιστροφής", "Κατάσταση"};
                dialogText = "Ενοικιάσεις που βρέθηκαν";
                break;
        }

        JDialog dialog = createDialog(dialogText,screenWidth/2, screenHeight/2);

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        if(entity.equals("Όχημα") || entity.equals("Ιστορικό"))
                        {
                            return Integer.class;
                        }
                        else
                        {
                            return String.class;
                        }
                    case 2:
                        if(entity.equals("Πελάτης") || entity.equals("Ιστορικό"))
                        {
                            return Integer.class;
                        }
                        else
                        {
                            return String.class;
                        }
                    case 3:
                        if(entity.equals("Ιστορικό"))
                        {
                            return Integer.class;
                        }
                        else
                        {
                            return String.class;
                        }
                    case 4:
                        if(entity.equals("Πελάτης"))
                        {
                            return Integer.class;
                        }
                        else
                        {
                            return String.class;
                        }
                    case 5:
                    case 6:
                        if(entity.equals("Ιστορικό"))
                        {
                            return java.util.Date.class;
                        }
                        else
                        {
                            return String.class;
                        }
                    default:
                        return String.class;
                }
            }
        };


        JTable table = createTable(model);
        if (entity.equals("Ιστορικό"))
        {

            DefaultTableCellRenderer dateRenderer = new DefaultTableCellRenderer() {
                private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                @Override
                protected void setValue(Object value) {
                    if (value instanceof java.util.Date) {
                        setText(sdf.format((java.util.Date) value));
                    } else {
                        setText("");
                    }
                }
            };

            table.getColumnModel().getColumn(5).setCellRenderer(dateRenderer);
            table.getColumnModel().getColumn(6).setCellRenderer(dateRenderer);
        }

        switch(entity)
        {
            case ("Χρήστης"):
                for(User u : (ArrayList<User>) a)
                {
                    Object[] row = {u.getName(), u.getSurname(), u.getUsername(), u.getEmail()};
                    model.addRow(row);
                }
                break;
            case ("Πελάτης"):
                for(Customer c : (ArrayList<Customer>) a)
                {
                    Object[] row = {c.getName(), c.getSurname(), Long.parseLong(c.getPhone()), c.getEmail(), Integer.parseInt(c.getAfm())};
                    model.addRow(row);
                }
                break;
            case ("Όχημα"):
                for(Vehicle v : (ArrayList<Vehicle>) a)
                {
                    Object[] row = {Long.parseLong(v.getId()), v.getPlate(), v.getBrand(), v.getType(), v.getModel(), v.getYearOfProduction(), v.getColor(), v.getState()};
                    model.addRow(row);
                }
                break;
            case ("Ιστορικό"):
                for (Rent r : (ArrayList<Rent>) a)
                {
                    Object[] row = {r.getRentId(), r.getUsername(), Long.parseLong(r.getCustomer().getAfm()), Long.parseLong(r.getVehicle().getId()), r.getVehicle().getPlate(),r.getRentDate(), r.getReturnDate(), r.getState()};
                    model.addRow(row);
                }
        }

        JScrollPane scrollPane = new JScrollPane(table);

        switch(use)
        {
            case ("Delete"):
                configurableButton = createButton("Διαγραφή " + entityNaming, 0, 0, 0, 0);
                break;
            case ("Edit"), ("All"):
                configurableButton = createButton("Επεξεργασία " + entityNaming, 0, 0, 0, 0);
                break;
            case ("Search"):
                configurableButton = createButton("OK", 0, 0, 0, 0);
                break;
            case ("Select"):
                configurableButton = createButton("Επιλογή", 0, 0, 0, 0);
                break;
            case ("History"):
                configurableButton = createButton("Προβολή Ιστορικού " + entityNaming, 0, 0, 0, 0);
                break;
            case ("Rent"):
                configurableButton = createButton("Επιλογή " + entityNaming, 0, 0, 0, 0);
                break;
            case ("Return"):
                if(entityNaming.equals("Πελάτη"))
                {
                    configurableButton = createButton("Επιστροφή από " + entityNaming, 0, 0, 0, 0);
                }
                else if(entity.equals("Ιστορικό"))
                {
                    configurableButton = createButton("Επιστροφή", 0, 0, 0, 0);
                }
                else
                {
                    configurableButton = createButton("Επιστροφή " + entityNaming, 0, 0, 0, 0);
                }
                break;
        }

        configurableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int row = table.getSelectedRow();
                if(row == -1)
                {
                    selected[0] = -1;
                }
                else
                {
                    selected[0] = table.convertRowIndexToModel(row);
                }
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e)
            {
                selected[0] = -1;
                dialog.dispose();
            }
        });

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                selected[0] = -1;
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(configurableButton);
        bottomPanel.add(Box.createHorizontalStrut(30));
        if(use.equals("All"))
        {
            JButton extraButton = createButton("Προβολή Ιστορικού " + entityNaming, 0, 0, 0, 0);
            extraButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int row = table.getSelectedRow();
                    if(row == -1)
                    {
                        selected[0] = -1;
                    }
                    else
                    {
                       if(entity.equals("Όχημα"))
                       {
                           selected[0] = table.convertRowIndexToModel(row) + Vehicle.getCars().size();
                       }
                       else
                       {
                           selected[0] = table.convertRowIndexToModel(row) + Customer.getCustomers().size();
                       }
                    }
                    dialog.dispose();
                }
            });
            bottomPanel.add(extraButton);
            bottomPanel.add(Box.createHorizontalStrut(30));
        }
        bottomPanel.add(cancelButton);

        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
        dialog.add(bottomPanel, BorderLayout.SOUTH);
        dialog.setResizable(true);
        ImageIcon rawIcon =  new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        dialog.setIconImage(rawIcon.getImage());
        dialog.setVisible(true);
        return selected[0];
    }

    private JDialog dialogCheckerUI;





    protected void CheckerUI(int check, String typeOf, String use)
    {
        String[] itemList;
        JLabel label = new JLabel();


        if(check == -1)
        {
            String dialogText = "";
            String labelText = "";
            switch(typeOf)
            {
                case ("Χρήστης"):
                    switch(use)
                    {
                        case ("Add"):
                            dialogText = "Προσθήκη Χρήστη";
                            labelText = "Επιτυχής Προσθήκη";
                            break;
                        case ("Delete"):
                            dialogText = "Διαγραφή Χρήστη";
                            labelText = "Επιτυχής Διαγραφή";
                            break;
                    }
                    break;
                case ("Πελάτης"):
                    switch(use)
                    {
                        case ("Add"):
                            dialogText = "Προσθήκη Πελάτη";
                            labelText = "Επιτυχής Προσθήκη";
                            break;
                        case ("Edit"):
                            dialogText = "Επεξεργασία Πελάτη";
                            labelText = "Επιτυχής Επεξεργασία";
                            break;
                    }
                    break;
                case ("Όχημα"):
                    switch(use)
                    {
                        case ("Add"):
                            dialogText = "Προσθήκη Οχήματος";
                            labelText = "Επιτυχής Προσθήκη";
                            break;
                        case ("Edit"):
                            dialogText = "Επεξεργασία Οχήματος";
                            labelText = "Επιτυχής Επεξεργασία";
                            break;
                    }
                    break;
            }

            String filename =  "/icons/check.png";
            dialogCheckerUI = createDialog(dialogText, 400,200);
            label = createLabel( labelText, 65, 50, 270, 40);
            label = addIconToLabel(label,filename);


            JButton okButton = createButton("OK", 150,140,100,40);
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogCheckerUI.dispose();
                }
            });

            ImageIcon rawIcon =  new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
            dialogCheckerUI.setIconImage(rawIcon.getImage());
            dialogCheckerUI.add(label);
            dialogCheckerUI.add(okButton);
            dialogCheckerUI.setVisible(true);
        }
        else
        {
            String filename = "/icons/errorNotFound.png";
            switch(typeOf)
            {
                case "Χρήστης":
                    itemList = new String[]{"Όνομα", "Επίθετο", "Username", "Email", "Password"};
                    if (check < itemList.length)
                    {
                        dialogCheckerUI = createDialog("Λάθος " + itemList[check] + " Χρήστη", 500,200);
                        label = createLabel("Το " + itemList[check] + " Δεν Μπορεί Να Είναι Κενό", 30, 50, 440, 40);
                    } else if (check == 5)
                    {
                        dialogCheckerUI = createDialog("Λάθος Username Χρήστη", 500,200);
                        label = createLabel("Το Username Χρησιμοποιείται Ήδη", 60, 50, 380, 40);
                    }
                    else if (check == 6)
                    {
                        dialogCheckerUI = createDialog("Λάθος Email Χρήστη", 500, 200);
                        label = createLabel("Το Email Χρησιμοποιείται Ήδη", 80, 50, 340, 40);
                    }
                    else if (check == 7)
                    {
                        dialogCheckerUI = createDialog("Λάθος Email Χρήστη", 500, 200);
                        label = createLabel("To Email Πρέπει Να Περιέχει @", 75, 50, 350, 40);
                    }
                    break;
                case "Πελάτης":
                    itemList = new String[]{"Όνομα", "Επίθετο", "Τηλέφωνο", "Email", "ΑΦΜ"};
                    if (check < itemList.length)
                    {
                        dialogCheckerUI = createDialog("Λάθος " + itemList[check] + " Πελάτη", 500,200);
                        label = createLabel("Το " + itemList[check] + " Δεν Μπορεί Να Είναι Κενό", 33, 50, 434, 40);
                    }
                    else if (check == 5)
                    {
                        dialogCheckerUI = createDialog("Λάθος Τηλέφωνο Πελάτη", 500, 200);
                        label = createLabel("Εισάγεται Έγκυρο Αριθμό Τηλεφώνου", 45, 50, 410, 40);
                    }
                    else if (check == 6)
                    {
                        dialogCheckerUI = createDialog("Λάθος Email Πελάτη", 500, 200);
                        label = createLabel("To Email Πρέπει Να Περιέχει @", 75, 50, 350, 40);
                    }
                    else if (check == 7)
                    {
                        dialogCheckerUI = createDialog("Λάθος ΑΦΜ Πελάτη", 500,200);
                        label = createLabel("Το ΑΦΜ Χρησιμοποιείται Ήδη", 80, 50, 340, 40);
                    }
                    else if (check == 8)
                    {
                        dialogCheckerUI = createDialog("Λάθος ΑΦΜ Πελάτη", 500,200);
                        label = createLabel("Εισάγεται Έγκυρο ΑΦΜ", 110, 50, 280, 40);
                    }
                    break;
                case "Όχημα":
                    itemList = new String[]{"ID", "Πινακίδα", "Μάρκα", "Τύπος", "Μοντέλο", "Έτος", "Χρώμα", "Κατάσταση"};
                    if (check < itemList.length)
                    {
                        String arthro;
                        String kataliksi;
                        if (check == 3)
                        {
                            arthro = "Ο ";
                            kataliksi = "ός";
                        }
                        else if (check == 1 ||  check == 2 || check == 7)
                        {
                            arthro = "Η ";
                            kataliksi = "ή";
                        }
                        else
                        {
                            arthro = "Το ";
                            kataliksi = "ό";
                        }
                        dialogCheckerUI = createDialog("Λάθος " + itemList[check] + " Οχήματος", 500,200);
                        label = createLabel(arthro + itemList[check] + " Δεν Μπορεί Να Είναι Κεν" + kataliksi , 40, 50, 420, 40);
                    } else if (check == 8)
                    {
                        dialogCheckerUI = createDialog("Λάθος ID Οχήματος", 500,200);
                        label = createLabel("Το ID Χρησιμοποιείται Ήδη", 90, 50, 320, 40);
                    }
                    else if (check == 9)
                    {
                        dialogCheckerUI = createDialog("Λάθος ID Οχήματος", 500,200);
                        label = createLabel("Το ID Δεν Είναι Έγκυρος Αριθμός", 60, 50, 380, 40);
                    }
                    else if (check == 10)
                    {
                        dialogCheckerUI = createDialog("Λάθος Πινακίδα Οχήματος", 500, 200);
                        label = createLabel("Η Πινακίδα Χρησιμοποιείται Ήδη", 60, 50, 380, 40);
                    }
                    else if (check == 11)
                    {
                        dialogCheckerUI = createDialog("Λάθος Έτος Οχήματος", 500, 200);
                        label = createLabel("Εισάγεται Έγκυρο Έτος (1885 - 2026)", 50, 50, 400, 40);
                    }
                    else if (check == 12)
                    {
                        dialogCheckerUI = createDialog("Λάθος Κατάσταση Οχήματος", 500, 200);
                        label = createLabel("Το Όχημα Είναι Ενοικιασμένο Σε Πελάτη", 30, 50, 440, 40);
                    }
                    break;
            }



            JButton okButton = createButton("OK", 200,140,100,40);
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogCheckerUI.dispose();
                }
            });

            label = addIconToLabel(label,filename);

            ImageIcon rawIcon =  new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
            dialogCheckerUI.setIconImage(rawIcon.getImage());
            dialogCheckerUI.add(label);
            dialogCheckerUI.add(okButton);
            dialogCheckerUI.setVisible(true);

        }
    }


    protected int ConfirmUI(Object o, String use, String typeOf)
    {
        String  filename="/icons/car_rent_main.png";
        int[] ret = new int[1];

        String dialogText = "";
        String labelText = "";
        String buttonText = "";
        String text="";
        int width=500;

        switch (typeOf)
        {
            case ("Χρήστης"):
                //noinspection SwitchStatementWithTooFewBranches
                switch(use)
                {
                    case ("Delete"):
                        dialogText = "Επιβεβαίωση Διαγραφής Χρήστη";
                        labelText = "Διαγραφή του Χρήστη?";
                        buttonText = "Διαγραφή";
                        break;
                }
                User u = (User) o;
                text = " Όνομα: " + u.getName() + "\n Επίθετο: " + u.getSurname() + "\n Username: " + u.getUsername() + "\n Email: " + u.getEmail() ;
                break;
            case ("Πελάτης"):
                switch(use)
                {
                    case ("Edit"):
                        dialogText = "Επιβεβαίωση Επεξεργασίας Πελάτη";
                        labelText = "Επεξεργασία του Πελάτη?";
                        buttonText = "Επεξεργασία";
                        break;
                    case ("History"):
                        dialogText = "Επιβεβαίωση Προβολής Ιστορικού Πελάτη";
                        labelText = "Προβολή Ιστορικού του Πελάτη?";
                        buttonText = "Προβολή";
                        width=540;
                        break;
                    case ("Rent"):
                        dialogText = "Επιβεβαίωση Ενοικίασης Οχήματος";
                        labelText = "Ενοικίαση σε Πελάτη?";
                        buttonText = "Ενοικίαση";
                        break;
                    case ("Return"):
                        dialogText = "Επιβεβαίωση Επιστροφής Οχήματος";
                        labelText = "Επιστροφή από Πελάτη?";
                        buttonText = "Επιστροφή";
                        break;
                }
                Customer c = (Customer) o;
                text = " Όνομα: " + c.getName() + "\n Επίθετο: " + c.getSurname() + "\n Τηλέφωνο: " + c.getPhone() + "\n Email: " + c.getEmail() + "\n ΑΦΜ: " + c.getAfm();
                break;
            case ("Όχημα"):
                switch(use)
                {
                    case ("Edit"):
                        dialogText = "Επιβεβαίωση Επεξεργασίας Οχήματος";
                        labelText = "Επεξεργασία του Οχήματος?";
                        buttonText = "Επεξεργασία";
                        break;
                    case ("History"):
                        dialogText = "Επιβεβαίωση Προβολής Ιστορικού Οχήματος";
                        labelText = "Προβολή Ιστορικού του Οχήματος?";
                        buttonText = "Προβολή";
                        width=540;
                        break;
                    case ("Rent"):
                        dialogText = "Επιβεβαίωση Ενοικίασης Οχήματος";
                        labelText = "Ενοικίαση του Οχήματος?";
                        buttonText = "Ενοικίαση";
                        break;
                    case ("Return"):
                        dialogText = "Επιβεβαίωση Επιστροφής Οχήματος";
                        labelText = "Επιστροφή του Οχήματος?";
                        buttonText = "Επιστροφή";
                        break;
                }
                Vehicle v = (Vehicle) o;
                text = " ID: " + v.getId() + "\n Πινακίδα: " + v.getPlate() + "\n Μάρκα: " + v.getBrand() + "\n Τύπος: " + v.getType() + "\n Μοντέλο: " + v.getModel() + "\n Έτος: " + v.getYearOfProduction() + "\n Χρώμα: " + v.getColor() + "\n Κατάσταση: " + v.getState();
                break;
        }


        JDialog dialog = createDialog(dialogText,600, 400);
        ImageIcon rawIcon =  new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        dialog.setIconImage(rawIcon.getImage());

        JLabel label = createLabel(labelText, 50, 10, width, 30);

        JTextArea textArea = createTextArea(text, 50,60,500,210);

        JButton confirmButton = createButton(buttonText, 100, 320, 160, 40);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ret[0] = 1;
                dialog.dispose();
            }
        });

        JButton cancelButton = createButton("Άκυρο", 360, 320, 140, 40);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ret[0] = 0;
                dialog.dispose();
            }
        });

        dialog.add(label);
        dialog.add(textArea);
        dialog.add(confirmButton);
        dialog.add(cancelButton);
        dialog.setVisible(true);
        return ret[0];
    }




    protected void emptyArray(String type)
    {
        String dialogText = "";
        String labelText = "";

        switch (type)
        {
            case ("Χρήστης"):
                dialogText = "Δεν Βρέθηκαν Χρήστες";
                labelText = "Δεν Βρέθηκαν Χρήστες";
                break;
            case ("Πελάτης"):
                dialogText = "Δεν Βρέθηκαν Πελάτες";
                labelText = "Δεν Βρέθηκαν Πελάτες";
                break;
            case ("Όχημα"):
                dialogText = "Δεν Βρέθηκαν Οχήματα";
                labelText = "Δεν Βρέθηκαν Οχήματα";
                break;
            case ("Ιστορικό"):
                dialogText = "Δεν Βρέθηκε Ιστορικό";
                labelText = "Δεν Βρέθηκε Ιστορικό";
                break;
        }
        JDialog dialog = createDialog(dialogText, 400, 200);
        JLabel label  = createLabel(labelText, 70, 50, 280, 40);
        JButton okButton = createButton("OK", 140, 140, 120, 40);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dialog.dispose();
            }
        });
        label = addIconToLabel(label, "/icons/errorNotFound.png");

        dialog.add(label);
        dialog.add(okButton);
        dialog.setVisible(true);
    }

    public void fileError(String use) {
        String filename = "/icons/errorNotFound.png";

        String dialogText = "";
        String labelText = "";

        switch (use) {
            case ("Save"):
                labelText = "Σφάλμα Στην Αποθήκευση Δεδομένων";
                dialogText = "Σφάλμα Στην Αποθήκευση Δεδομένων";
                break;
            case ("Load"):
                labelText = "Σφάλμα Στην Φόρτωση Δεδομένων";
                dialogText = "Σφάλμα Στην Φόρτωση Δεδομένων";
                break;
        }

        JDialog dialog = createDialog(dialogText, 500, 200);
        ImageIcon rawIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        dialog.setIconImage(rawIcon.getImage());

        JLabel label = createLabel(labelText, 40, 40, 420, 40);
        label = addIconToLabel(label, "/icons/errorNotFound.png");

        JButton okButton = createButton("OK", 200, 140, 100, 40);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dialog.dispose();
            }
        });

        dialog.add(label);
        dialog.add(okButton);
        dialog.setVisible(true);
    }
}