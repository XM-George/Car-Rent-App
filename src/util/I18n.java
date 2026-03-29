package util;

import java.text.MessageFormat;
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

    public static String getCurrentLanguage()
    {
        return bundle.getLocale().getLanguage();
    }

    public static String getString(String key)
    {
        return bundle.getString(key);
    }

    public static String getString(String key, Object... args)
    {
        return MessageFormat.format(bundle.getString(key), args);
    }
}
