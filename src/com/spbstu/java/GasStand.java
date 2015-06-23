package com.spbstu.java;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class GasStand implements Runnable {

	private static final int CAR_SWITCH_SPEED = 200;
	private static final int CAR_FILL_SPEED = 100;
	
	static final Integer FUEL_LVL_ISO = 100;
	private String _name;
	private View _view;
	LinkedBlockingQueue<Car> _cars;
	private volatile Integer _fuelLvl;
	Semaphore semaphore;
	Semaphore semaphore2;

	public GasStand(String name, Integer fuelLvl) {
		this._name = name;
		set_fuelLvl(fuelLvl);
	}

	public GasStand(String name) {
		this(name, FUEL_LVL_ISO);
	}

	@Override
	public void run() {
		semaphore = new Semaphore(0);
		semaphore2 = new Semaphore(0);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						semaphore.acquire();
						while (get_fuelLvl() != GasStand.FUEL_LVL_ISO) {
							Utill.pause(20);
							System.out.println("refuel");
							inc_fuelLvl();
						}
						semaphore2.release();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		_cars = new LinkedBlockingQueue<Car>();
		while (true) {
			Utill.pause(10);
			while (!_cars.isEmpty()) {
				Utill.pause(CAR_SWITCH_SPEED);
				try {
					servise(_cars.peek());
					_cars.remove();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void servise(Car car) throws InterruptedException {
		while (car._currentGas != car._desiredGas) {
			Utill.pause(CAR_FILL_SPEED);
			if (get_fuelLvl() == 0) {
				semaphore.release();
				semaphore2.acquire();
			}

			dec_fuelLvl();
			car._currentGas++;
		}
	}

	public void display(View view) {
		this._view = view;
		view.drawGasStand(_cars, get_fuelLvl(), _name);
	}

	public void addCarToService(Car car) {
		_cars.add(car);
	}

	public Integer get_fuelLvl() {
		synchronized (_fuelLvl) {
			return _fuelLvl;
		}
	}

	public void set_fuelLvl(Integer _fuelLvl) {
		synchronized (_fuelLvl) {
			this._fuelLvl = _fuelLvl;
		}
	}

	public void dec_fuelLvl() {
		synchronized (_fuelLvl) {
			this._fuelLvl--;
		}
	}

	public void inc_fuelLvl() {
		synchronized (_fuelLvl) {
			this._fuelLvl++;
		}
	}

}
