package supblois.alexc.ovh.supblois.dao;

import java.sql.SQLException;

/**
 * Created by ben on 10/12/17.
 */

public interface IDbManager {

    void open() throws SQLException;
    void close();
    IAccountDAO getAccountDAO();
    IConnectedDAO getConnectedDAO();
    IMessageDAO getMessageDAO();

}
