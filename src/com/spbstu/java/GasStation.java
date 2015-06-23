package com.spbstu.java;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class GasStation implements Runnable {

	private static final int CAR_GEN_FREQ = 500;
	private static final Boolean isAuto = true;
	Controller _cntrl;
	ExecutorService _servise;
	List<GasStand> _stands;
	Semaphore s;

	@Override
	public void run() {
		
		Thread.currentThread().setName("GasStation");
		
		s = new Semaphore(0);
		_stands = Arrays.asList(new GasStand("Gas1"), new GasStand("Gas2"),
				new GasStand("Gas3"));

		_servise = Executors.newCachedThreadPool();
		_cntrl = new Controller();

		_servise.submit(_cntrl);
		for (GasStand gasStand : _stands) {
			_servise.submit(gasStand, "GasStand");
		}

		_cntrl.setGasStations(_stands);

		while (true) {
			try {
				if (!isAuto) {
					s.acquire();
				}
				Car car = Car.gen();
				Utill.pause(CAR_GEN_FREQ);
				_cntrl.addCar(car);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void genSingleCar() {
		s.release();
	}

	public void display(View view) {
		_cntrl.dispay(view);
		for (GasStand gasStand : _stands) {
			gasStand.display(view);
		}
	}

}
