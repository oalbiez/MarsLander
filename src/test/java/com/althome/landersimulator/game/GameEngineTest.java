package com.althome.landersimulator.game;

import com.althome.landersimulator.entities.*;
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

    private ControlPanel buildControlZero() {
        return new ControlPanel(0,0);
    }

    private ControlPanel buildControl_3_25() {
        return new ControlPanel(3, 25);
    }

    private ControlPanel buildControlMax() {
        return new ControlPanel(4, 90);
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
        final ControlPanel ctrl = buildControlZero();
        final Shuttle ship1 = buildInitialShip_01();
        final Shuttle ship2 = engine.computeNextState(ground, ship1, ctrl);
        final Shuttle ship3 = engine.computeNextState(ground, ship2, ctrl);
        final Shuttle ship4 = engine.computeNextState(ground, ship3, ctrl);
        final Shuttle ship5 = engine.computeNextState(ground, ship4, ctrl);

        assertEquals(ship5.position.x, 2500, PRECISION);
        assertEquals(ship5.position.y, 2670, PRECISION);

        assertEquals(ship5.speed.hSpeed, 0, PRECISION);
        assertEquals(ship5.speed.vSpeed, -15, PRECISION);

        System.out.print(ground);
    }

    @Test
    public void computeNextState_fixedThrustAndTilt_01() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface_01();
        final ControlPanel ctrl = buildControl_3_25();
        final Shuttle ship1 = buildInitialShip_01();
        final Shuttle ship2 = engine.computeNextState(ground, ship1, ctrl);
        final Shuttle ship3 = engine.computeNextState(ground, ship2, ctrl);
        final Shuttle ship4 = engine.computeNextState(ground, ship3, ctrl);
        final Shuttle ship5 = engine.computeNextState(ground, ship4, ctrl);

        assertEquals(ship5.position.x, 2494, PRECISION);
        assertEquals(ship5.position.y, 2684, PRECISION);

        assertEquals(ship5.speed.hSpeed, -4, PRECISION);
        assertEquals(ship5.speed.vSpeed, -7, PRECISION);
    }

    @Test
    public void computeNextState_freeFall_02() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface_02();
        final ControlPanel ctrl = buildControlZero();
        final Shuttle ship1 = buildInitialShip_02();
        final Shuttle ship2 = engine.computeNextState(ground, ship1, ctrl);
        final Shuttle ship3 = engine.computeNextState(ground, ship2, ctrl);
        final Shuttle ship4 = engine.computeNextState(ground, ship3, ctrl);
        final Shuttle ship5 = engine.computeNextState(ground, ship4, ctrl);

        assertEquals(ship5.position.x, 6100, PRECISION);
        assertEquals(ship5.position.y, 2770, PRECISION);

        assertEquals(ship5.speed.hSpeed, -100, PRECISION);
        assertEquals(ship5.speed.vSpeed, -15, PRECISION);
        System.out.print(ground);
    }

    @Test
    public void computeNextState_fixedThrustAndTilt_02() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface_02();
        final ControlPanel ctrl = buildControl_3_25();
        final Shuttle ship1 = buildInitialShip_02();
        final Shuttle ship2 = engine.computeNextState(ground, ship1, ctrl);
        final Shuttle ship3 = engine.computeNextState(ground, ship2, ctrl);
        final Shuttle ship4 = engine.computeNextState(ground, ship3, ctrl);
        final Shuttle ship5 = engine.computeNextState(ground, ship4, ctrl);

        assertEquals(ship5.position.x, 6088, PRECISION);
        assertEquals(ship5.position.y, 2778, PRECISION);

        assertEquals(ship5.speed.hSpeed, -106, PRECISION);
        assertEquals(ship5.speed.vSpeed, -9, PRECISION);
    }

    @Test
    public void computeNextState_maxThrustAndTilt_02() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface_02();
        final ControlPanel ctrl = buildControlMax();
        final Shuttle ship1 = buildInitialShip_02();
        final Shuttle ship2 = engine.computeNextState(ground, ship1, ctrl);
        final Shuttle ship3 = engine.computeNextState(ground, ship2, ctrl);
        final Shuttle ship4 = engine.computeNextState(ground, ship3, ctrl);
        final Shuttle ship5 = engine.computeNextState(ground, ship4, ctrl);

        assertEquals(ship5.position.x, 6085, PRECISION);
        assertEquals(ship5.position.y, 2770, PRECISION);

        assertEquals(ship5.speed.hSpeed, -110, PRECISION);
        assertEquals(ship5.speed.vSpeed, -15, PRECISION);

        assertEquals(ship5.fuel.remaining, 590);
    }


}