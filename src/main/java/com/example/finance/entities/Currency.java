package com.example.finance.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlType
@ToString(exclude = {"curs"})
@Entity
@Getter
@NoArgsConstructor
public class Currency {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

    @Column
    private String value;

    @Column
    private String charCode;

    @Column
    private Integer nominal;

    @ManyToOne
    @JoinColumn(name = "curs_id", nullable = false)
    private Curs curs;

    @XmlTransient
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Value")
    public void setValue(String value) {
        this.value = value;
    }

    @XmlElement(name = "CharCode")
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    @XmlElement(name = "Nominal")
    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    @XmlTransient
    public void setCurs(Curs curs) {
        this.curs = curs;
    }
}
