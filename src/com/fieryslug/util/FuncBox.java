package com.fieryslug.util;

import com.fieryslug.panel.PanelRoot;

import javax.swing.*;


public class FuncBox {

  public static void switchPanel(JFrame jFrame, PanelRoot panel1, PanelRoot panel2) {

    jFrame.removeAll();
    jFrame.add(panel2);
    jFrame.revalidate();
    jFrame.repaint();

  }



}
