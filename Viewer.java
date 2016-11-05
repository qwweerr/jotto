package jotto;

import javax.swing.*;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

class Viewer extends JPanel implements Observer{
	private JottoModel model;
	private JButton easy_hint;
	private JButton hint;
	private JButton button;
	private JButton cheat;
	private JButton difficulty;
	private JLabel lable;
	private JLabel remain;

	private String word;
	private String aid;
	private int number;

	public Viewer(JottoModel model1){
		this.model = model1;
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		easy_hint = new JButton("Easy Hint");
		hint = new JButton("More Hint");
		button = new JButton("Letters used");
		cheat = new JButton("Cheat");
		lable = new JLabel();
		remain = new JLabel();
		difficulty = new JButton("difficulty");

		this.add(cheat);
		this.add(button);
		this.add(easy_hint);
		this.add(hint);
		this.add(difficulty);
		//this.add(Box.createVerticalGlue());

		this.add(remain);
		this.add(lable);
		
		//this.add(Box.createVerticalStrut(10));

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				word = model.usedletters();
				lable.setText(word);
				//model.cleanup();
			}
		});

		cheat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				word = model.getSecret();
				lable.setText(word);
			}
		});

		easy_hint.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		aid = model.getEasyHint();
        		lable.setText(aid);
        	}
        });

        hint.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		aid = model.getHint();
        		lable.setText(aid);
        	}
        });

        difficulty.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		aid = "Difficulty is: " + model.getDifficulty();
        		lable.setText(aid);
        	}
        });
    }
        
        public void update(Observable arg0, Object arg1){
        	number = model.max - model.getCurrentWord();
        	remain.setText(number + " times left.      ");
    	}
	
}