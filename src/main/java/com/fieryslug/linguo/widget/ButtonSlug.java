package com.fieryslug.linguo.widget;

import com.fieryslug.linguo.util.Reference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ButtonSlug extends JButton {

    public boolean isMouseInside = false;
    public Image imageDefault;
    public Image imageHover;
    public Image imagePress;

    public int scrollNum = 1;
    private boolean scrollState = false;

    private JLabel label;

    public ButtonSlug() {

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.label = new JLabel("", SwingConstants.CENTER);
        this.label.setPreferredSize(new Dimension(200, 200));
        this.label.setHorizontalTextPosition(SwingConstants.CENTER);
        this.label.setFont(Reference.MONOSPACED40);
        this.label.setForeground(Reference.WHITE);

    }

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

    public void setupScrolling() {

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

                int t = mouseWheelEvent.getWheelRotation();
                if(t == -1) {
                    scrollNum = Math.min(Reference.MAX, scrollNum+1);
                }
                else {
                    scrollNum = Math.max(1, scrollNum-1);
                }

                if(!ButtonSlug.this.scrollState) {
                    ButtonSlug.this.add(ButtonSlug.this.label);
                    ButtonSlug.this.scrollState = true;
                    scrollNum = 1;
                }
                ButtonSlug.this.label.setForeground(Reference.GRADIENT[scrollNum-1]);
                ButtonSlug.this.label.setText(String.valueOf(ButtonSlug.this.scrollNum));

            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                ButtonSlug.this.scrollNum = 1;
                ButtonSlug.this.scrollState = false;
                ButtonSlug.this.remove(ButtonSlug.this.label);
            }
        });

    }

}
