package academy.digitallab.store.product.entity;/*
 * Creado por:  @Autor: Alfonso Médicis
 * el 4/06/2020
 */

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_categories")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
