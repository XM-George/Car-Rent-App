package API;

import java.util.ArrayList;

/**
 * Η κλάση {@code EntitySearch} παρέχει γενικό μηχανισμό αναζήτησης
 * για διαφορετικούς οντότητες του προγράμματος.
 * <p>
 * Υποστηρίζει αναζήτηση για:
 * <ul>
 *   <li>Χρήστες</li>
 *   <li>Πελάτες</li>
 *   <li>Οχήματα</li>
 * </ul>
 * Ανάλογα με τον τύπο που δίνεται.
 */

public class EntitySearch
{

    /**
     * Δημιουργεί ένα νέο αντικείμενο {@code EntitySearch}.
     */
    public EntitySearch()
    {
        // default constructor
    }

    /**
     * Εκτελεί αναζήτηση οντότητας βάσει παραμέτρων και τύπου.
     *
     * @param a    πίνακας με τις παραμέτρους αναζήτησης
     * @param type ο τύπος της οντότητας προς αναζήτηση
     *             (π.χ. "Χρήστης", "Πελάτης", "Όχημα")
     * @return ακέραιος που δηλώνει το αποτέλεσμα της αναζήτησης:
     *         <ul>
     *           <li> 0 : Όλες οι παράμετροι είναι {@code null}</li>
     *           <li>-2 : Δε δόθηκε καμία παράμετρος αναζήτησης</li>
     *           <li>-1 : Δε βρέθηκαν αποτελέσματα</li>
     *           <li>&gt;0 : Πλήθος αποτελεσμάτων που βρέθηκαν</li>
     *         </ul>
     */

    public int searchEntity(String[] a, String type)
    {
        if(checkForNull(a))         //Έλεγχος για το αν είναι όλα null
        {
            return 0;
        }

        boolean flag=true;
        for(String s : a)     //Έλεγχος για το αν οι παράμετροι είναι κενοί, δηλαδή ""
        {
            if(!s.isEmpty())
            {
                flag=false;     //Αν έστω και μία δεν είναι αλλάζουμε το flag και βγαίνουμε
                break;
            }
        }
        if (!flag)          //Αν μπούμε εδώ σημαίνει οτι έχουμε αναζητήσει με τουλάχιστον μια παράμετρο
        {

            //Φτιάχνουμε ένα γενικό ArrayList

            ArrayList<?> searchResult = null;

            //Ανάλογα με τον τύπο που θέλουμε να βρούμε καλούμε την ανάλογη search

            switch (type)
            {
                case "Χρήστης":
                    searchResult = UserHelp.SearchUsers(a);
                    break;
                case "Πελάτης":
                    searchResult = CustomerHelp.SearchCustomer(a);
                    break;
                case "Όχημα":
                    searchResult = VehicleHelp.SearchVehicles(a);
                    break;
            }
            if(searchResult==null || searchResult.isEmpty())
            {
                return -1;      //Αν μπούμε σε αυτό το if σημαίνει οτι δεν έχουμε αποτελέσματα στην αναζήτηση, επιστρέφεται -1 ώστε να εμφανιστεί ο σωστός διάλογος
            }
            else
            {
                return searchResult.size();         //Αλλιώς επιστρέφεται το πλήθος των αποτελεσμάτων που βρήκαμε
            }
        }
        else               //Δε βρέθηκαν παράμετροι αναζήτησης
        {
           return -2;       //επιστρέφεται -2 ώστε να εμφανιστεί ο σωστός διάλογος
        }
    }

    /**
     * Ελέγχει αν όλες οι παράμετροι ενός πίνακα είναι {@code null}.
     *
     * @param a πίνακας προς έλεγχο
     * @return {@code true} αν όλα τα στοιχεία είναι {@code null},
     *         {@code false} αν υπάρχει έστω ένα μη {@code null} στοιχείο
     */

    public boolean checkForNull(String[] a)
    {
        for(String s : a)
        {
            if(s != null)
            {
                //Μόλις βρεθεί έστω και ένα στοιχείο που δεν είναι null επιστρέφεται false

                return false;
            }
        }

        //Άμα επιστραφεί true έχει τελειώσει ο έλεγχος όλων των στοιχείων και είναι ολα null

        return true;
    }
}