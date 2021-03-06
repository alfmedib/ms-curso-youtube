package academy.digitallab.store.shopping.entity;/*
 * @Autor: Alfonso Médicis
 * el 10/06/2020
 */

import academy.digitallab.store.shopping.model.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name = "tbl_invoce_items")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "El stock debe ser mayor que cero")
    private Double quantity;
    private Double price;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private Product product;

    @Transient
    private Double subTotal;

    public Double getSubTotal(){
        if (this.price > 0 && this.quantity > 0){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }
}
