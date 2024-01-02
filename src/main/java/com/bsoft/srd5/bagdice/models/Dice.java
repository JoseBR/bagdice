package com.bsoft.srd5.bagdice.models;

import lombok.Getter;

@Getter
public class Dice {
    int faces;

    public Dice(int faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "D" + faces;
    }

}