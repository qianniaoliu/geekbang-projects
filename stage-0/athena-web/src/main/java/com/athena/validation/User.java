package com.athena.validation;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

public class User {

    @Range(max = 1000, min = 10)
    private Integer id;

    @Email
    private String mail;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>mail</tt>.
     *
     * @return property value of mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Setter method for property <tt>mail</tt>.
     *
     * @param mail value to be assigned to property mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
}