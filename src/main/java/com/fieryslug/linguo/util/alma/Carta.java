package com.fieryslug.linguo.util.alma;

public class Carta implements IWithName {

    public String description;
    public String match;

    public Carta() {
        this("", "");
    }

    public Carta(String discription, String match) {

        this.description = discription;
        this.match = match;

    }

    public boolean compare(String match1) {

        return this.match.equals(match1);

    }

    @Override
    public String getName() {
        return this.description;
    }
}
