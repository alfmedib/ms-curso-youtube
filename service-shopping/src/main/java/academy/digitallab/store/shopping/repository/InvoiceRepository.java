package academy.digitallab.store.shopping.repository;/*
 * @Autor: Alfonso Médicis
 * el 10/06/2020
 */

import academy.digitallab.store.shopping.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public List<Invoice> findByCustomerId(Long customerId);
    public Invoice findByNumberInvoice(String numberInvoice);
}
