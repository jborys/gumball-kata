package edu.designpatterns.state;

public class TestDevice implements GumballHardwareDevice {
	private String displayedMessage;
	private boolean wasQuarterEjected;
	private int numGumballs;

	@Override
	public void displayMessage(String message) {
		this.displayedMessage = message;
	}

	@Override
	public boolean dispenseGumball() {
		numGumballs--;
		return numGumballs >= 0;
	}

	@Override
	public void dispenseQuarter() {
		wasQuarterEjected = true;
	}

	public String getDisplayedMessage() {
		return displayedMessage;
	}

	public boolean wasQuarterEjected() {
		return wasQuarterEjected;
	}

	public int getCount() {
		return numGumballs;
	}

	public void addGumballs(int count) {
		numGumballs = Math.max(numGumballs, 0) + count;
	}
}
