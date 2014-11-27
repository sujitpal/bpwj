package sjm.examples.logic;

//import com.sun.java.swing.*;
//import com.sun.java.swing.border.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import sjm.engine.*;
import sjm.utensil.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 */

/**
 * This class supports a <code>LogikusIde</code> object, 
 * handling the interaction of the IDE's components. 
 * <p> 
 * An object of the IDE (Interactive Development 
 * Environment) has text areas for a Logikus program, a 
 * query against the program, and for the results of a 
 * query. 
 * <p> 
 * When a user clicks on the "Next" or "Rest" button, the 
 * mediator parses the program (if it has changed), and 
 * parses the query (if the query has changed). The 
 * mediator then proves the query against the program. 
 * After each proof, the mediator displays the query's 
 * variables in the results text area. 
 * <p> 
 * The mediator uses a separate thread to prove the query.  
 * While this thread conducts a proof, the mediator 
 * disables most of the IDE's components, except for the 
 * "Halt" button, which stops the proof thread.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
 
public class LogikusMediator
	implements ActionListener, Runnable {

	protected JButton proveNextButton;
	protected JButton proveRestButton;
	protected JButton haltButton;
	protected JButton clearProgramButton;
	protected JButton clearResultsButton;
		
	protected JTextArea programArea;
	protected JTextArea resultsArea;
	protected JTextArea queryArea;

	protected boolean proveRemaining;
	
	protected Thread computeThread;
	
	protected String lastProgramText = null;
	protected String lastQueryText = null;
	protected Program program;
	protected Query query;
/**
 * This method reacts, when the user presses one of the
 * IDE's buttons.
 *
 * @param   ActionEvent   the event
 */
public void actionPerformed(ActionEvent event) {
	Object object = event.getSource();
	if (object == clearResultsButton) {
		resultsArea.selectAll();
		resultsArea.copy();
		resultsArea.setText("");
	}
	if (object == clearProgramButton) {
		programArea.selectAll();
		programArea.copy();
		programArea.setText("");
		queryArea.setText("");
	}

	if (object == proveNextButton || 
		object == proveRestButton) {
			
		proveRemaining = (object == proveRestButton);
		conductProof();
	}
	if (object == haltButton) {
		if (computeThread != null) {
			computeThread.stop();
		}
		computeThread = null;
		setComputing(false);
	}
}
/*
 * Parse the program and query (if they have changed)
 * and proved the query in a separate thread.
 */
protected void conductProof() {
	setComputing(true);
	try {
		parseProgramAndQuery();
	} catch (Exception e) {
		String text = e.toString();
		if (e.getMessage() != null) {
			text = e.getMessage();
		}
		resultsArea.append(text + "\n");
		setComputing(false);
		return;
	}
	computeThread = new Thread(this);
	computeThread.start();
	// this thread will setComputing(false) in due time.
}
/**
 * Appends the given line to the results text area,
 * scheduling this event with the event-dispatching thread.
 *
 * @param   String   the string to append to the results
 *                   area
 */
protected void display(final String s) {
	// Using invokeAndWait() keeps appends from outrunning
	// the event dispatch thread.
	
	Runnable r = new Runnable() {
		public void run() {
			resultsArea.append(s);
		}
	};
	try {
		SwingUtilities.invokeAndWait(r);
	} catch (Exception e) {
		resultsArea.append(e.getMessage());
	}
}
/**
 * Make the IDE's GUI components available.
 */
public void initialize  
 (
	JButton proveNextButton, 
	JButton proveRestButton, 
	JButton haltButton, 
	JButton clearProgramButton, 
	JButton clearResultsButton, 
	JTextArea programArea, 
	JTextArea resultsArea, 
	JTextArea queryArea) {
		
	this.proveNextButton = proveNextButton; 
	this.proveRestButton = proveRestButton; 
	this.haltButton = haltButton; 
	this.clearProgramButton = clearProgramButton; 
	this.clearResultsButton = clearResultsButton; 
	this.programArea = programArea; 
	this.resultsArea = resultsArea; 
	this.queryArea = queryArea;
}
/*
 * Parses the program and query texts.
 */
protected void parseProgramAndQuery() {

	boolean programChanged = false;
	String programText = programArea.getText();
	programChanged = 
		(lastProgramText == null) ||
		(!lastProgramText.equals(programText));
	if (programChanged) {
		program = LogikusFacade.program(programText);
	}	
	lastProgramText = programText;
		
	String queryText = queryArea.getText();
	
// create a fresh query if the program changes or the
// query text changes

	if (programChanged || 
		(lastQueryText == null) ||
		(!lastQueryText.equals(queryText))) {
		query = LogikusFacade.query(queryText, program);
	}	
	lastQueryText = queryText;
}
/*
 * Proves the query against the program.
 */
protected void proveNext() {
	if (query.canFindNextProof()) {
		Unification vars = query.variables();
		if (vars.size() == 0) {
			display("yes\n");
		} else {
			display(vars + "\n");
		}
	} else {
		display("no\n");
	}
}
/* 
 * Proves the query against the program until no proofs
 * remain.
 */
protected void proveRemaining() {
	Unification vars = query.variables();
	while (query.canFindNextProof()) {
		if (vars.size() == 0) {
			display("yes\n");
			return;
		} else {
			display(vars + "\n");
		}
	}
	display("no\n");
}
/**
 * Proves the query against the program.
 */
public void run() {
	try {
		if (proveRemaining) {
			proveRemaining();
		} else {
			proveNext();
		}
	} catch (Exception e) {
		resultsArea.append(e.getMessage());
	} finally {
		setComputing(false);
	}
}
/*
 * Sets the state of the IDE to computing or not. Most of the 
 * IDE's controls are grayed out during computation of a 
 * program.
 *
 * @param   boolean  if true, indicates that a proof thread 
 *                   is finding one or more proofs
 */
protected void setComputing(boolean computing) {

	// computing means everything is disabled, except "Halt"
	
	proveNextButton.setEnabled(!computing);
	proveRestButton.setEnabled(!computing);
	clearProgramButton.setEnabled(!computing);
	clearResultsButton.setEnabled(!computing);
	programArea.setEnabled(!computing);
	resultsArea.setEnabled(!computing);
	queryArea.setEnabled(!computing);
	
	haltButton.setEnabled(computing);
}
}
