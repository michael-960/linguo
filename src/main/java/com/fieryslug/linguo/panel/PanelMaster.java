package com.fieryslug.linguo.panel;

import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.util.*;
import com.fieryslug.linguo.util.alma.*;
import com.fieryslug.linguo.widget.ButtonSlug;
import com.fieryslug.linguo.widget.LabelSlug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

public class PanelMaster extends PanelRoot implements IWithIndices {

    private int indexT;
    private int indexG;
    private int indexD;
    private int indexC;


    private Map<Integer, JButton> intButtonMap;

    private Image IMAGE_T[] = {Reference.BUTTON_SQ_T, Reference.BUTTON_SQ_TH, Reference.BUTTON_SQ_TP};
    private Image IMAGE_G[] = {Reference.BUTTON_SQ_G, Reference.BUTTON_SQ_GH, Reference.BUTTON_SQ_GP};
    private Image IMAGE_D[] = {Reference.BUTTON_SQ_D, Reference.BUTTON_SQ_DH, Reference.BUTTON_SQ_DP};
    private Image IMAGE_C[] = {Reference.BUTTON_SQ_C, Reference.BUTTON_SQ_CH, Reference.BUTTON_SQ_CP};

    private JLabel labelBlank;
    private JScrollPane scrollPane;
    private JPanel panelSide;
    public LabelSlug labelToRoot;
    public LabelSlug labelToTopic;
    public LabelSlug labelToGroup;
    public LabelSlug labelToDeck;

    private JLabel labelBlankLow;

    public ButtonSlug buttonBack;
    public ButtonSlug buttonAdd;
    public ButtonSlug buttonRemove;

    private int selectIndex = -1;

    private static final String ACTION_ADD = "action-add";
    private static final String ACTION_BACK = "action-back";
    private static final String ACTION_SELECT = "action-select";
    private static final String ACTION_REMOVE = "action-remove";

    public PanelMaster() {
        super();
        this.initialize();
    }

