package org.example.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @ElementCollection(targetClass = Contract.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "client_contract", joinColumns = @JoinColumn(name = "client_id"))
    @Enumerated(EnumType.STRING)
    private List<Contract> contractSet;


}
