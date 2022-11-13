package edu.designpatterns.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GumballMachineTest {
    private final TestDevice device = new TestDevice();
    private GumballMachine gumballMachine;

    @BeforeEach
    public void setup() {
        gumballMachine = new GumballMachine(device);
    }

    @Nested
    @DisplayName("Initial Condition")
    class InitialCondition {

        @Test
        public void initialConditionsEmptyMachineShouldNotDispenseQuarter() {
            assertThat(device.wasQuarterEjected()).isFalse();
        }

        @Test
        public void initialConditionsEmptyMachineShouldShowSO_StartMessage() {
            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.SO_START);
        }
    }

    @Nested
    @DisplayName("Sold Out")
    class SoldOut {
        @Test
        @DisplayName("Has no Gumballs")
        public void quarterInsertedMachineSoldOutShouldReleaseQuarter() {
            gumballMachine.quarterInserted();

            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.SO_QUART);
            assertThat(device.wasQuarterEjected()).isTrue();
        }

        @Test
        public void pushEjectButtonMachineSoldOutShouldDisplayEjectMsg() {
            gumballMachine.ejectQuarterRequested();

            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.SO_EJECT);
        }

        @Test
        public void turnCrankMachineSoldOutShouldDisplayNoGumballMsg() {
            gumballMachine.crankTurned();

            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.SO_CRANK);
        }

        @Test
        public void resetMachineSoldOutShouldDisplayNoQuarterStartMsg() {
            gumballMachine.reset();

            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.NQ_START);
        }

    }

    @Nested
    @DisplayName("Has No Quarter")
    class HasNoQuarter {
        @Test
        public void quarterInsertedShouldDisplayTurnCrankMsg() {
            gumballMachine.reset();
            gumballMachine.quarterInserted();


            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.NQ_QUART);
        }

        @Test
        public void ejectQuarterPressedShouldDisplayNoQuarterEjectMsg() {
            gumballMachine.reset();
            gumballMachine.ejectQuarterRequested();


            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.NQ_EJECT);
        }

        @Test
        public void turnCrankWithouQuarterShouldDisplayCrankMsg() {
            gumballMachine.reset();
            gumballMachine.crankTurned();

            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.NQ_CRANK);
        }
    }

    @Nested
    @DisplayName("Gumball Machine Has Quarter")
    class HasQuarter {
        @Test
        public void shouldDisplayTurnCrankForAGumballMsg() {
            gumballMachine.reset();
            gumballMachine.quarterInserted();

            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.HQ_START);
        }

        @Test
        public void quarterInsertedShouldDisplayInsertedMsgAndDispenseQuarter() {
            gumballMachine.reset();
            gumballMachine.quarterInserted();
            gumballMachine.quarterInserted();

            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.HQ_QUART);
            assertThat(device.wasQuarterEjected()).isTrue();
        }

        @Test
        public void quarterEjectedShouldDisplayEjectedQuarterMsg() {
            gumballMachine.reset();
            gumballMachine.quarterInserted();
            gumballMachine.ejectQuarterRequested();

            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.HQ_EJECT);
        }

        @Test
        public void crankTurnedShouldDisplayGumballMsgAndDispenseGumball() {
            gumballMachine.reset();
            device.addGumballs(5);
            gumballMachine.quarterInserted();
            gumballMachine.crankTurned();

            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.HQ_CRANK);
            assertThat(device.dispenseGumball()).isTrue();
        }

        @Test
        public void crankTurnedNoGumballsLeftShouldDispenseQuarterAndDisplaySoldOutMsg() {
            gumballMachine.reset();
            gumballMachine.quarterInserted();
            gumballMachine.crankTurned();


            assertThat(device.getDisplayedMessage()).isEqualTo(Messages.SO_START);
            assertThat(device.dispenseGumball()).isFalse();
        }
    }
}
