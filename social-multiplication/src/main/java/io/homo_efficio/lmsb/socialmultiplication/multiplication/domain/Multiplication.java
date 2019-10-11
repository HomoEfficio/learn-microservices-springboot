package io.homo_efficio.lmsb.socialmultiplication.multiplication.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-06
 */
@Entity
@Table
@Getter
@RequiredArgsConstructor
@ToString
//@EqualsAndHashCode
public final class Multiplication {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MULTIPLICATION_ID")
    private Long id;

    private final int factorA;
    private final int factorB;

    // empty constructor for JSON/JPA
    protected Multiplication() {
        this(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Multiplication that = (Multiplication) o;

        if (factorA != that.factorA) return false;
        return factorB == that.factorB;
    }

    @Override
    public int hashCode() {
        int result = factorA;
        result = 31 * result + factorB;
        return result;
    }
}
