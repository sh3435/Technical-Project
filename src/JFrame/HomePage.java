/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
//import java.util.Date;
import java.sql.Date;
import java.sql.PreparedStatement;


/**
 *
 * @author shrey
 */
public class HomePage extends javax.swing.JFrame {
DefaultTableModel model;
    /**
     * Creates new form HomePage
     */
    Color mouseEnterColor=new Color(0,0,0);
    Color mouseExitColor=new Color(51,51,51);
    

    public HomePage() {
        initComponents();
        showPieChart();
        setStudentDetailsToTable();
        setBookDetailsToTable();
        setDataToCards();
         
    }
    //to set the book details into the table
    public void setStudentDetailsToTable(){
        
        try {
         Connection con =DBConnection.getConnection();
            //can simply use statement because using select query 
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student_details");
            
            while(rs.next()){
                String studentId = rs.getString("student_id");
                String studentName = rs.getString("name");
                String course = rs.getString("course");
                String branch = rs.getString("branch");
                
                Object[] obj = {studentId,studentName,course,branch};
                model =(DefaultTableModel) tbl_studentdetails.getModel();
                //using model as we can add row in table and created object array above as this addRow takes object array as argument 
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void setBookDetailsToTable(){
        
        try {
            Connection con=DBConnection.getConnection();
            //can simply use statement because using select query 
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book_details");
            
            while(rs.next()){
                String bookId = rs.getString("book_id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                
                Object[] obj = {bookId,bookName,author,quantity};
                model =(DefaultTableModel) tbl_bookdetails.getModel();
                //using model as we can add row in table and created object array above as this addRow takes object array as argument 
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void setDataToCards(){


       Statement st=null;
       ResultSet rs=null;
        
       long l =System.currentTimeMillis();
       Date todaysDate = new Date(l);
        
        try {
          Connection con = DBConnection.getConnection();
           st = con.createStatement();
             rs = st.executeQuery("select * from book_details");
            
                     
            
              rs.last();
              lbl_noofbooks.setText(Integer.toString(rs.getRow()));
              
              rs = st.executeQuery("select * from student_details");
              rs.last();
              lbl_noofstudents.setText(Integer.toString(rs.getRow()));
              
              rs = st.executeQuery("select * from issue_book_details");
              rs.last();
              lbl_issuebooks.setText(Integer.toString(rs.getRow()));
              
            String sql = "select * from issue_book_details where due_date < ? and status = ?";
              PreparedStatement pst = con.prepareStatement(sql);
              pst.setDate(1, todaysDate);
              pst.setString(2, "pending");
              rs = pst.executeQuery();
              rs.last();
              lbl_defaulterlist.setText(Integer.toString(rs.getRow()));
              
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }//
    
    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
     try {
            Connection con = DBConnection.getConnection();
            String sql = "select book_name ,count(*) as issue_count from issue_book_details group by book_name";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
              barDataset.setValue(rs.getString("book_name"), (rs.getDouble("issue_count")));  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       //create chart
       JFreeChart piechart = ChartFactory.createPieChart("Issue Book Details", barDataset, true, true, false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
       piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
       piePlot.setSectionPaint("MotoG", new Color(255,102,153));
       piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelPieChart.removeAll();
        panelPieChart.add(barChartPanel, BorderLayout.CENTER);
        panelPieChart.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lbl_noofbooks = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lbl_noofstudents = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lbl_issuebooks = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        lbl_defaulterlist = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_studentdetails = new rojeru_san.complementos.RSTableMetro();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_bookdetails = new rojeru_san.complementos.RSTableMetro();
        panelPieChart = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        managestudent = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        manage = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        issuebook = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        returnbook = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        viewissue = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        viewrecord = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        defaulterlist = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 86, -1, -1));

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 30));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 5, 50));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel3.setText("Welcome, Admin");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, -1, 50));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("LIBRARY MANAGEMENT SYSTEM");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 14, 360, 40));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 25)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("X");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 20, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 70));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noofbooks.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lbl_noofbooks.setForeground(new java.awt.Color(102, 102, 12));
        lbl_noofbooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_noofbooks.setText("8");
        jPanel15.add(lbl_noofbooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 40));

        jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 130, 80));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 12));
        jLabel6.setText("Student Details");
        jPanel14.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 200, 30));

        jPanel16.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 102, 255)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noofstudents.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lbl_noofstudents.setForeground(new java.awt.Color(102, 102, 12));
        lbl_noofstudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_People_50px.png"))); // NOI18N
        lbl_noofstudents.setText("7");
        jPanel16.add(lbl_noofstudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, 40));

        jPanel14.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 130, 80));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 12));
        jLabel20.setText("No. of students");
        jPanel14.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 200, 30));

        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_issuebooks.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lbl_issuebooks.setForeground(new java.awt.Color(102, 102, 12));
        lbl_issuebooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Sell_50px.png"))); // NOI18N
        lbl_issuebooks.setText("7");
        jPanel17.add(lbl_issuebooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, 40));

        jPanel14.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 130, 80));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 12));
        jLabel22.setText("Issued Books");
        jPanel14.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 200, 30));

        jPanel18.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 102, 255)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_defaulterlist.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lbl_defaulterlist.setForeground(new java.awt.Color(102, 102, 12));
        lbl_defaulterlist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        lbl_defaulterlist.setText("1");
        jPanel18.add(lbl_defaulterlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 50));

        jPanel14.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, 130, 80));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 12));
        jLabel24.setText("Defaulter list");
        jPanel14.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 130, 30));

        tbl_studentdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Id", "Name", "Course", "Branch"
            }
        ));
        tbl_studentdetails.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        tbl_studentdetails.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        tbl_studentdetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_studentdetails.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        tbl_studentdetails.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tbl_studentdetails.setFuenteFilas(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_studentdetails.setFuenteFilasSelect(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tbl_studentdetails.setFuenteHead(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_studentdetails.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_studentdetails.setRowHeight(30);
        jScrollPane1.setViewportView(tbl_studentdetails);
        if (tbl_studentdetails.getColumnModel().getColumnCount() > 0) {
            tbl_studentdetails.getColumnModel().getColumn(1).setResizable(false);
            tbl_studentdetails.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel14.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 440, 200));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 12));
        jLabel25.setText("No. of Books");
        jPanel14.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 200, 30));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 12));
        jLabel26.setText("Book Details");
        jPanel14.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 200, 30));

        tbl_bookdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Id", "Book Name", "Author Name", "Quantity"
            }
        ));
        tbl_bookdetails.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        tbl_bookdetails.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        tbl_bookdetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_bookdetails.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        tbl_bookdetails.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tbl_bookdetails.setFuenteFilas(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_bookdetails.setFuenteFilasSelect(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tbl_bookdetails.setFuenteHead(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_bookdetails.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_bookdetails.setRowHeight(30);
        jScrollPane2.setViewportView(tbl_bookdetails);
        if (tbl_bookdetails.getColumnModel().getColumnCount() > 0) {
            tbl_bookdetails.getColumnModel().getColumn(1).setResizable(false);
            tbl_bookdetails.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel14.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 440, 200));

        panelPieChart.setLayout(new java.awt.BorderLayout());
        jPanel14.add(panelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 400, 310));

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 1070, 610));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Library_26px_1.png"))); // NOI18N
        jLabel7.setText(" LMS Dashboard");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 40));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 40));

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel8.setText("   Home page");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
        });
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, -1));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 210, 50));

        managestudent.setBackground(new java.awt.Color(51, 51, 51));
        managestudent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 153, 153));
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel27.setText(" Manage Students");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel27MouseExited(evt);
            }
        });
        managestudent.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 30));

        jPanel4.add(managestudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 210, 50));

        manage.setBackground(new java.awt.Color(51, 51, 51));
        manage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Book_26px.png"))); // NOI18N
        jLabel12.setText(" Manage Books");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });
        manage.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 30));

        jPanel4.add(manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 50));

        issuebook.setBackground(new java.awt.Color(51, 51, 51));
        issuebook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 153, 153));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Sell_26px.png"))); // NOI18N
        jLabel28.setText("  Issue Book");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel28MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel28MouseExited(evt);
            }
        });
        issuebook.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 30));

        jPanel4.add(issuebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 210, 50));

        returnbook.setBackground(new java.awt.Color(51, 51, 51));
        returnbook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 153, 153));
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Return_Purchase_26px.png"))); // NOI18N
        jLabel29.setText(" Return Book");
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel29MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel29MouseExited(evt);
            }
        });
        returnbook.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 30));

        jPanel4.add(returnbook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 210, 50));

        viewissue.setBackground(new java.awt.Color(51, 51, 51));
        viewissue.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(153, 153, 153));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Books_26px.png"))); // NOI18N
        jLabel30.setText(" View Issued Book");
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel30MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel30MouseExited(evt);
            }
        });
        viewissue.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 20));

        jPanel4.add(viewissue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 210, 40));

        viewrecord.setBackground(new java.awt.Color(51, 51, 51));
        viewrecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(153, 153, 153));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel31.setText(" View Records");
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel31MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel31MouseExited(evt);
            }
        });
        viewrecord.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 20));

        jPanel4.add(viewrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 210, 40));

        defaulterlist.setBackground(new java.awt.Color(51, 51, 51));
        defaulterlist.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(153, 153, 153));
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        jLabel32.setText(" Defaulter List");
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel32MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel32MouseExited(evt);
            }
        });
        defaulterlist.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 30));

        jPanel4.add(defaulterlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, -1, 50));

        logout.setBackground(new java.awt.Color(51, 51, 51));
        logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(153, 153, 153));
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Exit_26px.png"))); // NOI18N
        jLabel33.setText(" Log Out");
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel33MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel33MouseExited(evt);
            }
        });
        logout.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 30));

        jPanel4.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 210, 50));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, 660));

        setSize(new java.awt.Dimension(1279, 680));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(this,"Do you really want to exit?","",JOptionPane.YES_NO_OPTION);
        if(a==0){
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
              
        ManageBooks books =new ManageBooks();
        books.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        
        manage.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        // TODO add your handling code here:
               manage.setBackground(mouseExitColor); 
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
      
       
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
      
         
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseEntered
     
            managestudent.setBackground(mouseEnterColor); 
    }//GEN-LAST:event_jLabel27MouseEntered

    private void jLabel27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseExited
     
            managestudent.setBackground(mouseExitColor); 
    }//GEN-LAST:event_jLabel27MouseExited

    private void jLabel28MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseEntered
       
            issuebook.setBackground(mouseEnterColor); 
        
    }//GEN-LAST:event_jLabel28MouseEntered

    private void jLabel28MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseExited
      
            issuebook.setBackground(mouseExitColor); 
    }//GEN-LAST:event_jLabel28MouseExited

    private void jLabel29MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseEntered
        
           returnbook.setBackground(mouseEnterColor); 
    }//GEN-LAST:event_jLabel29MouseEntered

    private void jLabel29MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseExited
        
            returnbook.setBackground(mouseExitColor); 
    }//GEN-LAST:event_jLabel29MouseExited

    private void jLabel30MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseEntered
    
            viewissue.setBackground(mouseEnterColor); 
    }//GEN-LAST:event_jLabel30MouseEntered

    private void jLabel30MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseExited
      
            viewissue.setBackground(mouseExitColor); 
    }//GEN-LAST:event_jLabel30MouseExited

    private void jLabel31MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseEntered
       
            viewrecord.setBackground(mouseEnterColor); 
    }//GEN-LAST:event_jLabel31MouseEntered

    private void jLabel31MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseExited
      
            viewrecord.setBackground(mouseExitColor); 
    }//GEN-LAST:event_jLabel31MouseExited

    private void jLabel32MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseEntered
        
            defaulterlist.setBackground(mouseEnterColor); 
    }//GEN-LAST:event_jLabel32MouseEntered

    private void jLabel32MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseExited
       
            defaulterlist.setBackground(mouseExitColor); 
    }//GEN-LAST:event_jLabel32MouseExited

    private void jLabel33MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseEntered
     
            logout.setBackground(mouseEnterColor); 
    }//GEN-LAST:event_jLabel33MouseEntered

    private void jLabel33MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseExited
   
            logout.setBackground(mouseExitColor); 
    }//GEN-LAST:event_jLabel33MouseExited

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
      ManageStudents students=new ManageStudents();
      students.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
        IssueBook book =new IssueBook();
        book.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        ViewAllRecord record =new ViewAllRecord();
        record.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        IssueBookDetails books =new IssueBookDetails();
        books.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        DefaulterList list=new DefaulterList();
        list.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel32MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked

       
        int a = JOptionPane.showConfirmDialog(this, "Do you really want to logout?", "Select", JOptionPane.YES_NO_OPTION);
        if(a==0){
            setVisible(false);
            new LoginPage().setVisible(true);} 
       
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        ReturnBook book =new ReturnBook();
        book.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel29MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel defaulterlist;
    private javax.swing.JPanel issuebook;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_defaulterlist;
    private javax.swing.JLabel lbl_issuebooks;
    private javax.swing.JLabel lbl_noofbooks;
    private javax.swing.JLabel lbl_noofstudents;
    private javax.swing.JPanel logout;
    private javax.swing.JPanel manage;
    private javax.swing.JPanel managestudent;
    private javax.swing.JPanel panelPieChart;
    private javax.swing.JPanel returnbook;
    private rojeru_san.complementos.RSTableMetro tbl_bookdetails;
    private rojeru_san.complementos.RSTableMetro tbl_studentdetails;
    private javax.swing.JPanel viewissue;
    private javax.swing.JPanel viewrecord;
    // End of variables declaration//GEN-END:variables
}
