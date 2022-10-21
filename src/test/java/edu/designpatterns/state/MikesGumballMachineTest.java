package edu.designpatterns.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MikesGumballMachineTest {
    private final TestDevice device = new TestDevice();
    private GumballMachine gumballMachine;

    @BeforeEach
    public void setup() {
        gumballMachine = new GumballMachine(device);
    }

    // Sold Out
    @Test
    public void initialConditionsEmptyMachineShouldDisplaySoldOutStartMessage() {
        assertDisplay(Messages.SO_START);
    }

    @Test
    public void initialConditionsEmptyMachineShouldNotDispenseQuarter() {
        assertThat(device.wasQuarterEjected()).isFalse();
    }

    @Test
    public void insertQuarterOnEmptyMachineShouldDisplaySoldOutQuarterMessage() {
        gumballMachine.quarterInserted();
        assertDisplay(Messages.SO_QUART);
    }

    @Test
    public void quarterInsertedOnEmptyMachineShouldejectQuarterRequested() {
        gumballMachine.quarterInserted();
        assertThat(device.wasQuarterEjected()).isTrue();
    }

    @Test
    public void ejectQuarterOnEmptyMachineShouldDisplaySoldOutEjectMessage() {
        gumballMachine.ejectQuarterRequested();
        assertDisplay(Messages.SO_EJECT);
    }

    @Test
    public void turnCrankOnEmptyMachineShouldDisplaySoldOutCrankMessage() {
        gumballMachine.crankTurned();
        assertDisplay(Messages.SO_CRANK);
    }

    // No Quarter
    @Test
    public void atStartOnRefilledMachineShouldDisplayNoQuarterStartMessage() {
        refillMachine();
        assertDisplay(Messages.NQ_START);
    }

    @Test
    public void insertQuarterOnRefilledMachineShouldDisplayHasQuarterStartMessage() {
        refillMachine();
        gumballMachine.quarterInserted();
        assertDisplay(Messages.NQ_QUART);
    }

    @Test
    public void ejectQuarterOnRefilledMachineShouldDisplayNoQuarterEjectMessage() {
        refillMachine();
        gumballMachine.ejectQuarterRequested();
        assertDisplay(Messages.NQ_EJECT);
    }

    @Test
    public void ejectQuarterOnRefilledMachineShouldNotejectQuarterRequested() {
        refillMachine();
        gumballMachine.ejectQuarterRequested();
        assertThat(device.wasQuarterEjected()).isFalse();
    }

    @Test
    public void turnCrankOnRefilledMachineShouldDisplayNoQuarterCrankMessage() {
        refillMachine();
        gumballMachine.crankTurned();
        assertDisplay(Messages.NQ_CRANK);
    }

    @Test
    public void refillOnRefilledMachineShouldNotChangeNoQuarterStartMessage() {
        refillMachine();
        gumballMachine.refill();
        assertDisplay(Messages.NQ_START);
    }

    // Has Quarter
    @Test
    public void atStartWithQuarterShouldDisplayHasQuarterStartMessage() {
        hasQuarterState();
        assertDisplay(Messages.HQ_START);
    }

    @Test
    public void insertQuarterWithQuarterShouldDisplayHasQuarterQuarterMessage() {
        hasQuarterState();
        gumballMachine.quarterInserted();
        assertDisplay(Messages.HQ_QUART);
    }

    @Test
    public void insertQuarterWithQuarterShouldejectQuarterRequested() {
        hasQuarterState();
        gumballMachine.quarterInserted();
        assertThat(device.wasQuarterEjected()).isTrue();
    }

    @Test
    public void ejectQuarterWithQuarterShouldDisplayHasQuarterEjectMessage() {
        hasQuarterState();
        gumballMachine.ejectQuarterRequested();
        assertDisplay(Messages.HQ_EJECT);
    }

    @Test
    public void ejectQuarterWithQuarterShouldejectQuarterRequested() {
        hasQuarterState();
        gumballMachine.ejectQuarterRequested();
        assertThat(device.wasQuarterEjected()).isTrue();
    }

    @Test
    public void turnCrankWithQuarterShouldDisplayHasQuarterCrankMessage() {
        hasQuarterState();
        gumballMachine.crankTurned();
        assertDisplay(Messages.HQ_CRANK);
    }

    @Test
    public void turnCrankWithQuarterShouldDispenseGumball() {
        hasQuarterState();
        int count = device.getCount();
        gumballMachine.crankTurned();
        assertThat(device.getCount()).isEqualTo(count - 1);
    }

    @Test
    public void refillWithQuarterShouldNotChangeHasQuarterStartMessage() {
        hasQuarterState();
        gumballMachine.refill();
        assertDisplay(Messages.HQ_START);
    }

    @Test
    public void sellingGumballShouldDisplayNoQuarterStartMessage() {
        refillMachine();
        sellGumball();
        assertDisplay(Messages.NQ_START);
    }

    // Sell Out
    @Test
    public void insertQuarterWhenSoldOutDisplaySoldOutStartMessage() {
        sellOutState();
        assertDisplay(Messages.SO_START);
    }

    @Test
    public void sellingOutShouldReturnQuarter() {
        sellOutState();
        assertThat(device.wasQuarterEjected()).isTrue();
    }

    @Test
    public void insertQuarterWhenSoldOutShouldDisplaySoldOutQuarterMessage() {
        sellOutState();
        gumballMachine.quarterInserted();
        assertDisplay(Messages.SO_QUART);
    }

    @Test
    public void ejectQuarterWhenSoldOutShouldDisplaySoldOutEjectMessage() {
        sellOutState();
        gumballMachine.ejectQuarterRequested();
        assertDisplay(Messages.SO_EJECT);
    }

    @Test
    public void turnCrankWhenSoldOutShouldDisplaySoldOutCrankMessage() {
        sellOutState();
        gumballMachine.crankTurned();
        assertDisplay(Messages.SO_CRANK);
    }

    @Test
    public void refillWhenSoldOutShouldDisplayNoQuarterStartMessage() {
        sellOutState();
        gumballMachine.refill();
        assertDisplay(Messages.NQ_START);
    }

    // Error checks
    @Test
    public void cyclingQuarterOnEmptyMachineShouldDisplaySoldOutEjectMessage() {
        cycleQuarter();
        assertDisplay(Messages.SO_EJECT);
    }

    @Test
    public void turningCrankAfterGumballSoldShouldDisplayNoQuarterCrankMessage() {
        refillMachine();
        sellGumball();
        gumballMachine.crankTurned();
        assertDisplay(Messages.NQ_CRANK);
    }

    @Test
    public void cyclingQuarterShouldejectQuarterRequested() {
        refillMachine();
        cycleQuarter();
        assertThat(device.wasQuarterEjected()).isTrue();
    }

    @Test
    public void insertQuarterAfterCyclingQuarterShouldDisplayHasQuarterStartMessage() {
        refillMachine();
        cycleQuarter();
        gumballMachine.quarterInserted();
        assertDisplay(Messages.HQ_START);
    }

    @Test
    public void refillingMidCycleShouldNotEffectHasQuarter() {
        refillMachine();
        gumballMachine.quarterInserted();
        refillMachine();
        assertDisplay(Messages.HQ_START);
    }

    // Helper methods

    private void assertDisplay(String message) {
        assertThat(device.getDisplayedMessage()).isEqualTo(message);
    }

    private void refillMachine() {
        device.addGumballs(1);
        gumballMachine.refill();
    }

    private void hasQuarterState() {
        refillMachine();
        gumballMachine.quarterInserted();
    }

    private void sellGumball() {
        gumballMachine.quarterInserted();
        gumballMachine.crankTurned();
    }

    private void cycleQuarter() {
        gumballMachine.quarterInserted();
        gumballMachine.ejectQuarterRequested();
    }

    private void sellOutState() {
        refillMachine();

        while (0 < device.getCount()) {
            sellGumball();
        }

        gumballMachine.quarterInserted();
        gumballMachine.crankTurned();
    }
}
