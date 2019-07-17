package com.tw.apistackbase.entity;

import javax.persistence.*;

@Entity
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "case_name",length = 255,nullable = false)
    private String caseName;

    @Column(name = "time",nullable = false)
    private long time;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Case_Id")
    private CaseInfo caseInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Court_Id")
    private Court court;


    public Case() {
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public CaseInfo getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(CaseInfo caseInfo) {
        this.caseInfo = caseInfo;
    }

    public Case(String caseName, long time,CaseInfo caseInfo) {

        this.caseName = caseName;
        this.time = time;
        this.caseInfo=caseInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
