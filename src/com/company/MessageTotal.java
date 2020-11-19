package com.company;

public class MessageTotal implements Visitor{
    private int messageTotal = 0;

    public int getMessageTotal() {
        return messageTotal;
    }

    public void setMessageTotal(int messageTotal) {
        this.messageTotal = messageTotal;
    }

    @Override
    public void visitUser(User user) {
        setMessageTotal(getMessageTotal() + user.getTweets().size());
    }

    @Override
    public void visitGroup(Group group) {
    }
}
