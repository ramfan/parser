package com.company;

import java.util.Objects;

public class Item {
    private String city;
    private String street;
    private String house;
    private int floor;

    public Item(String _city, String _street, String _house, int _floor) {
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

    public int getFloor() {
        return floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;

        Item item = (Item)o;

        return Objects.equals(city, item.getCity())
                && Objects.equals(street, item.getStreet())
                && Objects.equals(house, item.getHouse())
                && Objects.equals(floor, item.getFloor());
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
