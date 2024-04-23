// import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class Add extends Frame
{

    static Add a;

    Label l0=new Label("Dev-Tech");
    Label l1 = new Label("Add Book");
    Label l2 = new Label("Enter Book ID");
    TextField tf1 = new TextField();
    Label l3 = new Label("Enter Book Name");
    TextField tf2 = new TextField();
    Label l4 = new Label("Enter Author Name");
    TextField tf3 = new TextField();
    Label l5 = new Label("Enter Price");
    TextField tf4 = new TextField();
    Button b1 = new Button("Add Book");
    Button b2 = new Button("Home");
    Button b3 = new Button("Exit");
    

    Add()
    {
        setTitle("Add Book");
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


        l2.setBounds(160,140,200,30);
        add(l2);
        l2.setForeground(Color.BLACK);
        // l2.setBackground(Color.RED);
        l2.setFont(new Font("Serif", Font.BOLD, 25));


        tf1.setBounds(450,140,200,30);
        add(tf1);
        tf1.setForeground(Color.GRAY);
        // tf1.setBackground(Color.RED);
        tf1.setFont(new Font("Serif", Font.BOLD, 20));


        l3.setBounds(160,200,200,30);
        add(l3);
        l3.setForeground(Color.BLACK);
        // l3.setBackground(Color.RED);
        l3.setFont(new Font("Serif", Font.BOLD, 25));


        tf2.setBounds(450,200,200,30);
        add(tf2);
        tf2.setForeground(Color.GRAY);
        // tf2.setBackground(Color.RED);
        tf2.setFont(new Font("Serif", Font.BOLD, 20));


        l4.setBounds(160,260,220,30);
        add(l4);
        l4.setForeground(Color.BLACK);
        // l4.setBackground(Color.RED);
        l4.setFont(new Font("Serif", Font.BOLD, 25));


        tf3.setBounds(450,260,200,30);
        add(tf3);
        tf3.setForeground(Color.GRAY);
        // tf3.setBackground(Color.RED);
        tf3.setFont(new Font("Serif", Font.BOLD, 20));


        l5.setBounds(160,320,200,30);
        add(l5);
        l5.setForeground(Color.BLACK);
        // l5.setBackground(Color.RED);
        l5.setFont(new Font("Serif", Font.BOLD, 25));


        tf4.setBounds(450,320,200,30);
        add(tf4);
        tf4.setForeground(Color.GRAY);
        // tf4.setBackground(Color.RED);
        tf4.setFont(new Font("Serif", Font.BOLD, 20));


        b1.setBounds(300,430,180,50);
        add(b1);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.PINK);
        b1.setFont(new Font("Serif", Font.BOLD, 30));


        b2.setBounds(80,520,100,35);
        add(b2);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.GREEN);
        b2.setFont(new Font("Serif", Font.BOLD, 30));


        b3.setBounds(620,520,100,35);
        add(b3);
        b3.setForeground(Color.WHITE);
        b3.setBackground(Color.RED);
        b3.setFont(new Font("Serif", Font.BOLD, 30));


        
        
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(tf1.getText().equals("") && tf2.getText().equals("") && tf3.getText().equals("") && tf4.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(a, "Please Enter Book Details First !");
                }
                else if(tf1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(a, "Please Enter Book ID !");
                }
                else if(tf2.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(a, "Please Enter Book Name !");
                }
                else if(tf3.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(a, "Please Enter Author Name !");
                }
                else if(tf4.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(a, "Please Enter Book Price !");
                }
                else
                {
                    try {
                        addBook();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(a,e);
                    } 
                    tf1.setText("");
                    tf2.setText("");
                    tf3.setText("");
                    tf4.setText("");
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
    

    public  void addBook() throws Exception
    {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "@Root2003");
        String BId = tf1.getText();
        // System.out.print("\nBook Name : ");
        String BName = tf2.getText();
        // System.out.print("\nAuthor Name : ");
        String AName = tf3.getText();
        // System.out.print("\nBook price : ");
        String Price = tf4.getText();
        double BPrice = Double.parseDouble(Price);


        PreparedStatement psmt = conn.prepareStatement("insert into books (BId,BName,AName,BPrice,Available) values (?,?,?,?,?)");
        psmt.setString(1, BId);
        psmt.setString(2, BName);
        psmt.setString(3, AName);
        psmt.setDouble(4, BPrice);
        psmt.setString(5, "Yes");

        psmt.executeUpdate();

        // Statement stmt = conn.createStatement();
        // String query =  "insert into Books (BId, BName, AName, BPrice) values ("+BId+","+BName+","+AName+","+BPrice+")";
        // stmt.executeUpdate(query);


        conn.close();
        JOptionPane.showMessageDialog(a, "Book Added Succesfully!.......");
    }

    public static void main(String arg[])
    {
        a = new Add();
    }
    
}
