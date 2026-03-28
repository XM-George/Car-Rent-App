package API;

//κλάση για save/load των δεδομένων του προγράμματος με δυαδικά αρχεία(.bin)


import java.io.*;



/**
 * Η κλάση {@code ManageData} είναι υπεύθυνη για τη διαχείριση
 * της αποθήκευσης και της φόρτωσης των δεδομένων της εφαρμογής.
 * <p>
 * Τα δεδομένα αποθηκεύονται σε δυαδικό αρχείο τύπου {@code .bin}
 * και φορτώνονται αυτόματα κατά την εκκίνηση του προγράμματος.
 * Περιέχει στατικό block αρχικοποίησης το οποίο
 * εκτελείται μία φορά κατά τη φόρτωση της κλάσης και
 * φορτώνει τα δεδομένα της εφαρμογής.
 * </p>
 * <p>
 * Αν το αρχείο δεν υπάρχει, τα δεδομένα αρχικοποιούνται
 * μέσω αρχείων CSV.
 * </p>
 */
public class ManageData
{

    /** Όνομα αρχείου αποθήκευσης δεδομένων */
    private static final String FILE_NAME = "car_rent_data.bin";

    /** Αντικείμενο που περιέχει όλα τα δεδομένα της εφαρμογής */
    protected static CarRentData data;


    /**
     * Δημιουργεί ένα νέο αντικείμενο {@code ManageData}.
     */
    public ManageData()
    {
        // default constructor
    }

    static
    {
        data = load();
    }


    /**
     * Επιστρέφει το αντικείμενο που περιέχει όλα τα δεδομένα
     * της εφαρμογής.
     *
     * @return αντικείμενο {@link CarRentData}
     */
    public static CarRentData getData()
    {
        return data;
    }

    /**
     * Αποθηκεύει τα δεδομένα της εφαρμογής σε δυαδικό αρχείο.
     * <p>
     * Η μέθοδος καλείται κατά το κλείσιμο του προγράμματος.
     * </p>
     */

    public static void save()
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME)))
        {
            out.writeObject(data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Φορτώνει τα δεδομένα της εφαρμογής από δυαδικό αρχείο.
     * <p>
     * Αν το αρχείο δεν υπάρχει, δημιουργείται νέο αντικείμενο
     * {@link CarRentData} και τα αρχικά δεδομένα φορτώνονται
     * από αρχεία CSV.
     * </p>
     * <p>
     * Αν παρουσιαστεί σφάλμα κατά τη φόρτωση, επιστρέφεται
     * νέο αντικείμενο δεδομένων ώστε να συνεχιστεί η εκτέλεση
     * του προγράμματος.
     * </p>
     *
     * @return αντικείμενο {@link CarRentData} με τα φορτωμένα δεδομένα
     */

    public static CarRentData load()
    {
        File file = new File(FILE_NAME);

        if (!file.exists())
        {
            data = new CarRentData();
            readCSV("resources/users.csv","user");
            readCSV("resources/vehicles_with_plates.csv","vehicle");
            readCSV("resources/customers.csv", "customer");
            return data;
        }


        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME)))
        {
            return (CarRentData) in.readObject();       //υπάρχει το αρχείο διαβάζουμε τα δεδομένα του και τα αποθηκεύουμε
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return new CarRentData();       //κάτι δεν πήγε καλά δημιουργούμε μία νέα κλάση για να τρέξει το πρόγραμμα
        }
    }
    /**
     * Διαβάζει δεδομένα από αρχείο CSV και δημιουργεί τα
     * αντίστοιχα αντικείμενα της εφαρμογής.
     * <p>
     * Η μέθοδος υποστηρίζει διαφορετικούς τύπους δεδομένων
     * ανάλογα με την τιμή της παραμέτρου {@code use}.
     * </p>
     * <ul>
     *   <li>{@code "Χρήστης"}: δημιουργία αντικειμένων {@link User}</li>
     *   <li>{@code "Όχημα"}: δημιουργία αντικειμένων {@link Vehicle}</li>
     *   <li>{@code "Πελάτης"}: δημιουργία αντικειμένων {@link Customer}</li>
     * </ul>
     *
     * @param filePath η σχετική διαδρομή του αρχείου CSV
     * @param use ο τύπος δεδομένων που θα διαβαστούν
     */
    public static void readCSV(String filePath, String use) {
        String line;

        try (InputStream is = ManageData.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is)))
        {

            br.readLine();
            //noinspection IfCanBeSwitch
            if(use.equals("vehicle"))
            {
                while ((line = br.readLine()) != null)
                {
                    //χωρίζονται απο κόμμα
                    String[] values = line.split(",");
                    new Vehicle(values);
                }
            }
            else if (use.equals("user"))
            {
                while ((line = br.readLine()) != null)
                {
                    //χωρίζονται απο κόμμα
                    String[] values = line.split(",");
                    new User(values);
                }
            }
            else if(use.equals("customer"))
            {
                while ((line = br.readLine()) != null)
                {
                    //χωρίζονται απο κόμμα
                    String[] values = line.split(",");
                     new Customer(values);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
