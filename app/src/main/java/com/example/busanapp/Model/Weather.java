package com.example.busanapp.Model;

public class Weather {
    private  String key;
    private  String value;

    private int id ;
    private String main ;
    private String description;
    private String icon;


    public Weather(String key, String value) {
        this.key =key;
        this.value =value;
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
