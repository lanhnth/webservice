/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author NguyenNgoc
 */
public class Cinema implements Serializable {

    private int id;
    private String name;
    private String address;
    private double id_x;
    private double id_y;
    
    private ArrayList<Room> rooms;

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public Cinema(int id, String name, String address, ArrayList<Room> rooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rooms = rooms;
    }
    
    public Cinema(int id, String name, String address, double id_x, double id_y) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.id_x = id_x;
        this.id_y = id_y;
    }

    public Cinema() {
    }

    public Cinema(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getId_x() {
        return id_x;
    }

    public void setId_x(double id_x) {
        this.id_x = id_x;
    }

    public double getId_y() {
        return id_y;
    }

    public void setId_y(double id_y) {
        this.id_y = id_y;
    }
    
    
}
