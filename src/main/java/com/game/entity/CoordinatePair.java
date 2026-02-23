package com.game.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CoordinatePair {
    private int x;
    private int y;

    public boolean isWithin(CoordinatePair topLeft, CoordinatePair bottomRight) {
        return this.x >= topLeft.getX() && this.x <= bottomRight.getX()
            && this.y <= topLeft.getY() && this.y >= bottomRight.getY();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
