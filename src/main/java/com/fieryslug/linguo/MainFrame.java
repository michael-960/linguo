package com.fieryslug.linguo;


import com.fieryslug.linguo.panel.PanelMain;
import com.fieryslug.linguo.panel.PanelMenu;
import com.fieryslug.linguo.util.FuncBox;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame {

    private PanelMenu panelMenu;
    private PanelMain panelMain;

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
	    this.panelMain = new PanelMain();

	    this.getContentPane().add(this.panelMenu);

	    this.panelMenu.buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelMenu, MainFrame.this.panelMain);

            }
        });

    }

    private void initIcon() {

	    Image image = null;
	    try {

            image = ImageIO.read(this.getClass().getResource("/res/icon.png"));

        } catch (IOException e) {
        }
	    this.setIconImage(image);

    }

}
