package edu.designpatterns.state;

import static edu.designpatterns.state.Messages.*;

public class SoldOutState extends GumballMachineState {


    @Override
    void start(GumballMachine gumballMachine) {
        gumballMachine.getDevice().displayMessage(SO_START);
    }

    @Override
    void quarterInserted(GumballMachine gumballMachine) {
        gumballMachine.getDevice().displayMessage(SO_QUART);
        gumballMachine.getDevice().dispenseQuarter();
    }

    @Override
    void ejectQuarterRequested(GumballMachine gumballMachine) {
        gumballMachine.getDevice().displayMessage(SO_EJECT);
    }

    @Override
    void crankTurned(GumballMachine gumballMachine) {
        gumballMachine.getDevice().displayMessage(SO_CRANK);
    }

    @Override
    void refill(GumballMachine gumballMachine) {
        gumballMachine.getDevice().displayMessage(NQ_START);
    }
}
