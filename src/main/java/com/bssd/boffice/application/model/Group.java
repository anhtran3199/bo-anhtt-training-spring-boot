package com.bssd.boffice.application.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@Builder
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "int4")
    private long id;

    @Column(name = "group_name", columnDefinition = "varchar(50)")
    private String groupName;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "varchar(50)")
    private LocalDateTime createdDate;

    @Column(name = "updated_date", columnDefinition = "timestamp")
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Column(name = "created_by", columnDefinition = "varchar(50)")
    @CreatedBy
    private String createdBy;

    @OneToMany(mappedBy = "userGroup", fetch = FetchType.EAGER)
    private Set<User> users;
}
