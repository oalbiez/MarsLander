package com.althome.landersimulator.input;

import com.althome.landersimulator.physic.ControlsConstraints;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Arnaud on 18/09/2016.
 */
public class DesiredControlsSequence implements Iterator<DesiredControls> {

    private final ArrayList<DesiredControls> sequence;
    private int currentIndex;

    public DesiredControlsSequence() {
        this.currentIndex = -1;
        this.sequence = new ArrayList<>();
    }

    public DesiredControlsSequence(int size) {
        this.currentIndex = -1;
        this.sequence = new ArrayList<>(size);
    }

    public DesiredControlsSequence(int[] thrusterInput, int[] tiltInput) {
        this.currentIndex = -1;
        this.sequence = new ArrayList<>(thrusterInput.length);
        for (int i=0; i<thrusterInput.length; i++) {
            this.sequence.add(new DesiredControls(thrusterInput[i], tiltInput[i]));
        }
    }

    public void addLastDesiredControls(final DesiredControls desiredControls) {
        this.sequence.add(desiredControls);
    }

    public void addFirstDesiredControls(final DesiredControls desiredControls) {
        this.sequence.add(0, desiredControls);
    }

    public void fillWith(int repetitions, final DesiredControls desiredControls) {
        this.reset();
        for (int i=0; i<repetitions; i++) {
            addLastDesiredControls(desiredControls); // same instance
        }
    }

    public void reset() {
        this.sequence.clear();
        this.currentIndex = -1;
    }

    public static DesiredControlsSequence generateRandom(int size, final ControlsConstraints cstr) {
        DesiredControlsSequence seq = new DesiredControlsSequence(size);
        for (int i=0; i<size; i++) {
            seq.addLastDesiredControls(DesiredControls.generateRandom(cstr));
        }
        return seq;
    }

    @Override
    public String toString() {
        StringBuilder seqPrint = new StringBuilder();
        for (int i=0; i<this.sequence.size(); i++) {
            seqPrint.append("(")
                    .append(this.sequence.get(i))
                    .append(") \n");
        }
        return seqPrint.toString();
    }

    @Override
    public boolean hasNext() {
        return currentIndex + 1 < sequence.size();
    }

    @Override
    public DesiredControls next() {
        currentIndex++;
        return this.sequence.get(currentIndex);
    }
}