    private void initialize() {

        this.labelBlank = FuncBox.createBlankLabel(Reference.PANEL_SIDE_WIDTH, Reference.PANEL_SIDE_HEIGHT);

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

        this.labelBlankLow = FuncBox.createBlankLabel(Reference.LABEL_BLANK_WIDTH, Reference.LABEL_BLANK_HEIGHT);

        this.buttonBack = new ButtonSlug();
        this.buttonBack.setupIcons(Reference.BUTTON_BACK, Reference.BUTTON_BACK, Reference.BUTTON_BACK);

        this.buttonAdd = new ButtonSlug();
        this.buttonAdd.setupIcons(Reference.BUTTON_ADD, Reference.BUTTON_ADD_H, Reference.BUTTON_ADD_P);

        this.buttonRemove = new ButtonSlug();
        this.buttonRemove.setupIcons(Reference.BUTTON_REMOVE, Reference.BUTTON_REMOVE_H, Reference.BUTTON_REMOVE_P);

        InputMap inputMap = this.getInputMap();
        ActionMap actionMap = this.getActionMap();

        System.out.println(inputMap.get(KeyStroke.getKeyStroke('\t')));

        inputMap.put(KeyStroke.getKeyStroke("ENTER"), ACTION_ADD);
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), ACTION_BACK);
        inputMap.put(KeyStroke.getKeyStroke("DELETE"), ACTION_REMOVE);
        actionMap.put(ACTION_ADD, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonAdd.doClick();
            }
        });
        actionMap.put(ACTION_BACK, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonBack.doClick();
            }
        });
        actionMap.put(ACTION_REMOVE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonRemove.doClick();
            }
        });

    }

    @Override
    public void enter(MainFrame frame) {

        this.panelSide.removeAll();
        this.removeAll();

        this.selectIndex = -1;


        ArrayList<Topic> topics = FuncBox.readJsonData();
        int length = 0;
        Font font = Reference.MONOSPACED45;

        Pair<JScrollPane, Map<Integer, JButton>> pair;

        if(this.indexT == -1) {

            length = topics.size();
            pair = FuncBox.<Topic>initScrollPanelInt(topics, font, new Color(190, 255, 183), IMAGE_T[0], IMAGE_T[1], IMAGE_T[2]);

        }
        else if(this.indexG == -1) {

            ArrayList<Group> groups = topics.get(this.indexT).groups;
            length = groups.size();
            pair = FuncBox.<Group>initScrollPanelInt(groups, font, new Color(169, 245, 255), IMAGE_G[0], IMAGE_G[1], IMAGE_G[2]);

        }
        else if(this.indexD == -1) {

            ArrayList<Deck> decks = topics.get(this.indexT).groups.get(this.indexG).decks;
            length = decks.size();
            pair = FuncBox.<Deck>initScrollPanelInt(decks, font, new Color(236, 188, 255), IMAGE_D[0], IMAGE_D[1], IMAGE_D[2]);

        }
        else {

            ArrayList<Carta> cartas = topics.get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).cartas;
            length = cartas.size();
            pair = FuncBox.<Carta>initScrollPanelInt(cartas, font, Reference.ORANGE, IMAGE_C[0], IMAGE_C[1], IMAGE_C[2]);

        }

        this.scrollPane = pair.first;
        this.intButtonMap = pair.second;

        this.linkButtons(frame, topics, length);

        this.add(this.labelBlank);
        this.add(this.scrollPane);

        this.panelSide.add(this.labelToRoot);
        if(this.indexT != -1) {
            this.labelToTopic.setText(topics.get(this.indexT).name);
            this.panelSide.add(this.labelToTopic);
        }
        if(this.indexG != -1) {
            this.labelToGroup.setText(topics.get(this.indexT).groups.get(this.indexG).title);
            this.panelSide.add(this.labelToGroup);
        }
        if(this.indexD != -1) {
            this.labelToDeck.setText(topics.get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).name);
            this.panelSide.add(this.labelToDeck);
        }

        this.add(this.panelSide);

        this.add(this.labelBlankLow);

        if(this.indexT != -1)
            this.add(this.buttonBack);

        this.add(this.buttonAdd);
        this.add(this.buttonRemove);
        this.requestFocus();
    }

    private void linkButtons(MainFrame frame, ArrayList<Topic> topics, int length) {

        for(int i=0; i<length; ++i) {

            System.out.println(i);
            ButtonSlug button = (ButtonSlug)this.intButtonMap.get(i);
            button.setFocusable(false);
            final int index = i;

            button.setFont(FuncBox.fontForMonospaceText(button.getText(), 225, 30, 55));

            if (this.indexT == -1) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        setIndices(index, -1, -1, -1);
                        FuncBox.switchPanel(frame, PanelMaster.this, PanelMaster.this);
                    }
                });
            } else if (this.indexG == -1) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        setIndices(indexT, index, -1, -1);
                        FuncBox.switchPanel(frame, PanelMaster.this, PanelMaster.this);
                    }
                });
            } else if (this.indexD == -1) {
                button.setupScrolling();
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.panelGame.setContentDeck(topics, indexT, indexG, index);
                        Config.req = button.scrollNum;
                        FuncBox.switchPanel(frame, PanelMaster.this, frame.panelGame);
                    }
                });
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                            setIndices(indexT, indexG, index, -1);
                            FuncBox.switchPanel(frame, PanelMaster.this, PanelMaster.this);
                        }
                    }
                });
            } else {
                button.setText(FuncBox.htmlWithNewLines(button.getText(), button.getFont(), 225));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.panelEditCarta.setParameters(indexT, indexG, indexD, index);
                        FuncBox.switchPanel(frame, PanelMaster.this, frame.panelEditCarta);
                    }
                });
            }
        }
    }

    public void linkLabels(MainFrame frame) {

        this.labelToRoot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                setIndices(-1, -1, -1, -1);
                FuncBox.switchPanel(frame, PanelMaster.this, PanelMaster.this);
            }
        });
        this.labelToTopic.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                setIndices(indexT, -1, -1, -1);
                FuncBox.switchPanel(frame, PanelMaster.this, PanelMaster.this);
            }
        });
        this.labelToGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                setIndices(indexT, indexG, -1, -1);
                FuncBox.switchPanel(frame, PanelMaster.this, PanelMaster.this);
            }
        });
        this.labelToDeck.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                setIndices(indexT, indexG, indexD, -1);
                FuncBox.switchPanel(frame, PanelMaster.this, PanelMaster.this);
            }
        });

    }

    @Override
    public void exit(MainFrame frame) {
        super.exit(frame);
    }

    public void setIndices(int indexT, int indexG, int indexD, int indexC) {

        this.indexT = indexT;
        this.indexG = indexG;
        this.indexD = indexD;
        this.indexC = indexC;

    }

    @Override
    public int getIndexT() {
        return this.indexT;
    }

    @Override
    public int getIndexG() {
        return this.indexG;
    }

    @Override
    public int getIndexD() {
        return this.indexD;
    }

    @Override
    public int getIndexC() {
        return this.indexC;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(Reference.BG_GNOME_LARGE, 0, 0, this);
    }
}
