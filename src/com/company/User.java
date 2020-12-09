package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User extends Subject implements SysEntry, Observer{
    private double creationTime;
    private double recentUpdateTime;
    private String userID;
    private List<User> following = new ArrayList<User>();
    private ObservableList<User> followingList = FXCollections.observableList(following);
    private List<String> tweets = new ArrayList<String>();
    private List<String> feed = new ArrayList<String>(Arrays.asList("News Feed:", "recently updated at: "+ recentUpdateTime));
    private ObservableList<String> feedList = FXCollections.observableList(feed);

    public User(String newID) {
        this.userID = newID;
        this.creationTime = System.currentTimeMillis();
    }

    public ObservableList<User> getFollowingList() {
        return followingList;
    }

    public void addFollowingList(User user) {
        followingList.add(user);
    }

    public List<String> getTweets() {
        return tweets;
    }

    public ObservableList<String> getFeedList() {
        return feedList;
    }
    public void postTweet (String message){
            tweets.add(message);
            feedList.add(this.userID + " : " + message);
            notifyObservers(message);
    }
    public double getCreationTime()
    {
        return creationTime;
    }
    public double getRecentUpdateTime()
    {
        return recentUpdateTime;
    }

    @Override
    public String getId() {
        return this.userID;
    }

    @Override
    public String toString() {
        return this.userID;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitUser(this);
    }

    @Override
    public void update(Subject subject, String message) {
        if (subject instanceof User) {
            this.feedList.add(((User) subject).getId() + " : " + message);
            recentUpdateTime = System.currentTimeMillis();
            this.feedList.set(1, "recently updated at: "+ recentUpdateTime);
        }
    }
}