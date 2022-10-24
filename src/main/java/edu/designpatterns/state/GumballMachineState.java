package edu.designpatterns.state;

abstract class GumballMachineState {
    abstract void start(GumballMachine gumballMachine);

    abstract void quarterInserted(GumballMachine gumballMachine);

    abstract void ejectQuarterRequested(GumballMachine gumballMachine);

    abstract void crankTurned(GumballMachine gumballMachine);

    abstract void refill(GumballMachine gumballMachine);
}
