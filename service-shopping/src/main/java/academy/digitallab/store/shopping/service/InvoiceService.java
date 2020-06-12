package academy.digitallab.store.shopping.service;/*
 * @Autor: Alfonso Médicis
 * el 10/06/2020
 */

import academy.digitallab.store.shopping.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    public List<Invoice> findAllInvoice();
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);
    public Invoice getInvoice( Long id);
}
