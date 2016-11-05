package jotto;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.io.*;
import java.awt.Font;

class Viewer1 extends JPanel implements Observer {
    private JottoModel model;
    private int guess;
    private int partial;
    private int exact;
    private String lastGuess;
    private JTable table = new JTable(11,3);
    private JLabel label = new JLabel();
    
    
    
    public Viewer1(JottoModel model1) {
        this.model = model1;
    	label.setFont(new Font("Serif", Font.BOLD, 40));
    	
        
        
        table.setEnabled(false); 

        
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.SOUTH);
        this.add(table, BorderLayout.NORTH);
    	
        
        table.setValueAt("Guessed", 0, 0);
    	table.setValueAt("Partial", 0, 1);
    	table.setValueAt("Exact", 0, 2);
        
        
        
    }


    public void update(Observable arg0, Object arg1) {
    	guess = model.getCurrentWord();
    	if (guess > 0) { 
    		partial = model.getPartial();
    		exact = model.getExact();
    		lastGuess = model.getLastGuess();
            //System.out.println(lastGuess);
    		table.setValueAt(lastGuess, guess, 0);
    		table.setValueAt(partial, guess, 1);
    		table.setValueAt(exact, guess, 2);
    	}
    	label.setText(lastGuess);
    }
}

