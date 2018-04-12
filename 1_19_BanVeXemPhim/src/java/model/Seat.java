/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author NguyenNgoc
 */
public class Seat implements Serializable{

    private int row;
    private int col;
    private String type;
    private int id;
    private Room room;
    
    public void in(){
        System.out.println("ID " + id);
        System.out.println("Type " + type);
    }

    public Seat() {
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Seat(int row, int col, String type, int id, Room room) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.id = id;
        this.room = room;
    }

    

    
    public Seat(int col, int row, String type, int idSeat) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.id = idSeat;
    }

    public int getId() {
        return id;
    }

    public void setId(int idSeat) {
        this.id = idSeat;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
