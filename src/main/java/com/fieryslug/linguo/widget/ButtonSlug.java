package com.fieryslug.linguo.widget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonSlug extends JButton {

    public boolean isMouseInside = false;
    public Image imageDefault;
    public Image imageHover;
    public Image imagePress;

    public void setupIcons(Image imageDefault, Image imageHover, Image imagePress) {

        this.imageDefault = imageDefault;
        this.imageHover = imageHover;
        this.imagePress = imagePress;

        this.setIcon(new ImageIcon(this.imageDefault));

        this.setOpaque(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);

        this.setVerticalTextPosition(SwingConstants.CENTER);
        this.setHorizontalAlignment(SwingConstants.CENTER);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                ButtonSlug.this.setIcon(new ImageIcon(ButtonSlug.this.imagePress));
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if(ButtonSlug.this.isMouseInside)
                    ButtonSlug.this.setIcon(new ImageIcon(ButtonSlug.this.imageHover));
                else
                    ButtonSlug.this.setIcon(new ImageIcon(ButtonSlug.this.imageDefault));
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                ButtonSlug.this.setIcon(new ImageIcon(ButtonSlug.this.imageHover));
                ButtonSlug.this.isMouseInside = true;
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                ButtonSlug.this.setIcon(new ImageIcon(ButtonSlug.this.imageDefault));
                ButtonSlug.this.isMouseInside = false;
            }
        });

    }

}
