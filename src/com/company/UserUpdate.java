package com.company;

public class UserUpdate implements  Visitor
{
    String lastUpdateUser = "no one";
    double recentUpdateTime = 0;

    public String getLastUpdateUser()
    {
        return lastUpdateUser;
    }

    @Override
    public void visitUser(User user) {
        if(user.getRecentUpdateTime() > recentUpdateTime)
        {
            recentUpdateTime = user.getRecentUpdateTime();
            lastUpdateUser = user.getId();
        }
    }

    @Override
    public void visitGroup(Group group) {

    }
}
