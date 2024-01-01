package com.umiuni.shop.auth.entity;

import lombok.*;

import javax.persistence.*;

//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@ToString
//@Table(name = "user")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "user_type")

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String password;

}
