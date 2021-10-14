package com.fieryslug.linguo.util;

import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.panel.PanelRoot;
import com.fieryslug.linguo.util.alma.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.fieryslug.linguo.widget.ButtonSlug;
import layout.TableLayout;
import org.apache.commons.io.FileUtils;
import org.json.*;

public class FuncBox {

    public static void switchPanel(MainFrame jFrame, PanelRoot panel1, PanelRoot panel2) {

        jFrame.getContentPane().removeAll();

        jFrame.getContentPane().add(panel2);

        panel1.revalidate();
        panel1.repaint();
        panel2.revalidate();
        panel2.repaint();

        panel1.exit(jFrame);
        panel2.enter(jFrame);


        jFrame.invalidate();
        jFrame.validate();



    }

    public static void switchFromPanelAdd(MainFrame jFrame) {

        if(jFrame.panelAdd.indexT == -1) {
            FuncBox.switchPanel(jFrame, jFrame.panelAdd, jFrame.panelTopics);
        }
        else if(jFrame.panelAdd.indexG == -1) {
            FuncBox.switchPanel(jFrame, jFrame.panelAdd, jFrame.panelGroups);
        }
        else if(jFrame.panelAdd.indexD == -1) {
            FuncBox.switchPanel(jFrame, jFrame.panelAdd, jFrame.panelDecks);
        }
        else {
            FuncBox.switchPanel(jFrame, jFrame.panelAdd, jFrame.panelCartas);
        }
    }

    public static void switchFromPanelRemove(MainFrame jFrame) {

        if(jFrame.panelRemove.indexT == -1) {
            FuncBox.switchPanel(jFrame, jFrame.panelRemove, jFrame.panelTopics);
        }
        else if(jFrame.panelRemove.indexG == -1) {
            FuncBox.switchPanel(jFrame, jFrame.panelRemove, jFrame.panelGroups);
        }
        else if(jFrame.panelRemove.indexD == -1) {
            FuncBox.switchPanel(jFrame, jFrame.panelRemove, jFrame.panelDecks);
        }
        else {
            FuncBox.switchPanel(jFrame, jFrame.panelRemove, jFrame.panelCartas);
        }

    }

    public static <T extends PanelRoot & IWithIndices> void switchFromPanel(MainFrame jFrame, T panel1) {

        if(panel1.getIndexT() == -1) {
            FuncBox.switchPanel(jFrame, panel1, jFrame.panelTopics);
        }
        else if(panel1.getIndexG() == -1) {
            FuncBox.switchPanel(jFrame, panel1, jFrame.panelGroups);
        }
        else if(panel1.getIndexD() == -1) {
            FuncBox.switchPanel(jFrame, panel1, jFrame.panelDecks);
        }
        else {
            FuncBox.switchPanel(jFrame, panel1, jFrame.panelCartas);
        }

    }

    public static ArrayList<Topic> readJsonData() {

        String data = "";

        try {
            data = FileUtils.readFileToString(new File("data.json"), "UTF-8");
        } catch (IOException e) {
            System.out.println("Error occurred while opening file.");
        }

        JSONArray jarrayTopic = new JSONArray(data);
        ArrayList<Topic> topics = new ArrayList<>();

        for(int i=0; i<jarrayTopic.length(); ++i) {

            Topic topic = new Topic();

            JSONObject jobjectTopic = jarrayTopic.getJSONObject(i);
            topic.name = jobjectTopic.getString("topic");

            JSONArray jarrayGroup = jobjectTopic.getJSONArray("groups");

            for(int j=0; j<jarrayGroup.length(); ++j) {

                Group group = new Group();

                JSONObject jobjectGroup = jarrayGroup.getJSONObject(j);
                group.title = jobjectGroup.getString("title");

                JSONArray jarrayDeck = jobjectGroup.getJSONArray("decks");

                for(int k=0; k<jarrayDeck.length(); ++k) {

                    Deck deck = new Deck();

                    JSONObject jobjectDeck = jarrayDeck.getJSONObject(k);
                    deck.name = jobjectDeck.getString("name");

                    JSONArray jarrayCarta = jobjectDeck.getJSONArray("cartas");

                    for(int l=0; l<jarrayCarta.length(); ++l) {

                        Carta carta = new Carta();

                        JSONObject jobjectCarta = jarrayCarta.getJSONObject(l);
                        carta.description = jobjectCarta.getString("description");
                        carta.match = jobjectCarta.getString("match");

                        deck.cartas.add(carta);
                    }

                    group.decks.add(deck);
                }

                topic.groups.add(group);
            }

            topics.add(topic);
        }

        return topics;

    }

