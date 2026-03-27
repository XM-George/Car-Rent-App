package util;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n
{
    private static ResourceBundle bundle;

    static
    {
        setLocale(Locale.of("gr"));
    }

    public static void setLocale(Locale locale)
    {
        bundle = ResourceBundle.getBundle("messages", locale);
    }

    public static String getString(String key)
    {
        return bundle.getString(key);
    }
}
