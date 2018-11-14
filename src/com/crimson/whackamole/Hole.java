package com.crimson.whackamole;

public class Hole {
    private int ID;
    private POS position;
    private NPC occupant;

    public POS getPosition() {
        return position;
    }
    public int getID() {
        return ID;
    }

    public NPC getOccupant() {
        return occupant;
    }

    public void setPosition(POS position) {
        this.position = position;
    }


    public void setOccupant(NPC occupant) {
        this.occupant = occupant;
    }
    public void setID(int guid) {
        ID = guid;
    }


}
