package com.company;

public class GroupTotal implements Visitor {

    private int groupTotal = 0;
    public void setGroupTotal(int userTotal)
    {
        this.groupTotal=userTotal;
    }

    public int getGroupTotal()
    {
        return groupTotal;
    }

    @Override
    public void visitUser(User user) { }

    @Override
    public void visitGroup(Group group) {
        setGroupTotal(getGroupTotal() + 1);
    }
}
