package com.fieryslug.linguo.panel;

import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.util.FuncBox;
import com.fieryslug.linguo.util.Reference;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends PanelGnome {

    public JButton buttonStart;

    public PanelMenu() {

        super();

        this.initialize();

    }

    @Override
    public void enter(MainFrame frame) {

        this.removeAll();

        this.panelInterior.add(this.buttonStart);
        this.add(this.panelInterior);
        this.add(FuncBox.createBlankLabel(4000, 1));
    }

    @Override
    public void exit(MainFrame frame) {

    }



    private void initialize() {

        this.buttonStart = new JButton(new ImageIcon(Reference.BUTTON_START));
        this.buttonStart.setOpaque(false);
        this.buttonStart.setContentAreaFilled(false);
        this.buttonStart.setBorderPainted(false);
        this.buttonStart.setFocusPainted(false);

    }


}