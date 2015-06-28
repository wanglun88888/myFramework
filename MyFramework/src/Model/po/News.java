package Model.po;

import java.sql.Timestamp;


/**
 * News entity. @author MyEclipse Persistence Tools
 */
public class News extends AbstractNews implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public News() {
    }

    
    /** full constructor */
    public News(String title, String content, Timestamp publishTime) {
        super(title, content, publishTime);        
    }
   
}
