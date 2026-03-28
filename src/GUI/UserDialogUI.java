package GUI;

import API.EntitySearch;
import API.User;
import API.UserHelp;
import util.I18n;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class UserDialogUI extends BaseDialogUI
{

    String[] userOptions = {I18n.getString("name") , I18n.getString("surname") , I18n.getString("username") , I18n.getString("email") , I18n.getString("password")};
    String typeOf = "user";
    String filename = "/icons/userUI.png";
    EntitySearch search = new EntitySearch();

    public void AddUser()
    {
        String[] result = UIConstructor(I18n.getString("user.add"), typeOf, userOptions, "Add", filename);
        if(!"Cancel".equals(result[userOptions.length]))
        {
            int check = UserHelp.checkUserDetails(result);
            if(check == -1)
            {
                new User(result);
            }
            CheckerUI(check, typeOf, "Add");
        }
    }

    public void RemoveUser(String username)
    {
        String[] searchUserOptions = {I18n.getString("name") , I18n.getString("surname") , I18n.getString("username") , I18n.getString("email")};
        String[] result = UIConstructor(I18n.getString("user.delete"), typeOf, searchUserOptions, "Find", filename);
        if(!"Cancel".equals(result[searchUserOptions.length]))
        {
            int s = search.searchEntity(result, typeOf);
            int selection;
            if(s<0)
            {
                entityNotFound(400,200,typeOf, s);
            }
            else if(s>0)
            {
                ArrayList<User> u = UserHelp.SearchUsers(result);
                selection = TableSearchResult(typeOf, "Delete", u);
                if(selection >= 0)
                {
                    int confirm = ConfirmUI(u.get(selection), "Delete", typeOf);
                    if(confirm == 1)
                    {
                        if(!username.equals(u.get(selection).getUsername()))
                        {
                            UserHelp.deleteUser(u.get(selection));
                            deleteSuccessOrFailed("Success");
                        }
                        else
                        {
                            deleteSuccessOrFailed("Failed");
                        }
                    }
                }
            }
        }
    }

    public void SignOut(JFrame frame)
    {
        frame.dispose();
        new Login();
    }

    protected void deleteSuccessOrFailed(String use)
    {
        String dialogText = "";
        String labelText = "";
        int width=0;

        switch(use)
        {
            case "Success":
                filename = "/icons/check.png";
                dialogText = I18n.getString("user.delete.success");
                labelText = I18n.getString("delete.success");
                width = 310;
                break;
            case "Failed":
                filename = "/icons/errorNotFound.png";
                dialogText = I18n.getString("user.delete.error");
                labelText = I18n.getString("user.current.delete.error");
                width=490;
                break;
        }
        JDialog dialog = createDialog(dialogText, 500,200);
        ImageIcon rawIcon =  new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        dialog.setIconImage(rawIcon.getImage());

        JLabel label = createLabel(labelText, (500-width)/2,50,width,40);
        label = addIconToLabel(label, filename);

        JButton button = createButton("OK", 200, 140, 100, 40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dialog.dispose();
            }
        });

        dialog.add(label);
        dialog.add(button);
        dialog.setVisible(true);
    }
}
