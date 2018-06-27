package com.fieryslug.linguo.panel;

import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.util.FuncBox;
import com.fieryslug.linguo.util.Pair;
import com.fieryslug.linguo.util.Reference;
import com.fieryslug.linguo.util.alma.Carta;
import com.fieryslug.linguo.util.alma.Deck;
import com.fieryslug.linguo.util.alma.Group;
import com.fieryslug.linguo.util.alma.Topic;
import com.fieryslug.linguo.widget.ButtonSlug;
import com.fieryslug.linguo.widget.LabelSlug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.ToDoubleBiFunction;

public class PanelCartas extends PanelGnome {

    public int indexT;
    public int indexG;
    public int indexD;

    public LabelSlug labelToRoot;
    public LabelSlug labelToTopic;
    public LabelSlug labelToGroup;
    public LabelSlug labelToDeck;

    public ButtonSlug buttonBack;
    public ButtonSlug buttonAdd;
    public ButtonSlug buttonRemove;

    private ArrayList<Topic> contentTopics;
    private JScrollPane scrollpane;
    private Map<Carta, JButton> cartaButtonMap;
    private JPanel panelSide;

    public PanelCartas() {

        this.initialize();

    }

    private void initialize() {

        this.panelSide = new JPanel();
        this.panelSide.setPreferredSize(new Dimension(Reference.PANEL_SIDE_WIDTH, Reference.PANEL_SIDE_HEIGHT));
        this.panelSide.setOpaque(false);

        this.buttonAdd = new ButtonSlug();
        this.buttonAdd.setupIcons(Reference.BUTTON_ADD, Reference.BUTTON_ADD_H, Reference.BUTTON_ADD_P);

        this.buttonBack = new ButtonSlug();
        this.buttonBack.setupIcons(Reference.BUTTON_BACK, Reference.BUTTON_BACK, Reference.BUTTON_BACK);

        this.buttonRemove = new ButtonSlug();
        this.buttonRemove.setupIcons(Reference.BUTTON_REMOVE, Reference.BUTTON_REMOVE_H, Reference.BUTTON_REMOVE_P);


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


    }

    public void setParameters(int indexT, int indexG, int indexD) {
        this.indexT = indexT;
        this.indexG = indexG;
        this.indexD = indexD;
    }

    @Override
    public void enter(MainFrame frame) {

        this.removeAll();

        this.contentTopics = FuncBox.readJsonData();

        Pair<JScrollPane, Map<Carta, JButton>> pair = FuncBox.<Carta>initScrollPanel(this.contentTopics.get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).cartas, Reference.MONOSPACED45, Reference.ORANGE, Reference.BUTTON_SQ_C, Reference.BUTTON_SQ_CH, Reference.BUTTON_SQ_CP);
        this.scrollpane = pair.first;
        this.cartaButtonMap = pair.second;

        this.linkButtons(frame);

        this.add(FuncBox.createBlankLabel(250, 500));
        this.add(this.scrollpane);


        Topic topic = this.contentTopics.get(this.indexT);
        Group group = topic.groups.get(this.indexG);
        Deck deck = group.decks.get(this.indexD);

        this.labelToTopic.setText(topic.name);
        this.labelToGroup.setText(group.title);
        this.labelToDeck.setText(deck.name);


        this.panelSide.add(this.labelToRoot);
        this.panelSide.add(this.labelToTopic);
        this.panelSide.add(this.labelToGroup);
        this.panelSide.add(this.labelToDeck);

        this.add(this.panelSide);
        this.add(FuncBox.createBlankLabel(4000, 1));
        this.add(this.buttonBack);
        this.add(this.buttonAdd);
        this.add(this.buttonRemove);


    }

    private void linkButtons(MainFrame frame) {

        ArrayList<Carta> cartas = this.contentTopics.get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).cartas;


        for(int c = 0; c < cartas.size(); ++c) {

            Carta carta = cartas.get(c);
            JButton button = this.cartaButtonMap.get(carta);

            button.setFont(FuncBox.fontForMonospaceText(button.getText(), 225, 20, 70));

            final int indexC = c;

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    frame.panelEditCarta.setParameters(PanelCartas.this.indexT, PanelCartas.this.indexG, PanelCartas.this.indexD, indexC);
                    FuncBox.switchPanel(frame, frame.panelCartas, frame.panelEditCarta);

                }
            });

        }

    }

    @Override
    public void exit(MainFrame frame) {

        this.labelToDeck.toDefaults();
        this.labelToTopic.toDefaults();
        this.labelToGroup.toDefaults();
        this.labelToDeck.toDefaults();

    }
}
