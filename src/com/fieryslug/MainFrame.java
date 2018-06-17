package com.fieryslug;


import com.fieryslug.panel.PanelMenu;
import com.fieryslug.panel.PanelRoot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private PanelRoot panelMenu;

	public MainFrame() {

	    this.setBounds(400, 150, 1000, 800);

	    this.setTitle("Linguo");
        this.initIcon();
	    this.initPanels();


		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	private void initPanels() {

	    this.panelMenu = new PanelMenu();

	    this.add(this.panelMenu);

    }

    private void initIcon() {

	    Image image = null;
	    try {

            image = ImageIO.read(this.getClass().getResource("/com/fieryslug/resource/icon.png"));

        } catch (IOException ioe) {
        }
	    this.setIconImage(image);

    }

}
