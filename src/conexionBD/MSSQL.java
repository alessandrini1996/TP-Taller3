package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class MSSQL {

	public static void main(String[] args) {
		Connection connect = getMSSQLConnection();
	    Statement stmt = null;
		try {
			stmt = (Statement) connect.createStatement();
		} catch (SQLException e) {
			System.out.println("Problema de conexión o permiso.");
		}
	    
	    String SQL = "SELECT * FROM dbo.LIBRO ORDER BY NRO_LIBRO";
	    ResultSet rs = null;
	    try{
	    	rs =  stmt.executeQuery(SQL);
	    }catch( SQLServerException e){
	    	System.out.println("Problema al ejecutar la Consulta: " + SQL);
	    }catch(SQLException e){
	    	System.out.println("Problemas de conexión o consulta.");
	    }
	    if( rs != null ){
		    try {
				while (rs.next()) {
				    System.out.println(rs.getString("NRO_LIBRO"));
				}
				rs.close();
			} catch (SQLException e) {
				System.out.println("Error de registros o de campos.");
			}
	    }
	    try {
			connect.close();
		} catch (SQLException e) {
			System.out.println("No pude cerrar la conexión porque ya estaba cerrada.");
		}
	}
	
	private static Connection getMSSQLConnection() {
		Connection con = null;
        try {
        	String url = "jdbc:sqlserver://localhost:1433;dataBaseName=TP1BD;integratedSecurity=true";
        	String user = "rochi-PC\\rochi";
        	String password= "";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println("Conexion exitosa!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al rastrear getConnection() : " + e.getMessage());
        }
        return con;
    }
}
