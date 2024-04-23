import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Remove extends Frame
{
    static Remove r;

    Label l0=new Label("Dev-Tech");
    Label l1 = new Label("Remove Book");
    Label l2 = new Label("Enter Book ID");
    TextField tf1 = new TextField();
    Button b1 = new Button("Remove Book");
    Button b2 = new Button("Home");
    Button b3 = new Button("Exit");

    Remove()
    {
        setTitle("Remove Book");
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


        l1.setBounds(270,50,300,60);
        add(l1);
        l1.setForeground(Color.ORANGE);
        // l1.setBackground(Color.RED);
        l1.setFont(new Font("Serif", Font.BOLD, 46));


        l2.setBounds(160,230,180,30);
        add(l2);
        l2.setForeground(Color.BLACK);
        // l2.setBackground(Color.RED);
        l2.setFont(new Font("Serif", Font.BOLD, 25));


        tf1.setBounds(450,230,180,30);
        add(tf1);
        tf1.setForeground(Color.GRAY);
        // tf1.setBackground(Color.RED);
        tf1.setFont(new Font("Serif", Font.BOLD, 20));


        b1.setBounds(295,380,210,50);
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
                if(tf1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(r, "Plese enter Book ID first...");
                }
                else
                {
                    try {
                        removeBook();
                    } catch (Exception e) {
                        JOptionPane.showConfirmDialog(r, e);
                    }
                }
                
                
                // new Add().setVisible(true);
                // dispose();
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

    public void removeBook() throws Exception
    {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "@Root2003");
        PreparedStatement psmt1 = conn.prepareStatement("select * from books where BId = (?)");
        psmt1.setString(1, tf1.getText());
        ResultSet rs = psmt1.executeQuery();
        if(rs.next()== true)
        {
            if(rs.getString("Available").equals("Yes"))
            {
                int response =  JOptionPane.showConfirmDialog(r, "Book ID: "+rs.getString("BID")+"\nBook Name: "+rs.getString("BName")+"\nAuther Name: "+rs.getString("AName"), "Are you sure want to remove this book...?",JOptionPane.YES_NO_OPTION);
                if(response == JOptionPane.YES_OPTION)
                {
                    PreparedStatement psmt2 = conn.prepareStatement("delete from Books where BId = (?)");
                    psmt2.setString(1,tf1.getText());
                    psmt2.executeUpdate();
                    JOptionPane.showMessageDialog(r, "Book removed succesfully.....");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(r,"You can't remove this book, This book is issued to a student...\nPlease ask student to submit it first....!'");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(r,"There is no Book available with this Book ID.....\nPlease enter correct Book ID....");
        }
        conn.close();
        // Clear the text field
        tf1.setText("");
    }
        
    public static void main(String arg[])
    {
        r = new Remove();
    }
}
