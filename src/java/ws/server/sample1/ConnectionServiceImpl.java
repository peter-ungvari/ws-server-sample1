/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.server.sample1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jupi
 */
public class ConnectionServiceImpl implements ConnectionService {

    private static final Logger LOGGER = Logger.getLogger(ConnectionServiceImpl.class.getName());

    @Override
    public Connection getConnection(String name) {
        DataSource ds = null;
        String sampleDbName = name;
        try {
            ds = (DataSource) InitialContext.doLookup(sampleDbName);
        } catch (NamingException e) {
            LOGGER.log(Level.WARNING, "{} resource not found.", sampleDbName);
        }
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            LOGGER.warning("Cannot get connection.");
        }
        return conn;
    }
}
