package com.fieryslug.linguo.panel;

import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.util.Config;
import com.fieryslug.linguo.util.alma.Carta;
import com.fieryslug.linguo.widget.ButtonSlug;
import com.fieryslug.linguo.util.FuncBox;
import com.fieryslug.linguo.util.Reference;
import com.fieryslug.linguo.util.alma.Topic;
import com.fieryslug.linguo.widget.LabelSlug;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PanelGame extends PanelGnome {

    private ArrayList<Topic> contentTopics;

    private ArrayList<Carta> copyCartas;
    private Map<Carta, Integer> countCorrect;

    private Carta currCarta;
    private int totalCarta;
    private int progress;
    private int indexT;
    private int indexD;
    private int indexG;
    private Random random;

    public ButtonSlug buttonBack;

    public JPanel panelSide;
    public LabelSlug labelToRoot;
    public LabelSlug labelToTopic;
    public LabelSlug labelToGroup;
    public LabelSlug labelToDeck;

    public JLabel labelCount;
    public JLabel labelDescription;
    public JTextPane paneDescription;
    public JLabel labelCorrection;
    public JScrollPane scrollPane;
    public JTextField fieldMatch;
    private int stage = 0;

    private static final String ACTION_BACK = "action-back";


    public PanelGame() {

        this.initialize();

    }

    private void initialize() {

        this.buttonBack = new ButtonSlug();
        this.buttonBack.setupIcons(Reference.BUTTON_BACK, Reference.BUTTON_BACK, Reference.BUTTON_BACK);

        this.labelCount = new JLabel("", SwingConstants.CENTER);
        this.labelCount.setPreferredSize(new Dimension(1000, 60));
        this.labelCount.setForeground(Reference.ORANGE);
        this.labelCount.setFont(Reference.MONOSPACED45);

        this.labelDescription = new JLabel("", SwingConstants.CENTER);
        this.labelDescription.setPreferredSize(new Dimension(1200, 500));
        this.labelDescription.setHorizontalTextPosition(SwingConstants.CENTER);
        this.labelDescription.setFont(Reference.MONOSPACED80);
        this.labelDescription.setForeground(new Color(144, 216, 200));

        this.labelCorrection = new JLabel("", SwingConstants.CENTER);
        this.labelCorrection.setPreferredSize(new Dimension(1200, 100));
        this.labelCorrection.setHorizontalTextPosition(SwingConstants.CENTER);
        this.labelCorrection.setFont(Reference.MONOSPACED70BOLD);
        this.labelCorrection.setForeground(new Color(170, 122, 21));

        this.fieldMatch = new JTextField();
        this.fieldMatch.setPreferredSize(new Dimension(1000, 100));
        this.fieldMatch.setFont(Reference.MONOSPACED70);
        this.fieldMatch.setOpaque(false);
        this.fieldMatch.setHorizontalAlignment(SwingConstants.CENTER);
        this.fieldMatch.setCaretColor(Color.WHITE);
        this.fieldMatch.setForeground(Color.WHITE);
        this.fieldMatch.setBorder(Reference.BORDER_FIELD);
        this.fieldMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PanelGame.this.next();
            }
        });

        this.panelSide = new JPanel();
        this.panelSide.setPreferredSize(new Dimension(Reference.PANEL_SIDE_WIDTH, Reference.PANEL_SIDE_HEIGHT));
        this.panelSide.setOpaque(false);

        this.labelToRoot = new LabelSlug("[root]");
        this.labelToRoot.setPreferredSize(new Dimension(Reference.LABEL_LINK_WIDTH, Reference.LABEL_LINK_HEIGHT));
        this.labelToRoot.setupInteraction(Reference.MONOSPACED35, Reference.MONOSPACED40, Reference.WHITE, Reference.ORANGE);

        this.labelToTopic = new LabelSlug("");
        this.labelToTopic.setPreferredSize(new Dimension(Reference.LABEL_LINK_WIDTH, Reference.LABEL_LINK_HEIGHT));
        this.labelToTopic.setupInteraction(Reference.MONOSPACED35, Reference.MONOSPACED40, Reference.GREEN, Reference.ORANGE);

        this.labelToGroup = new LabelSlug("");
        this.labelToGroup.setPreferredSize(new Dimension(Reference.LABEL_LINK_WIDTH, Reference.LABEL_LINK_HEIGHT));
        this.labelToGroup.setupInteraction(Reference.MONOSPACED35, Reference.MONOSPACED40, Reference.BLUE, Reference.ORANGE);

        this.labelToDeck = new LabelSlug("");
        this.labelToDeck.setPreferredSize(new Dimension(Reference.LABEL_LINK_WIDTH, Reference.LABEL_LINK_HEIGHT));
        this.labelToDeck.setupInteraction(Reference.MONOSPACED35, Reference.MONOSPACED40, Reference.PINK, Reference.ORANGE);

        InputMap inputMap = this.fieldMatch.getInputMap();
        ActionMap actionMap = this.fieldMatch.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), ACTION_BACK);
        actionMap.put(ACTION_BACK, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonBack.doClick();
            }
        });


    }

    public void setContentDeck(ArrayList<Topic> topics, int indexT, int indexG, int indexD) {

        this.contentTopics = topics;
        this.indexT = indexT;
        this.indexG = indexG;
        this.indexD = indexD;

    }

    @Override
    public void enter(MainFrame frame) {

        this.removeAll();

        this.random = new Random();
        this.copyCartas = FuncBox.readJsonData().get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).cartas;
        this.countCorrect = new HashMap<>();
        for(Carta c : this.copyCartas)
            this.countCorrect.put(c, 0);

        this.totalCarta = this.copyCartas.size();
        this.stage = 1;
        this.progress = 0;



        this.labelDescription.setFont(Reference.MONOSPACED80);
        this.labelDescription.setForeground(Reference.LIGHT_BLUE);

        this.panelInterior.add(this.labelCount);
        this.panelInterior.add(this.labelDescription);
        //this.panelInterior.add(this.paneDescription);
        this.panelInterior.add(this.labelCorrection);
        this.panelInterior.add(this.fieldMatch);


        this.add(FuncBox.createBlankLabel(Reference.PANEL_SIDE_WIDTH, Reference.PANEL_SIDE_HEIGHT));
        this.add(this.panelInterior);

        this.labelToTopic.setText(this.contentTopics.get(this.indexT).name);
        this.labelToGroup.setText(this.contentTopics.get(this.indexT).groups.get(this.indexG).title);
        this.labelToDeck.setText(this.contentTopics.get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).name);

        this.panelSide.add(this.labelToRoot);
        this.panelSide.add(this.labelToTopic);
        this.panelSide.add(this.labelToGroup);
        this.panelSide.add(this.labelToDeck);

        this.add(this.panelSide);
        this.add(FuncBox.createBlankLabel(Reference.LABEL_BLANK_WIDTH, Reference.LABEL_BLANK_HEIGHT));
        this.add(this.buttonBack);

        this.next();
        this.fieldMatch.requestFocus();

    }

    private boolean isDone() {

        if(Config.mode == 0) {
            return this.copyCartas.size() == 0;
        }
        else if(Config.mode == 1) {

            for(Carta c : this.countCorrect.keySet()) {
                if(this.countCorrect.get(c) < Config.req) {
                    return false;
                }
            }
            return true;

        }

        return false;

    }
    private void checkCarta() {
        if(this.stage == 0) {
            this.stage = 1;
            this.fieldMatch.setEditable(false);
            boolean b = this.currCarta.compare(this.fieldMatch.getText());
            int correctCount = this.countCorrect.get(this.currCarta);
            if(Config.mode == 0) {
                System.out.println(this.fieldMatch.getText());

                if(b) {
                    this.fieldMatch.setForeground(Reference.GREEN);

                    if(correctCount < Config.req)
                        this.progress += 1;

                    correctCount += 1;

                    if(correctCount >= Config.req)
                        this.copyCartas.remove(this.currCarta);

                    this.labelCount.setText(this.progress + "/" + this.totalCarta * Config.req);
                }
                else {
                    this.labelCorrection.setText(this.currCarta.match);
                    this.fieldMatch.setForeground(Reference.RED);
                }
                this.fieldMatch.setFont(Reference.MONOSPACED70BOLD);
                this.fieldMatch.setCaretColor(Reference.TRANSPARENT);
            }
            else if(Config.mode == 1) {

                if(b) {

                    this.fieldMatch.setForeground(Reference.GREEN);
                    if(correctCount < Config.req)
                        this.progress += 1;

                    correctCount += 1;
                    this.labelCount.setText(this.progress + "/" + this.totalCarta * Config.req);

                }
                else {
                    this.labelCorrection.setText(this.currCarta.match);
                    this.fieldMatch.setForeground(Reference.RED);
                }
                this.fieldMatch.setFont(Reference.MONOSPACED70BOLD);
                this.fieldMatch.setCaretColor(Reference.TRANSPARENT);

            }
            this.countCorrect.put(this.currCarta, correctCount);
        }
    }

    @Override
    public void exit(MainFrame frame) {

        this.fieldMatch.setText("");

        this.labelToRoot.toDefaults();
        this.labelToTopic.toDefaults();
        this.labelToGroup.toDefaults();
        this.labelToDeck.toDefaults();
    }

    private void next() {

        if(this.stage == 0) {
            /*
            this.stage = 1;
            this.fieldMatch.setEditable(false);
            boolean b = this.currCarta.compare(this.fieldMatch.getText());
            System.out.println(this.fieldMatch.getText());

            if(b) {
                this.progress += 1;
                this.labelCount.setText(this.progress + "/" + this.totalCarta);
                this.fieldMatch.setForeground(Reference.GREEN);
                this.copyCartas.remove(this.currCarta);
            }
            else {
                this.fieldMatch.setForeground(Reference.RED);
            }
            this.fieldMatch.setFont(Reference.MONOSPACED70BOLD);
            this.fieldMatch.setCaretColor(Reference.TRANSPARENT);
            */
            this.checkCarta();


        }
        else if(this.stage == 1) {

            this.labelCorrection.setText("");

            if(!this.isDone()) {

                this.stage = 0;
                this.fieldMatch.setEditable(true);
                this.currCarta = FuncBox.randomItem(this.copyCartas, this.random);
                this.fieldMatch.setFont(Reference.MONOSPACED70);
                this.fieldMatch.setForeground(Reference.WHITE);
                this.labelCount.setText(this.progress + "/" + this.totalCarta * Config.req);
                this.fieldMatch.setCaretColor(Reference.WHITE);
                this.fieldMatch.setText("");


                this.labelDescription.setText(this.currCarta.description);
                String s = FuncBox.htmlWithNewLines(this.currCarta.description, this.labelDescription.getFont(), 1000);
                this.labelDescription.setText(s);
                System.out.println(s);
            }
            else {

                if(this.progress > 0) {

                    this.stage = 2;
                    this.labelDescription.setText("-HECHO-");
                    this.labelDescription.setForeground(Reference.PINK);
                    this.labelDescription.setFont(Reference.MONOSPACED70BOLD);
                    this.paneDescription.setText("HECHO!");

                }
                else {

                    this.stage = -1;
                    this.labelDescription.setText("no hay cartas.");
                    this.paneDescription.setText("No hay cartas.");
                    this.fieldMatch.setEditable(false);

                }
            }
        }
    }
}
