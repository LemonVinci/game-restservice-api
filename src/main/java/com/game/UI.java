package com.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class UI {
	
	private static final int DOT = 200, DASH = DOT * 3, FREQ = 800;
	static JTextField tfMorse;
	static JTextField tfHuman;
	
	
	public static void main (String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		JButton b=new JButton("Alta");
		b.setBounds(50, 100, 100, 40);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		final JTextField tfBinary = new JTextField();
		tfBinary.setBounds(50,150,150,20);
		
		tfMorse = new JTextField();
		tfMorse.setBounds(50,200,150,20);
		
		tfHuman = new JTextField();
		tfHuman.setBounds(50,250,150,20);
		
	
		final ButtonModel bModel = b.getModel();
		bModel.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
			
				if(bModel.isPressed()) {
				
				}
			}
		});
		
		JFrame f=new JFrame();
		f.add(b);
		f.add(tfBinary);
		f.add(tfMorse);
		f.add(tfHuman);
		
		f.setSize(400,800);
		f.setLayout(null);
		f.setVisible(true);
	}

}
