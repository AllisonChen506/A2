package com.company;

import java.util.ArrayList;

public class UserVerification implements Visitor
{
    ArrayList idList = new ArrayList();
    Boolean valid = true;

    public Boolean getValid()
    {
        return valid;
    }



    @Override
    public void visitUser(User user) {
        if(idList.contains(user.getId()) || user.getId().contains(" "))
        {
            valid=false;
        }
        idList.add(user.getId());
    }

    @Override
    public void visitGroup(Group group) {
        if(idList.contains(group.getId()) || group.getId().contains(" "))
            valid=false;
        idList.add(group.getId());
    }
}
