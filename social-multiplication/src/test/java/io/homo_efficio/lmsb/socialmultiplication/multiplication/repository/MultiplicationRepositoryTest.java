package io.homo_efficio.lmsb.socialmultiplication.multiplication.repository;

import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.Multiplication;
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

    @Test
    public void projection_test() {
        // given
        Multiplication multiplication = new Multiplication(20, 30);
        Multiplication dbMultiplication = repository.save(multiplication);

        // when
        MultiplicationView multiplicationView = repository.findById(dbMultiplication.getId(), MultiplicationView.class)
                .orElseThrow(() -> new RuntimeException("Projection Not Working"));

        // then
        assertThat(multiplicationView.getFactorA()).isEqualTo(multiplication.getFactorA());
    }

    interface MultiplicationView {
        int getFactorA();
    }
}
