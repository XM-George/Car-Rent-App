package API;

import util.I18n;

import java.util.ArrayList;


/**
 * Η κλάση {@code VehicleHelp} παρέχει βοηθητικές συναρτήσεις
 * για έλεγχο και αναζήτηση οχημάτων.
 */
public class VehicleHelp {


    /**
     * Δημιουργεί ένα νέο αντικείμενο {@code VehicleHelp}.
     */
    public VehicleHelp()
    {
        // default constructor
    }

    /**
     * Ελέγχει αν τα στοιχεία ενός οχήματος είναι σωστά για προσθήκη ή επεξεργασία.
     *
     * @param v πίνακας με τα στοιχεία του οχήματος
     * @param vehicle όχημα προς επεξεργασία
     * @return
     * <ul>
     *   <li>0–7 : Το αντίστοιχο πεδίο είναι κενό (0=id, 1=πινακίδα, 2=μάρκα, 3=τύπος, 4=μοντέλο, 5=έτος, 6=χρώμα, 7=κατάσταση)</li>
     *   <li>8 : Το ID υπάρχει ήδη</li>
     *   <li>9 : Το ID δεν είναι έγκυρος αριθμός</li>
     *   <li>10 : Η πινακίδα υπάρχει ήδη</li>
     *   <li>11 : Το έτος παραγωγής δεν είναι έγκυρο (μήκος != 4, οχι αριθμός, ή εκτός 1885–2026)</li>
     *   <li>12 : Το όχημα είναι ήδη νοικιασμένο και δεν μπορεί να γίνει διαθέσιμο</li>
     *   <li>-1 : Όλα τα στοιχεία είναι αποδεκτά</li>
     * </ul>
     */

    public static int checkVehicleDetails(String[] v, Vehicle vehicle)
    {
        String check;
        for(int i=0;i<v.length-1;i++)
        {
            check = v[i];
            if(check == null || check.isEmpty())        //Περίπτωση που υπάρχει άδειο κελί
            {
                return i;       //Ανάλογα με το i που επιστρέφεται ξέρουμε πιο στοιχείο είναι κενό (0 σημαίνει id 1 σημαίνει πινακίδα κτλ) και εμφανίζεται αντίστοιχος διάλογος
            }
        }

        //Έλεγχος για το αν είναι σωστό το id
        //Αν επιστραφεί 8 σημαίνει οτι υπάρχει ήδη αυτό το id σε άλλο όχημα
        //Αν επιστραφεί 9 σημαίνει οτι δεν είναι έγκυρος αριθμός

        check = v[0];
        if(Vehicle.getIDs().contains(check))
        {
            return 8;
        }

        for (char c : check.toCharArray())
        {
            if (!Character.isDigit(c))
            {
                return 9;
            }
        }

        //Έλεγχος για το αν είναι σωστή η πινακίδα
        //Αν επιστραφεί 10 σημαίνει οτι η πινακίδα υπάρχει ήδη σε άλλο όχημα

        check = v[1];
        if(Vehicle.getNPs().contains(check))
        {
            return 10;
        }

        //Έλεγχος για το αν είναι σωστό το έτος
        //Το έτος πρέπει να είναι τετραψήφιος αριθμός ανάμεσα στο 1885 και το 2026, αν επιστραφεί 11 κάτι απο αυτά δεν ισχύει

       check = v[5];
        if(check.length() != 4)
        {
            return 11;
        }

        for (char c : check.toCharArray())
        {
            if (!Character.isDigit(c))
            {
                return 11;
            }
        }

        int year = Integer.parseInt(check);
        if(year < 1885 || year > 2026)
        {
            return 11;
        }

        //Έλεγχος για το αν είναι σωστή η κατάσταση του οχήματος
        //Αν επιστραφεί 12 σημαίνει οτι το αυτοκίνητο είναι ήδη νοικιασμένο σε πελάτη και δεν μπορεί να αλλάξει σε διαθέσιμο, χρησιμοποιείτε για έλεγχο κατά την επεξεργασία

        check = v[7];
        if(check.equals(I18n.getString("vehicle.status.available")))
        {
            if(vehicle != null)
            {
                if(vehicle.getActiveRent() != null)
                {
                    return 12;
                }
            }
        }

        return -1;           //Αν επιστραφεί -1 σημαίνει οτι όλα είναι δεκτά
    }



    /**
     * Αναζητά οχήματα βάσει συγκεκριμένων παραμέτρων.
     * <p>
     * Η αναζήτηση γίνεται μόνο για τα πεδία που δεν είναι κενά.
     * Κάθε νέα παράμετρος φιλτράρει περαιτέρω τα αποτελέσματα.
     * </p>
     *
     * @param elements πίνακας με τα κριτήρια αναζήτησης
     * @return λίστα οχημάτων που ταιριάζουν με τα κριτήρια
     */
    public static ArrayList<Vehicle> SearchVehicles(String []elements)
    {
        ArrayList<Vehicle> allCars = Vehicle.getCars();
        ArrayList<Vehicle> temp = new ArrayList<>();

        //for loop για έλεγχο της κάθε παραμέτρου

        for (int i=0;i<elements.length - 1;i++)
        {
            temp.clear();
            if(elements[i]!=null && !elements[i].isEmpty())     //αν υπάρχει παράμετρος συνεχίζουμε αλλιώς πάμε στην επόμενη
            {

                //για κάθε όχημα στην getCars ελέγχουμε αν η αντίστοιχη παράμετρος που αναζητάμε είναι ίδια με τα δεδομένα του

                for(Vehicle v : allCars)
                {
                    String check = getSpecific(i, v);       //επιστρέφει το ανάλογο στοιχείο του ν σε σχέση με το i (0 επιστρέφει id, 1 επιστρέφει πινακίδα κτλ)
                    if(check !=null && !check.isEmpty())
                    {
                        if(check.equals(elements[i]))
                        {
                            temp.add(v);          //αν είναι ίδια τότε το ν μπαίνει στη λίστα temp
                        }
                    }
                }
                allCars = new ArrayList<>(temp);    //Όταν ελέγξουμε όλα τα οχήματα για μία παράμετρο τότε βάζουμε στην allCars τα οχήματα που πέρασαν τον έλεγχο και επαναλαμβάνουμε τη διαδικασία μέχρι να τελειώσουν οι παράμετροι αναζήτησης
            }
        }

        return allCars;     //επιστρέφουμε την allCars η οποία θα περιέχει όλα τα οχήματα που ταιριάζουν στην αναζήτηση που κάναμε (μπορεί να είναι και κενή)
    }


    /**
     * Επιστρέφει το αντίστοιχο στοιχείο του οχήματος
     * ανάλογα με τον ακέραιο δείκτη που δίνεται.
     *
     * @param i δείκτης του στοιχείου (0=id, 1=πινακίδα, 2=μάρκα, 3=τύπος, 4=μοντέλο, 5=έτος, 6=χρώμα, 7=κατάσταση)
     * @param v όχημα
     * @return η τιμή του ζητούμενου στοιχείου
     */
    protected static String getSpecific(int i, Vehicle v)
    {

        //επιστρέφει ανάλογα με τον ακέραιο i που θα περαστεί το ανάλογο δεδομένο του οχήματος v

        switch (i)
        {
            case 0:
                return v.getId();
            case 1:
                return v.getPlate();
            case 2:
                return v.getBrand();
            case 3:
                return v.getType();
            case 4:
                return v.getModel();
            case 5:
                return v.getYearOfProduction();
            case 6:
                return v.getColor();
            case 7:
                return v.getState();
        }
        return null;
    }
}