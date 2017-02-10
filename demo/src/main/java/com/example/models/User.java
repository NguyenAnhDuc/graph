package com.example.models;

/**
 * Created by ducna on 1/20/2017.
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Represents an User for this web application.
 */
@Entity
@Table(name = "test_user")
public class User {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int pk_user_id;

    //private String s_username;
    //private String s_password;
    //private String s_first_name;
    //private String s_last_name;
    //private String s_fullname;

    private String s_phone;
    private String s_address;
    private String s_email;
    private String s_province_name;
    private String s_village_name;
    /*private int b_class;
    private String s_class_name;
    private int fk_school_id;
    private String s_school_name;
    private int fk_village_id;
    private int fk_province_id;
    private int b_user_type;

    private int b_status;
    private int b_current_round;
    private int i_total_score;

    private char s_Level;
    private int i_TotalTime;
    private Date d_create_date;
    private Date d_birth;*/


    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    public User() { }

/*    public User(long id) {
        this.id = id;
    }*/

    public User(int pk_user_id, String email, String phone, String province, String address) {
        this.pk_user_id = pk_user_id;
        this.s_email = email;
        this.s_phone = phone;
        this.s_province_name = province;
        this.s_address = address;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getS_email() {
        return s_email;
    }

    public void setS_email(String s_email) {
        this.s_email = s_email;
    }

    public String getS_province_name() {
        return s_province_name;
    }

    public void setS_province_name(String s_province_name) {
        this.s_province_name = s_province_name;
    }

    public int getPk_user_id() {
        return pk_user_id;
    }

    public void setPk_user_id(int pk_user_id) {
        this.pk_user_id = pk_user_id;
    }

    public String getS_village_name() {
        return s_village_name;
    }

    public void setS_village_name(String s_village_name) {
        this.s_village_name = s_village_name;
    }
} // class User
