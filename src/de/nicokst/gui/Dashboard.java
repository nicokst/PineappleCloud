package de.nicokst.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 3831716327433440949L;
	private JPanel contentPane;
	private JTextField command;

	public Dashboard() {
		setTitle("PineappleCloud");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 774, 333);
		contentPane.add(scrollPane);
		
		JTextArea history = new JTextArea();
		history.setEditable(false);
		scrollPane.setViewportView(history);
		
		command = new JTextField();
		command.setEnabled(false);
		command.setBounds(10, 390, 679, 20);
		contentPane.add(command);
		command.setColumns(10);
		
		JButton send = new JButton("> SEND");
		send.setEnabled(false);
		send.setBounds(695, 387, 89, 23);
		contentPane.add(send);
		
		JButton start = new JButton("START CLOUD");
		start.setBounds(474, 11, 147, 23);
		contentPane.add(start);
		
		JButton stop = new JButton("STOP CLOUD");
		stop.setEnabled(false);
		stop.setBounds(631, 11, 141, 23);
		contentPane.add(stop);
		
		JLabel lblPineapplecloudVxx = new JLabel("PINEAPPLECLOUD vXX");
		lblPineapplecloudVxx.setBounds(10, 15, 156, 14);
		contentPane.add(lblPineapplecloudVxx);
		
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				start.setEnabled(false);
				stop.setEnabled(true);
				send.setEnabled(true);
				command.setEnabled(true);
				
			}
		});
	}
}
