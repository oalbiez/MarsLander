package com.althome.landersimulator.game;

import com.althome.landersimulator.entities.*;
import com.althome.landersimulator.utils.Utils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static com.althome.landersimulator.game.GameEngine.*;

/**
 * Created by Arnaud on 13/09/2016.
 */
public class GameEngineTest {

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
    public void computeNextState_01() {

        final Surface ground = buildSurface();
        final Shuttle ship1 = buildInitialShip_01();
        final Shuttle ship2 = computeNextState(ship1, ground);
        final Shuttle ship3 = computeNextState(ship2, ground);
        final Shuttle ship4 = computeNextState(ship3, ground);
        final Shuttle ship5 = computeNextState(ship4, ground);


        assertEquals(ship5.position.x, 2500, 0.5);
        assertEquals(ship5.position.y, 2670, 0.5);

        assertEquals(ship5.speed.hSpeed, 0, 0.5);
        assertEquals(ship5.speed.vSpeed, -15, 0.5);
    }

    @Test
    public void computeNextState_02() {

        final Surface ground = buildSurface();
        final Shuttle ship1 = buildInitialShip_02();
        final Shuttle ship2 = computeNextState(ship1, ground);
        final Shuttle ship3 = computeNextState(ship2, ground);
        final Shuttle ship4 = computeNextState(ship3, ground);
        final Shuttle ship5 = computeNextState(ship4, ground);

        assertEquals(ship5.position.x, 6100, 0.5);
        assertEquals(ship5.position.y, 2770, 0.5);

        assertEquals(ship5.speed.hSpeed, -100, 0.5);
        assertEquals(ship5.speed.vSpeed, -15, 0.5);

    }


}