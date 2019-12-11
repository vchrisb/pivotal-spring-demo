package net.banck.serviceb;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;
    private Long height;
}
