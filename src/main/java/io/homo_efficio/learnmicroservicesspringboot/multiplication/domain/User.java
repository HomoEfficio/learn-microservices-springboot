package io.homo_efficio.learnmicroservicesspringboot.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-07
 */
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public final class User {

    private final String alias;

    public User() {
        this.alias = null;
    }
}
