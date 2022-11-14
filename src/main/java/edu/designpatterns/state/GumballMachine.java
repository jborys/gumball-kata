package edu.designpatterns.state;

import static edu.designpatterns.state.Messages.*;

public class GumballMachine {
	private final GumballHardwareDevice device;
	boolean soldOut;
	boolean hasQuarter;

	GumballMachineState state;
	public GumballMachine(GumballHardwareDevice device) {
		this.device = device;
		device.displayMessage(SO_START);
		soldOut = true;
	}

	public void quarterInserted() {
		if(soldOut) {
			state = new SoldOutState();
		} else if (hasQuarter) {
			state = new HasQuarterState();
		} else {
			state = new HasNoQuarterState();
		}
		state.quarterInserted(this);
	}

	public void ejectQuarterRequested() {
		if (soldOut) {
			state = new SoldOutState();
		} else if (hasQuarter) {
			state = new HasQuarterState();
		} else {
			state = new HasNoQuarterState();
		}
		state.ejectQuarterRequested(this);
	}

	public void crankTurned() {
		if (soldOut) {
			state = new SoldOutState();
		} else if (hasQuarter) {
			state = new HasQuarterState();
		} else {
			state = new HasNoQuarterState();
		}
		state.crankTurned(this);
	}

	public void crankTurnedWhenHasQuarter() {
		if (device.dispenseGumball()) {
			device.displayMessage(NQ_START);
		} else {

			//old code
			soldOut = true;
			device.displayMessage(SO_START);
			device.dispenseQuarter();
		}
		hasQuarter = false;
	}

	public void reset() {
		state = new SoldOutState();
		state.reset(this);
	}

	public void setState(GumballMachineState state) {
		this.state = state;
	}

	public GumballHardwareDevice getDevice() {
		return device;
	}
}
