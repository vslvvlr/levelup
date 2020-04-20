package application.domain;

// entity = строка таблицы.

import application.domain.UserAddressEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    //identity автоинкремент, sequence создание последовательности,
    //table как sequence на работает как транзакция но меленный, identity через sequence

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(length = 50, nullable = false, unique = true)
    private String passport;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false) // n+1 query: сначала insert потом update
    private List<UserAddressEntity> addresses;

    @OneToOne (mappedBy = "user")
    private AuthDetailsEntity authDetails;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
