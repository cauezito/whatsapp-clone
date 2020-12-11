package br.com.cauezito.whatsapp;

public enum FirebaseEnum {
    USERS("users"),

    //STORAGE
    IMAGES("images"),
    PROFILE("profile"),
    PROFILE_PIC_DEFAULT("profile.jpeg");

    private String name;

    FirebaseEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
