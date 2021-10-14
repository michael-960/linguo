package com.fieryslug.linguo.util.listener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class DeadKeyListener implements KeyListener {

    private JTextField field;
    private Map<String, Character> conversionTable;

    public DeadKeyListener(JTextField field) {
        this.field = field;
        this.conversionTable = new HashMap<>();
        this.conversionTable.put("'a", '\u00e1');
        this.conversionTable.put("'e", '\u00e9');
        this.conversionTable.put("'i", '\u00ed');
        this.conversionTable.put("'o", '\u00f3');
        this.conversionTable.put("'u", '\u00fa');
        this.conversionTable.put("0a", '\u00e5');
        this.conversionTable.put("0u", '\u016f');
        this.conversionTable.put("^a", '\u00e2');
        this.conversionTable.put("^e", '\u00ea');
        this.conversionTable.put("^i", '\u00ee');
        this.conversionTable.put("^o", '\u00f4');
        this.conversionTable.put("^u", '\u00fb');
        this.conversionTable.put("`a", '\u00e0');
        this.conversionTable.put("`e", '\u00e8');
        this.conversionTable.put("`i", '\u00ec');
        this.conversionTable.put("`o", '\u00f2');
        this.conversionTable.put("`u", '\u016e');
        this.conversionTable.put("~n", '\u00f1');


    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {
        System.out.println("KeyTyped" + this.field.toString());
        String s = this.field.getText();
        for(String key : this.conversionTable.keySet()) {
            if(s.endsWith(key)) {
                String n = s.substring(0, s.length() - key.length());
                n = n + this.conversionTable.get(key);
                this.field.setText(n);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
