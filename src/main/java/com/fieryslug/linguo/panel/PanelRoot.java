package com.fieryslug.linguo.panel;


import com.fieryslug.linguo.MainFrame;

import javax.swing.*;
import java.awt.*;

public abstract class PanelRoot extends JPanel {

    public PanelRoot() {

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.BLACK);

    }

    public void enter(MainFrame frame) {


    }

    public void exit(MainFrame frame) {


    }
}

