package br.com.cauezito.whatsapp;

public enum FirebaseEnum {
    USERS("users");

    private String name;

    FirebaseEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
