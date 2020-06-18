package academy.digitallab.store.customer.repository.entity;/*
 * @Autor: Alfonso Médicis
 * el 8/06/2020
 */

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_regions")
public class Region implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
