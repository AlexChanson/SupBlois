package supblois.alexc.ovh.supblois.dao;

/**
 * Created by ben on 10/12/17.
 */

public class ConnectedAccount {

    private final String num;
    private final String passwd;

    public ConnectedAccount(String num, String passwd) {
        this.num = num;
        this.passwd = passwd;
    }

    public String getNum() {
        return num;
    }

    public String getPasswd() {
        return passwd;
    }
}
