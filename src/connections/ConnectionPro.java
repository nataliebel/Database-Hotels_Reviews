package connections;

import java.sql.*;

public class ConnectionPro {
    private static Connection conData;
    
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conData=DriverManager.getConnection("jdbc:mysql://localhost:3306/project_hotel_reviews?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root" , "123456");
           
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("cant connect");
        }
        return conData;
    }
}
