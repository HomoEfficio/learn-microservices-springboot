package io.homo_efficio.lmsb.socialmultiplication.multiplication.repository;

import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.Multiplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-09
 */
@Repository
public interface MultiplicationRepository extends JpaRepository<Multiplication, Long> {

    <T> Optional<T> findById(Long id, Class<T> clazz);
}