    public static void writeJsonData(ArrayList<Topic> topics) {

        JSONArray jarrayTopic = new JSONArray();

        for(Topic topic : topics) {

            JSONObject jobjectTopic = new JSONObject();
            jobjectTopic.put("topic", topic.name);

            JSONArray jarrayGroup = new JSONArray();
            for(Group group : topic.groups) {

                JSONObject jobjectGroup = new JSONObject();
                jobjectGroup.put("title", group.title);

                JSONArray jarrayDeck = new JSONArray();
                for(Deck deck : group.decks) {

                    JSONObject jobjectDeck = new JSONObject();
                    jobjectDeck.put("name", deck.name);

                    JSONArray jarrayCarta = new JSONArray();
                    for(Carta carta : deck.cartas) {

                        JSONObject jobjectCarta = new JSONObject();

                        jobjectCarta.put("description", carta.description);
                        jobjectCarta.put("match", carta.match);
                        jarrayCarta.put(jobjectCarta);
                    }

                    jobjectDeck.put("cartas", jarrayCarta);
                    jarrayDeck.put(jobjectDeck);
                }

                jobjectGroup.put("decks", jarrayDeck);
                jarrayGroup.put(jobjectGroup);
            }

            jobjectTopic.put("groups", jarrayGroup);
            jarrayTopic.put(jobjectTopic);
        }

        String data = jarrayTopic.toString(4);
        try {
            FileUtils.write(new File("data.json"), data, "UTF-8");
        } catch (IOException e) {

        }
    }

    public static void writeStringToFile(String file, String s) {

        FileWriter fw = null;

        try {
            fw = new FileWriter(new File(file), true);
            fw.write(s);
        } catch (IOException e) {
            System.out.println("Error occurred while opening file.");
        }
        try {
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred while closing file.");
        }

    }

