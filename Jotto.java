package jotto;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.Color;

public class Jotto{
	private static final Dimension preferredSize = new Dimension(800,300);
	private static final Dimension minSize = new Dimension(700,150);
	private static final Dimension maxSize = new Dimension(1000,800);

	/*public class NestedLayout extends JPanel {
		public NestedLayout(Viewer v, Viewer1 v1, Viewer2 v2, JPanel north, JPanel west, JPanel east) {
			super();
			this.setBackground(Color.GRAY);


        	north.setBoarder(BorderFactory.createTitledBorder("Word List"));
        	north.setSize(100,100);
			west.setBoarder(BorderFactory.createTitledBorder("Guess Here"));
			west.setSize(50,50);
			east.setBoarder(BorderFactory.createTitledBorder("Hint"));
			east.setSize(150,150);

			north.add(v, BorderLayout.EAST);
			west.add(v1, BorderLayout.CENTER);
			east.add(v2, BorderLayout.NORTH);
		
			this.setLayout(new BorderLayout());
			this.add(north, BorderLayout.NORTH);
			this.add(east, BorderLayout.EAST);
			this.add(west, BorderLayout.WEST);
		}
	}*/


	public static void main(String[] args) throws InterruptedException{
		JottoModel model;
		JFrame frame = new JFrame("Jotto Game");
		InputStream input = IWordList.class.getResourceAsStream("words.txt");
		Scanner in = new Scanner(new InputStreamReader(input));

		String difficulty = JOptionPane.showInputDialog(frame,"Chose the difficulty"," (0-2)",JOptionPane.QUESTION_MESSAGE);
			if(difficulty.equals("0") || difficulty.equals("1") || difficulty.equals("2")){
				int i = Integer.parseInt(difficulty);
				model = new JottoModel(in,i);
			}else{
				model = new JottoModel(in);
			}

			while(Integer.parseInt(difficulty) > 2 || Integer.parseInt(difficulty) < 0){
				difficulty = JOptionPane.showInputDialog(frame,"Chose the right level,"," thank you!",JOptionPane.QUESTION_MESSAGE);
			}

		Viewer view = new Viewer(model);
		Viewer1 v1 = new Viewer1(model);
		Viewer2 v2= new Viewer2(model);

		model.addObserver(view);
		model.addObserver(v1);
		model.addObserver(v2);

		model.notifyObservers();

		JPanel p = new JPanel(new BorderLayout());
		frame.setBackground(Color.GRAY);
		frame.getContentPane().add(p);
		p.add(view, BorderLayout.NORTH);
		p.add(v1, BorderLayout.CENTER);
		p.add(v2, BorderLayout.SOUTH);



		frame.setPreferredSize(Jotto.preferredSize);
        frame.setMinimumSize(Jotto.minSize);
        frame.setMaximumSize(Jotto.maxSize);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

	}
}