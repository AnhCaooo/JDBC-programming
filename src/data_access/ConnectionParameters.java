package data_access;

public class ConnectionParameters {

	// Set the username and password in SQLite
	public static final String username = "";
	public static final String password = "";

	// JDBC driver
	public static final String jdbcDriver = "org.sqlite.JDBC";
	public static final String databaseLocation = "databases/";
	public static final String databaseName = "StudentDatabase.sqlite";
	public static final String connectionString = "jdbc:sqlite:" + databaseLocation + databaseName;

	// PK violation: The error code in SQLite is 19
	public static final int PK_VIOLATION_ERROR = 19;

}
