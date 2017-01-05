package training_project;
public class users {
   String name;
   String passwd;
   
   users(){
   name="";    
   passwd="";
   }
   
   void set_password(String pwd){
       passwd=pwd;
   }
   
   void set_username(String usrname){
       name=usrname;
   }
   
   String get_password(){
       return passwd;
   }
   
   String get_name(){
       return name;
   }
}
