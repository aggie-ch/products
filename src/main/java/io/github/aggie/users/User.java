package io.github.aggie.users;

import io.github.aggie.common.BaseEntity;

import io.github.aggie.common.validator.Name;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Table(name = "users", indexes = @Index(name = "email", columnList = "email"))
@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Pattern(regexp = "[A-Za-z]+")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Name
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
}
