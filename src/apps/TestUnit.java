package apps;

import utilities.DBServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import java.io.File;

public class TestUnit {
    public static void main(String[] args) {
        String driver = "JDBC:mysql://localhost:3306/library";
        try (Connection connection = DBServices.makeConnection(driver, "root", "root3306")) {
            System.out.println("OK");
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
