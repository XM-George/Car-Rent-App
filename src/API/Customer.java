package API;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

/**
 * Η κλάση {@code Customer} αναπαριστά έναν πελάτη της εφαρμογής
 * ενοικίασης αυτοκινήτων.
 * <p>
 * Κάθε πελάτης διαθέτει προσωπικά στοιχεία όπως όνομα, επώνυμο,
 * τηλέφωνο, email και ID.
 * </p>
 * <p>
 * Η κλάση υλοποιεί {@link Serializable} ώστε οι πελάτες να μπορούν
 * να αποθηκεύονται και να φορτώνονται από αρχείο.
 * </p>
 */
public class Customer implements Serializable {

    /** Αναγνωριστικό έκδοσης για serialization */
    @Serial
    private static final long serialVersionUID = 1L;

    /** Όνομα πελάτη */
    String name;

    /** Επώνυμο πελάτη */
    String surname;

    /** Τηλέφωνο πελάτη */
    String phone;

    /** Email πελάτη */
    String email;

    /** ID πελάτη */
    String id;

    /** Λίστα με το Ιστορικό Ενοικιάσεων πελάτη*/
    ArrayList<Rent> rents;

    /** Λίστα με τις Ενεργές Ενοικιάσεις πελάτη*/
    ArrayList<Rent> activeRents;

    /**
     * Κατασκευαστής πελάτη.
     * <p>
     * Δημιουργεί έναν νέο πελάτη χρησιμοποιώντας έναν πίνακα String
     * με τα στοιχεία του πελάτη και τον προσθέτει αυτόματα
     * στη λίστα πελατών και το ID του στο σύνολο ID.
     * Επίσης, αρχικοποιεί τις λίστες με τις ενεργές και όχι ενεργές ενοικιάσεις
     * </p>
     *
     * @param elements πίνακας με τα στοιχεία του πελάτη:
     *                 <ul>
     *                   <li>0 - Όνομα</li>
     *                   <li>1 - Επώνυμο</li>
     *                   <li>2 - Τηλέφωνο</li>
     *                   <li>3 - Email</li>
     *                   <li>4 - ID</li>
     *                 </ul>
     */
    public Customer(String[] elements) {
        this.name = elements[0];
        this.surname = elements[1];
        this.phone = elements[2];
        this.email = elements[3];
        this.id = elements[4];
        getCustomerId().add(elements[4]);
        this.addToList();
        rents = new ArrayList<>();
        activeRents = new ArrayList<>();
    }

    /**
     * Επιστρέφει το όνομα του πελάτη.
     *
     * @return όνομα πελάτη
     */
    public String getName() {
        return name;
    }

    /**
     * Επιστρέφει το επώνυμο του πελάτη.
     *
     * @return επώνυμο πελάτη
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Επιστρέφει το τηλέφωνο του πελάτη.
     *
     * @return τηλέφωνο πελάτη
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Επιστρέφει το email του πελάτη.
     *
     * @return email πελάτη
     */
    public String getEmail() {
        return email;
    }

    /**
     * Επιστρέφει το ID του πελάτη.
     *
     * @return ID πελάτη
     */
    public String getId() {
        return id;
    }

    /**
     * Θέτει το όνομα του πελάτη.
     *
     * @param name νέο όνομα
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Θέτει το επώνυμο του πελάτη.
     *
     * @param surname νέο επώνυμο
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Θέτει το τηλέφωνο του πελάτη.
     *
     * @param phone νέο τηλέφωνο
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Θέτει το email του πελάτη.
     *
     * @param email νέο email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Θέτει το ID του πελάτη.
     *
     * @param id νέο ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Επιστρέφει τη λίστα με όλους τους πελάτες του συστήματος.
     *
     * @return λίστα πελατών
     */
    public static ArrayList<Customer> getCustomers() {
        return ManageData.getData().getCustomers();
    }

    /**
     * Επιστρέφει το σύνολο με όλα τα μοναδικά ID των πελατών.
     *
     * @return σύνολο ID πελατών
     */
    public static Set<String> getCustomerId() {
        return ManageData.getData().getCustomersId();
    }

    /**
     * Επιστρέφει τη λίστα με το ιστορικό ενοικιάσεων του πελάτη.
     *
     * @return τη λίστα με το ιστορικό ενοικιάσεων του πελάτη
     */
    public ArrayList<Rent> getRents() {
        return rents;
    }

    /**
     * Επιστρέφει τη λίστα με τις ενεργές ενοικιάσεις του πελάτη.
     *
     * @return τη λίστα με τις ενεργές ενοικιάσεις του πελάτη
     */
    public ArrayList<Rent> getActiveRents() {
        return activeRents;
    }

    /**
     * Προσθέτει τον πελάτη στη λίστα όλων των πελατών.
     */
    public void addToList() {
        getCustomers().add(this);
    }

    /**
     * Ενημερώνει όλα τα στοιχεία του πελάτη.
     *
     * @param a πίνακας με τα νέα στοιχεία:
     *          <ul>
     *            <li>0 - Όνομα</li>
     *            <li>1 - Επώνυμο</li>
     *            <li>2 - Τηλέφωνο</li>
     *            <li>3 - Email</li>
     *            <li>4 - ID</li>
     *          </ul>
     */
    public void EditAll(String[] a) {
        setName(a[0]);
        setSurname(a[1]);
        setPhone(a[2]);
        setEmail(a[3]);
        setId(a[4]);
    }
}