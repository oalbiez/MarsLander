package com.althome.landersimulator.input;

import com.althome.landersimulator.physic.ControlsConstraints;

import java.util.ArrayList;

/**
 * Created by Arnaud on 18/09/2016.
 */
public class DesiredControlsSequence {

    private final ArrayList<DesiredControls> sequence;

    private DesiredControlsSequence(ArrayList<DesiredControls> sequence) {
        this.sequence = sequence;
    }

    public DesiredControlsSequence() {
        this.sequence = new ArrayList<>();
    }

    public DesiredControlsSequence(int size) {
        this.sequence = new ArrayList<>(size);
    }

    public DesiredControlsSequence(int[] thrusterInput, int[] tiltInput) {
        this.sequence = new ArrayList<>(thrusterInput.length);
        for (int i=0; i<thrusterInput.length; i++) {
            this.sequence.add(new DesiredControls(thrusterInput[i], tiltInput[i]));
        }
    }

    public void addLastDesiredControls(final DesiredControls desiredControls) {
        this.sequence.add(desiredControls);
    }

    public void fillWith(int repetitions, final DesiredControls desiredControls) {
        this.reset();
        for (int i=0; i<repetitions; i++) {
            addLastDesiredControls(desiredControls); // same instance
        }
    }

    public void reset() {
        this.sequence.clear();
    }

    public ArrayList<DesiredControls> getSequence() {
        return sequence;
    }

    public static DesiredControlsSequence generateRandom(int size, final ControlsConstraints cstr) {
        DesiredControlsSequence seq = new DesiredControlsSequence(size);
        for (int i=0; i<size; i++) {
            seq.addLastDesiredControls(DesiredControls.generateRandom(cstr));
        }
        return seq;
    }

    public DesiredControlsSequence duplicate() {
        ArrayList<DesiredControls> seqClone = new ArrayList<>(this.sequence.size());
        for (int i=0; i<this.sequence.size(); i++) {
            seqClone.add(this.sequence.get(i).duplicate());
        }
        DesiredControlsSequence clone = new DesiredControlsSequence(seqClone);
        return clone;
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

}
