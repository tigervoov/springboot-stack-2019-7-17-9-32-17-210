package com.tw.apistackbase.entity;


import javax.persistence.*;
import java.io.PrintStream;

@Entity
public class CaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="subjective",nullable = false)
    private String SubjectiveInfo;

    @Column(name="objectiveInfo",nullable = false)
    private String objectiveInfo;

    public CaseInfo(String subjectiveInfo, String objectiveInfo) {
        SubjectiveInfo = subjectiveInfo;
        this.objectiveInfo = objectiveInfo;
    }

    public CaseInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectiveInfo() {
        return SubjectiveInfo;
    }

    public void setSubjectiveInfo(String subjectiveInfo) {
        SubjectiveInfo = subjectiveInfo;
    }

    public String getObjectiveInfo() {
        return objectiveInfo;
    }

    public void setObjectiveInfo(String objectiveInfo) {
        this.objectiveInfo = objectiveInfo;
    }
}
