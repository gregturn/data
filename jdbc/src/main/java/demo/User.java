package demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User { //<1>

    //<2>
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    // ... Getters omitted

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email) { //<4>
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) { //<6>
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
