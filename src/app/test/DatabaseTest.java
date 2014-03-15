package app.test;

import junit.framework.TestCase;
import app.dao.connector.DatabaseManager;
import app.db.MySQLDatabaseManager;


public class DatabaseTest extends TestCase{

	
	public void doTestConnection(){
		DatabaseManager myDatabaseManager = new MySQLDatabaseManager();
		assertNotNull(myDatabaseManager.getConnection());
	}

}
