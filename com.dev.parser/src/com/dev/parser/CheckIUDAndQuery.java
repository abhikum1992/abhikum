package com.dev.parser;



import com.avaje.ebean.Ebean;  
import com.avaje.ebean.config.GlobalProperties;  
import com.dev.bean.JobRequirementBean;
  
  
public class CheckIUDAndQuery {  
  
    public static void main(String[] args) {  
    	JobRequirementBean jrb = Ebean.find(JobRequirementBean.class,1);
    	System.out.println(jrb.getEmployer());
    	System.out.println(jrb.getJobTitle());
          
        //System.setProperty("catalina.base", "D:/apps/tomcat6");  
        //System.setProperty("ebean.props.file", "D:/apps/tomcat6/conf/zsite.ebean.properties");  
        //GlobalProperties.put("ebean.debug.sql", "true");  
          
    	/*EBasicVer e = new EBasicVer();  
        e.setName("test");  
        e.setDescription("something");  
          
        // will insert  
        Ebean.save(e);  
          
        e.setDescription("changed");  
          
        // this will update  
        Ebean.update(e);  
          */
        // find the inserted entity by its id  
        /*ESimple e2 = Ebean.find(ESimple.class, e.getId());  
        System.out.println("Got "+e2.getDescription());  
          
        Ebean.delete(e);  
*/        // can use delete by id when you don't have the bean  
        //Ebean.delete(ESimple.class, e.getId());  
          
    }  
}  