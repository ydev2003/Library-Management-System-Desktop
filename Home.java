import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
public class Home  extends Frame
{   
    static Home h;
    
    Label l0=new Label("Dev-Tech");
    Label l1 = new Label("Library Management System");
    Label l2 = new Label("----------------------------------------");
    Label l3 = new Label("Search Book");
    Label l4 = new Label("**********************************");
    JButton b1 = new JButton();
    Button b2 = new Button("Add Book");
    Button b3 = new Button("Remove Book");
    Button b4 = new Button("Issue Book");
    Button b5 = new Button("Submit Book");
    Button b6 = new Button("Exit");
    TextField tf1 = new TextField();
    
    Home() 
    {
        setTitle("Library Management System");
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(1000,800);
        setBackground(Color.ORANGE);
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


        l1.setBounds(200,50,600,60);
        add(l1);
        l1.setForeground(Color.RED);
        // l1.setBackground(Color.RED);
        l1.setFont(new Font("Serif", Font.BOLD, 46));


        l2.setBounds(180,80,640,60);
        add(l2);
        l2.setForeground(Color.GREEN);
        // l2.setBackground(Color.WHITE);
        l2.setFont(new Font("Serif", Font.BOLD, 46));


        l3.setBounds(200,190,160,40);
        add(l3);
        l3.setForeground(Color.BLACK);
        // l3.setBackground(Color.WHITE);
        l3.setFont(new Font("Serif", Font.BOLD, 30));


        tf1.setBounds(380,190,340,40);
        add(tf1);
        tf1.setForeground(Color.GRAY);
        tf1.setBackground(Color.WHITE);
        tf1.setFont(new Font("Serif", Font.BOLD, 30));


        b1.setBounds(720,190,40,40);
        add(b1);
        // b1.setForeground(Color.BLUE);
        b1.setBackground(Color.WHITE);
        b1.setFont(new Font("Serif", Font.BOLD, 15));
        ImageIcon icon1 = new ImageIcon ("F:\\JAVA\\Codes\\LMS\\Search.jpeg");
        b1.setIcon(icon1);


        b2.setBounds(150,330,200,50);
        add(b2);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.MAGENTA);
        b2.setFont(new Font("Serif",Font.BOLD,30));


        b3.setBounds(650,330,200,50);
        add(b3);
        b3.setForeground(Color.WHITE);
        b3.setBackground(Color.MAGENTA);
        b3.setFont(new Font("Serif",Font.BOLD,30));
        // ImageIcon icon2  = new ImageIcon("F:\\JAVA\\Codes\\LMS\\Remove.jpeg");
        // b3.setIcon(icon2);

        b4.setBounds(150,480,200,50);
        add(b4);
        b4.setForeground(Color.WHITE);
        b4.setBackground(Color.MAGENTA);
        b4.setFont(new Font("Serif",Font.BOLD,30));


        b5.setBounds(650,480,200,50);
        add(b5);
        b5.setForeground(Color.WHITE);
        b5.setBackground(Color.MAGENTA);
        b5.setFont(new Font("Serif",Font.BOLD,30));

        b6.setBounds(400,620,200,60);
        add(b6);
        b6.setForeground(Color.WHITE);
        b6.setBackground(Color.RED);
        b6.setFont(new Font("Serif", Font.BOLD, 40));


        l4.setBounds(300,700,400,60);
        add(l4);
        l4.setForeground(Color.GRAY);
        // l4.setBackground(Color.RED);
        l4.setFont(new Font("Serif", Font.BOLD, 40));




        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String searchTerm = tf1.getText().trim();
        
                if (searchTerm.isEmpty()) {
                    JOptionPane.showMessageDialog(h, "Please first enter the book name, author name or book iD !");
                } else {
                    try
                    {
                    searchBookAndDisplayDialog(searchTerm);                        
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(h, e);
                    }
                }
            }
        });
        

        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Add().setVisible(true);
                dispose();
            }
        });


        b3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Remove().setVisible(true);
                dispose();
            }
        });


        b4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Issue().setVisible(true);
                dispose();
            }
        });


        b5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Submit().setVisible(true);
                dispose();
            }
        });

        b6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                // new Add().setVisible(true);
                dispose();
            }
        });  

    }    

    private void searchBookAndDisplayDialog(String searchTerm) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "@Root2003");
        PreparedStatement psmt = conn.prepareStatement("SELECT * FROM books WHERE BId LIKE ? OR BName LIKE ? or AName LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        psmt.setString(1, "%" + searchTerm + "%");
        psmt.setString(2, "%" + searchTerm + "%");
        psmt.setString(3, "%" + searchTerm + "%");
        ResultSet rs = psmt.executeQuery();
    
        // Count the number of rows in the ResultSet
        int rowCount = 0;
        if (rs.last()) {
            rowCount = rs.getRow();
            rs.beforeFirst(); // Move the cursor back to the beginning of the ResultSet
        }
    
        if (rowCount > 0) {
            JFrame f = new JFrame("Book Details");
            f.setLocationRelativeTo(h);
            f.setBackground(Color.CYAN);
            f.setSize(950, 400);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationRelativeTo(h);
            String[] col = {"Book ID", "Book Name", "Author Name", "Book Price", "Available"};
            Object[][] data = new Object[rowCount][5];
            int i = 0;
            while (rs.next())
            {
                data[i][0] = rs.getString("BId");
                data[i][1] = rs.getString("BName");
                data[i][2] = rs.getString("AName");
                data[i][3] = rs.getDouble("BPrice");
                data[i][4] = rs.getString("Available");
                i++;
            }
            JLabel lb = new JLabel("***Book Details***");
            JTable tb = new JTable(data, col);
            int[] columnWidths = {40, 250, 150, 50, 25}; // Specify the width for each column
            for (int j = 0; j < columnWidths.length; j++)
            {
                TableColumn column = tb.getColumnModel().getColumn(j);
                column.setPreferredWidth(columnWidths[j]);
            }
            JTableHeader header = tb.getTableHeader();
            header.setPreferredSize(new Dimension(header.getPreferredSize().width, 60));
            header.setBounds(50,100,500,200);
            header.setBounds(150,330,200,50);
            // header add(b2);
            header.setForeground(Color.black);
            header.setBackground(Color.blue);
            header.setFont(new Font("Serif",Font.BOLD,23));
            
            tb.setFont(new Font("Serif",Font.ITALIC,20));
            tb.setRowHeight(40);
            tb.setBounds(50,100,500,200);
            tb.setBounds(150,330,200,50);
            // add(b2);
            tb.setForeground(Color.darkGray);
            tb.setBackground(Color.cyan);
            tb.setFont(new Font("Serif",Font.BOLD,20));

            JScrollPane jsp = new JScrollPane(tb);
            f.add(jsp);
            jsp.setBounds(50, 100, 500, 200);
            jsp.setBackground(Color.CYAN);
            f.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(h, "Book not found", "Book Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public static void main(String args[])
    { 
        h = new Home();   
    } 

}   