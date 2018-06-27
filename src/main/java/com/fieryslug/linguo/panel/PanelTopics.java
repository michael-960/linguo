package com.fieryslug.linguo.panel;

import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.util.Pair;
import com.fieryslug.linguo.widget.ButtonSlug;
import com.fieryslug.linguo.util.FuncBox;
import com.fieryslug.linguo.util.Reference;
import com.fieryslug.linguo.util.alma.Topic;
import com.fieryslug.linguo.widget.LabelSlug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PanelTopics extends PanelRoot {

    public ArrayList<Topic> contentTopics;

    public JScrollPane scrollPane;
    public JLabel labelTitle;
    public JPanel subPanel;
    public JPanel panelInterior;
    public ButtonSlug buttonAdd;
    public ButtonSlug buttonRemove;

    public JPanel panelSide;
    public LabelSlug labelToRoot;

    private JLabel labelBlank0;


    public Map<JButton, Topic> buttonTopicMap;
    public Map<Topic, JButton> topicButtonMap;

    public PanelTopics() {

        super();
        this.initialize();
    }

    private void initialize() {

        this.labelTitle = new JLabel("Topics", SwingConstants.CENTER);
        this.labelTitle.setPreferredSize(new Dimension(Reference.LABEL_TITLE_WIDTH, Reference.LABEL_TITLE_HEIGHT));
        this.labelTitle.setFont(Reference.MONOSPACED70);
        this.labelTitle.setForeground(Reference.GREEN);

        this.buttonAdd = new ButtonSlug();
        this.buttonAdd.setupIcons(Reference.BUTTON_ADD, Reference.BUTTON_ADD_H, Reference.BUTTON_ADD_P);

        this.buttonRemove = new ButtonSlug();
        this.buttonRemove.setupIcons(Reference.BUTTON_REMOVE, Reference.BUTTON_REMOVE_H, Reference.BUTTON_REMOVE_P);

        this.panelSide = new JPanel();
        this.panelSide.setPreferredSize(new Dimension(Reference.PANEL_SIDE_WIDTH, Reference.PANEL_SIDE_HEIGHT));
        this.panelSide.setOpaque(false);

        this.labelToRoot = new LabelSlug("[root]");
        this.labelToRoot.setPreferredSize(new Dimension(Reference.LABEL_LINK_WIDTH, Reference.LABEL_LINK_HEIGHT));
        this.labelToRoot.setupInteraction(Reference.MONOSPACED35, Reference.MONOSPACED40, Reference.WHITE, Reference.ORANGE);


    }

    @Override
    public void enter(MainFrame frame) {

        this.removeAll();
        this.buttonTopicMap = new HashMap<>();
        this.topicButtonMap = new HashMap<>();

        this.contentTopics = FuncBox.readJsonData();

        Pair<JScrollPane, Map<Topic, JButton>> pair = FuncBox.<Topic>initScrollPanel(this.contentTopics, Reference.MONOSPACED45, new Color(190, 255, 183), Reference.BUTTON_SQ_T, Reference.BUTTON_SQ_TH, Reference.BUTTON_SQ_TP);
        this.topicButtonMap = pair.second;
        this.scrollPane = pair.first;

        /*
        int x = 4;
        int y = contentTopics.size() / 4 + 1;

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



        for(int t = 0; t < this.contentTopics.size(); ++t) {

            Topic topic = this.contentTopics.get(t);

            ButtonSlug temp = new ButtonSlug();
            temp.setText(topic.name);
            temp.setForeground(new Color(195, 255, 192));
            temp.setBackground(Color.black);
            temp.setVerticalTextPosition(SwingConstants.CENTER);
            temp.setHorizontalTextPosition(SwingConstants.CENTER);
            temp.setFont(Reference.MONOSPACED45);
            temp.setupIcons(Reference.BUTTON_SQ_T, Reference.BUTTON_SQ_TH, Reference.BUTTON_SQ_TP);

            int currX, currY;
            currX = t % x;
            currY = t / x;

            this.subPanel.add(temp, String.valueOf(currX) + ", " + String.valueOf(currY));
            this.buttonTopicMap.put(temp, topic);
            this.topicButtonMap.put(topic, temp);

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

        this.panelSide.add(this.labelToRoot);

        this.add(this.panelSide);
        this.add(FuncBox.createBlankLabel(4000, 1));
        this.add(this.buttonAdd);
        this.add(this.buttonRemove);

    }

    @Override
    public void exit(MainFrame frame) {

        this.labelToRoot.toDefaults();

    }

    public void linkButtons(MainFrame frame) {

        for(int t=0; t<this.contentTopics.size(); ++t) {

            Topic topic = this.contentTopics.get(t);
            JButton button = this.topicButtonMap.get(topic);
            final int index = t;


            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    frame.panelGroups.setParameters(PanelTopics.this.contentTopics, index);
                    FuncBox.switchPanel(frame, frame.panelTopics, frame.panelGroups);

                }
            });


        }

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(Reference.BG_GNOME_LARGE, 0, 0, this);
    }
}
