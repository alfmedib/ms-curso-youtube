package academy.digitallab.store.shopping.service;/*
 * @Autor: Alfonso Médicis
 * el 10/06/2020
 */

import academy.digitallab.store.shopping.entity.Invoice;
import academy.digitallab.store.shopping.repository.InvoiceItemRepository;
import academy.digitallab.store.shopping.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl implements  InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;


    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {

        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());

        if(invoice == null){
            log.info("PASA A NULO JEJEJE");
            return  null;
        }
        //invoice.setCreateAt(new Date());
        invoice.setState("CREATED");

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {

        Invoice invoiceDB = getInvoice(invoice.getId());

        if(invoiceDB == null){
            return null;
        }

        invoiceDB.setId(invoice.getId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
       return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {

        Invoice invoiceDB = getInvoice(invoice.getId());

        if(invoiceDB == null){
            return null;
        }

        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }
}
