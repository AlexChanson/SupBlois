package supblois.alexc.ovh.supblois.dao;

import java.util.Optional;

/**
 * Created by ben on 10/12/17.
 */

public interface IConnectedDAO {

    String getPasswd(String num);
    void updatePswd(String num, String passwd);
}
