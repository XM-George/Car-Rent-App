package GUI;

import API.Customer;
import API.Rent;
import API.User;
import API.Vehicle;
import util.I18n;

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
            case ("user"):
                switch(use)
                {
                    case ("Add"):
                        buttonText = I18n.getString("user.add");
                        break;
                    case ("Find"):
                        buttonText = I18n.getString("user.search");
                        break;
                }
                dialogHeight = 450;
                break;
            case ("customer"):
                switch(use)
                {
                    case ("Add"):
                        buttonText = I18n.getString("customer.add");
                        break;
                    case ("Find"):
                        buttonText = I18n.getString("customer.search");
                        break;
                }
                dialogHeight = 450;
                break;
            case ("vehicle"):
                switch(use)
                {
                    case ("Add"):
                        buttonText = I18n.getString("vehicle.add");
                        break;
                    case ("Find"):
                        buttonText = I18n.getString("vehicle.search");
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
            if(types[i].equals(I18n.getString("password")))
            {
                passField = createPasswordField(50, k, 300, 35);
                textFields[i] = passField;
                dialog.add(passField);
            }
            else if(types[i].equals(I18n.getString("status")))
            {
                String[] a={I18n.getString("vehicle.status.available") , I18n.getString("vehicle.status.rented")};
                JComboBox<String> comboBox = createComboBox(a, 50, k,300,35);
                if(use.equals("Add"))
                {
                    comboBox.setEnabled(false);
                }
                if(title.equals(I18n.getString("vehicle.return")))
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
                    if(!types[i].equals(I18n.getString("status")))
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

        JButton cancelButton = createButton(I18n.getString("cancel"), 280, dialogHeight - 70, 100, 40);
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
        String dialogText = "";
        String labelText = "";

        switch(entity)
        {
            case("user"):
                dialogText = I18n.getString("entity.notFound",I18n.getString("user.title"));
                labelText = I18n.getString("entity.notFound",I18n.getString("user.title"));
                break;
            case("customer"):
                dialogText = I18n.getString("entity.notFound",I18n.getString("customer.title"));
                labelText = I18n.getString("entity.notFound",I18n.getString("customer.title"));
                break;
            case("vehicle"):
                dialogText = I18n.getString("entity.notFound",I18n.getString("vehicle.title"));
                labelText = I18n.getString("entity.notFound",I18n.getString("vehicle.title"));
                break;
        }

        if(use == -1)
        {
            filename = "/icons/errorNotFound.png";
            entityNotFoundDialog = createDialog(dialogText, width, height);
            label = createLabel(labelText, 70,40, 260, 40);
            addIconToLabel(label, filename);
        }
        else if(use == -2)
        {
            filename = "/icons/warningEmptySearch.png";
            entityNotFoundDialog = createDialog(I18n.getString("parameters.notFound"), width, height);
            label = createLabel(I18n.getString("parameters.enterParameters"), 20,40, 360, 40);
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
        JButton cancelButton = createButton(I18n.getString("cancel"), 0, 0, 0, 0);
        String dialogText= "";

        switch(entity)
        {
            case ("user"):
                columnNames = new String[]{I18n.getString("name") , I18n.getString("surname") , I18n.getString("username") , I18n.getString("username")};
                dialogText = I18n.getString("user.found");
                break;
            case ("customer"):
                columnNames = new String[]{I18n.getString("name") , I18n.getString("surname") , I18n.getString("phone") , I18n.getString("email") , I18n.getString("id")};
                dialogText = I18n.getString("customer.found");
                break;
            case ("vehicle"):
                columnNames = new String[]{I18n.getString("id") , I18n.getString("numberplate") , I18n.getString("make") , I18n.getString("type") ,I18n.getString("model") , I18n.getString("year") , I18n.getString("color") , I18n.getString("status")};
                dialogText = I18n.getString("vehicle.found");
                break;
            case ("history"):
                columnNames = new String[]{I18n.getString("rent.ID") , I18n.getString("user.title.caps") , I18n.getString("customer.ID"),I18n.getString("vehicle.ID"),I18n.getString("vehicle.numberPlate"), I18n.getString("rent.date.rent") , I18n.getString("rent.date.return") , I18n.getString("status")};
                dialogText = I18n.getString("rent.found");
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
                        if(entity.equals("vehicle") || entity.equals("history"))
                        {
                            return Integer.class;
                        }
                        else
                        {
                            return String.class;
                        }
                    case 2:
                        if(entity.equals("customer") || entity.equals("history"))
                        {
                            return Integer.class;
                        }
                        else
                        {
                            return String.class;
                        }
                    case 3:
                        if(entity.equals("history"))
                        {
                            return Integer.class;
                        }
                        else
                        {
                            return String.class;
                        }
                    case 4:
                        if(entity.equals("customer"))
                        {
                            return Integer.class;
                        }
                        else
                        {
                            return String.class;
                        }
                    case 5:
                    case 6:
                        if(entity.equals("history"))
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
        if (entity.equals("history"))
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
            case ("user"):
                for(User u : (ArrayList<User>) a)
                {
                    Object[] row = {u.getName(), u.getSurname(), u.getUsername(), u.getEmail()};
                    model.addRow(row);
                }
                break;
            case ("customer"):
                for(Customer c : (ArrayList<Customer>) a)
                {
                    Object[] row = {c.getName(), c.getSurname(), Long.parseLong(c.getPhone()), c.getEmail(), Integer.parseInt(c.getAfm())};
                    model.addRow(row);
                }
                break;
            case ("vehicle"):
                for(Vehicle v : (ArrayList<Vehicle>) a)
                {
                    Object[] row = {Long.parseLong(v.getId()), v.getPlate(), v.getBrand(), v.getType(), v.getModel(), v.getYearOfProduction(), v.getColor(), v.getState()};
                    model.addRow(row);
                }
                break;
            case ("history"):
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
                configurableButton = createButton(I18n.getString(entity + ".delete" ), 0, 0, 0, 0);
                break;
            case ("Edit"), ("All"):
                configurableButton = createButton(I18n.getString(entity + ".edit.info"), 0, 0, 0, 0);
                break;
            case ("Search"):
                configurableButton = createButton("OK", 0, 0, 0, 0);
                break;
            case ("Select"):
                configurableButton = createButton(I18n.getString("select"), 0, 0, 0, 0);
                break;
            case ("History"):
                configurableButton = createButton(I18n.getString(entity + ".history.see"), 0, 0, 0, 0);
                break;
            case ("Rent"):
                configurableButton = createButton(I18n.getString(entity + ".select"), 0, 0, 0, 0);
                break;
            case ("Return"):
                if(entity.equals("customer"))
                {
                    configurableButton = createButton(I18n.getString("customer.return"), 0, 0, 0, 0);
                }
                else if(entity.equals("history"))
                {
                    configurableButton = createButton(I18n.getString("return"), 0, 0, 0, 0);
                }
                else
                {
                    configurableButton = createButton(I18n.getString("vehicle.return"), 0, 0, 0, 0);
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
            JButton extraButton = createButton(I18n.getString(entity + ".history.see"), 0, 0, 0, 0);
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
                       if(entity.equals("vehicle"))
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
                case ("user"):
                    switch(use)
                    {
                        case ("Add"):
                            dialogText = I18n.getString("user.add");
                            labelText = I18n.getString("add.success");
                            break;
                        case ("Delete"):
                            dialogText = I18n.getString("user.delete");
                            labelText = I18n.getString("delete.success");
                            break;
                    }
                    break;
                case ("customer"):
                    switch(use)
                    {
                        case ("Add"):
                            dialogText = I18n.getString("customer.add");
                            labelText = I18n.getString("add.success");
                            break;
                        case ("Edit"):
                            dialogText = I18n.getString("customer.edit");
                            labelText = I18n.getString("edit.success");
                            break;
                    }
                    break;
                case ("vehicle"):
                    switch(use)
                    {
                        case ("Add"):
                            dialogText = I18n.getString("vehicle.add");
                            labelText = I18n.getString("add.success");
                            break;
                        case ("Edit"):
                            dialogText = I18n.getString("vehicle.edit");
                            labelText = I18n.getString("edit.success");
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
                case "user":
                    itemList = new String[]{I18n.getString("name") , I18n.getString("surname") , I18n.getString("username") , I18n.getString("email") , I18n.getString("password")};
                    if (check < itemList.length)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("user.error.wrong" , itemList[check]), 500,200);
                        label = createLabel(I18n.getString("user.error.empty" , itemList[check]), 30, 50, 440, 40);
                    } else if (check == 5)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("user.error.wrong" , "username"), 500,200);
                        label = createLabel(I18n.getString("user.error.exists" , "username"), 60, 50, 380, 40);
                    }
                    else if (check == 6)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("user.error.wrong" , "email"), 500, 200);
                        label = createLabel(I18n.getString("user.error.exists" , "email"), 80, 50, 340, 40);
                    }
                    else if (check == 7)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("user.error.wrong" , "email"), 500, 200);
                        label = createLabel(I18n.getString("error.email.invalid"), 75, 50, 350, 40);
                    }
                    break;
                case "customer":
                    itemList = new String[]{I18n.getString("name") , I18n.getString("surname") , I18n.getString("phone") , I18n.getString("email"), I18n.getString("id")};
                    if (check < itemList.length)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("customer.error.wrong" , itemList[check]), 500,200);
                        label = createLabel(I18n.getString("customer.error.empty" , itemList[check]), 33, 50, 434, 40);
                    }
                    else if (check == 5)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("customer.error.wrong" , I18n.getString("phone")), 500, 200);
                        label = createLabel(I18n.getString("customer.error.phone"), 45, 50, 410, 40);
                    }
                    else if (check == 6)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("customer.error.wrong" , "email"), 500, 200);
                        label = createLabel(I18n.getString("error.email.invalid"), 75, 50, 350, 40);
                    }
                    else if (check == 7)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("customer.error.wrong" , "ID"), 500,200);
                        label = createLabel(I18n.getString("customer.error.exists" , "ID"), 80, 50, 340, 40);
                    }
                    else if (check == 8)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("customer.error.wrong" , "ID"), 500,200);
                        label = createLabel(I18n.getString("customer.error.id"), 110, 50, 280, 40);
                    }
                    break;
                case "vehicle":
                    itemList = new String[]{I18n.getString("id") , I18n.getString("numberplate") , I18n.getString("make") , I18n.getString("type") , I18n.getString("model") , I18n.getString("year") , I18n.getString("color") , I18n.getString("status")};
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
                        dialogCheckerUI = createDialog(I18n.getString("vehicle.error.wrong" , itemList[check]), 500,200);
                        label = createLabel(I18n.getString("vehicle.error.empty" , arthro , itemList[check] , kataliksi) , 40, 50, 420, 40);
                    } else if (check == 8)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("vehicle.error.wrong" , I18n.getString("id")), 500,200);
                        label = createLabel(I18n.getString("vehicle.error.exists" , "Το" , I18n.getString("id")), 90, 50, 320, 40);
                    }
                    else if (check == 9)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("vehicle.error.wrong" , I18n.getString("id")), 500,200);
                        label = createLabel(I18n.getString("vehicle.error.id.invalid"), 60, 50, 380, 40);
                    }
                    else if (check == 10)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("vehicle.error.wrong" , I18n.getString("numberplate")), 500, 200);
                        label = createLabel(I18n.getString("vehicle.error.exists" , "Η" , I18n.getString("numberplate")), 60, 50, 380, 40);
                    }
                    else if (check == 11)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("vehicle.error.wrong" , I18n.getString("year")), 500, 200);
                        label = createLabel(I18n.getString("vehicle.error.year.invalid"), 50, 50, 400, 40);
                    }
                    else if (check == 12)
                    {
                        dialogCheckerUI = createDialog(I18n.getString("vehicle.error.wrong" , I18n.getString("status")), 500, 200);
                        label = createLabel(I18n.getString("vehicle.error.rented"), 30, 50, 440, 40);
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
            case ("user"):
                //noinspection SwitchStatementWithTooFewBranches
                switch(use)
                {
                    case ("Delete"):
                        dialogText = I18n.getString("user.delete.confirm");
                        labelText = I18n.getString("user.delete.question");
                        buttonText = I18n.getString("delete");
                        break;
                }
                User u = (User) o;
                text =  I18n.getString("name") + ": " + u.getName() + "\n" +
                        I18n.getString("surname") + ": " + u.getSurname() + "\n" +
                        I18n.getString("username") + ": " + u.getUsername() + "\n" +
                        I18n.getString("email") + ": " + u.getEmail() ;
                break;
            case ("customer"):
                switch(use)
                {
                    case ("Edit"):
                        dialogText = I18n.getString("customer.edit.confirm");
                        labelText = I18n.getString("customer.edit.question");
                        buttonText = I18n.getString("edit");
                        break;
                    case ("History"):
                        dialogText = I18n.getString("customer.history.see.confirm");
                        labelText = I18n.getString("customer.history.see.question");
                        buttonText = I18n.getString("see");
                        width=540;
                        break;
                    case ("Rent"):
                        dialogText = I18n.getString("vehicle.rent.confirm");
                        labelText = I18n.getString("customer.rent.question");
                        buttonText = I18n.getString("rent");
                        break;
                    case ("Return"):
                        dialogText = I18n.getString("vehicle.return.confirm");
                        labelText = I18n.getString("customer.return.question");
                        buttonText = I18n.getString("return");
                        break;
                }
                Customer c = (Customer) o;
                text =  I18n.getString("name") + ": " + c.getName() + "\n" +
                        I18n.getString("surname") + ": " + c.getSurname() + "\n" +
                        I18n.getString("phone") + ": " + c.getPhone() + "\n" +
                        I18n.getString("email") + ": " + c.getEmail() + "\n" +
                        I18n.getString("id") + ": " + c.getAfm();
                break;
            case ("vehicle"):
                switch(use)
                {
                    case ("Edit"):
                        dialogText = I18n.getString("vehicle.edit.confirm");
                        labelText = I18n.getString("vehicle.edit.question");
                        buttonText = I18n.getString("edit");
                        break;
                    case ("History"):
                        dialogText = I18n.getString("vehicle.history.see.confirm");
                        labelText = I18n.getString("vehicle.history.see.question");
                        buttonText = I18n.getString("see");
                        width=540;
                        break;
                    case ("Rent"):
                        dialogText = I18n.getString("vehicle.rent.confirm");
                        labelText = I18n.getString("vehicle.rent.question");
                        buttonText = I18n.getString("rent");
                        break;
                    case ("Return"):
                        dialogText = I18n.getString("vehicle.return.confirm");
                        labelText = I18n.getString("vehicle.return.question");
                        buttonText = I18n.getString("return");
                        break;
                }
                Vehicle v = (Vehicle) o;
                text =  I18n.getString("id") + ": " + v.getId() + "\n" +
                        I18n.getString("numberplate") + ": " + v.getPlate() + "\n" +
                        I18n.getString("make") + ": " + v.getBrand() + "\n" +
                        I18n.getString("type") + ": " + v.getType() + "\n" +
                        I18n.getString("model") + ": " + v.getModel() + "\n" +
                        I18n.getString("year") + ": " + v.getYearOfProduction() + "\n" +
                        I18n.getString("color") + ": " + v.getColor() + "\n" +
                        I18n.getString("status") + ": " + v.getState();
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

        JButton cancelButton = createButton(I18n.getString("cancel"), 360, 320, 140, 40);
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
            case ("user"):
                dialogText = I18n.getString("user.notFound.plural");
                labelText = I18n.getString("user.notFound.plural");
                break;
            case ("customer"):
                dialogText = I18n.getString("customer.notFound.plural");
                labelText = I18n.getString("customer.notFound.plural");
                break;
            case ("vehicle"):
                dialogText = I18n.getString("vehicle.notFound.plural");
                labelText = I18n.getString("vehicle.notFound.plural");
                break;
            case ("history"):
                dialogText = I18n.getString("history.notFound");
                labelText = I18n.getString("history.notFound");
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
                labelText = I18n.getString("file.save.error");
                dialogText = I18n.getString("file.save.error");
                break;
            case ("Load"):
                labelText = I18n.getString("file.load.error");
                dialogText = I18n.getString("file.load.error");
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