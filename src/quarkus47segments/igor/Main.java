package quarkus47segments.igor;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Path("/clock")
public class Main {
	
	final GpioController gpio = GpioFactory.getInstance();
	GpioPinDigitalOutput pin01 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "1", PinState.LOW);
	GpioPinDigitalOutput pin02 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "2", PinState.LOW);
	GpioPinDigitalOutput pin03 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "3", PinState.LOW);
	GpioPinDigitalOutput pin04 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "4", PinState.LOW);
	GpioPinDigitalOutput pin05 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "5", PinState.LOW);
	GpioPinDigitalOutput pin06 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "6", PinState.HIGH);
	GpioPinDigitalOutput pin07 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "7", PinState.LOW);
	GpioPinDigitalOutput pin08 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "8", PinState.HIGH);
	GpioPinDigitalOutput pin09 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "9", PinState.HIGH);
	GpioPinDigitalOutput pin10 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "10", PinState.LOW);
	GpioPinDigitalOutput pin11 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "11", PinState.LOW);
	GpioPinDigitalOutput pin12 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "12", PinState.HIGH);
	
	
	public void setClockValue(String value) throws InterruptedException {
		
		String[] r1 = value.split("T");
		String[] r2 = r1[1].split(":");
		
		long end = System.currentTimeMillis()+7000;
		while(System.currentTimeMillis() < end){
			pin12.low();
			pin08.high();
			pin09.high();
			pin06.high();
			display(Integer.parseInt((String) r2[0].subSequence(0, 1)));
			TimeUnit.MILLISECONDS.sleep(5);
			
			pin12.high();
			pin08.low();
			pin09.high();
			pin06.high();
			display(Integer.parseInt((String) r2[0].substring(1)));
			TimeUnit.MILLISECONDS.sleep(5);
			
			pin12.high();
			pin08.high();
			pin09.low();
			pin06.high();
			display(Integer.parseInt((String) r2[1].subSequence(0, 1)));
			TimeUnit.MILLISECONDS.sleep(5);
			
			pin12.high();
			pin08.high();
			pin09.high();
			pin06.low();
			display(Integer.parseInt((String) r2[1].substring(1)));
			TimeUnit.MILLISECONDS.sleep(5);
		}
		
		pin12.high();
		pin08.high();
		pin09.high();
		pin06.high();

	}
	
	public void display(int number) {
		
		System.out.println("numero " + number);

		if(number == 0) {
			pin01.high();
			pin02.high();
			pin03.low();
			pin04.high();
			pin05.low();
			pin07.high();
			pin10.high();
			pin11.high();
		}
		if(number == 1) {
			pin01.low();
			pin02.low();
			pin03.low();
			pin04.high();
			pin05.low();
			pin07.high();
			pin10.low();
			pin11.low();
		}
		if(number == 2) {
			pin01.high();
			pin02.high();
			pin03.low();
			pin04.low();
			pin05.high();
			pin07.high();
			pin10.low();
			pin11.high();
		}
		if(number == 3) {
			pin01.low();
			pin02.high();
			pin03.low();
			pin04.high();
			pin05.high();
			pin07.high();
			pin10.low();
			pin11.high();
		}
		if(number == 4) {
			pin01.low();
			pin02.low();
			pin03.low();
			pin04.high();
			pin05.high();
			pin07.high();
			pin10.high();
			pin11.low();
		}
		if(number == 5) {
			pin01.low();
			pin02.high();
			pin03.low();
			pin04.high();
			pin05.high();
			pin07.low();
			pin10.high();
			pin11.high();
		}
		if(number == 6) {
			pin01.high();
			pin02.high();
			pin03.low();
			pin04.high();
			pin05.high();
			pin07.low();
			pin10.high();
			pin11.low();
		}
		if(number == 7) {
			pin01.low();
			pin02.low();
			pin03.low();
			pin04.high();
			pin05.low();
			pin07.high();
			pin10.low();
			pin11.high();
		}
		if(number == 8) {
			pin01.high();
			pin02.high();
			pin03.low();
			pin04.high();
			pin05.high();
			pin07.high();
			pin10.high();
			pin11.high();
		}
		if(number == 9) {
			pin01.low();
			pin02.high();
			pin03.low();
			pin04.high();
			pin05.high();
			pin07.high();
			pin10.high();
			pin11.high();
		}

	}

	@GET
	@Path("/{name}")
	public String greeting(@PathParam("name") String name) throws InterruptedException {
		System.out.println("Time " + name);
		setClockValue(name);
		return "hello " + name;
	}

}