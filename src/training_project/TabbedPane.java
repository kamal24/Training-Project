package training_project;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPane extends JPanel{
      TabbedPane(){
          createGui();
      }
    
      public void createGui(){
          JTabbedPane Jtb=new JTabbedPane();
          
          JPanel jp=new JPanel();
          
          JPanel jp1=new JPanel();
          
          Jtb.addTab("User", jp);
          Jtb.addTab("Admin",  jp1);
      }
}
