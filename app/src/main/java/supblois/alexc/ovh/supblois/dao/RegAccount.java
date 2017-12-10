package supblois.alexc.ovh.supblois.dao;

/**
 * Created by ben on 09/12/17.
 */

public class RegAccount {
    public String num;
    public String firstName; // si vide, utiliser num pour l'affichage
    public String lastName;

    public RegAccount(String num, String firstName, String lastName) {
        this.num = num;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getNum() {
        return num;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // example of functional setter
    public static RegAccount updateNum(RegAccount reg, String num){
        return new RegAccount(num, reg.getFirstName(), reg.getLastName());
    }


}
