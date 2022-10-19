package edu.designpatterns.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GumballMachineTest {
	private TestDevice device = new TestDevice();
	private GumballMachine gumballMachine;

	@BeforeEach
	public void setup() {
		gumballMachine = new GumballMachine(device);
	}

	@Test
	public void initialConditionsEmptyMachineShouldShowSO_StartMessage() {
		assertThat(device.getDisplayedMessage()).isEqualTo(Messages.SO_START);
	}

	@Test
	public void initialConditionsEmptyMachineShouldNotDispenseQuarter() {
		assertThat(device.wasQuarterEjected()).isFalse();
	}
}
