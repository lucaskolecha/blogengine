package com.blogengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernando
 */
@Entity
@Table(name = "comments")
@XmlRootElement(name = "comment")
public class Comment extends AbstractEntity {

    @Column(nullable = false)
    private String text;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Post post;
    
    @Override
    public void updateParameters(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
