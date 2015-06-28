package Model.po;

import java.sql.Timestamp;


/**
 * AbstractNews entity provides the base persistence definition of the News entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNews  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String title;
     private String content;
     private Timestamp publishTime;


    // Constructors

    /** default constructor */
    public AbstractNews() {
    }

    
    /** full constructor */
    public AbstractNews(String title, String content, Timestamp publishTime) {
        this.title = title;
        this.content = content;
        this.publishTime = publishTime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPublishTime() {
        return this.publishTime;
    }
    
    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }
   








}