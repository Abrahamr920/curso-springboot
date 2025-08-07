package com.abraham.springboot.jpa.springboot_jpa_relationship.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = { "addresses", "invoices" })
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinColumn(name = "client_id")
    @JoinTable(name = "tbl_clients_to_addresses", joinColumns = @JoinColumn(name = "client_id"), //
            inverseJoinColumns = @JoinColumn(name = "address_id"), uniqueConstraints = @UniqueConstraint(columnNames = "address_id"))
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    @Builder.Default
    private List<Invoice> invoices = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private ClientDetails clientDetails;

    public Client(String lastName, String name) {
        this.lastName = lastName;
        this.name = name;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
        // invoices.forEach(invoice -> invoice.setClient(this));
    }

    public Client addInvoice(Invoice invoice) {
        invoices.add(invoice);
        // invoice.setClient(this);
        return this;
    }

    public void removeInvoice(Long invoiceIdToRemove) {
        invoices.removeIf(invoice -> invoice.getId().equals(invoiceIdToRemove));
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
        clientDetails.setClient(this);
    }

    public void removeClientDetails() {
        clientDetails.setClient(null);
        this.clientDetails = null;

    }

}
