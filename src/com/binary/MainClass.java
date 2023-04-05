/*Application of JDBC(Java Database Connectivity)*/
package com.binary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFileChooser;

public class MainClass {

	public static void main(String[] args) {
	
		
		try {
			//load/register the driver and Establish the connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "root");
			
			//Establish the statement
			PreparedStatement stmt = connect.prepareStatement("insert into image(pic) values(?)");
			
			//making user to pick the picture through dialogue box
			JFileChooser jf = new JFileChooser();
			jf.showOpenDialog(null); //initially nothing
			File file = jf.getSelectedFile();
			FileInputStream f = new FileInputStream(file);			
			
			//set the selected user input
			stmt.setBinaryStream(1,f,f.available());
			stmt.executeUpdate();
			//Closing the connection
			connect.close();
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
