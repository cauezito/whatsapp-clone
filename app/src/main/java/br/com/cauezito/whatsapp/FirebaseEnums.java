package br.com.cauezito.whatsapp;

public enum FirebaseEnums {
    USERS("users");

    private String name;

    FirebaseEnums(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
