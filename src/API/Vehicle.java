package API;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;


/**
 * Η κλάση {@code Vehicle} αναπαριστά ένα όχημα στο σύστημα ενοικίασης.
 * <p>
 * Κάθε όχημα έχει μοναδικό ID, αριθμό πινακίδας και χαρακτηριστικά
 * όπως μάρκα, τύπος, μοντέλο, έτος παραγωγής, χρώμα και κατάσταση.
 * </p>
 */
public class Vehicle implements Serializable {

    /** Αναγνωριστικό έκδοσης για serialization */
    @Serial
    private static final long serialVersionUID = 1L;

    /** Μοναδικό ID του οχήματος */
    private String id;

    /** Αριθμός πινακίδας */
    private String plate;

    /** Μάρκα του οχήματος */
    private String brand;

    /** Τύπος του οχήματος */
    private String type;

    /** Μοντέλο του οχήματος */
    private String model;

    /** Έτος παραγωγής */
    private String yearOfProduction;

    /** Χρώμα του οχήματος */
    private String color;

    /** Κατάσταση του οχήματος */
    private String state;

    /** Λίστα με το ιστορικό ενοικιάσεων του οχήματος*/
    private ArrayList<Rent> rents;

    /** Ενεργή ενοικίαση του οχήματος*/
    private Rent activeRent;


    /**
     * Constructor που δημιουργεί ένα όχημα από έναν πίνακα στοιχείων.
     *
     * @param elements πίνακας με τα στοιχεία του οχήματος:
     * <ul>
     *   <li>0 - ID</li>
     *   <li>1 - πινακίδα</li>
     *   <li>2 - μάρκα</li>
     *   <li>3 - τύπος</li>
     *   <li>4 - μοντέλο</li>
     *   <li>5 - έτος παραγωγής</li>
     *   <li>6 - χρώμα</li>
     *   <li>7 - κατάσταση</li>
     * </ul>
     */

    public Vehicle(String[] elements){
        this.id = elements[0];
        getIDs().add(elements[0]);
        this.plate = elements[1];
        getNPs().add(elements[1]);
        this.brand = elements[2];
        this.type = elements[3];
        this.model = elements[4];
        this.yearOfProduction = elements[5];
        this.color = elements[6];
        this.state = elements[7];
        this.addToList();
        this.rents = new ArrayList<>();
        this.activeRent = null;
    }

    /**
     * Επιστρέφει τη λίστα με όλα τα οχήματα.
     *
     * @return η λίστα με όλα τα οχήματα
     */

    public static ArrayList<Vehicle> getCars() {
        return ManageData.getData().getCars();
    }

    /**
     * Επιστρέφει το σύνολο με όλα τα μοναδικά IDs των οχημάτων.
     *
     * @return το σύνολο με όλα τα μοναδικά IDs
     */

    public static Set<String> getIDs() {
        return ManageData.getData().getIds();
    }

    /**
     * Επιστρέφει το σύνολο με όλες τις πινακίδες των οχημάτων.
     *
     * @return το σύνολο με όλες τις πινακίδες
     */

    public static Set<String> getNPs() {
        return ManageData.getData().getNps();
    }

    /**
     * Επιστρέφει τη λίστα με το ιστορικό ενοικιάσεων του οχήματος.
     *
     * @return τη λίστα με το ιστορικό ενοικιάσεων του οχήματος
     */
    public ArrayList<Rent> getRents() {
        return rents;
    }

    /**
     * Επιστρέφει την ενεργή ενοικίαση του οχήματος αν υπάρχει.
     *
     * @return ενεργή ενοικίαση του οχήματος
     */
    public Rent getActiveRent() {
        return activeRent;
    }

    /**
     * Προσθέτει το όχημα στη λίστα με όλα τα οχήματα.
     */

    public void addToList() {
        getCars().add(this);
    }


    /**
     * Επιστρέφει το ID του οχήματος.
     * @return το μοναδικό αναγνωριστικό του οχήματος
     */
    public String getId(){return id;}

    /**
     * Επιστρέφει την πινακίδα του οχήματος.
     * @return ο αριθμός πινακίδας
     */
    public String getPlate(){return plate;}

    /**
     * Επιστρέφει τη μάρκα του οχήματος.
     * @return η μάρκα του οχήματος
     */
    public String getBrand(){return brand;}

    /**
     * Επιστρέφει τον τύπο του οχήματος.
     * @return ο τύπος του οχήματος
     */
    public String getType(){return type;}

    /**
     * Επιστρέφει το μοντέλο του οχήματος.
     * @return το μοντέλο του οχήματος
     */
    public String getModel(){return model;}

    /**
     * Επιστρέφει το έτος παραγωγής του οχήματος.
     * @return το έτος παραγωγής
     */
    public String getYearOfProduction(){return yearOfProduction;}

    /**
     * Επιστρέφει το χρώμα του οχήματος.
     * @return το χρώμα
     */
    public String getColor(){return color;}

    /**
     * Επιστρέφει την κατάσταση του οχήματος.
     * @return η κατάσταση του οχήματος
     */
    public String getState(){return state;}


    /**
     * Ορίζει το ID του οχήματος.
     * @param id το νέο ID του οχήματος
     */
    public void setId(String id){this.id = id;}

    /**
     * Ορίζει την πινακίδα του οχήματος.
     * @param plate ο νέος αριθμός πινακίδας
     */
    public void setPlate(String plate){this.plate = plate;}

    /**
     * Ορίζει τη μάρκα του οχήματος.
     * @param brand η νέα μάρκα
     */
    public void setBrand(String brand){this.brand = brand;}

    /**
     * Ορίζει το τύπο του οχήματος.
     * @param type ο νέος τύπος
     */
    public void setType(String type){this.type = type;}

    /**
     * Ορίζει το μοντέλο του οχήματος.
     * @param model το νέο μοντέλο
     */
    public void setModel(String model){this.model = model;}

    /**
     * Ορίζει το έτος του οχήματος.
     * @param yearOfProduction το νέο έτος παραγωγής
     */
    public void setYearOfProduction(String yearOfProduction){this.yearOfProduction = yearOfProduction;}

    /**
     * Ορίζει το χρώμα του οχήματος.
     * @param color το νέο χρώμα
     */
    public void setColor(String color){this.color = color;}

    /**
     * Ορίζει την κατάσταση του οχήματος.
     * @param state η νέα κατάσταση
     */
    public void setState(String state){this.state = state;}

    /**
     * Ορίζει την ενεργή ενοικίαση του οχήματος.
     * @param r η νέα ενεργή ενοικίαση
     */
    public void setActiveRent(Rent r){this.activeRent = r;}

    /**
     * Βοηθητική συνάρτηση για επεξεργασία όλων των στοιχείων του οχήματος.
     *
     * @param a πίνακας με τα νέα στοιχεία του οχήματος
     */

    public void EditAll(String[] a)
    {
        setId(a[0]);
        setPlate(a[1]);
        setBrand(a[2]);
        setType(a[3]);
        setModel(a[4]);
        setYearOfProduction(a[5]);
        setColor(a[6]);
        setState(a[7]);
    }

}



