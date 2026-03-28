package API;

import java.util.ArrayList;

/**
 * Η κλάση {@code CustomerHelp} περιέχει βοηθητικές μεθόδους
 * για τον έλεγχο και την αναζήτηση πελατών.
 * <p>
 * Περιλαμβάνει ελέγχους εγκυρότητας στοιχείων πελάτη καθώς και
 * μηχανισμό αναζήτησης πελατών βάσει συγκεκριμένων κριτηρίων.
 * </p>
 */
public class CustomerHelp {



    /**
     * Δημιουργεί ένα νέο αντικείμενο {@code CustomerHelp}.
     */
    public CustomerHelp()
    {
        // default constructor
    }

    /**
     * Ελέγχει την εγκυρότητα των στοιχείων ενός πελάτη.
     *
     * @param c πίνακας με τα στοιχεία του πελάτη:
     *          <ul>
     *            <li>0 - Όνομα</li>
     *            <li>1 - Επώνυμο</li>
     *            <li>2 - Τηλέφωνο</li>
     *            <li>3 - Email</li>
     *            <li>4 - ID</li>
     *          </ul>
     * @return ακέραιος κωδικός που δηλώνει το αποτέλεσμα του ελέγχου:
     *         <ul>
     *           <li>0–4 : Υπάρχει κενό πεδίο (ανάλογα με τη θέση)</li>
     *           <li>5 : Όχι έγκυρο τηλέφωνο</li>
     *           <li>6 : Όχι έγκυρο email</li>
     *           <li>7 : Υπάρχει ήδη πελάτης με το ίδιο ID</li>
     *           <li>8 : To ID δεν είναι αριθμός</li>
     *           <li>-1 : Όλα τα στοιχεία είναι έγκυρα</li>
     *         </ul>
     */
    public static int checkCustomerDetails(String[] c)
    {
        String check;
        for(int i=0;i<c.length-1;i++)
        {
            check = c[i];
            if(check == null || check.isEmpty())        //Περίπτωση που υπάρχει άδειο κελί
            {
                return i;       //Ανάλογα με το i που επιστρέφεται ξέρουμε πιο στοιχείο είναι κενό (0 σημαίνει όνομα 1 σημαίνει επίθετο κτλ) και εμφανίζεται αντίστοιχος διάλογος
            }
        }

        //Έλεγχος για το αν είναι σωστό το τηλέφωνο
        //Αν επιστραφεί 5 σημαίνει οτι το τηλέφωνο δεν είναι έγκυρο

        check = c[2];
        for (char ch : check.toCharArray())
        {
            if (!Character.isDigit(ch))
            {
                return 5;
            }
        }

        //Έλεγχος για το αν είναι σωστό το email
        //Αν επιστραφεί 6 σημαίνει οτι το email δεν είναι έγκυρο

        check = c[3];
        if(!check.contains("@"))
        {
            return 6;
        }

        //Έλεγχος για το αν είναι σωστό το ID
        //Αν επιστραφεί 7 σημαίνει οτι υπάρχει ήδη αυτό το ID σε άλλο πελάτη
        //Αν επιστραφεί 8 σημαίνει οτι το ID δεν είναι έγκυρο

        check = c[4];
        if(Customer.getCustomerId().contains(check))
        {
            return 7;
        }

        for (char ch : check.toCharArray())
        {
            if (!Character.isDigit(ch))
            {
                return 8;
            }
        }

        return -1;           //Αν επιστραφεί -1 σημαίνει οτι όλα είναι δεκτά
    }

    /**
     * Αναζητά πελάτες βάσει συγκεκριμένων παραμέτρων.
     * <p>
     * Η αναζήτηση γίνεται μόνο για τα πεδία που δεν είναι κενά.
     * Κάθε νέα παράμετρος φιλτράρει περαιτέρω τα αποτελέσματα.
     * </p>
     *
     * @param elements πίνακας με τα κριτήρια αναζήτησης
     * @return λίστα πελατών που ταιριάζουν με τα κριτήρια
     */
    public static ArrayList<Customer> SearchCustomer(String []elements)
    {
        ArrayList<Customer> allCustomers = Customer.getCustomers();
        ArrayList<Customer> temp = new ArrayList<>();

        //for loop για έλεγχο της κάθε παραμέτρου

        for (int i=0;i<elements.length - 1;i++)
        {
            temp.clear();
            if(elements[i]!=null && !elements[i].isEmpty())     //αν υπάρχει παράμετρος συνεχίζουμε αλλιώς πάμε στην επόμενη
            {

                //για κάθε πελάτη στην getCustomers ελέγχουμε αν η αντίστοιχη παράμετρος που αναζητάμε είναι ίδια με τα δεδομένα του

                for(Customer c : allCustomers)
                {
                    String check = getSpecific(i, c);       //επιστρέφει το ανάλογο στοιχείο του c σε σχέση με το i (0 επιστρέφει όνομα, 1 επιστρέφει επίθετο κτλ)
                    if(check !=null && !check.isEmpty())
                    {
                        if(check.equals(elements[i]))
                        {
                            temp.add(c);          //αν είναι ίδια τότε το c μπαίνει στη λίστα temp
                        }
                    }
                }
                allCustomers = new ArrayList<>(temp);    //Όταν ελέγξουμε όλους τους πελάτες για μία παράμετρο τότε βάζουμε στην allCustomers τους πελάτες που πέρασαν τον έλεγχο και επαναλαμβάνουμε τη διαδικασία μέχρι να τελειώσουν οι παράμετροι αναζήτησης
            }
        }

        return allCustomers;     //επιστρέφουμε την allCustomers η οποία θα περιέχει όλους τους πελάτες που ταιριάζουν στην αναζήτηση που κάναμε (μπορεί να είναι και κενή)
    }

    /**
     * Επιστρέφει συγκεκριμένο στοιχείο ενός πελάτη
     * ανάλογα με τον ακέραιο δείκτη που δίνεται.
     *
     * @param i δείκτης στοιχείου:
     *          <ul>
     *            <li>0 - Όνομα</li>
     *            <li>1 - Επώνυμο</li>
     *            <li>2 - Τηλέφωνο</li>
     *            <li>3 - Email</li>
     *            <li>4 - ID</li>
     *          </ul>
     * @param c ο πελάτης
     * @return το αντίστοιχο στοιχείο του πελάτη ή {@code null}
     */

    protected static String getSpecific(int i, Customer c)
    {

        //επιστρέφει ανάλογα με τον ακέραιο i που θα περαστεί το ανάλογο δεδομένο του οχήματος v

        switch (i)
        {
            case 0:
                return c.getName();
            case 1:
                return c.getSurname();
            case 2:
                return c.getPhone();
            case 3:
                return c.getEmail();
            case 4:
                return c.getId();
        }
        return null;
    }
}

