package com.company;

public class Item {
    private String city;
    private String street;
    private String house;
    private String floor;

    public Item(String _city, String _street, String _house, String _floor) {
        city = _city;
        street = _street;
        house = _house;
        floor = _floor;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFloor() {
        return floor;
    }

    public boolean equal(Item item) {
        return city.equals(item.getCity())
                || street.equals(item.getStreet())
                || house.equals(item.getHouse())
                || floor.equals(item.getFloor());
    }

    public String toString() {
        return city + " " + street + " " + house + " " + floor;
    }
}
