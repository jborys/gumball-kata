package edu.designpatterns.state;

abstract class GumballMachineState {
    abstract void start(GumballMachine gumballMachine);
    abstract void quarterInserted(GumballMachine machine);
    abstract void ejectQuarterRequested(GumballMachine machine);
    abstract void crankTurned(GumballMachine machine);
    abstract void reset(GumballMachine machine);
}
