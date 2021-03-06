package com.althome.landersimulator.entities.surface;

import com.althome.landersimulator.entities.shuttle.Position;

import java.util.BitSet;

/**
 * Created by Arnaud on 17/09/2016.
 */
public class BitMatrixSurface implements Surface {

    private final int xLength;
    private final int yLength;
    private final BitSet[] map; // false = free position

    // only one flat zone.
    private int flatZoneXmin;
    private int flatZoneXmax;
    private int flatZoneXmid;
    private int flatZoneY;

    public BitMatrixSurface(int xLength, int yLength) {
        this.xLength = xLength;
        this.yLength = yLength;
        this.map = new BitSet[xLength];
        for (int i=0; i<xLength; i++) {
            this.map[i] = new BitSet(yLength);
        }
    }

    @Override
    public void build(int[] compactedLandX, int[] compactedLandY) {
        buildMap(compactedLandX, compactedLandY);
        findTheFlatZone(compactedLandX, compactedLandY);
    }

    private void buildMap(int[] compactedLandX, int[] compactedLandY) {
        int nbPoints = compactedLandX.length;
        int prevX = compactedLandX[0];
        int prevY = compactedLandY[0];
        int curX;
        int curY;
        double delta;
        for (int pti=1; pti<nbPoints; pti++) {
            curX = compactedLandX[pti];
            curY = compactedLandY[pti];
            delta = ((curY - prevY) / (double) ( curX - prevX ));
            int k=1;
            for (int j=prevX; j<curX; j++) {
                if ( curY != prevY ) {
                    this.map[j].set(0, prevY + (int) (k * delta) + 1, true);
                } else {
                    this.map[j].set(0, curY + 1, true);
                }
                k++;
            }
            prevX = compactedLandX[pti];
            prevY = compactedLandY[pti];
        }
    }

    private void findTheFlatZone(int[] compactedLandX, int[] compactedLandY) {
        // Find the flat zone
        int prevY = -1;
        for (int i=0; i < compactedLandY.length; i++) {
            if (  compactedLandY[i] == prevY ) {
                this.flatZoneXmin = compactedLandX[i-1];
                this.flatZoneXmax = compactedLandX[i];
                this.flatZoneY = compactedLandY[i];
                this.flatZoneXmid = ( this.flatZoneXmin + this.flatZoneXmax ) / 2;
                break;
            }
            prevY = compactedLandY[i];
        }

    }

    private boolean isOutOfRange(final Position position) {
        return ( position.x >= xLength || position.x < 0 || position.y >= yLength || position.y < 0 );
    }

    @Override
    public boolean isAFreePosition(final Position position) {
        return ! (isOutOfRange(position)) && ! (this.map[(int)position.x].get((int)position.y));
    }

    @Override
    public boolean isOnTheLandingZone(final Position position) {
        return position.x > this.flatZoneXmin && position.x < this.flatZoneXmax;
    }

    @Override
    public int horizontalDistanceFromMiddleOfTheLandingZone(final Position position) {
        return (int) Math.abs(this.flatZoneXmid - position.x);
    }

    @Override
    public int verticalDistanceFromTheLandingZone(Position position) {
        return (int) Math.abs(this.flatZoneY - position.y);
    }

    @Override
    public String toString() {
        StringBuilder dots = new StringBuilder();
        for (int y=yLength-1; y>-1; y-=150) {
            for (int x=0; x<xLength; x+=100) {
                if ( this.map[x].get(y) ) dots.append('×');
                else dots.append('.');
            }
            dots.append('\n');
        }
        return dots.toString();
    }

}
