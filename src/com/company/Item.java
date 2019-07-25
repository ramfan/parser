package com.company;

import java.util.Objects;

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
        return city == item.getCity()
                && street == item.getStreet()
                && house == item.getHouse()
                && floor == item.getFloor();
    }

    @Override
    public int hashCode(){
        return Objects.hash(city, street, house, floor);
    }

    @Override
    public String toString() {
        return city + " " + street + " " + house + " " + floor;
    }
}
