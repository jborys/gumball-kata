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
		if(soldOut) {
			device.dispenseQuarter();
			device.displayMessage(SO_QUART);
		} else if (hasQuarter) {
			device.dispenseQuarter();
			device.displayMessage(HQ_QUART);
		} else {
			device.displayMessage(NQ_QUART);
			hasQuarter = true;
		}
	}

	public void ejectQuarterRequested() {
		if (soldOut) {
			device.displayMessage(SO_EJECT);
		} else if (hasQuarter) {
			device.dispenseQuarter();
			device.displayMessage(HQ_EJECT);
		} else {
			device.displayMessage(NQ_EJECT);
		}
	}

	public void crankTurned() {
		if (soldOut) {
			device.displayMessage(SO_CRANK);
		} else if (hasQuarter) {
			if (device.dispenseGumball()) {
				device.displayMessage(NQ_START);
			} else {
				soldOut = true;
				device.displayMessage(SO_START);
				device.dispenseQuarter();
			}
			hasQuarter = false;
		} else {
			device.displayMessage(NQ_CRANK);
		}
	}

	public void reset() {
		device.displayMessage(NQ_START);
		soldOut = false;
	}
}
