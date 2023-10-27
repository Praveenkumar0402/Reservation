package Socialapp.Instagram.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;


@Table(name="users")
@Entity
@Data
@NoArgsConstructor
public class User {
    @Column(name="users_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id")
    @SequenceGenerator(name="id",sequenceName = "userid",initialValue = 107,allocationSize = 1)
    private int id;
    @NonNull
    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "phno",unique = true)
    private String phno;
    private int following;
    private int follower;

    @Column(name="noofposts")
    private int posts;

    private String status;

    // @OneToMany(mappedBy = "user")
    // @JsonIgnore
    // private List<Posts> postsList;

    // @OneToMany(mappedBy = "user")
    // @JsonIgnore
    // private List<Comment> commentList;


}
