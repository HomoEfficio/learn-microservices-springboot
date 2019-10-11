package io.homo_efficio.lmsb.socialmultiplication.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-07
 */
@Entity
@Table(name = "USERS")
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public final class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private final String alias;

    // empty constructor for JSON/JPA
    public User() {
        this.alias = null;
    }
}
