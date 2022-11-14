package edu.designpatterns.state;

import static edu.designpatterns.state.Messages.HQ_EJECT;
import static edu.designpatterns.state.Messages.HQ_QUART;

public class HasQuarterState extends GumballMachineState {
    @Override
    void start(GumballMachine gumballMachine) {

    }

    @Override
    void quarterInserted(GumballMachine machine) {
        //old code
        machine.getDevice().dispenseQuarter();
        machine.getDevice().displayMessage(HQ_QUART);
    }

    @Override
    void ejectQuarterRequested(GumballMachine machine) {
        //old code
        machine.getDevice().dispenseQuarter();
        machine.getDevice().displayMessage(HQ_EJECT);
    }

    @Override
    void crankTurned(GumballMachine machine) {
        //old code
        machine.crankTurnedWhenHasQuarter();
    }

    @Override
    void reset(GumballMachine machine) {

    }
}
