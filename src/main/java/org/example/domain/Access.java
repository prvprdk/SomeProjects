package org.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String url;
    private String login;
    private String typeAccess;
    private String password;
    private boolean checkAccess;
    @ManyToOne(fetch = FetchType.LAZY)
    private Site site;
}
