package com.company;

import java.util.ArrayList;
import java.util.List;

public class Group implements SysEntry {
    private double creationTime;
    private String groupID;
    private List<SysEntry> groupMembers = new ArrayList<SysEntry>();

    public Group(String newID) {
        this.groupID = newID;
        this.creationTime = System.currentTimeMillis();
    }
    public double getCreationTime()
    {
        return creationTime;
    }

    public void addGroupMember(SysEntry newMember){
        this.groupMembers.add(newMember);
    }

    public Boolean containsUserId(String UserID) {
        for (SysEntry member : groupMembers) {
            if (member instanceof User) {
                if (member.getId().equals(UserID))
                    return true;
            }
            else if (member instanceof Group) {
                if (((Group) member).containsUserId(UserID))
                    return true;
            }
        }
        return false;
    }

    public Boolean containsGroupId(String memberID){
        for (SysEntry member : groupMembers) {
            if (member instanceof User)
                continue;
            else if (member instanceof Group) {
                if (member.getId().equals(memberID))
                    return true;
                else {
                    if(((Group) member).containsGroupId(memberID))
                        return true;
                }
            }
        }
        return false;
    }

    public User getUser(String userID){
        for (SysEntry member : groupMembers) {
            if (member instanceof User) {
                if (member.getId().equals(userID))
                    return (User) member;
            }
            else if (member instanceof Group) {
                if (((Group) member).containsUserId(userID))
                    return ((Group) member).getUser(userID);
            }
        }
        return null;
    }
    @Override
    public String getId() {
        return this.groupID;
    }

    @Override
    public String toString() {
        return this.groupID;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitGroup(this);
        for(SysEntry member : groupMembers) {
            if (member instanceof User)
                member.accept(visitor);
            else if (member instanceof Group) {
                member.accept(visitor);
            }
        }
    }
}
