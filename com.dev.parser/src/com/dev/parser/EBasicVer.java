package com.dev.parser;

import java.sql.Timestamp;  

import javax.persistence.Entity;  
import javax.persistence.Id;  
import javax.persistence.Table;  
import javax.persistence.Version;  
  
/** 
 * A simple entity bean. This table does not have to exist if 
 * you use Ebean's DDL generation. 
 */  
@Entity  
@Table(name="test_junktable")  
public class EBasicVer {  
      
    @Id  
    Integer id;  
      
    String name;  
      
    String description;  
      
    
      
    public Integer getId() {  
        return id;  
    }  
  
    public void setId(Integer id) {  
        this.id = id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getDescription() {  
        return description;  
    }  
  
    public void setDescription(String description) {  
        this.description = description;  
    }  
  
   
  
}  