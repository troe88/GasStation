package com.spbstu.java;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller implements Runnable {

	private static final int CAR_ADD2SERV_FREQ = 1000;
	LinkedBlockingQueue<Car> _cars;
	View _view;
	Random _random;
	private List<GasStand> _stands;

	@Override
	public void run() {
		_cars = new LinkedBlockingQueue<Car>(5);
		_random = new Random();
		while (true) {
			Utill.pause(10);
			while(!_cars.isEmpty()){
				Utill.pause(_random.nextInt(CAR_ADD2SERV_FREQ) + 100);
				int gasStationNum = _random.nextInt(3); 
				_stands.get(gasStationNum).addCarToService(_cars.remove());
			}
		}
	}

	public void set_view(View _view) {
		this._view = _view;
	}
	
	public void addCar(Car car) {
		try {
			_cars.put(car);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dispay(View view) {
		_view = view;
		_view.drawController(Collections.unmodifiableCollection(_cars));
	}

	public void setGasStations(List<GasStand> _stands) {
		this._stands = _stands;
	}
	
}
