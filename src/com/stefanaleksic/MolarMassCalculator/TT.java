package com.stefanaleksic.MolarMassCalculator;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 * Created by stefan987 on 7/9/13.
 */
public class TT {
	private boolean alreadyDidSpecial = false;
	private String text = "";
	private ArrayList<String> elements = new ArrayList<String>();
	private List<int[]> spots = new ArrayList<int[]>();

	public double calculateMolarMass(ArrayList<Element> defaultElements,
			String message)  throws Exception
	{
		this.text = message.replace(" ", "");

		String paraText = "";
		if(text.indexOf("(") + text.indexOf(")") != -2 && text.indexOf("(") + text.indexOf(")") < Math.max(text.indexOf("("), text.indexOf(")"))){
			throw new Exception();
		}
		if (text.indexOf("(") != -1 && text.indexOf(")") != -1) {
			paraText = text.substring(text.indexOf("(") + 1, text.indexOf(")"));
			String numAfterPara = "";
			int count = text.indexOf(")") + 1;
			while (count < text.length()
					&& Character.isDigit(text.charAt(count))) {

				numAfterPara += text.charAt(count);
				count++;
			}

			for (int i = 0; i < Integer.parseInt(numAfterPara); i++) {
				scanNoPara(paraText);
			}

			text = text.substring(0, text.indexOf("("))
					+ text.substring(text.indexOf(")") + numAfterPara.length()
							+ 1);
			scanNoPara(text);

		} else {
			scanNoPara(text);
		}

		double totalAtomicMass = 0;
		for (int i = 0; i < elements.size(); i++) {
			for (int j = 0; j < defaultElements.size(); j++) {
				if (elements.get(i).equals(defaultElements.get(j).toString())) {
					totalAtomicMass += (defaultElements.get(j).getAtomicMass());

				}
			}
		}
		return totalAtomicMass;

	}

	public void scanNoPara(String text) {
		int start = 0;
		int end = 0;

		for (int i = 0; i < text.toCharArray().length; i++) {

			if (end != 0)
				if (Character.isUpperCase(text.toCharArray()[i])) {
					spots.add(new int[] { start, end });
					start = i;
				}

			if (i == text.toCharArray().length - 1) {
				spots.add(new int[] { start, text.toCharArray().length });
			}
			end++;

		}
		int q = 0;

		for (int[] spot : spots) {
			String elementName = "";
			String numberAfter = "";
			for (int i = spot[0]; i < spot[1]; i++) {

				if (Character.isDigit(text.charAt(i))) {
					numberAfter += text.charAt(i);
				} else {
					elementName += text.charAt(i);
				}

			}

			if (!numberAfter.equals(""))
				for (int iteration = 0; iteration < Integer
						.parseInt(numberAfter); iteration++) {
					elements.add(elementName);
				}
			else
				elements.add(elementName);
		}
		spots.clear();
	}

	public void repeatElement(int amountToRepeat, String temp) {
		for (int i = 0; i < amountToRepeat; i++) {
			elements.add(temp);
		}

	}

}
