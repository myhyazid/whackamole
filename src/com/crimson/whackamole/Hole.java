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
    /*Scoring mechanism

    OnMouseClicked Event, do if checking to make sure the hole is occupied by occupant.Within 0-3.

    if (GetGridPaneLocation.OccupantID >=0 && <=3) {
    //Perks
    score=score+NPC.score;

    Under the same block, gives the user their perks, score or time.
    User have 2 seconds, same goes to the system.

     */

    public void setID(int guid) {
        ID = guid;
    }


}
