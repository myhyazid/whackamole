package com.crimson.whackamole;

public class GroupofHoles {
    private Hole ListofHoles[] = new Hole[16];
    private int NumofHoles;

    public void addHole(Hole p){
        ListofHoles[NumofHoles]=p;
    }

    public Hole getListofHoles(int i) {
        return ListofHoles[i];
    }
}
