
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Database {
    public static String DBDriver="com.mysql.jdbc.Driver";
public static String DBDatabase="jdbc:mysql://localhost/laundry1.0";
public static String DBUsername="root";
public static String DBPassword="";
public static  Connection Konku; 

public static Connection getConnection(){
 try {
 Konku = DriverManager.getConnection(DBDatabase, DBUsername, DBPassword );
 System.out.print("Tersambung");
 }catch (SQLException e){
 JOptionPane.showMessageDialog(null,"Tidak Ada Koneksi=" +
 e.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
 System.err.printf("Database dan Password Salah" + e.getMessage());
 System.exit(0);
 }
return Konku;
} 
}
