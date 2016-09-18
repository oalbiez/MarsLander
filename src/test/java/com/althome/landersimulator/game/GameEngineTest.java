package com.althome.landersimulator.game;

import com.althome.landersimulator.entities.shuttle.*;
import com.althome.landersimulator.entities.surface.BitMatrixSurface;
import com.althome.landersimulator.entities.surface.Surface;
import com.althome.landersimulator.input.DesiredControls;
import com.althome.landersimulator.input.DesiredControlsSequence;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Created by Arnaud on 13/09/2016.
 */
public class GameEngineTest {

    private final double PRECISION = 0.5;

    private GameEngine buildStandardGameEngine() {
        return new GameEngine();
    }

    private DesiredControls buildControlZero() {
        return new DesiredControls(0,0);
    }

    private DesiredControls buildControl_3_25() {
        return new DesiredControls(3, 25);
    }

    private DesiredControls buildControlMax() {
        return new DesiredControls(4, 90);
    }

    private DesiredControlsSequence buildVariableSequence() {
        return new DesiredControlsSequence(
                new int[]{0, 4, 4, 3, 4, 3, 2, 3, 4, 4},
                new int[]{-15, 45, 30, 15, 30, 15, 30, 45, 30, 15}
        );
    }

    private Shuttle buildInitialShip_01() {
        ControlPanel control = new ControlPanel(0,0);
        FuelTank remainingFuel = new FuelTank(550);
        Position position = new Position(2500,2700);
        Speed speed = new Speed(0,0);
        Status status = Status.FLYING;
        return new Shuttle(position,speed,control,remainingFuel,status);
    }

    private Shuttle buildInitialShip_02() {
        ControlPanel control = new ControlPanel(0,90);
        FuelTank remainingFuel = new FuelTank(600);
        Position position = new Position(6500,2800);
        Speed speed = new Speed(-100,0);
        Status status = Status.FLYING;
        return new Shuttle(position,speed,control,remainingFuel,status);
    }

    private Surface buildSurface_01() {
        Surface s = new BitMatrixSurface(7000,3000);
        s.build(new int[]{0, 1000, 1500, 3000, 4000, 5500, 6999},
                new int[]{100, 500, 1500, 1000, 150, 150, 800});
        return s;
    }

    private Surface buildSurface_02() {
        Surface s = new BitMatrixSurface(7000,3000);
        s.build(new int[]{0, 1000, 1500, 3000, 3500, 3700, 5000, 5800, 6000, 6999},
                new int[]{100, 500, 100, 100, 500, 200, 1500, 300, 1000, 2000});
        return s;
    }

    @Test
    public void computeNextState_freeFall_01() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface_01();
        final Shuttle ship1 = buildInitialShip_01();

        final DesiredControls ctrl = buildControlZero();
        final DesiredControlsSequence seq = new DesiredControlsSequence();

        seq.fillWith(4, ctrl);

        Shuttle ship5 = engine.computeFinalState(ground, ship1, seq);

        assertEquals(2500, ship5.position.x, PRECISION);
        assertEquals(2670, ship5.position.y, PRECISION);

        assertEquals(0, ship5.speed.hSpeed, PRECISION);
        assertEquals(-15, ship5.speed.vSpeed, PRECISION);
    }

    @Test
    public void computeNextState_fixedThrustAndTilt_01() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface_01();
        final Shuttle ship1 = buildInitialShip_01();

        final DesiredControls ctrl = buildControl_3_25();
        final DesiredControlsSequence seq = new DesiredControlsSequence();

        seq.fillWith(4, ctrl);

        Shuttle ship5 = engine.computeFinalState(ground, ship1, seq);

        assertEquals(ship5.position.x, 2494, PRECISION);
        assertEquals(ship5.position.y, 2684, PRECISION);

        assertEquals(ship5.speed.hSpeed, -4, PRECISION);
        assertEquals(ship5.speed.vSpeed, -7, PRECISION);
    }

    @Test
    public void computeNextState_freeFall_02() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface_02();
        final Shuttle ship1 = buildInitialShip_02();

        final DesiredControls ctrl = buildControlZero();
        final DesiredControlsSequence seq = new DesiredControlsSequence();

        seq.fillWith(4, ctrl);

        Shuttle ship5 = engine.computeFinalState(ground, ship1, seq);

        assertEquals(ship5.position.x, 6100, PRECISION);
        assertEquals(ship5.position.y, 2770, PRECISION);

        assertEquals(ship5.speed.hSpeed, -100, PRECISION);
        assertEquals(ship5.speed.vSpeed, -15, PRECISION);

    }

    @Test
    public void computeNextState_fixedThrustAndTilt_02() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface_02();
        final Shuttle ship1 = buildInitialShip_02();

        final DesiredControls ctrl = buildControl_3_25();
        final DesiredControlsSequence seq = new DesiredControlsSequence();

        seq.fillWith(4, ctrl);

        Shuttle ship5 = engine.computeFinalState(ground, ship1, seq);

        assertEquals(ship5.position.x, 6088, PRECISION);
        assertEquals(ship5.position.y, 2778, PRECISION);

        assertEquals(ship5.speed.hSpeed, -106, PRECISION);
        assertEquals(ship5.speed.vSpeed, -9, PRECISION);
    }

    @Test
    public void computeNextState_maxThrustAndTilt_02() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface_02();
        final Shuttle ship1 = buildInitialShip_02();

        final DesiredControls ctrl = buildControlMax();
        final DesiredControlsSequence seq = new DesiredControlsSequence();

        seq.fillWith(4, ctrl);

        Shuttle ship5 = engine.computeFinalState(ground, ship1, seq);

        assertEquals(ship5.position.x, 6085, PRECISION);
        assertEquals(ship5.position.y, 2770, PRECISION);

        assertEquals(ship5.speed.hSpeed, -110, PRECISION);
        assertEquals(ship5.speed.vSpeed, -15, PRECISION);

        assertEquals(ship5.fuel.remaining, 590);
    }

    @Test
    public void computeNextState_variableSequence_01() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface_01();
        final Shuttle ship1 = buildInitialShip_01();

        final DesiredControlsSequence seq = buildVariableSequence();

        Shuttle ship11 = engine.computeFinalState(ground, ship1, seq);

        System.out.println(ship11);

        assertEquals(ship11.position.x, 2464, PRECISION);
        assertEquals(ship11.position.y, 2607, PRECISION);

        assertEquals(ship11.speed.hSpeed, -10, PRECISION);
        assertEquals(ship11.speed.vSpeed, -14, PRECISION);

        assertEquals(ship11.fuel.remaining, 524);
    }

}