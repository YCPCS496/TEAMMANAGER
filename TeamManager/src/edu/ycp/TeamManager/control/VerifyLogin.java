/**
 * 
 */
package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

/**
 * @author dan
 *
 */
public class VerifyLogin {
	public String verifyLogin(LoginData data){
		IDatabase db = Database.getInstance();
		return db.Login(data);
	}
}
