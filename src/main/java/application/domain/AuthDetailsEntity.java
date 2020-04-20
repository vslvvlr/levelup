package application.domain;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "auth_details")
public class AuthDetailsEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "id")
    private UserEntity user;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "login", nullable = false, unique = true)
    private String login;
    @Column (name = "password", nullable = false)
    private String password;

}
