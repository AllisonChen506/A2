package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositiveMessage implements Visitor{
    private double positiveMessage = 0;
    private double totalMessages = 0;
    private double positivePercentage = 0;
    private List<String> positiveWord = new ArrayList<String>(Arrays.asList("good", "great","nice"));

    public double getPositiveMessage() {
        if(totalMessages == 0)
        {
            return positivePercentage;
        }
        setPositivePercentage((positiveMessage/totalMessages)*100.0);
        return positivePercentage;
    }

    public void setPositivePercentage(double positivePercentage)
    {
        this.positivePercentage = positivePercentage;
    }

    @Override
    public void visitUser(User user) {
        for(String message : user.getTweets())
        {
            totalMessages+=1;
            for(String positive : positiveWord)
            {
                if(message.toLowerCase().contains(positive.toLowerCase()))
                {
                    positiveMessage+=1;
                    break;
                }
            }
        }
    }

    @Override
    public void visitGroup(Group group) {
    }
}
