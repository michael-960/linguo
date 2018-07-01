package com.fieryslug.linguo.panel;

import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.util.Pair;
import com.fieryslug.linguo.widget.ButtonSlug;
import com.fieryslug.linguo.util.FuncBox;
import com.fieryslug.linguo.util.Reference;
import com.fieryslug.linguo.util.alma.Deck;
import com.fieryslug.linguo.util.alma.Group;
import com.fieryslug.linguo.util.alma.Topic;
import com.fieryslug.linguo.widget.LabelSlug;
import layout.TableLayout;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PanelDecks extends PanelRoot {

    public ArrayList<Topic> contentTopics;
    public int indexT;
    public int indexG;
    private Map<Deck, JButton> deckButtonMap;

    public ButtonSlug buttonAdd;
    public ButtonSlug buttonBack;
    public ButtonSlug buttonRemove;

    public JPanel subPanel;
    private JScrollPane scrollPane;

    public JPanel panelSide;
    public LabelSlug labelToRoot;
    public LabelSlug labelToTopic;
    public LabelSlug labelToGroup;

    public PanelDecks() {

        super();
        this.initialize();

    }

    private void initialize() {

        this.buttonAdd = new ButtonSlug();
        this.buttonAdd.setupIcons(Reference.BUTTON_ADD, Reference.BUTTON_ADD_H, Reference.BUTTON_ADD_P);

        this.buttonBack = new ButtonSlug();
        this.buttonBack.setupIcons(Reference.BUTTON_BACK, Reference.BUTTON_BACK, Reference.BUTTON_BACK);

        this.buttonRemove = new ButtonSlug();
        this.buttonRemove.setupIcons(Reference.BUTTON_REMOVE, Reference.BUTTON_REMOVE_H, Reference.BUTTON_REMOVE_P);

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

    }



    @Override
    public void enter(MainFrame frame) {

        this.removeAll();
        this.deckButtonMap = new HashMap<>();

        this.contentTopics = FuncBox.readJsonData();

        Group group = this.contentTopics.get(this.indexT).groups.get(this.indexG);

        Pair<JScrollPane, Map<Deck, JButton>> pair = FuncBox.initScrollPanel(group.decks, Reference.MONOSPACED45, new Color(236, 188, 255), Reference.BUTTON_SQ_D, Reference.BUTTON_SQ_DH, Reference.BUTTON_SQ_DP);

        this.scrollPane = pair.first;
        this.deckButtonMap = pair.second;

        /*
        int x = 4;
        int y = group.decks.size() / 4 + 1;

        double widthCol[] = new double[x];
        double heightRow[] = new double[y];

        for(int i = 0; i < x; ++i) {
            widthCol[i] = 350;
        }

        for(int j = 0; j < y; ++j) {
            heightRow[j] = 350;
        }

        double size[][] = {widthCol, heightRow};

        this.subPanel = new JPanel(new TableLayout(size));
        this.subPanel.setOpaque(false);
        this.subPanel.setPreferredSize(new Dimension(1400, 350 * y));

        for(int d = 0; d < group.decks.size(); ++d) {

            Deck deck = group.decks.get(d);

            ButtonSlug temp = new ButtonSlug();
            temp.setText(deck.name);
            temp.setForeground(new Color(195, 255, 192));
            temp.setBackground(Color.black);
            temp.setVerticalTextPosition(SwingConstants.CENTER);
            temp.setHorizontalTextPosition(SwingConstants.CENTER);
            temp.setFont(Reference.MONOSPACED45);
            temp.setupIcons(Reference.BUTTON_SQ_D, Reference.BUTTON_SQ_DH, Reference.BUTTON_SQ_DP);

            int currX = d % x;
            int currY = d / x;

            this.subPanel.add(temp, String.valueOf(currX) + ", " + String.valueOf(currY));
            this.deckButtonMap.put(deck, temp);
        }

        this.scrollPane = new JScrollPane(this.subPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(Reference.BG_GNOME_SMALL, 0, 0, this);
            }
        };
        this.scrollPane.setPreferredSize(new Dimension(1400, 800));
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.scrollPane.getViewport().setOpaque(false);
        this.scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
        */
        this.linkButtons(frame);

        this.add(FuncBox.createBlankLabel(Reference.PANEL_SIDE_WIDTH, Reference.PANEL_SIDE_HEIGHT));
        this.add(this.scrollPane);

        this.labelToTopic.setText(this.contentTopics.get(this.indexT).name);
        this.labelToGroup.setText(group.title);

        this.panelSide.add(this.labelToRoot);
        this.panelSide.add(this.labelToTopic);
        this.panelSide.add(this.labelToGroup);
        this.add(this.panelSide);
        this.add(FuncBox.createBlankLabel(4000, 1));
        this.add(this.buttonBack);
        this.add(this.buttonAdd);
        this.add(this.buttonRemove);

    }

    private void linkButtons(MainFrame frame) {

        ArrayList<Deck> decks = this.contentTopics.get(this.indexT).groups.get(this.indexG).decks;

        for(int d = 0; d < decks.size(); ++d) {

            Deck deck = decks.get(d);
            JButton button = this.deckButtonMap.get(deck);
            final int indexD = d;

            JLabel label = new JLabel("", SwingConstants.CENTER);
            button.setLayout(new FlowLayout(FlowLayout.CENTER));
            label.setPreferredSize(new Dimension(100, 150));
            label.setFont(Reference.MONOSPACED55);
            label.setForeground(new Color(151, 210, 207, 168));
            label.setHorizontalTextPosition(SwingConstants.CENTER);
            button.add(label);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    frame.panelGame.setContentDeck(PanelDecks.this.contentTopics, PanelDecks.this.indexT, PanelDecks.this.indexG, indexD);
                    FuncBox.switchPanel(frame, frame.panelDecks, frame.panelGame);

                }
            });

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    if(SwingUtilities.isRightMouseButton(mouseEvent)) {

                        frame.panelCartas.setParameters(PanelDecks.this.indexT, PanelDecks.this.indexG, indexD);
                        FuncBox.switchPanel(frame, frame.panelDecks, frame.panelCartas);

                    }
                }
            });

            button.addMouseWheelListener(new MouseAdapter() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                    int r = mouseWheelEvent.getWheelRotation();
                    JLabel labeltemp = (JLabel)(button.getComponents()[0]);
                    int c = Integer.valueOf(button.getText());
                    if(r == -1) {
                        c += 1;
                    }
                    else {
                        c = Math.max(1, c-1);
                    }
                    labeltemp.setText(String.valueOf(c));

                }
            });

        }

    }

    @Override
    public void exit(MainFrame frame) {

        this.labelToRoot.toDefaults();
        this.labelToTopic.toDefaults();
        this.labelToGroup.toDefaults();

    }

    public void setParameters(ArrayList<Topic> contentTopics, int indexT, int indexG) {

        this.contentTopics = contentTopics;
        this.indexT = indexT;
        this.indexG = indexG;

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(Reference.BG_GNOME_LARGE, 0, 0, this);
    }
}
