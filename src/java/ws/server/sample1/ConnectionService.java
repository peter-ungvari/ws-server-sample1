/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.server.sample1;

import java.sql.Connection;

/**
 *
 * @author jupi
 */
public interface ConnectionService {
    
    Connection getConnection(String name);

}
