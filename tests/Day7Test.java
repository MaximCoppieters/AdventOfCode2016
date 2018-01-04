package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import solution.day7.Day7;

class Day7Test {

	@Test
	void isAbaFoundCorrectly() throws FileNotFoundException {
		Day7 d = new Day7();
		
		assertTrue(d.isViewAbba("bddb"));
		assertFalse(d.isViewAbba("abaa"));
		assertFalse(d.isViewAbba("aaaa"));
		assertFalse(d.isViewAbba("aaaaaaa"));
	}
	
	@Test
	void isViewCreatedCorrectly() throws FileNotFoundException {
		Day7 d = new Day7();
		String view;
		String view2;
		String view3;
		
		view = d.createViewFromIndex("azerty", 0);
		view2 = d.createViewFromIndex("azerty", 1);
		view3 = d.createViewFromIndex("azerty", 5);
		
		assertEquals("azer", view);
		assertEquals("zert", view2);
		assertEquals("y", view3);
	}
	
	@Test
	void doesRegexCaptureGroup() throws FileNotFoundException {
		Day7 d = new Day7();
		
		String line = "e[qrsntvxhtfurcgcynx]oyswvu";
		String hypernet = d.getSequences(line, "\\[(.+?)\\]");
		
		assertEquals("qrsntvxhtfurcgcynx   ", hypernet, "Hypernets are captured wrongly");
	}
	
	@Test
	void doesRegexCaptureMultipleGroups() throws FileNotFoundException {
		Day7 d = new Day7();
		
		String line = "ee[qrsn]oys[wvdzaadza]uaa";
		String hypernet = d.getSequences(line, "\\[(.+?)\\]");
		
		assertEquals("qrsn   wvdzaadza   ", hypernet, "Hypernets are captured wrongly");
	}
	
	

}
