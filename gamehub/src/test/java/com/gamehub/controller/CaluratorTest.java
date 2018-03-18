package com.gamehub.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CaluratorTest {
	@Test
	public void testSum() {
		double result = 0;
		Calcurator cal = new Calcurator();
		result = cal.sum(30, 30);
		assertEquals(60, result, 0);
	}
}
