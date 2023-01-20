package com.example.finance.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "ValCurs")
@ToString()
@NoArgsConstructor
@Getter
@Entity
public class Curs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false,unique = true)
    private LocalDate date;

    @Column
    private String name;
    @OneToMany(mappedBy = "curs")
    private List<Currency> valute;

    @XmlTransient
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Valute")
    public void setValute(List<Currency> valute) {
        this.valute = valute;
    }
}
