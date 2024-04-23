// import java.util.*;
import java.awt.*;
import java.awt.event.*;
// import javax.swing.*;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.Scanner;
// import java.io.File;
// import java.io.FileNotFoundException;
// import java.util.ArrayList;
// import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class Issue extends Frame
{
    static Issue i;

    Label l0=new Label("Dev-Tech");
    Label l1 = new Label("Issue Book");
    Label l2 = new Label("Enter Your ID");
    TextField tf1 = new TextField();
    Label l3 = new Label("Enter Book ID");
    TextField tf2 = new TextField();
    Button b1 = new Button("Issue Book");
    Button b2 = new Button("Home");
    Button b3 = new Button("Exit");

    Issue()
    {
        setTitle("Issue Book");
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(800,600);
        setBackground(Color.CYAN);
        setLayout(null);


        //for put the frame in center
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int centerX = (screenWidth - frameWidth) / 2;
        int centerY = (screenHeight - frameHeight) / 2;
        setLocation(centerX, centerY);


        l0.setBounds(7,38,45,20);
        add(l0);
        l0.setBackground(Color.BLUE);
        l0.setFont(new Font("Serif", Font.BOLD, 10));


        l1.setBounds(300,50,300,60);
        add(l1);
        l1.setForeground(Color.ORANGE);
        // l1.setBackground(Color.RED);
        l1.setFont(new Font("Serif", Font.BOLD, 46));


        l2.setBounds(160,180,200,30);
        add(l2);
        l2.setForeground(Color.BLACK);
        // l2.setBackground(Color.RED);
        l2.setFont(new Font("Serif", Font.BOLD, 25));


        tf1.setBounds(450,180,200,30);
        add(tf1);
        tf1.setForeground(Color.GRAY);
        // tf1.setBackground(Color.RED);
        tf1.setFont(new Font("Serif", Font.BOLD, 20));


        l3.setBounds(160,280,200,30);
        add(l3);
        l3.setForeground(Color.BLACK);
        // l3.setBackground(Color.RED);
        l3.setFont(new Font("Serif", Font.BOLD, 25));


        tf2.setBounds(450,280,200,30);
        add(tf2);
        tf2.setForeground(Color.GRAY);
        // tf2.setBackground(Color.RED);
        tf2.setFont(new Font("Serif", Font.BOLD, 20));


        b1.setBounds(310,400,180,50);
        add(b1);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.PINK);
        b1.setFont(new Font("Serif", Font.BOLD, 30));

        
        b2.setBounds(80,500,100,35);
        add(b2);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.GREEN);
        b2.setFont(new Font("Serif", Font.BOLD, 30));


        b3.setBounds(620,500,100,35);
        add(b3);
        b3.setForeground(Color.WHITE);
        b3.setBackground(Color.RED);
        b3.setFont(new Font("Serif", Font.BOLD, 30));


        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(tf1.getText().equals("") && tf2.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(i, "Please enter details first !");
                }
                else if(tf1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(i, "Please enter your ID !");
                }
                else if(tf2.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(i, "Please enter book ID !");
                }
                else
                {
                    try {
                        issueBook();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(i,e);
                    } 
                    tf1.setText("");
                    tf2.setText("");
                }
            }
        });

        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Home().setVisible(true);
                dispose();
            }
        });

        b3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                // new Add().setVisible(true);
                dispose();
            }
        });
        
    }


    public void issueBook() throws Exception
    {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "@Root2003");
        PreparedStatement psmt1 = conn.prepareStatement("select * from student where SId = (?)");
        psmt1.setString(1, tf1.getText());
        ResultSet rs1 = psmt1.executeQuery();
        if(rs1.next()== true)
        {
            if(rs1.getInt("BTaken")<3)
            {
                PreparedStatement psmt2 = conn.prepareStatement("select * from books where BId = (?)");
                psmt2.setString(1, tf2.getText());
                ResultSet rs2 = psmt2.executeQuery();
                if(rs2.next() == true)
                {
                    if(rs2.getString("Available").equals("Yes"))
                    {
                        int response1 =  JOptionPane.showConfirmDialog(i, "ID: "+rs1.getString("SID")+"\nName: "+rs1.getString("SName")+"\nFather Name: "+rs1.getString("FName")+"\nAge: "+rs1.getString("SAge")+"\nMobile No.: "+rs1.getString("SMobile"), "Please verify it is you?",JOptionPane.YES_NO_OPTION);
                        if(response1 == JOptionPane.YES_OPTION)
                        {
                            int response2 =  JOptionPane.showConfirmDialog(i, "Book ID: "+rs2.getString("BID")+"\nBook Name: "+rs2.getString("BName")+"\nAuther Name: "+rs2.getString("AName"), "You want to issue this book?",JOptionPane.YES_NO_OPTION);
                            if(response2 == JOptionPane.YES_OPTION)
                            {
                                PreparedStatement psmt3 = conn.prepareStatement("insert into bookissued (BId,SId) values (?,?)");
                                psmt3.setString(2, tf1.getText());
                                psmt3.setString(1, tf2.getText());
                                psmt3.executeUpdate();

                                PreparedStatement psmt4 = conn.prepareStatement("update student set BTaken  = BTaken + 1 where SId = (?);");
                                psmt4.setString(1, tf1.getText());
                                psmt4.executeUpdate();

                                PreparedStatement psmt5 = conn.prepareStatement("update books set Available  = 'No' where BId = (?);");
                                psmt5.setString(1, tf2.getText());
                                psmt5.executeUpdate();

                                JOptionPane.showMessageDialog(i,"Book issued to you succesfully...!\nThank you for using our service....!!!");

                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(i,"This Book already have taken by another student....");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(i, "There is no book available with this book ID.....\nPlease enter correct Book ID.....");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(i,"You have already taken 3 Books !\nWe don't issue more than 3 books...\n Sorry!!!'");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(i,"You are not a registered student.....\nPlease register yourself first...");
            new Register().setVisible(true);
            dispose();
        }
        conn.close();
        // Clear the text field
        tf1.setText("");
    }
    

    public static void main(String arg[])
    {
        i = new Issue();
    }
    
}
