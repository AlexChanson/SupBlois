package supblois.alexc.ovh.supblois.dao;

/**
 * Created by ben on 09/12/17.
 */

public class RegAccount {
    public String num;
    public String firstName; // si vide, utiliser num pour l'affichage
    public String lastName;

    public String getNum() {
        return num;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
