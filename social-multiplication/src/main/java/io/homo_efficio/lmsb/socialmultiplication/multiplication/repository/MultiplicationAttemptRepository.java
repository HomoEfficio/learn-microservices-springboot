package io.homo_efficio.lmsb.socialmultiplication.multiplication.repository;

import io.homo_efficio.lmsb.socialmultiplication.multiplication.domain.MultiplicationAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-09
 */
@Repository
public interface MultiplicationAttemptRepository extends JpaRepository<MultiplicationAttempt, Long> {

    List<MultiplicationAttempt> findTop5ByUser_AliasOrderByIdDesc(String alias);
}
