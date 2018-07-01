package com.fieryslug.linguo.panel;


import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.util.FuncBox;
import com.fieryslug.linguo.util.Reference;
import com.fieryslug.linguo.util.alma.Carta;
import com.fieryslug.linguo.util.alma.Deck;
import com.fieryslug.linguo.util.alma.Group;
import com.fieryslug.linguo.util.alma.Topic;

import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Ref;
import java.util.ArrayList;

public class PanelAdd extends PanelGnome {

    public JButton buttonBack;
    public JButton buttonConfirm;

    public JLabel labelTitle;
    public JTextField fieldTopic;

    private JLabel labelMatch;
    private JTextField fieldMatch;

    public ArrayList<Topic> topics;
    public int indexT = -1;
    public int indexG = -1;
    public int indexD = -1;

    private static final String ACTION_SAVE = "action-save";
    private static final String ACTION_BACK = "action-back";



    public PanelAdd() {

        super();
        this.initialize();

    }

    private void initialize() {

        this.labelTitle = new JLabel("Topic:", SwingConstants.CENTER);
        this.labelTitle.setPreferredSize(new Dimension(800, 100));
        this.labelTitle.setFont(Reference.MONOSPACED80);
        this.labelTitle.setForeground(Reference.DARK_GREEN);

        this.fieldTopic = new JTextField();
        this.fieldTopic.setPreferredSize(new Dimension(Reference.TEXTFIELD_WIDTH, Reference.TEXTFIELD_HEIGHT));
        this.fieldTopic.setHorizontalAlignment(SwingConstants.CENTER);
        this.fieldTopic.setFont(Reference.MONOSPACED80);
        this.fieldTopic.setOpaque(false);
        this.fieldTopic.setBorder(BorderFactory.createEmptyBorder());
        this.fieldTopic.setForeground(new Color(230, 255, 236));
        this.fieldTopic.setCaretColor(Reference.WHITE);
        this.fieldTopic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(PanelAdd.this.indexD != -1) {
                    PanelAdd.this.fieldMatch.requestFocus();
                }
                else {
                    buttonConfirm.doClick();
                }
            }
        });

        this.labelMatch = new JLabel("match", SwingConstants.CENTER);
        this.labelMatch.setPreferredSize(new Dimension(Reference.LABEL_TITLE_WIDTH, Reference.LABEL_TITLE_HEIGHT));
        this.labelMatch.setFont(Reference.MONOSPACED80);
        this.labelMatch.setForeground(Reference.DARK_GREEN);

        this.fieldMatch = new JTextField();
        this.fieldMatch.setPreferredSize(new Dimension(Reference.TEXTFIELD_WIDTH, Reference.TEXTFIELD_HEIGHT));
        this.fieldMatch.setHorizontalAlignment(SwingConstants.CENTER);
        this.fieldMatch.setFont(Reference.MONOSPACED80);
        this.fieldMatch.setOpaque(false);
        this.fieldMatch.setBorder(BorderFactory.createEmptyBorder());
        this.fieldMatch.setForeground(Reference.GREEN);
        this.fieldMatch.setCaretColor(Reference.WHITE);
        this.fieldMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonConfirm.doClick();
            }
        });

        this.buttonBack = new JButton();
        this.buttonBack.setIcon(new ImageIcon(Reference.BUTTON_BACK));

        this.buttonBack.setOpaque(false);
        this.buttonBack.setBorderPainted(false);
        this.buttonBack.setFocusPainted(false);
        this.buttonBack.setContentAreaFilled(false);

        this.buttonConfirm = new JButton();
        this.buttonConfirm.setIcon(new ImageIcon(Reference.BUTTON_CONFIRM));

        this.buttonConfirm.setOpaque(false);
        this.buttonConfirm.setBorderPainted(false);
        this.buttonConfirm.setFocusPainted(false);
        this.buttonConfirm.setContentAreaFilled(false);

        this.panelInterior.add(FuncBox.createBlankLabel(4000, 200));
        this.panelInterior.add(this.labelTitle);
        this.panelInterior.add(FuncBox.createBlankLabel(4000, 20));
        this.panelInterior.add(this.fieldTopic);

        this.fieldTopic.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), ACTION_BACK);
        this.fieldTopic.getActionMap().put(ACTION_BACK, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonBack.doClick();
            }
        });

        this.fieldMatch.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), ACTION_BACK);
        this.fieldMatch.getActionMap().put(ACTION_BACK, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonBack.doClick();
            }
        });

        this.add(this.buttonBack);
        this.add(this.buttonConfirm);


    }

    public void saveAndExit() {

        String text = this.fieldTopic.getText();
        ArrayList<Topic> topics = FuncBox.readJsonData();

        if(this.indexT == -1) {

            topics.add(new Topic(text, new ArrayList<>()));

        }
        else if(this.indexG == -1) {

            topics.get(this.indexT).groups.add(new Group(text, new ArrayList<>()));

        }
        else if (this.indexD == -1) {

            topics.get(this.indexT).groups.get(this.indexG).decks.add(new Deck(text, new ArrayList<>()));

        }
        else {

            String text2 = this.fieldMatch.getText();
            topics.get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).cartas.add(new Carta(text, text2));

        }

        FuncBox.writeJsonData(topics);

    }

    public void setParameters(int indexT, int indexG, int indexD) {
        this.indexT = indexT;
        this.indexG = indexG;
        this.indexD = indexD;
    }

    @Override
    public void enter(MainFrame frame) {

        this.fieldTopic.requestFocus();
        this.topics = FuncBox.readJsonData();

        if(this.indexT == -1) {
            this.labelTitle.setText("new topic:");
        }
        else if(this.indexG == -1) {
            this.labelTitle.setText("new group:");
        }
        else if(this.indexD == -1) {
            this.labelTitle.setText("new deck:");
        }
        else {
            this.labelTitle.setText("description:");
            this.panelInterior.add(this.labelMatch);
            this.panelInterior.add(this.fieldMatch);
        }

    }

    @Override
    public void exit(MainFrame frame) {

        this.fieldTopic.setText("");
        this.fieldMatch.setText("");
        this.panelInterior.remove(this.labelMatch);
        this.panelInterior.remove(this.fieldMatch);

    }
}
