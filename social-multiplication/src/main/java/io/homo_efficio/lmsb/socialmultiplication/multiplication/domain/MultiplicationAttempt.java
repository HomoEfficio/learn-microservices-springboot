package io.homo_efficio.lmsb.socialmultiplication.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Identifies the attempt from a {@link User} to solve a {@link Multiplication}.
 *
 * @author homo.efficio@gmail.com
 * created on 2019-10-07
 */
@Entity
@Table
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public final class MultiplicationAttempt {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MULTIPLICATION_ATTEMPT_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private final User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MULTIPLICATION_ID")
    private final Multiplication multiplication;

    private final int resultAttempt;
    private final boolean correct;

    // empty constructor for JSON/JPA
    public MultiplicationAttempt() {
        this.user = null;
        this.multiplication = null;
        this.resultAttempt = -1;
        this.correct = false;
    }
}
