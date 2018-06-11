package com.blogengine.model;

import com.blogengine.util.Criptography;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernando
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.findByUsername", query = "select u from User u where u.username = :username"),
    @NamedQuery(name = "User.findByCredentials", query = "select u from User u where u.username = :username and u.password = :password")
})
@XmlRootElement(name = "user")
public class User extends AbstractEntity {

    @Column(length = 255, nullable = false, unique = true)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = Criptography.encrypt(password);
        this.userType = UserType.USER;
    }

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = Criptography.encrypt(password);
        this.userType = userType;
    }

    public User(String username, String password, UserType userType, Profile profile) {
        this.username = username;
        this.password = Criptography.encrypt(password);
        this.userType = userType;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Criptography.encrypt(password);
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.username);
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{id=" + getId() + ", username=" + username + ", userType=" + userType + '}';
    }

    @Override
    public void updateParameters(Object entity) {
        final User other = (User) entity;
        this.username = other.username;
        this.password = other.password;
        this.userType = other.userType;
    }

    public enum UserType {
        ROOT, ADMIN, USER;
    }

}
