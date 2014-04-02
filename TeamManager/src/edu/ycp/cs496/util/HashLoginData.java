/**
 * 
 */
package edu.ycp.cs496.util;


/**
 * @author dan
 *
 */
public class HashLoginData {
	/**
	 * 
	 * @param data
	 * @return the hash of the data inputed
	 */
	public String hashData(String data){
		return BCrypt.hashpw(data, BCrypt.gensalt());	
	}
}
