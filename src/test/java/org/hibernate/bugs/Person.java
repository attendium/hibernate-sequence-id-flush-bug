package org.hibernate.bugs;

import jakarta.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    protected Account account;

    public Person() {}

    public Person(final Account parent) {
        this.account = parent;
    }
}
