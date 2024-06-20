package com.wipro.app.model;

public enum MachineType {
    SWINGO800("Swingo800"),
    SWINGO2000("Swingo2000"),
    ULTIMAX1900("Ultimax1900");

    private String value;

    MachineType( String value) {

        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
