package com.fieryslug.linguo.panel;

import com.fieryslug.linguo.MainFrame;
import com.fieryslug.linguo.util.IWithIndices;

import javax.swing.*;

public class PanelMaster extends PanelRoot implements IWithIndices {

    private int indexT;
    private int indexG;
    private int indexD;
    private int indexC;

    private JScrollPane scrollPane;

    public PanelMaster() {
        super();
        this.initialize();
    }

    private void initialize() {

    }

    @Override
    public void enter(MainFrame frame) {

        if(this.indexT == -1) {

        }
        else if(this.indexG == -1) {

        }
        else if(this.indexD == -1) {

        }
        else {

        }



    }

    private void linkButtons() {

    }

    @Override
    public void exit(MainFrame frame) {
        super.exit(frame);
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
