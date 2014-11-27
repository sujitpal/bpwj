package sjm.examples.query;

//import com.sun.java.swing.*;
//import com.sun.java.swing.border.*;
//import com.sun.java.swing.text.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import sjm.utensil.SwingUtensil;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This is a simple user environment (UE) for queries 
 * written in the Jaql language. This UE applies Jaql 
 * queries to the ChipSource axiom source.
 * <p>
 * This class contains just the methods that create Swing 
 * components, and uses a "mediator" to control how the 
 * components interact.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0
 *
 * @see JaqlMediator
 */ 

public class JaqlUe {
	protected JaqlMediator mediator;
	protected JButton goButton;
	protected JButton clearButton;
	protected JTextArea metadataArea;
	protected JTextArea queryArea;
	protected JTextArea resultArea;
	protected static int PREFERREDWIDTH = 600;
/*
 * The panel for the "Go!" and "Clear" buttons.
 */
protected JPanel buttonPanel() {
	JPanel p = new JPanel();
	p.setLayout(new BorderLayout());
	p.add(goButton(), "North");
	p.add(clearButton(), "South");
	p.setBorder(new EmptyBorder(10, 6, 5, 6));
	return p;
}
/*
 * The "Clear" button.
 */
protected JButton clearButton() {
	if (clearButton == null) {
		clearButton = new JButton("Clear");
		clearButton.addActionListener(mediator());
		clearButton.setFont(SwingUtensil.ideFont());
	}
	return clearButton;
}
/*
 * The "Go!" button. This method also establishes "Ctrl-G"
 * as a shortcut for pressing the button.
 */
protected JButton goButton() {
	if (goButton == null) {
		goButton = new JButton("Go!");
		goButton.addActionListener(mediator());
		goButton.setFont(SwingUtensil.ideFont());
		
		// ctrl-g keystroke:
		KeyStroke ctrlg = KeyStroke.getKeyStroke(
			KeyEvent.VK_G, InputEvent.CTRL_MASK);
		
		goButton.registerKeyboardAction(
			mediator(),
			ctrlg,
			JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	return goButton;
}
/*
 * The split pane that contains the query area and the 
 * result area.
 */
protected JSplitPane ioPane() {
	Dimension min = new Dimension(PREFERREDWIDTH, 80);
	Dimension pref = new Dimension(PREFERREDWIDTH, 180);
	
	JPanel q = SwingUtensil.textPanel(
		"Query", queryArea(), pref, min);
	JPanel r = SwingUtensil.textPanel(
		"Results", resultArea(), pref, min);

	JSplitPane jsp = new JSplitPane(
		JSplitPane.VERTICAL_SPLIT, false, q, r);
	jsp.setDividerSize(3);
	return jsp;
}
/**
 * Launch the interactive development environment.
 */
public static void main(String[] args) {
	sjm.utensil.SwingUtensil.launch(
		new JaqlUe().mainPanel(), "Jaql and Chips");
}
/*
 * The main panel, which contains all components.
 */
protected JPanel mainPanel() {
	JPanel p = new JPanel();
	p.setLayout(new BorderLayout());
	p.add(upperPanel(), "Center");
	p.add(metadataPanel(), "South");
	return p;
}
/*
 * The object that controls the component interactions.
 */
protected JaqlMediator mediator() {
	if (mediator == null) {
		mediator = new JaqlMediator();
		mediator.initialize(
			goButton(), 
			clearButton(),
			queryArea(), 
			resultArea(), 
			metadataArea());		
	}
	return mediator;
}
/*
 * The metadata text area.
 */
protected JTextArea metadataArea() {
	if (metadataArea == null) {
		metadataArea = SwingUtensil.ideTextArea();
		ChipSource cs = new ChipSource();
		metadataArea.append(cs.queryChip() + "\n");
		metadataArea.append(cs.queryCustomer() + "\n");
		metadataArea.append(cs.queryOrder() + "\n");
	}
	return metadataArea;
}
/*
 * The panel that contains the metadata text area.
 */
protected JPanel metadataPanel() { 
	return SwingUtensil.textPanel(
		"Metadata", 
		metadataArea(), 
		new Dimension(PREFERREDWIDTH, 120), 
		new Dimension(PREFERREDWIDTH, 80));
}
/*
 * The input text area.
 */
protected JTextArea queryArea() {
	if (queryArea == null) {
		queryArea = SwingUtensil.ideTextArea();
		queryArea.setText( 			
			"select ChipName, PricePerBag from Chip \n" +
			"where Oil != \"Sunflower\"");
	}
	return queryArea;
}
/*
 * The output text area.
 */
protected JTextArea resultArea() {
	if (resultArea == null) {
		resultArea = SwingUtensil.ideTextArea();
	}
	return resultArea;
}
/*
 * The panel that contains the query area, the result area 
 * and the buttons.
 */
protected JPanel upperPanel() {
	JPanel p = new JPanel();
	p.setLayout(new BorderLayout());
	p.add(ioPane(), "Center");
	p.add(buttonPanel(), "East");
	return p;
}
}
