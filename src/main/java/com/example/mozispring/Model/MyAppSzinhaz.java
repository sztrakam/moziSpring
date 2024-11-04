package com.example.mozispring.Model;
import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "szinhazak")
public class MyAppSzinhaz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nev")
    private String nev;

    @Column(name = "varos")
    private String varos;

    @Column(name = "ferohely")
    private int ferohely;

    public Long getId() {
        return id;
    }

    public String getNev() {
        return nev;
    }

    public String getVaros() {
        return varos;
    }

    public int getFerohely() {
        return ferohely;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    public void setFerohely(int ferohely) {
        this.ferohely = ferohely;
    }
}
