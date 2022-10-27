package com.bssd.boffice.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;


@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "int4")
    private long id;

    @Column(name = "username", columnDefinition = "varchar(50)")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(100)")
    private String password;

    @Column(name = "fullname", columnDefinition = "varchar(50)")
    private String fullname;

    @Column(name = "email", columnDefinition = "varchar(50)")
    private String email;

    @Column(name = "phone", columnDefinition = "varchar(11)")
    private String phone;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "timestamp")
    private LocalDateTime createdDate;

    @Column(name = "expire_date", columnDefinition = "timestamp")
    private LocalDateTime expireDate;

    @Column(name = "updated_date", columnDefinition = "timestamp")
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @CreatedBy
    @Column(name = "created_by", columnDefinition = "varchar(50)")
    private String createdBy;

    @Column(name = "updated_by", columnDefinition = "varchar(50)")
    @LastModifiedBy
    private String updatedBy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Group userGroup;

}
