package br.com.juno.recruta.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonIgnore
    private LocalDateTime lastModified;

    @Version
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    @JsonIgnore
    private Integer version;

    public BaseEntity(String name) {
        this.name = name;
    }
}
