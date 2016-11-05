package jotto;

import javax.swing.event.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.event.*;
import java.awt.Toolkit;
import java.util.*;

class Viewer2 extends JPanel implements Observer {

    private JottoModel model;
    private JButton button;
    private JButton end;
    private JTextField text;
    private JLabel label;
    private int guess;
    private int remain;
    private JFrame frame = (JFrame)this.getParent();
    
     

    public Viewer2(JottoModel model1) {
    	this.model = model1;
        
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        guess = model.getCurrentWord()+1;
        int curGuess = guess-1;
        button = new JButton("Guess #" + guess);
        button.setSize(80,40);
        text = new JTextField("");
        remain = 10-guess;
        end = new JButton("?");
        end.setSize(80,40);
        end.setText("end");
        this.add(button);
        this.add(text);
        this.add(end);
        
        // setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)
        end.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "The word is: " + model.getSecret(), "GG", JOptionPane.INFORMATION_MESSAGE);
                model.finish();
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean test = model.check(text.getText());
                if (test == true) {
                	if (model.winGame() == true) {
                		JOptionPane.showMessageDialog(frame, "You WIN! target: " + model.getSecret(), "WIN", JOptionPane.INFORMATION_MESSAGE);
                	} else {
                		JOptionPane.showMessageDialog(frame, "You LOST! target: " + model.getSecret(), "Lost", JOptionPane.INFORMATION_MESSAGE);
                	}
                	System.exit(0);
                } else if (model.lost() == true) {
                	JOptionPane.showMessageDialog(frame, model.getMessage(), "Wrong", JOptionPane.INFORMATION_MESSAGE);
                	
                }
            }
        });
        text.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                boolean test = model.check(text.getText());
                if (test == true) {
                	if (model.winGame() == true) {
                		JOptionPane.showMessageDialog(frame, "You WIN! target: " + model.getSecret(), "WIN", JOptionPane.INFORMATION_MESSAGE);
                	} else {
                		JOptionPane.showMessageDialog(frame, "You LOST! target: " + model.getSecret(), "Lost", JOptionPane.INFORMATION_MESSAGE);
                	}
                	System.exit(0);
                } else if (model.lost() == true){
                	JOptionPane.showMessageDialog(frame, model.getMessage(), "Wrong", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    
    public void update(Observable arg0, Object arg1) {
        guess = model.getCurrentWord()+1;
        remain = 10-guess;
        button.setText("Guess #" + guess);
        text.setText("");
    }
}

