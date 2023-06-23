package org.example.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String company;
    private String phone;
    private String email;
    private String site;
    @ManyToOne
            (fetch = FetchType.EAGER)
    private User author;
    @ElementCollection(targetClass = Contract.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "client_contract", joinColumns = @JoinColumn(name = "client_id"))
    @Enumerated(EnumType.STRING)
    private Set<Contract> contractSet;


}
