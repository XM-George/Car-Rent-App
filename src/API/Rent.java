package API;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * Η κλάση {@code Rent} αναπαριστά μία ενοικίαση οχήματος στο σύστημα.
 * <p>
 * Κάθε ενοικίαση συνδέεται:
 * <ul>
 *     <li>Με έναν χρήστη (username)</li>
 *     <li>Με έναν πελάτη ({@link Customer})</li>
 *     <li>Με ένα όχημα ({@link Vehicle})</li>
 *     <li>Με ημερομηνία ενοικίασης και επιστροφής</li>
 * </ul>
 * <p>
 * Η κλάση υλοποιεί {@link Serializable} ώστε τα αντικείμενα ενοικίασης
 * να μπορούν να αποθηκευτούν και να ανακτηθούν από αρχείο.
 */
public class Rent implements Serializable {

    /** Αναγνωριστικό έκδοσης για serialization */
    @Serial
    private static final long serialVersionUID = 1L;

    /** Μοναδικό αναγνωριστικό της ενοικίασης */
    private long rentId;

    /** Username του χρήστη που καταχώρησε την ενοικίαση */
    private String username;

    /** Ο πελάτης στον οποίο ανήκει η ενοικίαση */
    private Customer c;

    /** Το όχημα που ενοικιάζεται */
    private Vehicle v;

    /** Ημερομηνία έναρξης της ενοικίασης */
    private Date rentDate;

    /** Ημερομηνία επιστροφής του οχήματος */
    private Date returnDate;

    /** Κατάσταση της ενοικίασης (π.χ. Ενεργή Ενοικίαση, Ολοκληρωμένη) */
    private String state;


    /**
     * Δημιουργεί μία νέα ενοικίαση οχήματος.
     * <p>
     * Κατά τη δημιουργία:
     * <ul>
     *     <li>Ανατίθεται αυτόματα μοναδικό ID</li>
     *     <li>Η ενοικίαση προστίθεται στις λίστες του συστήματος</li>
     *     <li>Το όχημα σημειώνεται ως «Ενοικιασμένο»</li>
     *     <li>Η ενοικίαση προστίθεται στις ενεργές ενοικιάσεις πελάτη και οχήματος</li>
     * </ul>
     *
     * @param username   το username του χρήστη που καταχώρησε την ενοικίαση
     * @param c          ο πελάτης που ενοικιάζει το όχημα
     * @param v          το όχημα που ενοικιάζεται
     * @param rentDate   ημερομηνία ενοικίασης
     * @param returnDate ημερομηνία επιστροφής
     */

    public Rent(String username, Customer c, Vehicle v, Date rentDate, Date returnDate)
    {
        this.rentId = ManageData.getData().getRentIdCounter();
        this.username = username;
        this.c = c;
        this.v = v;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.state = "Ενεργή Ενοικίαση";
        this.addToList();
        c.getRents().add(this);
        c.getActiveRents().add(this);
        v.getRents().add(this);
        v.setState("Ενοικιασμένο");
        v.setActiveRent(this);
    }


    /**
     * Επιστρέφει το αναγνωριστικό της ενοικίασης.
     *
     * @return το ID της ενοικίασης
     */
    public long getRentId() {
        return rentId;
    }

    /**
     * Επιστρέφει το username του χρήστη που καταχώρησε την ενοικίαση.
     *
     * @return το username του χρήστη
     */
    public String getUsername() {
        return username;
    }

    /**
     * Επιστρέφει τον πελάτη της ενοικίασης.
     *
     * @return ο πελάτης
     */
    public Customer getCustomer() {
        return c;
    }

    /**
     * Επιστρέφει το όχημα της ενοικίασης.
     *
     * @return το όχημα
     */
    public Vehicle getVehicle() {
        return v;
    }

    /**
     * Επιστρέφει την ημερομηνία ενοικίασης.
     *
     * @return η ημερομηνία ενοικίασης
     */
    public Date getRentDate() {
        return rentDate;
    }

    /**
     * Επιστρέφει την ημερομηνία επιστροφής.
     *
     * @return η ημερομηνία επιστροφής
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Επιστρέφει την κατάσταση της ενοικίασης.
     *
     * @return η κατάσταση της ενοικίασης
     */
    public String getState() {
        return state;
    }

    /**
     * Ορίζει το αναγνωριστικό της ενοικίασης.
     *
     * @param rentId το νέο ID
     */
    public void setRentId(long rentId) {
        this.rentId = rentId;
    }

    /**
     * Ορίζει το username του χρήστη την ενοικίασης.
     *
     * @param username το username
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Ορίζει τον πελάτη της ενοικίασης.
     *
     * @param c ο πελάτης
     */
    public void setCustomer(Customer c) {
        this.c = c;
    }

    /**
     * Ορίζει το όχημα της ενοικίασης.
     *
     * @param v το όχημα
     */
    public void setVehicle(Vehicle v) {
        this.v = v;
    }

    /**
     * Ορίζει την ημερομηνία ενοικίασης.
     *
     * @param rentDate η ημερομηνία ενοικίασης
     */
    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * Ορίζει την ημερομηνία επιστροφής.
     *
     * @param returnDate η ημερομηνία επιστροφής
     */
    public void setReturnDate(Date returnDate) {this.returnDate = returnDate;}

    /**
     * Ορίζει την κατάσταση της ενοικίασης.
     *
     * @param state η νέα κατάσταση
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Προσθέτει την ενοικίαση στη λίστα ενοικιάσεων του συστήματος.
     */
    private void addToList()
    {
        ManageData.getData().getRents().add(this);
    }
}
