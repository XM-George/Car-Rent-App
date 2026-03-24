package API;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;



/**
 * Η κλάση {@code User} αναπαριστά έναν χρήστη του συστήματος.
 * <p>
 * Περιέχει τα βασικά στοιχεία του χρήστη όπως:
 * όνομα, επίθετο, username, email και password.
 * Παρέχει επίσης μεθόδους για πρόσβαση και διαχείριση των
 * χρηστών και των στοιχείων τους.
 * </p>
 */
public class User implements Serializable
{

    /** Αναγνωριστικό έκδοσης για serialization */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Όνομα του χρήστη.
     */
    private String name;

    /**
     * Επίθετο του χρήστη.
     */
    private String surname;

    /**
     * Username του χρήστη, μοναδικό για κάθε χρήστη.
     */
    private String username;

    /**
     * Email του χρήστη.
     */
    private String email;

    /**
     * Κωδικός πρόσβασης του χρήστη.
     */
    private String password;

    /**
     * Δημιουργεί έναν νέο χρήστη και τον προσθέτει στα αντίστοιχα
     * σύνολα.
     *
     * @param elements πίνακας με τα στοιχεία του χρήστη:
     *                 <ul>
     *                   <li>0 - name</li>
     *                   <li>1 - surname</li>
     *                   <li>2 - username</li>
     *                   <li>3 - email</li>
     *                   <li>4 - password</li>
     *                 </ul>
     */

    public User(String[] elements)
    {
        this.name = elements[0];
        this.surname = elements[1];
        this.username = elements[2];
        getUserUsernames().add(elements[2]);
        this.email = elements[3];
        getUserEmails().add(elements[3]);
        this.password = elements[4];
        getUserCredentials().put(elements[2], elements[4]);
        this.addToList();
    }

    /**
     * Επιστρέφει το όνομα του χρήστη.
     *
     * @return όνομα
     */
    public String getName() {
        return name;
    }

    /**
     * Επιστρέφει το επίθετο του χρήστη.
     *
     * @return επίθετο
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Επιστρέφει το username του χρήστη.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Επιστρέφει το email του χρήστη.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Επιστρέφει τον κωδικό πρόσβασης του χρήστη.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Ορίζει το όνομα του χρήστη.
     *
     * @param name το νέο όνομα
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Ορίζει το επίθετο του χρήστη.
     *
     * @param surname το νέο όνομα
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }


    /**
     * Ορίζει το username του χρήστη.
     *
     * @param username το νέο όνομα
     */
    public void setUsername(String username)
    {
        this.username = username;
    }


    /**
     * Ορίζει το email του χρήστη.
     *
     * @param email το νέο όνομα
     */
    public void setEmail(String email)
    {
        this.email = email;
    }


    /**
     * Ορίζει το password του χρήστη.
     *
     * @param password το νέο όνομα
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Επιστρέφει τη λίστα με όλους τους χρήστες.
     *
     * @return λίστα {@link User} με όλους τους χρήστες
     */

    public static ArrayList<User> getUsers()
    {
        return ManageData.getData().getUser();
    }

    /**
     * Επιστρέφει το σύνολο με όλα τα emails των χρηστών.
     *
     * @return σύνολο {@link Set} με emails
     */

    public static Set<String> getUserEmails()
    {
        return ManageData.getData().getUserEmails();
    }

    /**
     * Επιστρέφει το σύνολο με όλα τα usernames των χρηστών.
     *
     * @return σύνολο {@link Set} με usernames
     */

    public static Set<String> getUserUsernames()
    {
        return ManageData.getData().getUserUsernames();
    }

    /**
     * Επιστρέφει το map που περιέχει τα ζευγάρια username-password.
     *
     * @return {@link Map} με username ως key και password ως value
     */

    public static Map<String,String> getUserCredentials()
    {
        return ManageData.getData().getUserCredentials();
    }

    /**
     * Προσθέτει τον χρήστη στη λίστα με όλους τους χρήστες.
     */

    public void addToList()
    {
        getUsers().add(this);
    }

}
