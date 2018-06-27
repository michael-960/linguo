package com.fieryslug.linguo.panel;

import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.util.Pair;
import com.fieryslug.linguo.widget.ButtonSlug;
import com.fieryslug.linguo.util.FuncBox;
import com.fieryslug.linguo.util.Reference;
import com.fieryslug.linguo.util.alma.Group;
import com.fieryslug.linguo.util.alma.Topic;
import com.fieryslug.linguo.widget.LabelSlug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PanelGroups extends PanelRoot {

    private ArrayList<Topic> contentTopics;
    public int indexT;

    public JPanel subPanel;
    public JScrollPane scrollPane;
    public Map<JButton, Group> buttonGroupMap;
    public Map<Group, JButton> groupButtonMap;

    public ButtonSlug buttonBack;
    public ButtonSlug buttonAdd;
    public ButtonSlug buttonRemove;

    public JPanel panelSide;
    public LabelSlug labelToRoot;
    public LabelSlug labelToTopic;


    public PanelGroups() {

        super();
        this.initialize();

    }

    private void initialize() {

        this.buttonGroupMap = new HashMap<>();

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

    }

    public void setParameters(ArrayList<Topic> topics, int indexT) {
        this.contentTopics = topics;
        this.indexT = indexT;
    }

    @Override
    public void enter(MainFrame frame) {

        this.removeAll();
        this.buttonGroupMap = new HashMap<>();
        this.groupButtonMap = new HashMap<>();

        this.contentTopics = FuncBox.readJsonData();

        Topic topic = this.contentTopics.get(this.indexT);

        Pair<JScrollPane, Map<Group, JButton>> pair = FuncBox.<Group>initScrollPanel(topic.groups, Reference.MONOSPACED45, new Color(169, 245, 255), Reference.BUTTON_SQ_G, Reference.BUTTON_SQ_GH, Reference.BUTTON_SQ_GP);
        this.scrollPane = pair.first;
        this.groupButtonMap = pair.second;

        /*
        int x = 4;
        int y = topic.groups.size() / 4 + 1;

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

        for(int g = 0; g < topic.groups.size(); ++g) {

            Group group = topic.groups.get(g);

            ButtonSlug temp = new ButtonSlug();
            temp.setText(group.title);
            temp.setForeground(new Color(195, 255, 192));
            temp.setBackground(Color.black);
            temp.setVerticalTextPosition(SwingConstants.CENTER);
            temp.setHorizontalTextPosition(SwingConstants.CENTER);
            temp.setFont(Reference.MONOSPACED45);
            temp.setupIcons(Reference.BUTTON_SQ_G, Reference.BUTTON_SQ_GH, Reference.BUTTON_SQ_GP);

            int currX = g % x;
            int currY = g / x;

            this.subPanel.add(temp, String.valueOf(currX) + ", " + String.valueOf(currY));
            this.buttonGroupMap.put(temp, group);
            this.groupButtonMap.put(group,temp);

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
        this.labelToTopic.setText(topic.name);
        this.panelSide.add(this.labelToTopic);

        this.add(this.panelSide);
        this.add(FuncBox.createBlankLabel(Reference.LABEL_BLANK_WIDTH, Reference.LABEL_BLANK_HEIGHT));
        this.add(this.buttonBack);
        this.add(this.buttonAdd);
        this.add(this.buttonRemove);
    }

    private void linkButtons(MainFrame frame) {

        ArrayList<Group> groups = this.contentTopics.get(this.indexT).groups;

        for(int g = 0; g < groups.size(); ++g) {

            Group group = groups.get(g);
            JButton button = this.groupButtonMap.get(group);

            final int indexG = g;

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    frame.panelDecks.setParameters(PanelGroups.this.contentTopics, PanelGroups.this.indexT, indexG);
                    FuncBox.switchPanel(frame, frame.panelGroups, frame.panelDecks);
                }
            });

        }

    }

    @Override
    public void exit(MainFrame frame) {

        this.labelToRoot.toDefaults();
        this.labelToTopic.toDefaults();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(Reference.BG_GNOME_LARGE, 0, 0, this);
    }
}
