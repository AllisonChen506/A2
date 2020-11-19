package com.company;

public class UserTotal implements Visitor{
    private int userTotal = 0;

    public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }

    @Override
    public void visitUser(User user) {
        setUserTotal(getUserTotal()+1);
    }

    @Override
    public void visitGroup(Group group) {

    }

}
