package com.company;

public interface SysEntry {

    public String getId();
    public String toString();
    public void accept (Visitor visitor);
}