    public static JLabel createBlankLabel(int x, int y) {

        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(x, y));
        return label;

    }

    public static <T extends IWithName> Pair<JScrollPane, Map<T, JButton>> initScrollPanel(ArrayList<T> list, Font font, Color fontColor, Image image, Image imageH, Image imageP, Image background) {

        Map<T, JButton> tButtonMap = new HashMap<>();

        Pair<JScrollPane, Map<Integer, JButton>> pair = initScrollPanelInt(list, font, fontColor, image, imageH, imageP, background);

        for(int i : pair.second.keySet()) {

            tButtonMap.put(list.get(i), pair.second.get(i));

        }

        return new Pair(pair.first, tButtonMap);

    }

    public static <T extends IWithName> Pair<JScrollPane, Map<T, JButton>> initScrollPanel(ArrayList<T> list, Font font, Color fontColor, Image image, Image imageH, Image imageP) {

        return initScrollPanel(list, font, fontColor, image, imageH, imageP, Reference.BG_GNOME_SMALL);

    }

    public static <T extends IWithName> Pair<JScrollPane, Map<Integer, JButton>> initScrollPanelInt(ArrayList<T> list, Font font, Color fontColor, Image image, Image imageH, Image imageP, Image background) {

        Map<Integer, JButton> intButtonMap = new HashMap<>();

        int x = 4;
        int y = list.size() / 4 + 1;

        double widthCol[] = new double[x];
        double heightRow[] = new double[y];

        for (int i = 0; i < x; ++i) {
            widthCol[i] = Reference.BUTTON_SQ_SIZE;
        }

        for (int j = 0; j < y; ++j) {
            heightRow[j] = Reference.BUTTON_SQ_SIZE;
        }

        double size[][] = {widthCol, heightRow};
        JPanel subPanel = new JPanel(new TableLayout(size));
        subPanel.setOpaque(false);
        subPanel.setPreferredSize(new Dimension(Reference.PANEL_INTERIOR_WIDTH, Reference.BUTTON_SQ_SIZE * y));

        for (int i = 0; i < list.size(); ++i) {

            T obj = list.get(i);

            ButtonSlug temp = new ButtonSlug();
            temp.setText(obj.getName());
            temp.setForeground(fontColor);
            temp.setBackground(Color.BLACK);
            temp.setVerticalTextPosition(SwingConstants.CENTER);
            temp.setHorizontalTextPosition(SwingConstants.CENTER);
            temp.setFont(font);
            temp.setupIcons(image, imageH, imageP);

            int currX = i % x;
            int currY = i / x;

            subPanel.add(temp, String.valueOf(currX) + ", " + String.valueOf(currY));
            intButtonMap.put(i, temp);
        }

        JScrollPane scrollPane = new JScrollPane(subPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(background, 0, 0, this);
            }
        };
        scrollPane.setPreferredSize(new Dimension(Reference.PANEL_INTERIOR_WIDTH, Reference.PANEL_INTERIOR_HEIGHT));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        return new Pair<>(scrollPane, intButtonMap);
    }

    public static <T extends IWithName> Pair<JScrollPane, Map<Integer, JButton>> initScrollPanelInt(ArrayList<T> list, Font font, Color fontColor, Image image, Image imageH, Image imageP) {

        return initScrollPanelInt(list, font, fontColor, image, imageH, imageP, Reference.BG_GNOME_SMALL);

    }

    public static <T> T randomItem(ArrayList<T> list, Random random) {

        int len = list.size();
        System.out.println(len);
        int index = random.nextInt(len);
        return list.get(index);

    }

    public static Font fontForMonospaceText(String text, int width, int min, int max) {

        int w = 0;
        int px = 45;
        for(int i=0; i<text.length(); ++i) {

            if(Character.isIdeographic(text.codePointAt(i))) {
                w += Math.round(0.93333D * (double)px);
            }
            else {
                w += Math.round(0.6D * (double)px);
            }
        }

        if(w == 0) {
            return new Font("Monospaced", Font.PLAIN, 60);
        }

        int newpx = (int)Math.round((double)px * (double)width / (double)w);
        newpx = Math.min(newpx, max);
        newpx = Math.max(newpx, min);

        return new Font("Monospaced", Font.PLAIN, newpx);

    }

    public static String htmlWithNewLines(String text, int maxPerLine) {

        StringBuilder str = new StringBuilder();
        int counter = 0;

        str.append("<html><center>");

        for(int i=0; i<text.length(); ++i) {

            if(text.charAt(i) == '\n') {
                str.append("<br>");
                counter = 0;
            }
            else {
                if(counter >= maxPerLine) {
                    str.append("<br>");
                    counter = 0;
                }
                str.append(text.charAt(i));
                counter += 1;

            }

        }

        str.append("</center></html>");

        return str.toString();

    }

    public static String htmlWithNewLines(String text, Font font, int maxWidthPerLine) {

        int px = font.getSize();
        StringBuilder str = new StringBuilder();
        int width = 0;

        str.append("<html><center>");

        for(int i=0; i<text.length(); ++i) {

            if(text.charAt(i) == '\n') {
                str.append("<br>");
                width = 0;
            }
            else {
                if(width >= maxWidthPerLine) {
                    str.append("<br>");
                    width = 0;
                }
                str.append(text.charAt(i));
                width += calcMonospacedWidth(String.valueOf(text.charAt(i)), px);

            }

        }

        str.append("</center></html>");

        return str.toString();



    }

    public static int calcMonospacedWidth(String s, int px) {

        double r = 0;

        for(int i=0; i<s.length(); ++i) {

            if(Character.isIdeographic(s.codePointAt(i))) {
                r += (double)px * 0.93333D;
            }
            else {
                r += (double)px * 0.6D;
            }

        }

        return (int)Math.round(r);

    }
}