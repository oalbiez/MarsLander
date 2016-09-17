package com.althome.landersimulator.game;

import com.althome.landersimulator.entities.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Created by Arnaud on 13/09/2016.
 */
public class GameEngineTest {

    private GameEngine buildStandardGameEngine() {
        return new GameEngine();
    }

    private ControlPanel buildControlZero() {
        return new ControlPanel(0,0);
    }

    private ControlPanel buildControl() {
        return new ControlPanel(3, 25);
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

    private Surface buildSurface() {
        return new Surface();
    }

    @Test
    public void computeNextState_freeFall_01() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface();
        final ControlPanel ctrl = buildControlZero();
        final Shuttle ship1 = buildInitialShip_01();
        final Shuttle ship2 = engine.computeNextState(ground, ship1, ctrl);
        final Shuttle ship3 = engine.computeNextState(ground, ship2, ctrl);
        final Shuttle ship4 = engine.computeNextState(ground, ship3, ctrl);
        final Shuttle ship5 = engine.computeNextState(ground, ship4, ctrl);

        assertEquals(ship5.position.x, 2500, 0.5);
        assertEquals(ship5.position.y, 2670, 0.5);

        assertEquals(ship5.speed.hSpeed, 0, 0.5);
        assertEquals(ship5.speed.vSpeed, -15, 0.5);
    }

    @Test
    public void computeNextState_FixedthrustAndTilt_01() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface();
        final ControlPanel ctrl = buildControl();
        final Shuttle ship1 = buildInitialShip_01();
        final Shuttle ship2 = engine.computeNextState(ground, ship1, ctrl);
        final Shuttle ship3 = engine.computeNextState(ground, ship2, ctrl);
        final Shuttle ship4 = engine.computeNextState(ground, ship3, ctrl);
        final Shuttle ship5 = engine.computeNextState(ground, ship4, ctrl);

        assertEquals(ship5.position.x, 2494, 0.5);
        assertEquals(ship5.position.y, 2684, 0.5);

        assertEquals(ship5.speed.hSpeed, -4, 0.5);
        assertEquals(ship5.speed.vSpeed, -7, 0.5);
    }

    @Test
    public void computeNextState_freeFall_02() {

        final GameEngine engine = buildStandardGameEngine();
        final Surface ground = buildSurface();
        final ControlPanel ctrl = buildControlZero();
        final Shuttle ship1 = buildInitialShip_02();
        final Shuttle ship2 = engine.computeNextState(ground, ship1, ctrl);
        final Shuttle ship3 = engine.computeNextState(ground, ship2, ctrl);
        final Shuttle ship4 = engine.computeNextState(ground, ship3, ctrl);
        final Shuttle ship5 = engine.computeNextState(ground, ship4, ctrl);

        assertEquals(ship5.position.x, 6100, 0.5);
        assertEquals(ship5.position.y, 2770, 0.5);

        assertEquals(ship5.speed.hSpeed, -100, 0.5);
        assertEquals(ship5.speed.vSpeed, -15, 0.5);

    }


}