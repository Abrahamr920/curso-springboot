package com.abraham.springboot.jpa.springboot_jpa_relationship.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "client_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = { "client" })
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean premium;
    private Integer points;

    @OneToOne
    @JoinColumn
    private Client client;

    public ClientDetails(Integer points, boolean premium) {
        this.points = points;
        this.premium = premium;
    }

}
