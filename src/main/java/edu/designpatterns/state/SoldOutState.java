package edu.designpatterns.state;

import static edu.designpatterns.state.Messages.*;

public class SoldOutState extends GumballMachineState {
    @Override
    void start(GumballMachine gumballMachine) {

    }

    @Override
    void quarterInserted(GumballMachine machine) {
        machine.getDevice().dispenseQuarter();
        machine.getDevice().displayMessage(SO_QUART);
    }

    @Override
    void ejectQuarterRequested(GumballMachine machine) {
        machine.getDevice().displayMessage(SO_EJECT);
    }

    @Override
    void crankTurned(GumballMachine machine) {
        machine.getDevice().displayMessage(SO_CRANK);
    }

    @Override
    void reset(GumballMachine machine) {
        machine.getDevice().displayMessage(NQ_START);
        machine.soldOut = false;
    }
}
