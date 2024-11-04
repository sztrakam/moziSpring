package com.example.mozispring.Model;
import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "filmek")
public class MyAppFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cim")
    private String cim;

    @Column(name = "ev")
    private int ev;

    @Column(name = "hossz")
    private int hossz;

    public Long getId() {
        return id;
    }

    public String getCim() {
        return cim;
    }

    public int getEv() {
        return ev;
    }

    public int getHossz() {
        return hossz;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public void setHossz(int hossz) {
        this.hossz = hossz;
    }
}
