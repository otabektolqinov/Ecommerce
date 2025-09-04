package com.company.ecommerce.domain;

import com.company.ecommerce.enums.AuthRole;
import com.company.ecommerce.enums.AuthState;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "auth_user")
public class AuthUser extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Users users;
    @Enumerated(value = EnumType.STRING)
    private AuthState authState;
    @Enumerated(value = EnumType.STRING)
    private AuthRole role;

}
