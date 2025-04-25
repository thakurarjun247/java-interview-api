package project.freedom.authservice.entity;

import jakarta.persistence.*;


//todo: reference https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1.html
//@entity is mandatory, the name is optional it defaults to class name, converted to lowercase

@Entity(name = "users")
//optional @table
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Add @Column only if you want to customize (e.g. name, length, nullable).
    @Column

    private Integer id;
    /*JPA auto-maps all non-static, non-transient fields to columns by default.

    email is a persistent field → JPA will treat it as a column automatically.

    You only need @Column if you want to customize (name, length, nullable, etc).*/
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    public User() {
    }

//You need: A no-arg constructor ( public)

//
//Getters and setters for JPA to work properly (unless you're using Lombok)


    public User(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
