package hcc.pete.smartivr.pojo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Pete Chen
 * @date 2020/5/25
 */

@Data
@Entity(name = "t_user")
public class User {

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

}
