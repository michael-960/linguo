package com.fieryslug.linguo.widget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LabelSlug extends JLabel {

    private boolean isMouseInside = false;

    private Font fontDefault;
    private Font fontHover;
    private Color colorDefault;
    private Color colorPress;

    public LabelSlug(String s) {
        super(s, SwingConstants.CENTER);
    }

    public void toDefaults() {
        this.setFont(this.fontDefault);
        this.setForeground(this.colorDefault);
    }

    public void setupInteraction(Font fontDefault, Font fontHover, Color colorDefault, Color colorPress) {

        this.fontDefault = fontDefault;
        this.fontHover = fontHover;
        this.colorDefault = colorDefault;
        this.colorPress = colorPress;

        this.setFont(fontDefault);
        this.setForeground(colorDefault);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                LabelSlug.this.setFont(fontHover);
                LabelSlug.this.setForeground(colorPress);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

                if(LabelSlug.this.isMouseInside) {
                    LabelSlug.this.setFont(fontHover);
                }
                else {
                    LabelSlug.this.setFont(fontDefault);
                }
                LabelSlug.this.setForeground(colorDefault);

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                LabelSlug.this.isMouseInside = true;
                LabelSlug.this.setFont(fontHover);
                LabelSlug.this.setForeground(colorDefault);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                LabelSlug.this.isMouseInside = false;
                LabelSlug.this.setFont(fontDefault);
                LabelSlug.this.setForeground(colorDefault);
            }
        });

    }

}
