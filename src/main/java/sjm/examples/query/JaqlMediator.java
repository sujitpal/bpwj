package sjm.examples.query;

//import com.sun.java.swing.*;
import javax.swing.*;
import java.awt.event.*;
import sjm.engine.*;
import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This class controls the interaction of the visual
 * components in a <code>JaqlUe</code>.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0
 *
 * @see JaqlUe
 */ 
public class JaqlMediator implements ActionListener {
	protected JButton goButton;
	protected JButton clearButton;
	protected JTextArea metadataArea;
	protected JTextArea queryArea;
	protected JTextArea resultArea;

	protected Parser parser; 
	protected ChipSource chipSource; 
	protected Speller speller; 
/**
 * Parse the input as a query, prove it repeatedly,
 * and show its results.
 */
public void actionPerformed(ActionEvent e) {
	Object source = e.getSource();
	if (source == goButton) {
		try {
			actionPerformedUnsafe(e);
		} catch (Exception ex) {
			String text = ex.toString();
			if (ex.getMessage() != null) {
				text = ex.getMessage();
			}
			resultArea.append(text + "\n");
			return;
		}
	}
	if (source == clearButton) {
		resultArea.setText("");
	}
}
/*
 * This method provides all the "go" actions and relies on 
 * actionPerformed to handle exceptions.
 */
protected void actionPerformedUnsafe(ActionEvent e) {
	Assembly result = parseInput();
	if (result == null) {
		resultArea.append("\nCannot parse input text.");
		return;
	}
	QueryBuilder b = (QueryBuilder) result.getTarget();
	Query q = b.build(chipSource());
	showResults(q);
}
/*
 * Return a ChipSource, an axiom source for facts about
 * chips, customers, and orders. 
 */
protected ChipSource chipSource() {
	if (chipSource == null) {
		chipSource = new ChipSource();
	}
	return chipSource;
}
/**
 * Initialize this object, using the components of a 
 * <code>JaqlUe</code>. 
 */
public void initialize(	
	JButton goButton, 
	JButton clearButton, 
	JTextArea queryArea, 
	JTextArea resultArea,
	JTextArea metadataArea) {
		
	this.goButton = goButton;
	this.clearButton = clearButton;
	this.metadataArea = metadataArea;
	this.queryArea = queryArea;
	this.resultArea = resultArea;
}
/*
 * Apply the Jaql parser to the input.
 */
protected Assembly parseInput() {
	String s = queryArea.getText();
	Assembly a = new TokenAssembly(s);
	a.setTarget(new QueryBuilder(speller()));
	Assembly result = parser().completeMatch(a);
	return result;
}
/*
 * The parser to use for queries. 
 */
protected Parser parser() {
	if (parser == null) {
		parser = new JaqlParser(speller()).start();
	}
	return parser;
}
/*
 * Show the results of a successfully built query.
 */
protected void showResults(Query q) {
	Structure head = q.head();
	DynamicRule tail = q.resolvent();
	while (true) {
		if (!tail.canFindNextProof()) {
			break;
		}
		for (int i = 0; i < head.arity(); i++) {
			if (i > 0) {
				resultArea.append(", ");
			}
			Object value = head.terms()[i].eval();
			resultArea.append(value.toString());
		}
		resultArea.append("\n");
	}
}
/*
 * Return a speller to use for checking class and variable
 * names;
 */
protected Speller speller() {
	if (speller == null) {
		speller = new ChipSpeller();
	}
	return speller;
}
}
