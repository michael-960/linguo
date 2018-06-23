package com.fieryslug.linguo.panel;

import javax.swing.*;

public class PanelMain extends PanelRoot {

    public PanelMain() {

        super();
        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane);

    }



}
