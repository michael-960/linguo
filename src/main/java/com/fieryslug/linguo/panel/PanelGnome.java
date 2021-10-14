package com.fieryslug.linguo.panel;

import com.fieryslug.linguo.util.Colors;
import com.fieryslug.linguo.util.FuncBox;
import com.fieryslug.linguo.util.Reference;

import javax.swing.*;
import java.awt.*;

public class PanelGnome extends PanelRoot {

    public JPanel panelInterior;

    public PanelGnome() {

        super();
        this.panelInterior = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(Reference.BG_GNOME_SMALL, 0, 0, this);
            }
        };
        //this.panelInterior.setBackground(Colors.GREEN);
        //this.setBackground(Colors.LIME);
        this.panelInterior.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.panelInterior.setPreferredSize(new Dimension(Reference.PANEL_INTERIOR_WIDTH, Reference.PANEL_INTERIOR_HEIGHT));
        this.add(this.panelInterior);
        this.add(FuncBox.createBlankLabel(Reference.LABEL_BLANK_WIDTH, Reference.LABEL_BLANK_HEIGHT));


    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(Reference.BG_GNOME_LARGE, 0, 0, this);
    }
}
