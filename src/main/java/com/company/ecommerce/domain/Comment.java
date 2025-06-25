package com.company.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Users users;

    @Column(columnDefinition = "TEXT")
    private String pluses;

    @Column(columnDefinition = "TEXT")
    private String minuses;

    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    private Product product;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "comment"
    )
    private List<CommentFile> commentFileList;

}
