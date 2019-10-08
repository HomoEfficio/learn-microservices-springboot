package io.homo_efficio.learnmicroservicesspringboot.multiplication.repository;

import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.Multiplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-09
 */
@DataJpaTest
public class MultiplicationRepositoryTest {

    @Autowired
    private MultiplicationRepository repository;

    @Test
    public void avoid_multiplication_duplication() {
        Multiplication multiplication1 = new Multiplication(30, 80);
        Multiplication multiplication2 = new Multiplication(30, 80);

        Multiplication dbMultiplication1 = repository.save(multiplication1);
        Multiplication dbMultiplication2 = repository.save(multiplication2);

        assertThat(dbMultiplication1).isEqualTo(dbMultiplication2);
    }
}
