package com.techobbyist.signuplogin;

import java.util.LinkedList;

/**
 * Created by Ramzy on 2017-11-26.
 */

public class Task {
    String name;
    String deadline;
    LinkedList<String> requiredItems;
    String note;

    public Task(){
        name="";
        deadline="";
        requiredItems=new LinkedList<String>();
        note="";
    }

    public Task(String name,String deadline,LinkedList<String> requiredItems,String note){
        this.name=name;
        this.deadline=deadline;
        this.requiredItems=requiredItems;
        this.note=note;
    }

    public String getName(){
        return name;
    }
    public String getDeadline(){
        return deadline;
    }
    public LinkedList<String> getRequiredItems(){
        return requiredItems;
    }
    public String getNote(){
        return note;
    }

    void setName(String name){
        this.name=name;

    }

    void setDeadline(String deadline){
        this.deadline=deadline;

    }

    void setRequiredItems(LinkedList<String> requiredItems){
        this.requiredItems=requiredItems;

    }

    void setNote(String note){
        this.note=note;

    }

    boolean deleteTask(){
        name=null;
        deadline=null;
        requiredItems=null;
        note=null;
        return true;
    }
}

