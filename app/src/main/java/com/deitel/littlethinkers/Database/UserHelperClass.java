package com.deitel.littlethinkers.Database;

public class UserHelperClass {
    String name;
    String surname;
    String date_of_birth;
    String contact_no;
    String email;
    String username;
    String password;
    //String id;
    String user_role;
    String number_level;



    public UserHelperClass() {
    }



    public UserHelperClass(String name, String surname, String date_of_birth, String contact_no, String email, String username, String password, String user_role, String number_level) {
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.contact_no = contact_no;
        this.email = email;
        this.username = username;
        this.password = password;
        //this.id = id;
        this.user_role = user_role;
        this.number_level = number_level;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        this.password = password;
    }

    /**public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }**/

    public String getUser_role() {
        return user_role;
    }

    public void seN(String user_role) {
        this.user_role = user_role;
    }

    public String getNumber_level() {
        return number_level;
    }

    public void setNumber_level(String number_level) {
        this.number_level = number_level;
    }
}

