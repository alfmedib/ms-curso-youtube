package academy.digitallab.store.shopping.service;/*
 * @Autor: Alfonso Médicis
 * el 10/06/2020
 */

import academy.digitallab.store.shopping.client.CustomerClient;
import academy.digitallab.store.shopping.client.ProductClient;
import academy.digitallab.store.shopping.entity.Invoice;
import academy.digitallab.store.shopping.entity.InvoiceItem;
import academy.digitallab.store.shopping.model.Customer;
import academy.digitallab.store.shopping.model.Product;
import academy.digitallab.store.shopping.repository.InvoiceItemRepository;
import academy.digitallab.store.shopping.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements  InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;


    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {

        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());

        if(invoice == null){
           return  null;
        }
        //invoice.setCreateAt(new Date());
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach(invoiceItem -> {
            productClient.updateStokProduct(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });

        return invoiceDB;
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

        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if(null != null){
            Customer customer = customerClient.getCustomer((invoice.getCustomerId())).getBody();
            invoice.setCustomer(customer);
            List<InvoiceItem> listItems = invoice.getItems().stream()
                    .map(invoiceItem -> {
                        Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                        invoiceItem.setProduct(product);
                        return invoiceItem;
                    }).collect(Collectors.toList());
            invoice.setItems(listItems);
        }
        return invoice;
    }
}
