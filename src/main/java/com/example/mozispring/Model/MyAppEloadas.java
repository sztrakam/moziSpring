package com.example.mozispring.Model;
import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "eloadas")
public class MyAppEloadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name = "filmid")
    private Long filmId;

    @Column(name = "moziid")
    private Long moziId;

    @Column(name = "datum")
    private String datum;

    @Column(name = "nezoszam")
    private int nezoszam;

    @Column(name = "bevetel")
    private double bevetel;

    public Long getId() {
        return id;
    }

    public Long getFilmId() {
        return filmId;
    }

    public Long getMoziId() {
        return moziId;
    }

    public String getDatum() {
        return datum;
    }

    public int getNezoszam() {
        return nezoszam;
    }

    public double getBevetel() {
        return bevetel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public void setMoziId(Long moziId) {
        this.moziId = moziId;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setNezoszam(int nezoszam) {
        this.nezoszam = nezoszam;
    }

    public void setBevetel(double bevetel) {
        this.bevetel = bevetel;
    }
}
