import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class A02App {
	
	private static final String dbUrl = "jdbc:mysql://srv572.hstgr.io/u912419555_3421a02";
    private static final String dbUser = "u912419555_3421user";
    private static final String dbPassword = "A02@eecs3421";

    private static final String database = "u912419555_3421a02";
    private static final String table = "3421A02Data";
    static JLabel label2;
	static int v;
	public static void A02App(){
	
        JFrame frame = new JFrame("A02App");
        
        // Add a title in the Page Start section
        JLabel title = new JLabel("A02App", SwingConstants.CENTER);
        Font heading = new Font("Times Roman", Font.BOLD, 20);
        title.setFont(heading);
        title.setPreferredSize(new Dimension(300, 50));
        title.setOpaque(true);
        title.setBackground(Color.decode("#f0e9e9"));
        frame.getContentPane().add(title, BorderLayout.PAGE_START);
        
        JPanel pane = new JPanel( new FlowLayout());
        pane.setPreferredSize(new Dimension(300, 200));

        frame.getContentPane().add(pane,  BorderLayout.CENTER);
        
        JLabel label1 = new JLabel("Enter your student ID: ");
        pane.add(label1);
        
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension (100, 30));
        pane.add(textField);
        // Add button to our pan
        JButton start = new JButton("SUBMIT");
        start.setPreferredSize(new Dimension(100,50));
        // Add ActionListener to the button
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmitButtonClick(textField.getText());
            }
        });
        
        
        // Adjust the size and color of the start button
        start.setBackground(Color.white);

        pane.add(start);
        
        label2 = new JLabel("Your earlier number of visits are : X");

        pane.add(label2);
        
        // Show the Frame
        frame.pack();
        frame.setVisible(true);
	
	
	}
	
	   private static void handleSubmitButtonClick(String studentId) {
	        // Handle the button click, e.g., connect to the database and perform the logic
	        // You can use the provided database connection code and logic here
			try {
				Connection con = DriverManager.getConnection(
					dbUrl, dbUser, dbPassword);
				
	            PreparedStatement checkStatement = con.prepareStatement("SELECT * FROM 3421A02Data WHERE StudentID = ?");
	            checkStatement.setString(1, studentId);
	            ResultSet resultSet = checkStatement.executeQuery();
	            if (resultSet.next()) {
	                // Student ID exists, update VisitCount and display current value
	                int currentVisitCount = resultSet.getInt("VisitCount");
	                label2.setText("Your earlier number of visits are: " + currentVisitCount);

	                // Update VisitCount in the table
	                PreparedStatement updateStatement = con.prepareStatement("UPDATE 3421A02Data SET VisitCount = ? WHERE StudentID = ?");
	                updateStatement.setInt(1, currentVisitCount + 1);
	                updateStatement.setString(2, studentId);
	                updateStatement.executeUpdate();
	            } else {
	                // Student ID doesn't exist, insert a new row
	                label2.setText("Your earlier number of visits are: 0");

	                PreparedStatement insertStatement = con.prepareStatement("INSERT INTO 3421A02Data (StudentID, VisitCount) VALUES (?, 1)");
	                insertStatement.setString(1, studentId);
	                insertStatement.executeUpdate();
	            }

	            // Close the connections
	            resultSet.close();
	            con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            label2.setText("Error connecting to the database");
	        }
	    }
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                A02App();
            }
        });
    }
}
