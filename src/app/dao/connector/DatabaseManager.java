package app.dao.connector;

import java.sql.Connection;

public interface DatabaseManager {
	
	Connection getConnection();

}
