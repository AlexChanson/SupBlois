package supblois.alexc.ovh.supblois.dao;

/**
 * Created by ben on 10/12/17.
 */

public interface IDbManager {

    void open();
    void close();
    Object execRaw(String request);
    IAccountDAO getAccountDAO();
    IConnectedDAO getConnectedDAO();
    IMessageDAO getMessageDAO();

}
