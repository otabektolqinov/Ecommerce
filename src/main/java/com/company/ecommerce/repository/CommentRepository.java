package com.company.ecommerce.repository;

import com.company.ecommerce.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndDeletedAtIsNull(Long id);

    Optional<List<Comment>> findAllByProductIdAndDeletedAtIsNull(Long id);

    Optional<List<Comment>> findAllByUsersIdAndDeletedAtIsNull(Long usersId);

}
