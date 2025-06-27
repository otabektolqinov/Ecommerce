package com.company.ecommerce.repository;

import com.company.ecommerce.domain.CommentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentFileRepository extends JpaRepository<CommentFile, Long> {
    List<CommentFile> findByCommentIdAndDeletedAtIsNull(Long commentId);

    Optional<CommentFile> findByIdAndDeletedAtIsNull(Long commentFileId);
}
