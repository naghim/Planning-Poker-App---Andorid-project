package com.example.notesmanager;

public class RecyclerItem {

    private String name;
    private String task;
    private String value;

    public RecyclerItem(String name, String task, String value){
        this.name = name;
        this.task = task;
        this.value = value;
    }

    public String getName()
    {
        return this.name;
    }

    public String getTask()
    {
        return this.task;
    }

    public String getValue()
    {
        return this.value;
    }
}
