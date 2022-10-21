package edu.designpatterns.state;

import static edu.designpatterns.state.Messages.*;

public class GumballMachine {
    private final GumballHardwareDevice device;
    private boolean soldOut;
    private boolean hasQuarter;

    public GumballMachine(GumballHardwareDevice device) {
        this.device = device;
        device.displayMessage(SO_START);
        soldOut = true;
    }

    public void quarterInserted() {
        if (soldOut) {
            quarterInsertedWhenSoldOut();
        } else if (hasQuarter) {
            quarterInsertedWhenHasQuarter();
        } else {
            quarterInsertedWhenNoQuarter();
        }
    }

    public void ejectQuarterRequested() {
        if (soldOut) {
            ejectQuarterWhenSoldOut();
        } else if (hasQuarter) {
            ejectQuarterWhenHasQuarter();
        } else {
            ejectQuarterWhenNoQuarter();
        }
    }

    public void crankTurned() {
        if (soldOut) {
            crankTurnedWhenSoldOut();
        } else if (hasQuarter) {
            crankTurnedWhenHasQuarter();
        } else {
            crankTurnedWhenNoQuarter();
        }

    }

    public void refill() {
        if (soldOut) {
            refillWhenSoldOut();
        }
    }

    //Insert Quarter
    private void quarterInsertedWhenNoQuarter() {
        device.displayMessage(NQ_QUART);
        hasQuarter = true;
    }

    private void quarterInsertedWhenHasQuarter() {
        device.dispenseQuarter();
        device.displayMessage(HQ_QUART);
    }

    private void quarterInsertedWhenSoldOut() {
        device.displayMessage(SO_QUART);
        device.dispenseQuarter();
    }

    //Eject Quarter
    private void ejectQuarterWhenNoQuarter() {
        device.displayMessage(NQ_EJECT);
    }

    private void ejectQuarterWhenHasQuarter() {
        device.displayMessage(HQ_EJECT);
        device.dispenseQuarter();
        hasQuarter = false;
    }

    private void ejectQuarterWhenSoldOut() {
        device.displayMessage(SO_EJECT);
    }

    //Crank Turned
    private void crankTurnedWhenNoQuarter() {
        device.displayMessage(NQ_CRANK);
    }

    private void crankTurnedWhenHasQuarter() {
        if (device.dispenseGumball()) {
            device.displayMessage(NQ_START);
        } else {
            soldOut = true;
            device.displayMessage(SO_START);
            device.dispenseQuarter();
        }
        hasQuarter = false;
    }

    private void crankTurnedWhenSoldOut() {
        device.displayMessage(SO_CRANK);
    }

    //Refill
    private void refillWhenSoldOut() {
        soldOut = false;
        device.displayMessage(NQ_START);
    }
}
