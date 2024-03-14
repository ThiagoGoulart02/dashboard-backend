package com.example.dashboard.domain.entity;

import com.example.dashboard.web.representation.request.user.RequestUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String password;
    private String company_name;

    public User(RequestUser requestUser) {
        this.email = requestUser.email();
        this.password = requestUser.password();
        this.company_name = requestUser.company_name();
    }
}
