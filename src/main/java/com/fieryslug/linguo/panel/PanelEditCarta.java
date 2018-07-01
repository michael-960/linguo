package com.fieryslug.linguo.panel;

import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.util.FuncBox;
import com.fieryslug.linguo.util.IWithIndices;
import com.fieryslug.linguo.util.Reference;
import com.fieryslug.linguo.util.alma.Carta;
import com.fieryslug.linguo.util.alma.Topic;
import com.fieryslug.linguo.widget.ButtonSlug;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import java.sql.Ref;
import java.util.ArrayList;

public class PanelEditCarta extends PanelGnome implements IWithIndices {

    private int indexT;
    private int indexG;
    private int indexD;
    private int indexC;

    public JLabel labelDescription;
    public JLabel labelMatch;

    public JTextField fieldDescription;
    public JTextField fieldMatch;
    public JTextArea areaDescription;
    public JTextPane paneDescription;
    public JScrollPane scrollPane;

    public ButtonSlug buttonConfirm;
    public ButtonSlug buttonBack;

    private static final String ACTION_NEXT = "action-next";
    private static final String ACTION_BACK = "action-back";

    public PanelEditCarta() {

        this.initialize();

    }

    private void initialize() {

        this.buttonConfirm = new ButtonSlug();
        this.buttonConfirm.setupIcons(Reference.BUTTON_CONFIRM, Reference.BUTTON_CONFIRM, Reference.BUTTON_CONFIRM);

        this.buttonBack = new ButtonSlug();
        this.buttonBack.setupIcons(Reference.BUTTON_BACK, Reference.BUTTON_BACK, Reference.BUTTON_BACK);

        this.labelDescription = new JLabel("description", SwingConstants.CENTER);
        this.labelDescription.setForeground(Reference.DARK_GREEN);
        this.labelDescription.setFont(Reference.MONOSPACED55);
        this.labelDescription.setPreferredSize(new Dimension(Reference.LABEL_TITLE_WIDTH, Reference.LABEL_TITLE_HEIGHT));

        this.labelMatch = new JLabel("match", SwingConstants.CENTER);
        this.labelMatch.setForeground(Reference.DARK_GREEN);
        this.labelMatch.setFont(Reference.MONOSPACED55);
        this.labelMatch.setPreferredSize(new Dimension(Reference.LABEL_TITLE_WIDTH, Reference.LABEL_TITLE_HEIGHT));

        this.fieldDescription = new JTextField();
        this.fieldDescription.setPreferredSize(new Dimension(Reference.TEXTFIELD_WIDTH, Reference.TEXTFIELD_HEIGHT));
        this.fieldDescription.setFont(Reference.MONOSPACED60);
        this.fieldDescription.setForeground(Reference.WHITE);
        this.fieldDescription.setHorizontalAlignment(SwingConstants.CENTER);
        this.fieldDescription.setOpaque(false);
        this.fieldDescription.setCaretColor(Reference.WHITE);
        this.fieldDescription.setBorder(BorderFactory.createEmptyBorder());
        this.fieldDescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PanelEditCarta.this.fieldMatch.requestFocus();
            }
        });

        this.paneDescription = new JTextPane();
        this.paneDescription.setPreferredSize(new Dimension(1000, 300));
        this.paneDescription.setFont(Reference.MONOSPACED55);
        this.paneDescription.setForeground(Reference.WHITE);
        this.paneDescription.setOpaque(false);
        this.paneDescription.setCaretColor(Reference.WHITE);

        this.scrollPane = new JScrollPane(this.paneDescription, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setPreferredSize(new Dimension(1200, 300));
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.scrollPane.setOpaque(false);
        this.scrollPane.getViewport().setOpaque(false);
        this.scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        this.fieldMatch = new JTextField();
        this.fieldMatch.setPreferredSize(new Dimension(Reference.TEXTFIELD_WIDTH, Reference.TEXTFIELD_HEIGHT));
        this.fieldMatch.setFont(Reference.MONOSPACED60);
        this.fieldMatch.setForeground(Reference.GREEN);
        this.fieldMatch.setHorizontalAlignment(SwingConstants.CENTER);
        this.fieldMatch.setOpaque(false);
        this.fieldMatch.setCaretColor(Reference.WHITE);
        this.fieldMatch.setBorder(BorderFactory.createEmptyBorder());
        this.fieldMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonConfirm.doClick();
            }
        });

        this.panelInterior.add(FuncBox.createBlankLabel(4000, 100));
        this.panelInterior.add(this.labelDescription);
        this.panelInterior.add(this.scrollPane);
        this.panelInterior.add(FuncBox.createBlankLabel(4000, 60));
        this.panelInterior.add(this.labelMatch);
        this.panelInterior.add(this.fieldMatch);

        InputMap inputMap = this.fieldMatch.getInputMap();
        ActionMap actionMap = this.fieldMatch.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("shift ENTER"), Reference.ACTION_NEXT);
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), ACTION_BACK);
        actionMap.put(Reference.ACTION_NEXT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PanelEditCarta.this.paneDescription.requestFocus();
            }
        });
        actionMap.put(ACTION_BACK, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonBack.doClick();
            }
        });

        InputMap inputMap1 = this.paneDescription.getInputMap();
        ActionMap actionMap1 = this.paneDescription.getActionMap();

        inputMap1.put(KeyStroke.getKeyStroke("ENTER"), ACTION_NEXT);
        inputMap1.put(KeyStroke.getKeyStroke("shift ENTER"), "insert-break");
        inputMap1.put(KeyStroke.getKeyStroke("ESCAPE"), ACTION_BACK);
        actionMap1.put(ACTION_NEXT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fieldMatch.requestFocus();
            }
        });
        actionMap1.put(ACTION_BACK, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonBack.doClick();
            }
        });

        this.add(this.buttonBack);
        this.add(this.buttonConfirm);


    }

    public void setParameters(int indexT, int indexG, int indexD, int indexC) {

        this.indexT = indexT;
        this.indexG = indexG;
        this.indexD = indexD;
        this.indexC = indexC;

    }

    public void saveAndExit() {

        ArrayList<Topic> topics = FuncBox.readJsonData();
        Carta carta = topics.get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).cartas.get(this.indexC);

        carta.description = this.paneDescription.getText();
        carta.match = this.fieldMatch.getText();

        FuncBox.writeJsonData(topics);

    }

    @Override
    public void enter(MainFrame frame) {

        Carta carta = FuncBox.readJsonData().get(this.indexT).groups.get(this.indexG).decks.get(this.indexD).cartas.get(this.indexC);

        //this.fieldDescription.requestFocus();

        this.fieldDescription.setText(carta.description);
        this.fieldMatch.setText(carta.match);

        this.paneDescription.setText(carta.description);
        System.out.println(carta.description);

        StyledDocument doc = this.paneDescription.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        this.paneDescription.requestFocus();

    }

    @Override
    public void exit(MainFrame frame) {

        this.fieldDescription.setText("");
        this.paneDescription.setText("");

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
}
