package API;

//κλάση που περιέχει όλα τα δεδομένα του προγράμματος

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * Η κλάση {@code CarRentData} αποθηκεύει όλα τα κύρια δεδομένα
 * της εφαρμογής ενοικίασης αυτοκινήτων.
 * <p>
 * Περιέχει όλες τις στοιχεία που χρησιμοποιούνται από το πρόγραμμα,
 * όπως χρήστες, πελάτες, οχήματα και μοναδικά δεδομένα.
 * </p>
 * <p>
 * Η κλάση είναι {@link Serializable} ώστε να μπορεί να αποθηκεύεται
 * και να φορτώνεται από αρχείο.
 * </p>
 */
public class CarRentData implements Serializable {

    /** Αναγνωριστικό έκδοσης για serialization */
    @Serial
    private static final long serialVersionUID = 1L;

    /** Λίστα όλων των χρηστών του συστήματος */
    private ArrayList<User> users = new ArrayList<>();

    /** Σύνολο με όλα τα usernames των χρηστών (για μοναδικότητα) */
    private Set<String> userUsernames = new HashSet<>();

    /** Σύνολο με όλα τα emails των χρηστών (για μοναδικότητα) */
    private Set<String> userEmails = new HashSet<>();

    /** Χάρτης με credentials χρηστών (username και password) */
    private Map<String, String> userCredentials = new HashMap<>();

    /** Λίστα όλων των πελατών */
    private ArrayList<Customer> customers = new ArrayList<>();

    /** Σύνολο με όλα τα ID των πελατών (για μοναδικότητα) */
    private Set<String> customerId = new HashSet<>();

    /** Λίστα όλων των οχημάτων */
    private ArrayList<Vehicle> cars = new ArrayList<>();

    /** Σύνολο με όλα τα ID των οχημάτων (για μοναδικότητα) */
    private Set<String> ids = new HashSet<>();

    /** Σύνολο με όλες τις πινακίδες των οχημάτων (για μοναδικότητα) */
    private Set<String> nps = new HashSet<>();

    /** Μετρητής για τα id ενοικίασης */
    private int rentIdCounter = 0;

    /** Λίστα όλων των ενοικιάσεων */
    private ArrayList<Rent>  rents = new ArrayList<>();



    /**
     * Δημιουργεί ένα νέο αντικείμενο {@code CarRentData}.
     */
    public CarRentData()
    {
        // Default constructor
    }

    /**
     * Επιστρέφει τη λίστα όλων των χρηστών.
     *
     * @return λίστα χρηστών
     */
    protected ArrayList<User> getUser() {
        return users;
    }

    /**
     * Επιστρέφει το σύνολο με όλα τα usernames των χρηστών.
     *
     * @return σύνολο usernames
     */
    protected Set<String> getUserUsernames() {
        return userUsernames;
    }

    /**
     * Επιστρέφει το σύνολο με όλα τα emails των χρηστών.
     *
     * @return σύνολο emails
     */
    protected Set<String> getUserEmails() {
        return userEmails;
    }

    /**
     * Επιστρέφει τον χάρτη με τα credentials των χρηστών.
     *
     * @return map username, password
     */
    protected Map<String, String> getUserCredentials() {
        return userCredentials;
    }

    /**
     * Επιστρέφει τη λίστα όλων των πελατών.
     *
     * @return λίστα πελατών
     */
    protected ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     * Επιστρέφει το σύνολο με όλα τα ID των πελατών.
     *
     * @return σύνολο ID πελατών
     */
    protected Set<String> getCustomersId() {
        return customerId;
    }

    /**
     * Επιστρέφει τη λίστα όλων των οχημάτων.
     *
     * @return λίστα οχημάτων
     */
    protected ArrayList<Vehicle> getCars() {
        return cars;
    }

    /**
     * Επιστρέφει το σύνολο με όλα τα ID των οχημάτων.
     *
     * @return σύνολο IDs
     */
    protected Set<String> getIds() {
        return ids;
    }

    /**
     * Επιστρέφει το σύνολο με όλες τις πινακίδες των οχημάτων.
     *
     * @return σύνολο NPS
     */
    protected Set<String> getNps() {
        return nps;
    }

    /**
     * Επιστρέφει τη λίστα όλων των ενοικιάσεων.
     *
     * @return λίστα ενοικιάσεων
     */
    protected ArrayList<Rent> getRents()
    {
        return rents;
    }


    /**
     * Επιστρέφει τον μετρητή rentId.
     *
     * @return μετρητή rentId
     */
    protected int getRentIdCounter()
    {
        rentIdCounter++;
        return rentIdCounter;
    }
}
