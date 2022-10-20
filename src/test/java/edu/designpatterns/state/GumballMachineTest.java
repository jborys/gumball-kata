package edu.designpatterns.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static edu.designpatterns.state.Messages.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GumballMachineTest {
    private final TestDevice device = new TestDevice();
    private GumballMachine gumballMachine;

    @BeforeEach
    public void setup() {
        gumballMachine = new GumballMachine(device);
    }

    @Nested
    @DisplayName("Gumball Machine Sold Out")
    class SoldOut {
        @Test
        public void initialConditionsEmptyMachineShouldShowSO_StartMessage() {
            assertThat(device.getDisplayedMessage()).isEqualTo(SO_START);
        }

        @Test
        public void initialConditionsEmptyMachineShouldNotDispenseQuarter() {
            assertThat(device.wasQuarterEjected()).isFalse();
        }

        @Test
        public void emptyMachineQuarterInsertedShouldShowSO_QuartMessage() {
            gumballMachine.quarterInserted();

            assertThat(device.getDisplayedMessage()).isEqualTo(SO_QUART);
            assertThat(device.wasQuarterEjected()).isTrue();
        }

        @Test
        public void emptyMachineEjectQuarterShouldShowSO_EjectMessage() {
            gumballMachine.ejectQuarterRequested();

            assertThat(device.getDisplayedMessage()).isEqualTo(SO_EJECT);
        }

        @Test
        public void emptyMachineCrankTurnedShouldShowSO_CrankMessage() {
            gumballMachine.crankTurned();

            assertThat(device.getDisplayedMessage()).isEqualTo(SO_CRANK);
        }

        @Test
        public void emptyMachineResetPushedShouldShowSO_CrankMessage() {
            gumballMachine.refill();

            assertThat(device.getDisplayedMessage()).isEqualTo(NQ_START);
        }
    }

    @Nested
    @DisplayName("Gumball Machine No Quarter")
    class NoQuarter {

        @Test
        public void initialConditionHasGumballsHasNoQuarter() {
            gumballMachine.refill();

            assertThat(device.getDisplayedMessage()).isEqualTo(NQ_START);
        }

        @Test
        public void quarterInsertedMachineHasGumballsShouldShowNQ_QuartMessage() {
            gumballMachine.refill();
            gumballMachine.quarterInserted();

            assertThat(device.getDisplayedMessage()).isEqualTo(NQ_QUART);
        }

        @Test
        public void quarterEjectedMachineHasGumballsShouldShowNQ_EjectMessage() {
            gumballMachine.refill();
            gumballMachine.ejectQuarterRequested();

            assertThat(device.getDisplayedMessage()).isEqualTo(NQ_EJECT);
        }

        @Test
        public void crankTurnedMachineHasGumballsShouldShowNQ_CrankMessage() {
            gumballMachine.refill();
            gumballMachine.crankTurned();

            assertThat(device.getDisplayedMessage()).isEqualTo(NQ_CRANK);
        }

    }

    @Nested
    @DisplayName("Gumball Machine Has Quarter")
    class HasQuarter {

        @Test
        public void initialConditionHasGumballsHasAQuarter() {
            gumballMachine.refill();
            gumballMachine.quarterInserted();

            assertThat(device.getDisplayedMessage()).isEqualTo(HQ_START);
        }

        @Test
        public void quarterInsertedMachineHasGumballsAndAQuarterShouldShowNQ_QuartMessage() {
            gumballMachine.refill();
            gumballMachine.quarterInserted();

            gumballMachine.quarterInserted();

            assertThat(device.getDisplayedMessage()).isEqualTo(HQ_QUART);
        }

        @Test
        public void ejectQuarterMachineHasGumballsAndAQuarterShouldShowHQ_EjectMessage() {
            gumballMachine.refill();
            gumballMachine.quarterInserted();

            gumballMachine.ejectQuarterRequested();

            assertThat(device.getDisplayedMessage()).isEqualTo(HQ_EJECT);
        }

        @Test
        public void ejectQuarterPushedTwiceMachineHasGumballsAndAQuarterShouldOnlyDispenseOneQuarter() {
            gumballMachine.refill();
            gumballMachine.quarterInserted();

            gumballMachine.ejectQuarterRequested();
            assertThat(device.wasQuarterEjected()).isTrue();

            gumballMachine.ejectQuarterRequested();

            assertThat(device.getDisplayedMessage()).isEqualTo(NQ_EJECT);
        }

        @Test
        public void turnCrankMachineHasGumballsAndAQuarterShouldShowHQ_CrankMessage() {
            hasQuarterState();

            gumballMachine.crankTurned();

            assertThat(device.getDisplayedMessage()).isEqualTo(HQ_CRANK);
        }

        @Test
        public void turnCrankMachineHasNoGumballsAndAQuarterShouldShowSO_StartMessage() {
            gumballMachine.refill();
            sellGumball();

            assertThat(device.getDisplayedMessage()).isEqualTo(SO_START);
        }

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
}
