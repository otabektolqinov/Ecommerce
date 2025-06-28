package com.company.ecommerce.repository;

import com.company.ecommerce.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndDeletedAtIsNull(Long id);

    Page<Comment> findAllByProductIdAndDeletedAtIsNull(Long id, Pageable pageable);

    Page<Comment> findAllByUsersIdAndDeletedAtIsNull(Long usersId, Pageable pageable);


}
