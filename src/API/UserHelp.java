package API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * Η κλάση {@code UserHelp} παρέχει βοηθητικές μεθόδους
 * για διαχείριση χρηστών, έλεγχο στοιχείων και αναζήτηση.
 */
public class UserHelp
{
    /**
     * Δημιουργεί ένα νέο αντικείμενο {@code UserHelp}.
     */
    public UserHelp()
    {
        // default constructor
    }

    /**
     * Ελέγχει τα credentials (username και password) για το login.
     *
     * @param username το username που δίνεται
     * @param password το password που δίνεται
     * @return
     * <ul>
     *   <li>1 : Το username υπάρχει και το password είναι σωστό, είσοδος στην εφαρμογή</li>
     *   <li>0 : Το username υπάρχει αλλά το password είναι λάθος</li>
     *   <li>2 : Το username δεν υπάρχει</li>
     * </ul>
     */

    public int checkCredentials(String username, String password)
    {
        Map<String, String> credentials = new HashMap<>(User.getUserCredentials());

        if(credentials.containsKey(username))
        {
            if(credentials.get(username).equals(password))
            {
                return 1;       //αν υπάρχει το username και αν το password είναι σωστό επιστρέφεται 1 και γίνεται login
            }
            else
            {
                return 0;       //αν υπάρχει το username και το password δεν είναι σωστό επιστρέφεται 0 και εμφανίζεται αντίστοιχο μήνυμα
            }
        }
        else
        {
            return 2;           //αν δεν υπάρχει το username επιστρέφεται 2 και εμφανίζεται αντίστοιχο μήνυμα
        }
    }


    /**
     * Ελέγχει αν τα στοιχεία ενός χρήστη είναι σωστά για προσθήκη ή επεξεργασία.
     *
     * @param u πίνακας με τα στοιχεία του χρήστη
     * @return
     * <ul>
     *   <li>0–4 : δείχνει αν και ποιο στοιχείο είναι κενό (0=όνομα, 1=επίθετο, 2=username, 3=email, 4=password)</li>
     *   <li>5 : Το username υπάρχει ήδη</li>
     *   <li>6 : Το email υπάρχει ήδη</li>
     *   <li>7 : Το email δεν είναι έγκυρο (δεν περιέχει '@')</li>
     *   <li>-1 : Όλα τα στοιχεία είναι αποδεκτά</li>
     * </ul>
     */
    public static int checkUserDetails(String[] u)
    {
        String check;
        for(int i=0;i<u.length-1;i++)
        {
            check = u[i];
            if(check == null || check.isEmpty())        //Περίπτωση που υπάρχει άδειο κελί
            {
                return i;       //Ανάλογα με το i που επιστρέφεται ξέρουμε πιο στοιχείο είναι κενό (0 σημαίνει name 1 σημαίνει surname) και εμφανίζεται αντίστοιχος διάλογος
            }
        }

        //Έλεγχος για το αν είναι σωστό το username
        //Αν επιστραφεί 5 σημαίνει οτι υπάρχει ήδη αυτό το username σε άλλο user

        check = u[2];
        if(User.getUserUsernames().contains(check))
        {
            return 5;
        }

        //Έλεγχος για το αν είναι σωστό το email
        //Αν επιστραφεί 6 σημαίνει οτι το email υπάρχει ήδη σε άλλο user
        //Αν επιστραφεί 7 σημαίνει οτι το email δεν είναι έγκυρο

        check = u[3];
        if(User.getUserEmails().contains(check))
        {
            return 6;
        }

        if(!check.contains("@"))
        {
            return 7;
        }


        return -1;           //Αν επιστραφεί -1 σημαίνει οτι όλα είναι δεκτά
    }


    /**
     * Αναζητά χρήστες βάσει παραμέτρων.
     *
     * @param elements πίνακας παραμέτρων αναζήτησης (name, surname, username, email)
     * @return λίστα {@link User} που ταιριάζουν με τις παραμέτρους
     */
    public static ArrayList<User> SearchUsers(String[] elements)
    {
        ArrayList<User> allUsers = User.getUsers();
        ArrayList<User> temp = new ArrayList<>();

        //for loop για έλεγχο της κάθε παραμέτρου

        for (int i=0;i<elements.length - 1;i++)
        {
            temp.clear();
            if(elements[i]!=null && !elements[i].isEmpty())     //αν υπάρχει παράμετρος συνεχίζουμε αλλιώς πάμε στην επόμενη
            {

                //για κάθε user στην allUsers ελέγχουμε αν η αντίστοιχη παράμετρος που αναζητάμε είναι ίδια με τα δεδομένα του

                for(User u : allUsers)
                {
                    String check = getSpecific(i, u);       //επιστρέφει το ανάλογο στοιχείο του u σε σχέση με το i (0 επιστρέφει όνομα, 1 επιστρέφει επίθετο κτλ)
                    if(check !=null && !check.isEmpty())
                    {
                        if(check.equals(elements[i]))
                        {
                            temp.add(u);          //αν είναι ίδια τότε το u μπαίνει στη λίστα temp
                        }
                    }
                }
                allUsers = new ArrayList<>(temp);    //Όταν ελέγξουμε όλους τους users για μία παράμετρο τότε βάζουμε στην allUsers τους users που πέρασαν τον έλεγχο και επαναλαμβάνουμε τη διαδικασία μέχρι να τελειώσουν οι παράμετροι αναζήτησης
            }
        }

        return allUsers;     //επιστρέφουμε την allUsers η οποία θα περιέχει όλα τους users που ταιριάζουν στην αναζήτηση που κάναμε (μπορεί να είναι και κενή)
    }



    /**
     * Επιστρέφει το συγκεκριμένο στοιχείο ενός χρήστη με βάση τον δείκτη.
     *
     * @param i δείκτης του στοιχείου (0=name, 1=surname, 2=username, 3=email, 4=password)
     * @param u χρήστης
     * @return το ζητούμενο στοιχείο ως String
     */
    protected static String getSpecific(int i, User u)
    {

        //επιστρέφει ανάλογα με τον ακέραιο i που θα περαστεί το ανάλογο δεδομένο του user u

        switch (i)
        {
            case 0:
                return u.getName();
            case 1:
                return u.getSurname();
            case 2:
                return u.getUsername();
            case 3:
                return u.getEmail();
            case 4:
                return u.getPassword();
        }
        return null;
    }

    /**
     * Διαγράφει έναν χρήστη και όλα τα δεδομένα του.
     *
     * @param u χρήστης προς διαγραφή
     */

    public static void deleteUser(User u)
    {
        User.getUserUsernames().remove(u.getUsername());        //Διαγράφει το username από τη λίστα με τα usernames
        User.getUserEmails().remove(u.getEmail());          //Διαγράφει το email από τη λίστα με τα emails
        User.getUserCredentials().remove(u.getUsername());  //Διαγράφει τα username και password από το map με τα credentials των χρηστών
        User.getUsers().remove(u);                          //Διαγράφει τον user από τη λίστα με όλους τους users
    }
}
