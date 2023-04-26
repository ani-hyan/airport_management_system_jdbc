package org.example.entity;

import java.sql.Date;

public class Company {
    private int id;
    private String name;
    private Date foundDate;


    public Company(String name, Date foundDate) {
        this.name = name;
        this.foundDate = foundDate;
    }

    public Company(int id, String name, Date foundDate) {
        this.id = id;
        this.name = name;
        this.foundDate = foundDate;
    }

    public Company() {
    }

    public int getCompanyId() {
        return id;
    }

    public void setCompanyId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

    @Override
    public String toString() {
        return  id + " " + name + " " + foundDate;
    }
}
