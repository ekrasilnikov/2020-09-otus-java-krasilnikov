package ru.otus.core.model;

import javax.persistence.*;

@Entity
@Table(name = "addressData")
public class AddressDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "addr")
    private String street;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid", referencedColumnName = "id", nullable = false)
    private Client client;

    public AddressDataSet() {
    }

    public AddressDataSet(String street, Client client) {
        this.street = street;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "id=" + id +
                ", street='" + street + '\'' +
                '}';
    }
}