/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.followsportscompetition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author huseyinkaradana
 */
public class Db {
     Connection conn = null;

    public static Connection java_db() {
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/followsports?useTimezone=true&serverTimezone=UTC", "root", "bc748596");
            return conn;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
}
