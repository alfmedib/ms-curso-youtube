package academy.digitallab.store.shopping.repository;/*
 * @Autor: Alfonso Médicis
 * el 10/06/2020
 */

import academy.digitallab.store.shopping.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
