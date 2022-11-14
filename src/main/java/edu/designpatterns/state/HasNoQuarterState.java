package edu.designpatterns.state;

import static edu.designpatterns.state.Messages.*;

public class HasNoQuarterState extends GumballMachineState {
    @Override
    void start(GumballMachine gumballMachine) {

    }

    @Override
    void quarterInserted(GumballMachine machine) {
        //old code
        machine.getDevice().displayMessage(NQ_QUART);
        machine.hasQuarter = true;
    }

    @Override
    void ejectQuarterRequested(GumballMachine machine) {
        //old code
        machine.getDevice().displayMessage(NQ_EJECT);
    }

    @Override
    void crankTurned(GumballMachine machine) {
        //old code
        machine.getDevice().displayMessage(NQ_CRANK);
    }

    @Override
    void reset(GumballMachine machine) {

    }
}
