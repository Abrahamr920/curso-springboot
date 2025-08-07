package com.abraham.curso.springboot.di.factura.springboot_difactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@RequestScope
// @JsonIgnoreProperties({ "targetSource", "advisors" }) // This annotation is
// used to ignore the targetSource property in
// the JSON serialization.
public class Invoice {
    @Autowired
    private Client client;

    @Value("${invoice.description}")
    private String description;
    @Autowired
    @Qualifier("itemsInvoiceJson")
    private List<Item> items;

    @PostConstruct // Esta anotación se utiliza en un método que necesita ejecutarse después de que
                   // se haya realizado la inyección de dependencias para realizar cualquier
                   // inicialización.
    public void init() {
        client.setName("Rafael " + client.getName());
    }

    @PreDestroy // Esta anotación se utiliza en métodos como una notificación de devolución de
                // llamada para señalar que la instancia está en proceso de ser eliminada por el
                // contenedor.
    public void destroy() {
        System.out.println("Destruyendo el componente de la Factura");
    }

    public Invoice() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal() {
        return items.stream().mapToInt(Item::getImporte).sum();
    }
}
