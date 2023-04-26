package org.example.entity;

public class Passenger {
    private int id;
    private String name;
    private String phone;
    private String country;
    private String city;

    public Passenger(int id, String name, String phone, String country, String city) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.city = city;
    }

    public Passenger(String name, String phone, String country, String city) {
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.city = city;
    }

    public Passenger() {
    }

    public Passenger(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  id + " " + name + " " + phone + " " + country + " " + city;
    }
}
