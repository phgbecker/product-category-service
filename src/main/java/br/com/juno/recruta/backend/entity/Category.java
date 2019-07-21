package br.com.juno.recruta.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "products", callSuper = true)
public class Category extends BaseEntity {

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "categories"
    )
    @JsonIgnore
    private List<Product> products;

    public Category(String name) {
        super(name);
    }
}
