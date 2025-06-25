package com.company.ecommerce.domain;

import com.company.ecommerce.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users extends BaseEntity{

    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private LocalDate birthday;

    @OneToMany(
            mappedBy = "users",
            cascade = CascadeType.ALL
    )
    private List<Orders> orders;

    @OneToMany(
            mappedBy = "users",
            cascade = CascadeType.ALL
    )
    private List<CartItem> cartItemList;

}
