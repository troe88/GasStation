package com.spbstu.java;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextArea;

import java.awt.Insets;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

// Заправочная станция. Заправочная станция с самообслуживанием имеет некоторое число насосов для заправки автомобилей покупателей топливом.
// Действует следующая система: покупатели сначала платят кассиру за топливо; кассир активирует насос чтобы доставить топливо. 
// Возьмите систему с несколькими покупателями и заправочной станцией с тремя насосами и одним кассиром. 
// Учитывая условие что плата за топливо может быть различной, необходимо удостоверится что не будет неудовлетворенного покупателя
// (если насос выдал неправильный объем топлива).

public class Main {

	private static JTextArea cntrlTextArea;
	private static JSlider gas1_slider;
	private static JSpinner gas1_spinner;
	private static JTextArea gas1_textArea;
	private static JSlider gas2_slider;
	private static JSpinner gas2_spinner;
	private static JTextArea gas2_textArea;
	private static JPanel panel_3;
	private static JSlider gas3_slider;
	private static JSpinner gas3_spinner;
	private static JTextArea gas3_textArea;

	public static void main(String[] args) {

		Executor executor = Executors.newSingleThreadExecutor();
		GasStation gasStation = new GasStation();
		executor.execute(gasStation);

		JFrame frame = new JFrame("JFrame Example");

		Timer timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gasStation.display(new View() {

					@Override
					public void drawController(Collection<?> _cars) {
						cntrlTextArea.setText("");
						for (Object o : _cars) {
							cntrlTextArea.setText(cntrlTextArea.getText() + o.toString() + '\n');
						}
					}

					@Override
					public void drawGasStand(LinkedBlockingQueue<Car> _cars,
							Integer _fuelLvl, String _name) {
						switch (_name) {
						case "Gas1":
							gas1_textArea.setText("");
							for (Object o : _cars) {
								gas1_textArea.setText(gas1_textArea.getText() + o.toString() + '\n');
							}
							gas1_slider.setValue(_fuelLvl);
							gas1_spinner.setValue(_fuelLvl);
							break;
						case "Gas2":
							gas2_textArea.setText("");
							for (Object o : _cars) {
								gas2_textArea.setText(gas2_textArea.getText() + o.toString() + '\n');
							}
							gas2_slider.setValue(_fuelLvl);
							gas2_spinner.setValue(_fuelLvl);
							break;
						case "Gas3":
							gas3_textArea.setText("");
							for (Object o : _cars) {
								gas3_textArea.setText(gas3_textArea.getText() + o.toString() + '\n');
							}
							gas3_slider.setValue(_fuelLvl);
							gas3_spinner.setValue(_fuelLvl);
							break;
						default:
							break;
						}
					}
				});
			}
		});
		timer.setInitialDelay(1000);
		timer.start();

		frame.setSize(830, 658);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 175, 214, 220, 206, 0 };
		gridBagLayout.rowHeights = new int[] { 32, 124, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Controller");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Gas1");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Gas2");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 0;
		frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Gas3");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 0;
		frame.getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 1, true));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 61, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JButton btnNewButton = new JButton("Generate car");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);

		cntrlTextArea = new JTextArea();
		GridBagConstraints gbc_cntrlTextArea = new GridBagConstraints();
		gbc_cntrlTextArea.fill = GridBagConstraints.BOTH;
		gbc_cntrlTextArea.gridx = 0;
		gbc_cntrlTextArea.gridy = 1;
		panel.add(cntrlTextArea, gbc_cntrlTextArea);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		gas1_slider = new JSlider();
		gas1_slider.setValue(0);
		gas1_slider.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_gas1_slider = new GridBagConstraints();
		gbc_gas1_slider.insets = new Insets(0, 0, 5, 0);
		gbc_gas1_slider.gridx = 0;
		gbc_gas1_slider.gridy = 0;
		panel_1.add(gas1_slider, gbc_gas1_slider);

		gas1_spinner = new JSpinner();
		GridBagConstraints gbc_gas1_spinner = new GridBagConstraints();
		gbc_gas1_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_gas1_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_gas1_spinner.gridx = 0;
		gbc_gas1_spinner.gridy = 1;
		panel_1.add(gas1_spinner, gbc_gas1_spinner);

		gas1_textArea = new JTextArea();
		GridBagConstraints gbc_gas1_textArea = new GridBagConstraints();
		gbc_gas1_textArea.fill = GridBagConstraints.BOTH;
		gbc_gas1_textArea.gridx = 0;
		gbc_gas1_textArea.gridy = 2;
		panel_1.add(gas1_textArea, gbc_gas1_textArea);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 1;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		gas2_slider = new JSlider();
		gas2_slider.setValue(0);
		gas2_slider.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_gas2_slider = new GridBagConstraints();
		gbc_gas2_slider.insets = new Insets(0, 0, 5, 0);
		gbc_gas2_slider.gridx = 0;
		gbc_gas2_slider.gridy = 0;
		panel_2.add(gas2_slider, gbc_gas2_slider);

		gas2_spinner = new JSpinner();
		GridBagConstraints gbc_gas2_spinner = new GridBagConstraints();
		gbc_gas2_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_gas2_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_gas2_spinner.gridx = 0;
		gbc_gas2_spinner.gridy = 1;
		panel_2.add(gas2_spinner, gbc_gas2_spinner);

		gas2_textArea = new JTextArea();
		GridBagConstraints gbc_gas2_textArea = new GridBagConstraints();
		gbc_gas2_textArea.fill = GridBagConstraints.BOTH;
		gbc_gas2_textArea.gridx = 0;
		gbc_gas2_textArea.gridy = 2;
		panel_2.add(gas2_textArea, gbc_gas2_textArea);

		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 1;
		frame.getContentPane().add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		gas3_slider = new JSlider();
		gas3_slider.setValue(0);
		gas3_slider.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_gas3_slider = new GridBagConstraints();
		gbc_gas3_slider.insets = new Insets(0, 0, 5, 0);
		gbc_gas3_slider.gridx = 0;
		gbc_gas3_slider.gridy = 0;
		panel_3.add(gas3_slider, gbc_gas3_slider);

		gas3_spinner = new JSpinner();
		GridBagConstraints gbc_gas3_spinner = new GridBagConstraints();
		gbc_gas3_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_gas3_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_gas3_spinner.gridx = 0;
		gbc_gas3_spinner.gridy = 1;
		panel_3.add(gas3_spinner, gbc_gas3_spinner);

		gas3_textArea = new JTextArea();
		GridBagConstraints gbc_gas3_textArea = new GridBagConstraints();
		gbc_gas3_textArea.fill = GridBagConstraints.BOTH;
		gbc_gas3_textArea.gridx = 0;
		gbc_gas3_textArea.gridy = 2;
		panel_3.add(gas3_textArea, gbc_gas3_textArea);
		frame.setVisible(true);
	}
}
