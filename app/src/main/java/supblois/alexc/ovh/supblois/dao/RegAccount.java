package supblois.alexc.ovh.supblois.dao;

/**
 * Created by ben on 09/12/17.
 */

public class RegAccount {
    public String num;
    public String firstName; // si vide, utiliser num pour l'affichage
    public String lastName;
    public int unreadMsg;

    public RegAccount(String num, String firstName, String lastName, int unread) {
        this.num = num;
        this.firstName = firstName;
        this.lastName = lastName;
        this.unreadMsg = unread;
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

    public int getUnreadMsg(){
        return unreadMsg;
    }

    // example of functional setter
    public static RegAccount updateNum(RegAccount reg, String num){
        return new RegAccount(num, reg.getFirstName(), reg.getLastName(), reg.getUnreadMsg());
    }


}
