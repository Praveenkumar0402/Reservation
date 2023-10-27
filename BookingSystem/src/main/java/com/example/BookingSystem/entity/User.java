package com.example.BookingSystem.entity;

import com.example.BookingSystem.enums.Gender;
import com.example.BookingSystem.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "id", initialValue = 100, allocationSize = 1)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Please enter the firstname")
    @Column(name = "first_name")
    private String firstname;

    @NotBlank(message = "Please enter the lastname")
    @Column(name = "last_name")
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotBlank(message = "Invalid email address")
    @Column(name = "email")
    private String email;

    private String password;

    @Pattern(regexp = "^[6789][0-9]{9}$", message = "Check the mobile number")
    @Column(name = "mobile")
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(name = "userstatus")
    private UserStatus userstatus;

    @NotBlank
    private String roles;


    public User(int id, String firstname, String lastname, Gender gender, String email, String password, String mobile, UserStatus userstatus, String roles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.userstatus = userstatus;
        this.roles = roles;
    }

    public User(User user) {
        id = user.getId();
        firstname = user.getFirstname();
        lastname = user.getLastname();
        gender = user.getGender();
        email = user.getEmail();
        password = user.getPassword();
        mobile = user.getMobile();
        userstatus = user.getUserstatus();
        roles = user.getRoles();
    }
}