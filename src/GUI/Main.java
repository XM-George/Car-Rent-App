package GUI;


import API.ManageData;

public class Main
{
    public static void main(String[] args)
    {
        new Login();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    ManageData.save();
                }
                catch (Exception e)
                {
                    new BaseDialogUI().fileError("Save");
                }
            }
        }));
    }
}
