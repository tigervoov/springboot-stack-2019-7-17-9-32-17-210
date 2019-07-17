package com.tw.apistackbase.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false,unique = true,length = 50)
    private String courtName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "case_id")
    private List<Case> cases;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prosecutor_id")
    private List<Prosecutor> prosecutor;

    public Court() {
    }


    public Court(String courtName) {
        this.courtName = courtName;
    }

    public List<Prosecutor> getProsecutor() {
        return prosecutor;
    }

    public void setProsecutor(List<Prosecutor> prosecutor) {
        this.prosecutor = prosecutor;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }
}
