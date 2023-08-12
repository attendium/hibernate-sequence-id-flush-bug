package org.hibernate.bugs;

import jakarta.persistence.*;

@Entity
public class Ticket {

    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Person owner;

    public Ticket() {}

    public Ticket(final Person owner) {
        this.owner = owner;
    }
}
