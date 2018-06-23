package com.fieryslug.linguo;


import com.fieryslug.linguo.util.FuncBox;
import com.fieryslug.linguo.util.alma.Carta;
import com.fieryslug.linguo.util.alma.Deck;
import com.fieryslug.linguo.util.alma.Group;
import com.fieryslug.linguo.util.alma.Topic;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

        ArrayList<Topic> topics =FuncBox.readJsonData();



        FuncBox.writeJsonData(topics);



	}
}
