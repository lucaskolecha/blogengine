package com.blogengine.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernando
 */
@Entity
@Table(name = "profiles")
@NamedQueries({
    @NamedQuery(name = "Profile.findByName", query = "select u from Profile u where u.name = :name"),
    @NamedQuery(name = "Profile.findByEmail", query = "select u from Profile u where u.email = :email")
})
@XmlRootElement(name = "profile")
public class Profile extends AbstractEntity {

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birth;

    public Profile() {
    }

    public Profile(String name, String email, Date birth) {
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public void updateParameters(Object entity) {
        final Profile other = (Profile) entity;
        this.name = other.name;
        this.email = other.email;
        this.birth = other.birth;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.birth);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profile other = (Profile) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.birth, other.birth)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Profile{" + "name=" + name + ", email=" + email + ", birth=" + birth + '}';
    }
}
