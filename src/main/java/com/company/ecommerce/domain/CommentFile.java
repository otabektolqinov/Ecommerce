package com.company.ecommerce.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CommentFile extends BaseEntity{

    @Column(columnDefinition = "TEXT")
    private String contentUrl;

    private String originalName;
    private String generatedName;
    private String mimeType;
    private Long size;

    @ManyToOne
    private Comment comment;

}
