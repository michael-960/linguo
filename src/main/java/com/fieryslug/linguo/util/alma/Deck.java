package com.fieryslug.linguo.util.alma;

import java.util.ArrayList;

public class Deck {

    public String name;
    public ArrayList<Carta> cartas;

    public Deck() {
        this("", new ArrayList<>());
    }

    public Deck(String name, ArrayList<Carta> cartas) {

        this.name = name;
        this.cartas = cartas;

    }

}
