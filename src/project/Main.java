package project;

import GUI.GUI;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Championship NBA = new Championship();
		GUI graphic = new GUI("NBA");
		graphic.buildTabbedPane();
	}
}
