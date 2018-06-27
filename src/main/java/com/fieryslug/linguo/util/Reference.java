package com.fieryslug.linguo.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Reference {


    public static final int PANEL_INTERIOR_WIDTH = 1400;
    public static final int PANEL_INTERIOR_HEIGHT = 800;
    public static final int LABEL_TITLE_WIDTH = 1200;
    public static final int LABEL_TITLE_HEIGHT = 90;
    public static final int BUTTON_SQ_SIZE = 350;
    public static final int BUTTON_SQ_IMG_SIZE = 180;
    public static final int TEXTFIELD_WIDTH = 1000;
    public static final int TEXTFIELD_HEIGHT = 100;
    public static final int LABEL_BLANK_WIDTH = 4000;
    public static final int LABEL_BLANK_HEIGHT = 1;
    public static final int LABEL_LINK_WIDTH = 250;
    public static final int LABEL_LINK_HEIGHT = 50;
    public static final int PANEL_SIDE_WIDTH = 250;
    public static final int PANEL_SIDE_HEIGHT = 500;

    public static final String ACTION_CONFIRM = "action-confirm";
    public static final String ACTION_NEXT = "action-next";

    public static final Font MONOSPACED45 = new Font("Monospaced", Font.PLAIN, 45);
    public static final Font MONOSPACED60 = new Font("Monospaced", Font.PLAIN, 60);
    public static final Font MONOSPACED70 = new Font("Monospaced", Font.PLAIN, 70);
    public static final Font MONOSPACED70BOLD = new Font("Monospaced", Font.BOLD, 70);
    public static final Font MONOSPACED80 = new Font("Monospaced", Font.PLAIN, 80);
    public static final Font MONOSPACED35 = new Font("Monospaced", Font.PLAIN, 35);
    public static final Font MONOSPACED40 = new Font("Monospaced", Font.PLAIN, 40);
    public static final Font MONOSPACED55 = new Font("Monospaced", Font.PLAIN, 55);

    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color ORANGE = new Color(255, 197, 98);
    public static final Color BLUE = new Color(55, 106, 239);
    public static final Color GREEN = new Color(109, 255, 121);
    public static final Color PINK = new Color(226, 37, 195);
    public static final Color RED = new Color(227, 81, 83);
    public static final Color DARK_GREEN = new Color(43, 127, 94);

    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);


    //public static final Font CUSTOM = Font.createFont();

    public static Image WALLPAPER;
    public static Image BG_GNOME_SMALL;
    public static Image BG_GNOME_SMALL_TOPICS;
    public static Image BG_GNOME_LARGE;

    public static Image BUTTON_START;

    public static Image BUTTON_SQ_T;
    public static Image BUTTON_SQ_TH;
    public static Image BUTTON_SQ_TP;

    public static Image BUTTON_SQ_G;
    public static Image BUTTON_SQ_GH;
    public static Image BUTTON_SQ_GP;

    public static Image BUTTON_SQ_D;
    public static Image BUTTON_SQ_DH;
    public static Image BUTTON_SQ_DP;

    public static Image BUTTON_SQ_C;
    public static Image BUTTON_SQ_CH;
    public static Image BUTTON_SQ_CP;

    public static Image BUTTON_SQ_X;
    public static Image BUTTON_SQ_XH;
    public static Image BUTTON_SQ_XP;

    public static Image BUTTON_SQ_Y;
    public static Image BUTTON_SQ_YH;
    public static Image BUTTON_SQ_YP;

    public static Image BUTTON_ADD;
    public static Image BUTTON_ADD_H;
    public static Image BUTTON_ADD_P;

    public static Image BUTTON_REMOVE;
    public static Image BUTTON_REMOVE_H;
    public static Image BUTTON_REMOVE_P;

    public static Image BUTTON_BACK;
    public static Image BUTTON_CONFIRM;

    public static Image BUTTON_DIR_ROOT;

    static {
        try {

            WALLPAPER = ImageIO.read(Reference.class.getResource("/res/background/wallpaper.jpg"));
            BG_GNOME_SMALL = ImageIO.read(Reference.class.getResource("/res/background/background_gnome.jpg"));
            BG_GNOME_SMALL_TOPICS = ImageIO.read(Reference.class.getResource("/res/background/background_gnome_topics.jpg"));
            BG_GNOME_LARGE = ImageIO.read(Reference.class.getResource("/res/background/background_gnome_bright.jpg"));

            BUTTON_START = ImageIO.read(Reference.class.getResource("/res/button_start/button1.png"));

            BUTTON_SQ_T = ImageIO.read(Reference.class.getResource("/res/button_sq/t/button0.png"));
            BUTTON_SQ_TH = ImageIO.read(Reference.class.getResource("/res/button_sq/t/button0_hover.png"));
            BUTTON_SQ_TP = ImageIO.read(Reference.class.getResource("/res/button_sq/t/button0_press.png"));

            BUTTON_SQ_G = ImageIO.read(Reference.class.getResource("/res/button_sq/g/button0.png"));
            BUTTON_SQ_GH = ImageIO.read(Reference.class.getResource("/res/button_sq/g/button0_hover.png"));
            BUTTON_SQ_GP = ImageIO.read(Reference.class.getResource("/res/button_sq/g/button0_press.png"));

            BUTTON_SQ_D = ImageIO.read(Reference.class.getResource("/res/button_sq/d/button0.png"));
            BUTTON_SQ_DH = ImageIO.read(Reference.class.getResource("/res/button_sq/d/button0_hover.png"));
            BUTTON_SQ_DP = ImageIO.read(Reference.class.getResource("/res/button_sq/d/button0_press.png"));

            BUTTON_SQ_C = ImageIO.read(Reference.class.getResource("/res/button_sq/c/button0.png"));
            BUTTON_SQ_CH = ImageIO.read(Reference.class.getResource("/res/button_sq/c/button0_hover.png"));
            BUTTON_SQ_CP = ImageIO.read(Reference.class.getResource("/res/button_sq/c/button0_press.png"));

            BUTTON_SQ_X = ImageIO.read(Reference.class.getResource("/res/button_sq/gray/button0.png"));
            BUTTON_SQ_XH = ImageIO.read(Reference.class.getResource("/res/button_sq/gray/button0_hover.png"));
            BUTTON_SQ_XP = ImageIO.read(Reference.class.getResource("/res/button_sq/gray/button0_press.png"));

            BUTTON_SQ_Y = ImageIO.read(Reference.class.getResource("/res/button_sq/red/button0.png"));
            BUTTON_SQ_YH = ImageIO.read(Reference.class.getResource("/res/button_sq/red/button0_hover.png"));
            BUTTON_SQ_YP = ImageIO.read(Reference.class.getResource("/res/button_sq/red/button0_press.png"));


            BUTTON_ADD = ImageIO.read(Reference.class.getResource("/res/button_add/buttonadd.png"));
            BUTTON_ADD_H = ImageIO.read(Reference.class.getResource("/res/button_add/buttonadd_hover.png"));
            BUTTON_ADD_P = ImageIO.read(Reference.class.getResource("/res/button_add/buttonadd_press.png"));

            BUTTON_REMOVE = ImageIO.read(Reference.class.getResource("/res/button_remove/buttonremove.png"));
            BUTTON_REMOVE_H = ImageIO.read(Reference.class.getResource("/res/button_remove/buttonremove_hover.png"));
            BUTTON_REMOVE_P = ImageIO.read(Reference.class.getResource("/res/button_remove/buttonremove_press.png"));

            BUTTON_BACK = ImageIO.read(Reference.class.getResource("/res/button_back/buttonback.png"));
            BUTTON_CONFIRM  = ImageIO.read(Reference.class.getResource("/res/button_confirm/buttonconfirm.png"));

            BUTTON_DIR_ROOT = ImageIO.read(Reference.class.getResource("/res/button_dir/buttondir_root.png"));

        } catch (IOException e) {
            System.out.println("Error occurred while loading images");
        }
    }

}
