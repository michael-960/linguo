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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static com.fieryslug.linguo.util.FuncBox.initScrollPanel;

public class PanelRemove extends PanelRoot {

    public ButtonSlug buttonConfirm;
    public ButtonSlug buttonBack;

    public JScrollPane scrollPane;

    private ArrayList<Topic> topics;
    public int indexT = -1;
    public int indexG = -1;
    public int indexD = -1;

    private Set<Integer> toRemove;
    private Map<Integer, JButton> intButtonMap;

    public PanelRemove() {

        this.initialize();

    }

    private void initialize() {

        this.buttonConfirm = new ButtonSlug();
        this.buttonConfirm.setupIcons(Reference.BUTTON_CONFIRM, Reference.BUTTON_CONFIRM, Reference.BUTTON_CONFIRM);

        this.buttonBack = new ButtonSlug();
        this.buttonBack.setupIcons(Reference.BUTTON_BACK, Reference.BUTTON_BACK, Reference.BUTTON_BACK);
    }

    public void setParameters(int indexT, int indexG, int indexD) {

        this.indexT = indexT;
        this.indexG = indexG;
        this.indexD = indexD;

    }

    @Override
    public void enter(MainFrame frame) {

        this.removeAll();

        this.toRemove = new HashSet<>();
        this.topics = FuncBox.readJsonData();

        Pair<JScrollPane, Map<Integer, JButton>> pair = null;

        if(this.indexT == -1) {
            pair = FuncBox.initScrollPanelInt(this.topics, Reference.MONOSPACED45, Color.LIGHT_GRAY, Reference.BUTTON_SQ_X, Reference.BUTTON_SQ_XH, Reference.BUTTON_SQ_XP);
        }
        else if(this.indexG == -1) {
            pair = FuncBox.initScrollPanelInt(this.topics.get(this.indexT).groups, Reference.MONOSPACED45, Color.BLACK, Reference.BUTTON_SQ_X, Reference.BUTTON_SQ_XH, Reference.BUTTON_SQ_XP);
        }
        else if(this.indexD == -1) {
            pair = FuncBox.initScrollPanelInt(this.topics.get(this.indexT).groups.get(this.indexG).decks, Reference.MONOSPACED45, Color.BLACK, Reference.BUTTON_SQ_X, Reference.BUTTON_SQ_XH, Reference.BUTTON_SQ_XP);
        }
        else {
            pair = FuncBox.initScrollPanelInt(this.topics.get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).cartas, Reference.MONOSPACED45, Color.BLACK, Reference.BUTTON_SQ_X, Reference.BUTTON_SQ_XH, Reference.BUTTON_SQ_XP);
        }

        this.scrollPane = pair.first;
        this.intButtonMap = pair.second;

        this.linkButtons();

        this.add(this.scrollPane);
        this.add(FuncBox.createBlankLabel(4000, 1));
        this.add(this.buttonBack);
        this.add(this.buttonConfirm);

    }

    @Override
    public void exit(MainFrame frame) {
        super.exit(frame);
    }

    public void saveAndExit() {

        if(this.indexT == -1) {

            for(Integer i : this.toRemove) {
                this.topics.set(i, null);
            }
            this.topics.removeAll(Collections.singleton(null));

        }
        else if(this.indexG == -1) {

            for(Integer i : this.toRemove) {
                this.topics.get(this.indexT).groups.set(i, null);
            }
            this.topics.get(this.indexT).groups.removeAll(Collections.singleton(null));

        }
        else if(this.indexD == -1) {

            for(Integer i : this.toRemove) {
                this.topics.get(this.indexT).groups.get(this.indexG).decks.set(i, null);
            }
            this.topics.get(this.indexT).groups.get(this.indexG).decks.removeAll(Collections.singleton(null));

        }
        else {

            ArrayList<Carta> cartas = this.topics.get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).cartas;

            for(int i : this.toRemove) {
                cartas.set(i, null);
            }
            cartas.removeAll(Collections.singleton(null));

        }

        FuncBox.writeJsonData(this.topics);

    }

    private void linkButtons() {

        for(int i : this.intButtonMap.keySet()) {

            ButtonSlug button = (ButtonSlug) this.intButtonMap.get(i);

            if(this.indexD != -1) {
                button.setFont(FuncBox.fontForMonospaceText(button.getText(), 225, 20, 60));
            }

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    if(button.imageDefault == Reference.BUTTON_SQ_X) {

                        button.setIcon(new ImageIcon(Reference.BUTTON_SQ_Y));
                        button.imageDefault = Reference.BUTTON_SQ_Y;
                        button.imageHover = Reference.BUTTON_SQ_YH;
                        button.imagePress = Reference.BUTTON_SQ_YP;
                        PanelRemove.this.toRemove.add(i);

                    }
                    else {

                        button.setIcon(new ImageIcon(Reference.BUTTON_SQ_X));
                        button.imageDefault = Reference.BUTTON_SQ_X;
                        button.imageHover = Reference.BUTTON_SQ_XH;
                        button.imagePress = Reference.BUTTON_SQ_XP;
                        PanelRemove.this.toRemove.remove(i);

                    }

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
