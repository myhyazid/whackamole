package com.crimson.whackamole;

public class Hole {
    private POS position;
    private float width;
    private float height;
    private NPC occupant;

    public POS getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public NPC getOccupant() {
        return occupant;
    }

    public void setPosition(POS position) {
        this.position = position;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }


    public void setOccupant(NPC occupant) {
        this.occupant = occupant;
    }
}
