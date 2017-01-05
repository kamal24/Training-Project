
package training_project;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Student extends JFrame implements ActionListener{
    String rollno;
    String name;
    String batch;
    JFrame f1;
    JFrame f2;
    JTextField[] tf;
    JComboBox  month;
    JComboBox  day;
    private JComboBox  year;
    private Container c;
    private JTable table;
    private JTextField filterText;
    private JTextField statusText;
    private TableRowSorter<MyTable> sorter;
    private JComboBox jc;
    
    Student(){
        f1=new JFrame("MOBILE DATA");
        //super();
        f1.setSize(400,250);
        tf=new JTextField[3];
        
        c=f1.getContentPane();
       // c.setLayout(new GridLayout(5,2));
        //c.setLayout(new FlowLayout());
        c.setLayout(null);
        
        tf[0]=new JTextField();
        tf[0].setBounds(150, 10, 200, 20);
        JLabel jb=new JLabel("Enter Mobile Number:");
        //jb.createToolTip();
        tf[0].createToolTip();
        jb.setLabelFor(tf[0]);
        jb.setBounds(10,10,200,20);
        c.add(jb);
        c.add(tf[0]);
        
        tf[1]=new JTextField();
        JLabel jb1=new JLabel("Enter Amount:");
       // tf[1].setPreferredSize(new Dimension(30,30));
        jb1.setLabelFor(tf[1]);
        tf[1].setBounds(150, 50, 200, 20);
        jb1.setBounds(10,50,200,20);
        
        c.add(jb1);
        c.add(tf[1]);
        
        //tf[2]=new JTextField();
        
        month=new JComboBox();
        for(int i=1;i<=12;i++)
            month.addItem(i);
        JPanel p=new JPanel();
        JLabel jb2=new JLabel("Enter Date:");
        p.setLayout(new GridLayout(1,5));
        p.add(month);
        
        day=new JComboBox();
        for(int i=1;i<=31;i++)
            day.addItem(i);
        p.add(day);
         
        year=new JComboBox();
        for(int i=2014;i<=2020;i++)
            year.addItem(i);
        p.add(year);
         
        jb2.setLabelFor(p);
        p.setBounds(150,90, 200, 20);
        jb2.setBounds(10,90,200,20);
        c.add(jb2);
        c.add(p);
        
         JPanel p1=new JPanel();
         p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton b=new JButton("Save");
        Dimension d=new Dimension(100,45);
        
        p1.add(b);
        
        JButton b1=new JButton("Cancel");
        
        p1.add(b1);

        JButton b2=new JButton("Display All");
        p1.add(b2);
        
        b.setPreferredSize(d);
        b1.setPreferredSize(d);
        b2.setPreferredSize(d);
        
        p1.setBounds(10,130, 400, 50);
        c.add(p1);
        
        b.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        
       // f1.pack();
        f1.setVisible(true);
        f1.setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
private void newFilter() {
        RowFilter<MyTable, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText(),jc.getSelectedIndex());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
    
    @Override
    
    
    public void actionPerformed(ActionEvent e) {
        connectivity ob=null;
        ResultSet rs=null;
        String s="";
        switch(e.getActionCommand()){
            case "Cancel":
                //setDefaultCloseOperation(EXIT_ON_CLOSE);
              //  System.out.println(month.getSelectedItem());
                System.exit(0);
                break;
            
            case "Save":
                ob=new connectivity();
                ob.connect();
                s="insert into mobile_no2 values("+tf[0].getText()+","+tf[1].getText()+",'"+day.getSelectedItem()+"-"+month.getSelectedItem()
                        +"-"+year.getSelectedItem()+"')";
              //  System.out.println(s);
                rs=ob.execute_query(s);             
        
            if(rs!=null){
                for(int i=0;i<2;i++)
                    tf[i].setText("");
                JOptionPane.showMessageDialog(rootPane,"Successfully Save");
            }
       
                break;
             
            case "Display All":
             ob=new connectivity();
             ob.connect();
             //f1.setVisible(false);
            //f1.getContentPane().removeAll();
            f2=new JFrame("MOBILE DATA");
            JPanel jp=new JPanel();
            s="select * from mobile_no2";
            rs=ob.execute_query(s);
            
            f2.setLayout(new GridLayout(2,1));
            String[] col={"MOBILE NO","AMOUNT","DATE OF RECHARGE"};
            int count=0;
            
            try {
            
            while(rs.next()){
           // data[count][0]=rs.getString(1);
             //data[count][1]=rs.getString(2);
             // data[count][2]=rs.getString(3);
              
           // System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));  
             
            count++;
            }
          //  System.out.println(count);
           String[][] data=new String[count][3];
           
           // Vector data=new Vector();
          rs.beforeFirst();
          count=0;
           while(rs.next()){
              
             data[count][0]=rs.getString(1);
             data[count][1]=rs.getString(2);
             data[count][2]=rs.getString(3);
             
         // System.out.println("kamal"+rs.getString(1)+rs.getString(2)+rs.getString(3));  
           count++;
            }
        
              
            MyTable model = new MyTable(col,data);
            sorter = new TableRowSorter<MyTable>(model);
            JTable jt=new JTable(model);
            jt.setRowSorter(sorter);
            jt.setPreferredScrollableViewportSize(new Dimension(500, 70));
            jt.setFillsViewportHeight(true);
               
            JScrollPane scpanel=new JScrollPane(jt);
            
            filterText=new JTextField();
            JLabel jl=new JLabel("Enter Filter Text:",SwingConstants.LEFT);
             JLabel jl1=new JLabel("Enter Filter Text By:",SwingConstants.LEFT);
            
            jc=new JComboBox();
            
            for(int i=0;i<=2;i++)
            jc.addItem(col[i]);
                    
            jl.setLabelFor(filterText);
            jl1.setLabelFor(jc);
            
            filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
            
            JPanel jp1=new JPanel();
            jp1.setLayout(new FlowLayout());
            filterText.setPreferredSize(new Dimension(100, 30));
            jc.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                   // filterText.setText("");
                    newFilter();
                  //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            JButton jb=new JButton("EXIT");
            jb.setPreferredSize(new Dimension(100,30));
            jp1.add(jl1);
            jp1.add(jc);
            jp1.add(jl);
            jp1.add(filterText);
            
            jb.addActionListener(this);
            jp1.add(jb);
            
            //   jp.add(jt);
           // jp.add(scpanel);
             
           // jt.setAutoCreateRowSorter(true);
            jp.setLayout(new BorderLayout());
            jp.add(jt.getTableHeader(),BorderLayout.PAGE_START);
            jp.add(jt,BorderLayout.CENTER);
            
          //  f2.add(scpanel);
            f2.add(jp);
            f2.add(jp1);
              
            f2.pack();
            f2.setSize(600,600);
            f2.setVisible(true);
            f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            f2.setDefaultCloseOperation(EXIT_ON_CLOSE);
            break;
        }
            catch (SQLException ex) {
            ex.printStackTrace();
        }
            break;
        case "EXIT":
             f1.setVisible(true);
             f2.setVisible(false);
             break;
            
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
