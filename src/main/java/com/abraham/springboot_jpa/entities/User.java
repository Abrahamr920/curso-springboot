package com.abraham.springboot_jpa.entities;

import java.util.ArrayList;
import java.util.List;

import com.abraham.springboot_jpa.validation.ExistsByUsername;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 4, max = 18)
    @ExistsByUsername

    private String username;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // or @JsonIgnore pero este ignora para insertar o leer
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles", //
            joinColumns = @JoinColumn(name = "user_id"), //
            inverseJoinColumns = @JoinColumn(name = "role_id"), //
            uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "role_id" }) })
    @EqualsAndHashCode.Exclude

    @JsonIgnoreProperties({ "users", "handler", "hibernateLazyInitializer" })
    private List<Role> roles = new ArrayList<>();

    private boolean enabled;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @PrePersist
    public void prePersist() {
        this.enabled = true;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
