package com.fieryslug.util;

import com.fieryslug.panel.PanelRoot;

import javax.swing.*;


public class FuncBox {

  public static void switchPanel(JFrame jFrame, PanelRoot panel1, PanelRoot panel2) {

    jFrame.getContentPane().removeAll();

    jFrame.getContentPane().add(panel2);

    panel1.revalidate();
    panel1.repaint();
    panel2.revalidate();
    panel2.repaint();

    panel1.exit();
    panel2.enter();

    jFrame.invalidate();
    jFrame.validate();


  }



}
