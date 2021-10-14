package com.fieryslug.linguo;


import com.fieryslug.linguo.panel.*;
import com.fieryslug.linguo.util.FuncBox;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainFrame extends JFrame {

    public PanelMenu panelMenu;
    public PanelTopics panelTopics;
    public PanelAdd panelAdd;
    public PanelRemove panelRemove;

    public PanelGroups panelGroups;
    public PanelDecks panelDecks;
    public PanelGame panelGame;
    public PanelCartas panelCartas;
    public PanelEditCarta panelEditCarta;

    public PanelMaster panelMaster;

    public MainFrame() {

	    this.setBounds(400, 150, 1400, 1000);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	    this.setTitle("Linguo");
        this.initIcon();
	    this.initPanels();
	    this.linkButtons();
	    this.linkLabels();


		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	private void initPanels() {

	    this.panelMenu = new PanelMenu();
	    this.panelTopics = new PanelTopics();
	    this.panelAdd = new PanelAdd();
	    this.panelRemove = new PanelRemove();
	    this.panelGroups = new PanelGroups();
	    this.panelDecks = new PanelDecks();
	    this.panelGame = new PanelGame();
	    this.panelCartas = new PanelCartas();
	    this.panelEditCarta = new PanelEditCarta();

	    this.panelMaster = new PanelMaster();

	    //this.getContentPane().add(this.panelMenu);
	    //this.panelMenu.enter(this);
        this.panelMaster.setIndices(-1, -1, -1, -1);
        this.getContentPane().add(this.panelMaster);
        this.panelMaster.enter(this);



    }

    private void linkButtons() {

		//panelMenu
		this.panelMenu.buttonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				//FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelMenu, MainFrame.this.panelTopics);
                panelMaster.setIndices(-1, -1, -1, -1);
                FuncBox.switchPanel(MainFrame.this, panelMenu, panelMaster);
			}
		});

        //panelTopics
		this.panelTopics.buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.panelAdd.setParameters(
                        -1,
                        -1,
                        -1
                );
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelTopics, MainFrame.this.panelAdd);
            }
        });
		this.panelTopics.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.panelRemove.setParameters(
                        -1,
                        -1,
                        -1
                );
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelTopics, MainFrame.this.panelRemove);
            }
        });

		//panelGroups
        this.panelGroups.buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGroups, MainFrame.this.panelTopics);
            }
        });
        this.panelGroups.buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.panelAdd.setParameters(
                        MainFrame.this.panelGroups.indexT,
                        -1,
                        -1
                );
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGroups, MainFrame.this.panelAdd);

            }
        });
        this.panelGroups.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.panelRemove.setParameters(
                        MainFrame.this.panelGroups.indexT,
                        -1,
                        -1
                );
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGroups, MainFrame.this.panelRemove);
            }
        });

        //panelDecks
        this.panelDecks.buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelDecks, MainFrame.this.panelGroups);
            }
        });
        this.panelDecks.buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.panelAdd.setParameters(
                        MainFrame.this.panelDecks.indexT,
                        MainFrame.this.panelDecks.indexG,
                        -1
                );
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelDecks, MainFrame.this.panelAdd);
            }
        });
        this.panelDecks.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.panelRemove.setParameters(
                        MainFrame.this.panelDecks.indexT,
                        MainFrame.this.panelDecks.indexG,
                        -1
                );
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelDecks, MainFrame.this.panelRemove);
            }
        });

        //panelCartas
        this.panelCartas.buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelCartas, MainFrame.this.panelDecks);
            }
        });
        this.panelCartas.buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.panelAdd.setParameters(
                        MainFrame.this.panelCartas.indexT,
                        MainFrame.this.panelCartas.indexG,
                        MainFrame.this.panelCartas.indexD
                );
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelCartas, MainFrame.this.panelAdd);
            }
        });
        this.panelCartas.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.panelRemove.setParameters(
                        MainFrame.this.panelCartas.indexT,
                        MainFrame.this.panelCartas.indexG,
                        MainFrame.this.panelCartas.indexD
                );
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelCartas, MainFrame.this.panelRemove);
            }
        });

        //panelGame
        this.panelGame.buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelMaster);
            }
        });

		//panelAdd
        this.panelAdd.buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            	MainFrame.this.panelAdd.saveAndExit();
            	//FuncBox.switchFromPanelAdd(MainFrame.this);
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelAdd, MainFrame.this.panelMaster);
            }
        });

        this.panelAdd.buttonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {

			    //FuncBox.switchFromPanelAdd(MainFrame.this);
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelAdd, MainFrame.this.panelMaster);


            }
		});

        //panelRemove
        this.panelRemove.buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                MainFrame.this.panelRemove.saveAndExit();
                //FuncBox.switchFromPanelRemove(MainFrame.this);
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelRemove, MainFrame.this.panelMaster);


            }
        });
        this.panelRemove.buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //FuncBox.switchFromPanelRemove(MainFrame.this);
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelRemove, MainFrame.this.panelMaster);


            }
        });

        //panelEditCarta
        this.panelEditCarta.buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                MainFrame.this.panelEditCarta.saveAndExit();
                //FuncBox.switchFromPanel(MainFrame.this, MainFrame.this.panelEditCarta);
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelEditCarta, MainFrame.this.panelMaster);

            }
        });
        this.panelEditCarta.buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //FuncBox.switchFromPanel(MainFrame.this, MainFrame.this.panelEditCarta);
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelEditCarta, MainFrame.this.panelMaster);

            }
        });

        //panelMaster
        panelMaster.buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(panelMaster.getIndexT() == -1) {

                }
                else if(panelMaster.getIndexG() == -1) {
                    panelMaster.setIndices(-1, -1, -1, -1);
                    FuncBox.switchPanel(MainFrame.this, panelMaster, panelMaster);
                }
                else if(panelMaster.getIndexD() == -1) {
                    panelMaster.setIndices(panelMaster.getIndexT(), -1, -1, -1);
                    FuncBox.switchPanel(MainFrame.this, panelMaster, panelMaster);
                }
                else if(panelMaster.getIndexC() == -1) {
                    panelMaster.setIndices(panelMaster.getIndexT(), panelMaster.getIndexG(), -1, -1);
                    FuncBox.switchPanel(MainFrame.this, panelMaster, panelMaster);
                }
            }
        });

        panelMaster.buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.panelAdd.setParameters(panelMaster.getIndexT(), panelMaster.getIndexG(), panelMaster.getIndexD());
                FuncBox.switchPanel(MainFrame.this, panelMaster, MainFrame.this.panelAdd);
            }
        });

        panelMaster.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.panelRemove.setParameters(panelMaster.getIndexT(), panelMaster.getIndexG(), panelMaster.getIndexD());
                FuncBox.switchPanel(MainFrame.this, panelMaster, MainFrame.this.panelRemove);
            }
        });

	}

	private void linkLabels() {

        //panelGroups
        this.panelGroups.labelToRoot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGroups, MainFrame.this.panelTopics);
            }
        });

        //panelDecks
        this.panelDecks.labelToRoot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelDecks, MainFrame.this.panelTopics);
            }
        });
        this.panelDecks.labelToTopic.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelDecks, MainFrame.this.panelGroups);
            }
        });

        //pangelGame
        this.panelGame.labelToRoot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                //FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelTopics);
                MainFrame.this.panelMaster.setIndices(-1, -1, -1, -1);
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelMaster);
            }
        });
        this.panelGame.labelToTopic.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                //FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelGroups);
                MainFrame.this.panelMaster.setIndices(panelMaster.getIndexT(), -1, -1, -1);
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelMaster);
            }
        });
        this.panelGame.labelToGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                //FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelDecks);
                MainFrame.this.panelMaster.setIndices(panelMaster.getIndexT(), panelMaster.getIndexG(), -1, -1);
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelMaster);
            }
        });

        //panelCartas
        this.panelCartas.labelToRoot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelTopics);
            }
        });
        this.panelCartas.labelToTopic.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelGroups);
            }
        });
        this.panelCartas.labelToGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelDecks);
            }
        });
        this.panelCartas.labelToDeck.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                FuncBox.switchPanel(MainFrame.this, MainFrame.this.panelGame, MainFrame.this.panelCartas);
            }
        });

        this.panelMaster.linkLabels(this);

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
