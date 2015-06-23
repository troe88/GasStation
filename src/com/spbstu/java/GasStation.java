package com.spbstu.java;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GasStation implements Runnable {

	private static final int CAR_GEN_FREQ = 500;
	Controller _cntrl;
	ExecutorService _servise;
	List<GasStand> _stands;

	@Override
	public void run() {

		_stands = Arrays.asList(new GasStand("Gas1"), new GasStand("Gas2"),
				new GasStand("Gas3"));

		_servise = Executors.newCachedThreadPool();
		_cntrl = new Controller();

		_servise.submit(_cntrl);
		for (GasStand gasStand : _stands) {
			_servise.submit(gasStand);
		}

		_cntrl.setGasStations(_stands);
		
		while (true) {
			Car car = Car.gen();
			Utill.pause(CAR_GEN_FREQ);
			_cntrl.addCar(car);
		}
	}

	public void display(View view) {
		_cntrl.dispay(view);
		for (GasStand gasStand : _stands) {
			gasStand.display(view);
		}
	}

}
