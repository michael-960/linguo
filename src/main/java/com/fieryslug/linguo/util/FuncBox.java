package com.fieryslug.linguo.util;

import com.fieryslug.linguo.panel.PanelRoot;
import com.fieryslug.linguo.util.alma.Carta;
import com.fieryslug.linguo.util.alma.Deck;
import com.fieryslug.linguo.util.alma.Group;
import com.fieryslug.linguo.util.alma.Topic;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.*;

public class FuncBox {

    public static void switchPanel(JFrame jFrame, PanelRoot panel1, PanelRoot panel2) {

        jFrame.getContentPane().removeAll();

        jFrame.getContentPane().add(panel2);

        panel1.revalidate();
        panel1.repaint();
        panel2.revalidate();
        panel2.repaint();

        panel1.exit();
        panel2.enter();

        jFrame.invalidate();
        jFrame.validate();


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





}
