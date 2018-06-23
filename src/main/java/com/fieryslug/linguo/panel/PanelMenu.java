package com.fieryslug.linguo.panel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PanelMenu extends PanelRoot {

    public JButton buttonStart;

    public PanelMenu() {

        super();

        this.initialize();

    }

    @Override
    public void enter() {
        super.enter();
    }

    @Override
    public void exit() {
        super.exit();
    }

    private void initialize() {


        Image image = null;
        try {

            image = ImageIO.read(this.getClass().getResource("/res/button1.png"));

        } catch (IOException e) {

        }

        this.buttonStart = new JButton(new ImageIcon(image));
        this.buttonStart.setOpaque(false);
        this.buttonStart.setContentAreaFilled(false);
        this.buttonStart.setBorderPainted(false);
        this.buttonStart.setFocusPainted(false);

        this.add(this.buttonStart);

    }

}