package io.homo_efficio.learnmicroservicesspringboot.multiplication.repository;

import io.homo_efficio.learnmicroservicesspringboot.multiplication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-09
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAlias(final String alias);
}
