package com.spbstu.java;

import java.util.Random;

public class Car {
	static Random _random = new Random();

	Integer _name;
	volatile Integer _desiredGas;
	volatile Integer _currentGas;

	static Car gen(){
		return new Car(_random.nextInt(1000), _random.nextInt(10) + 5);
	}
	
	private Car(int name, int desiredGas){
		_name = name;
		_desiredGas = desiredGas;
		_currentGas = 0;
	}
	
	@Override
	public String toString() {
		return  "Car: " + _name + " want: " + _desiredGas + " have: " + _currentGas;
	}
	
}
