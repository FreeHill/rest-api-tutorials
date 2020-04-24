package com.restful.RestfulApi.model;

public class Todo {
    private Integer id;
    private String message;

    public Todo(){
        //For serialisation
    }

    //Todo constructor
    public Todo(Integer id, String message){
        this.id = id;
        this.message = message;
    }

    //Todo getter
    public Integer getId(){
        return id;
    }

    //Todo setter
    public void setId(){
        this.id = id;
    }

    // Message getter
    public String getMessage(){
        return message;
    }

    // Message setter
    public void setMessage(){
        this.message = message;
    }
}
