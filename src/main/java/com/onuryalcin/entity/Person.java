package com.onuryalcin.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Person implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_person",allocationSize = 1)
    @GeneratedValue(generator = "seq_person",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="NAME", length = 100)
    private String name;

    @Column(name="LAST_NAME", length = 100)
    private String lastName;

    @OneToMany
    @JoinColumn(name = "person_address_id")
    private List<Address> personAddressList;

}
