package org.cz.h2;

import java.sql.*;

/**
 * Created by chenzhe8 on 2017/5/27.
 */
public class H2Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
                getConnection("jdbc:h2:.\\data\\czdata", "sa", "");
        // add application code here
        Statement stmt = conn.createStatement();

//        stmt.executeUpdate("CREATE TABLE TEST(ID INT PRIMARY KEY,\n" +
//                "   NAME VARCHAR(255));");
//        stmt.executeUpdate("INSERT INTO TEST VALUES(1, 'Hellottt');");

        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST ");
        while(rs.next()) {
            System.out.println(rs.getInt("ID")+","+rs.getString("NAME"));
        }

        conn.close();
    }

    public static void createTable(){

    }
}
