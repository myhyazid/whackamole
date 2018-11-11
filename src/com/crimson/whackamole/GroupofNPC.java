package com.crimson.whackamole;

public class GroupofNPC {
    private NPC ListofNPC[] = new NPC[3];
    private int NumofNPC;

    public void addNPC(NPC p){
        ListofNPC[NumofNPC]=p;
        NumofNPC++;
    }

    public NPC getListofNPC(int i) {
        return ListofNPC[i];
    }

    public int getNumofNPC() {
        return NumofNPC;
    }
}
