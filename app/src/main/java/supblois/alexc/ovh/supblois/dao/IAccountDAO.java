package supblois.alexc.ovh.supblois.dao;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by ben on 09/12/17.
 */

public interface IAccountDAO {

    void addAccount(String num);
    void addAccount(String num, String firstname);
    void addAccount(String num, String firstname, String lastname);
    RegAccount getByNumber(String number);
    ArrayList<RegAccount> getByFirstName(String firstName);
    ArrayList<RegAccount> getByLastName(String lastName);
    ArrayList<RegAccount> getAll();
    void setFirstName(String number, String newFirstName);
    void setLastName(String number, String newLastName);
    void setFirstAndLastName(String number, String newFirstName, String newLastName);
    boolean deleteByNumber(String number);


}
