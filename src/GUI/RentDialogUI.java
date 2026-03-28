package GUI;

import API.*;
import util.I18n;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class RentDialogUI extends BaseDialogUI
{

    String filename = "/icons/car_rentUI.png";
    String dialogText = "";
    String labelText = "";


    Vehicle vehi = null;
    Customer cust = null;
    public void RentVehicleUI(String use, String username)
    {
        dialogText = "";
        labelText = "";

        switch(use)
        {
            case ("Rent"):
                dialogText = I18n.getString("vehicle.rent");
                labelText = I18n.getString("vehicle.rent");
                break;
            case ("Return"):
                dialogText = I18n.getString("vehicle.return");
                labelText = I18n.getString("vehicle.return");
                break;
        }

        JDialog dialog = createDialog(dialogText, 500, 200);
        ImageIcon rawIcon =  new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        dialog.setIconImage(rawIcon.getImage());

        JLabel label = createLabel(labelText, 145, 30,210,30);

        JButton customerButton = createButton(I18n.getString("customer.search"), 260,110,230,40);
        JButton vehicleButton = createButton(I18n.getString("vehicle.search"), 10,110,240,40);
        vehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String[] vehicleOptions = {I18n.getString("id") , I18n.getString("numberplate") , I18n.getString("make") , I18n.getString("type") , I18n.getString("model") , I18n.getString("year") , I18n.getString("color") , I18n.getString("status")};
                String typeOf = "vehicle";
                String[] result = UIConstructor(dialogText, typeOf, vehicleOptions, "Find", filename);
                if (!result[vehicleOptions.length].equals("Cancel"))
                {
                    int s = new EntitySearch().searchEntity(result, typeOf);
                    if (s < 0)
                    {
                        entityNotFound(400, 200, typeOf, s);
                    }
                    else if (s > 0)
                    {
                        ArrayList<Vehicle> v = VehicleHelp.SearchVehicles(result);
                        int selection = TableSearchResult(typeOf, use, v);
                        if (selection >= 0)
                        {
                            int confirm;
                            switch(use)
                                {
                                    case ("Rent"):
                                        if (v.get(selection).getState().equals(I18n.getString("vehicle.status.rented")))
                                        {
                                            RentError("VehicleRented");
                                            return;
                                        }
                                        confirm = ConfirmUI(v.get(selection), "Rent", typeOf);
                                        if (confirm == 1)
                                        {
                                            vehicleButton.setEnabled(false);
                                            vehi = v.get(selection);

                                            if (!customerButton.isEnabled())
                                            {
                                                dialog.dispose();
                                                Date[] d = RentDate();
                                                if(d[0]!= null && d[1]!=null)
                                                {
                                                    new Rent(username, cust, vehi, d[0], d[1]);
                                                    RentSuccess("Rent");
                                                }
                                            }
                                        }
                                        break;
                                    case ("Return"):
                                        if (v.get(selection).getState().equals(I18n.getString("vehicle.status.available")))
                                        {
                                            RentError("VehicleAvailable");
                                            return;
                                        }
                                        confirm = ConfirmUI(v.get(selection), "Return", typeOf);
                                        if (confirm == 1)
                                        {
                                            dialog.dispose();
                                            new RentHelp().returnVehicle(v.get(selection).getActiveRent());
                                            RentSuccess("Return");
                                        }
                                        break;
                                }
                        }
                    }
                }
            }
        });


        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String[] customerOptions = {I18n.getString("name") , I18n.getString("surname") , I18n.getString("phone") , I18n.getString("email") , I18n.getString("id")};
                String typeOf = "customer";
                String UIConstructorText = "";
                switch(use)
                {
                    case ("Rent"):
                        UIConstructorText = I18n.getString("customer.rent");
                        break;
                    case ("Return"):
                        UIConstructorText = I18n.getString("customer.return");
                        break;
                }
                String[] result = UIConstructor(UIConstructorText, typeOf, customerOptions,"Find", filename);
                if(!result[customerOptions.length].equals("Cancel"))
                {
                    int s = new EntitySearch().searchEntity(result, typeOf);
                    if(s < 0)
                    {
                        entityNotFound(400,200,typeOf, s);
                    }
                    else if(s > 0)
                    {
                        ArrayList<Customer> c = CustomerHelp.SearchCustomer(result);
                        int selection = TableSearchResult(typeOf, use, c);
                        if (selection >= 0)
                        {
                            int confirm;
                            switch(use)
                            {
                                case ("Rent"):
                                    confirm = ConfirmUI(c.get(selection), "Rent", typeOf);
                                    if (confirm == 1)
                                    {
                                        customerButton.setEnabled(false);
                                        cust = c.get(selection);

                                        if (!vehicleButton.isEnabled())
                                        {
                                            dialog.dispose();
                                            Date[] d = RentDate();
                                            if(d[0]!= null && d[1]!=null)
                                            {
                                                new Rent(username, cust, vehi, d[0], d[1]);
                                                RentSuccess("Rent");
                                            }
                                        }
                                    }
                                    break;
                                case ("Return"):
                                    boolean flag = false;
                                    for (Rent r : c.get(selection).getRents())
                                    {
                                        if(r.getVehicle().getState().equals(I18n.getString("vehicle.status.rented")))
                                        {
                                            flag=true;
                                            break;
                                        }
                                    }
                                    if(flag)
                                    {
                                        int selectedRent = TableSearchResult("history", "Return", c.get(selection).getActiveRents());
                                        if(selectedRent >= 0)
                                        {
                                            confirm = ConfirmUI(c.get(selection), "Return", typeOf);
                                            if (confirm == 1)
                                            {
                                                dialog.dispose();
                                                new RentHelp().returnVehicle(c.get(selection).getRents().get(selectedRent));
                                                RentSuccess("Return");
                                            }
                                        }
                                    }
                                    else
                                    {
                                        RentError("customer");
                                        return;
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        });

        dialog.add(label);
        dialog.add(vehicleButton);
        dialog.add(customerButton);
        dialog.setVisible(true);
    }

    public void ReturnVehicleUI()
    {
        RentVehicleUI("Return","");
    }


    JDialog rentErrorDialog;
    JLabel rentErrorLabel;
    protected void RentError(String use)
    {
        String filename =  "/icons/warningEmptySearch.png";

        switch (use)
        {
            case ("customer"):
                rentErrorDialog = createDialog(I18n.getString("rent.vehicles.notFound"), 500, 200);
                rentErrorLabel = createLabel(I18n.getString("customer.vehicles.rented.notFound"), 20, 40, 460, 40);
                break;
            case ("VehicleRented"):
                 rentErrorDialog = createDialog(I18n.getString("vehicle.rented"), 500, 200);
                 rentErrorLabel = createLabel(I18n.getString("vehicle.rented.already"), 60, 40, 380, 40);
                break;
            case ("VehicleAvailable"):
                rentErrorDialog = createDialog(I18n.getString("vehicle.available.already"), 500, 200);
                rentErrorLabel = createLabel(I18n.getString("vehicle.rented.no"), 60, 40, 380, 40);
                break;
        }

        JButton okButton = createButton("OK", 200,120,100,40);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                rentErrorDialog.dispose();
            }
        });

        rentErrorLabel = addIconToLabel(rentErrorLabel, filename);
        rentErrorDialog.add(rentErrorLabel);
        rentErrorDialog.add(okButton);
        rentErrorDialog.setVisible(true);
    }

    protected void RentSuccess(String use)
    {
        String filename =  "/icons/check.png";
        String dialogText = "";
        String labelText = "";

        switch (use)
        {
            case ("Rent"):
                dialogText = I18n.getString("rent.complete.title");
                labelText = I18n.getString("rent.complete");
                break;
            case ("Return"):
                dialogText = I18n.getString("return.complete.title");
                labelText = I18n.getString("return.complete");
                break;
        }

        JDialog dialog = createDialog(dialogText, 400,200);
        dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(filename)));

        JLabel label = createLabel(labelText,40,40,320,40);
        label = addIconToLabel(label, filename);

        JButton okButton = createButton("OK", 150,140,100,40);
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

    protected Date[] RentDate()
    {
        String filename = "/icons/car_rent_main.png";

        Date[] d = new Date[2];

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename))).getImage();
        JDialog dialog = new JDialog(frame, I18n.getString("date.select") , true);
        dialog.getContentPane().setPreferredSize(new Dimension(600,300));
        dialog.pack();
        dialog.setFont(new Font(null, Font.BOLD, 20));
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(null);
        dialog.setIconImage(icon);

        JLabel rentLabel = createLabel(I18n.getString("date.start"),50,20,250,40);
        JLabel returnLabel = createLabel(I18n.getString("date.end"),50,70,200,40);

        JSpinner rentSpinner = new JSpinner(new SpinnerDateModel());
        rentSpinner.setBounds(350,20,200,40);
        JSpinner.DateEditor rentEditor = new JSpinner.DateEditor(rentSpinner, "dd/MM/yyyy");
        rentSpinner.setEditor(rentEditor);

        JSpinner returnSpinner = new JSpinner(new SpinnerDateModel());
        returnSpinner.setBounds(350,70,200,40);
        JSpinner.DateEditor returnEditor = new JSpinner.DateEditor(returnSpinner, "dd/MM/yyyy");
        returnSpinner.setEditor(returnEditor);

        JLabel returnBeforeRentErrorLabel = createLabel("",50,150,500,40);

        JButton confirmButton = createButton(I18n.getString("rent.confirm"),50,240,300,40);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Date today = java.util.Date.from( java.time.LocalDate.now() .atStartOfDay(java.time.ZoneId.systemDefault()) .toInstant() );

                d[0] = (Date) rentSpinner.getValue();
                d[1] = (Date) returnSpinner.getValue();
                if (d[1].before(d[0]))
                {
                    returnBeforeRentErrorLabel.setText(I18n.getString("date.return.before.date.rent.error"));
                    returnBeforeRentErrorLabel.setForeground(Color.RED);
                }
                else if(d[0].before(today) ||  d[1].before(today))
                {
                    returnBeforeRentErrorLabel.setText(I18n.getString("date.past.error"));
                    returnBeforeRentErrorLabel.setForeground(Color.RED);
                }
                else
                {
                    dialog.dispose();
                }
            }
        });

        JButton cancelButton = createButton(I18n.getString("cancel"), 400,240,150,40);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dialog.dispose();
            }
        });

        dialog.add(rentLabel);
        dialog.add(rentSpinner);
        dialog.add(returnLabel);
        dialog.add(returnSpinner);
        dialog.add(confirmButton);
        dialog.add(cancelButton);
        dialog.add(returnBeforeRentErrorLabel);

        dialog.setVisible(true);

        return d;
    }

}
