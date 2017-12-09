package supblois.alexc.ovh.supblois.dao;

import java.util.ArrayList;

/**
 * Created by ben on 09/12/17.
 */

public interface IAccountDAO {

    public abstract RegAccount getByNumber(String number);
    public abstract ArrayList<RegAccount> getByFirstName(String firstName);
    public abstract ArrayList<RegAccount> getByLastName(String lastName);
    public abstract ArrayList<RegAccount> getAll();
    public abstract void setFirstName(String number, String newFirstName);
    public abstract void setLastName(String number, String newLastName);
    public void setFirstAndLastName(String number, String newFirstName, String newLastName);


}
