package com.game.enums;

import java.util.NoSuchElementException;
import java.util.stream.Stream;


public enum GameEnum {
	
	A("A",".-"),
    B("B","-..."),
    C("C","-.-."),
    D("D","-.."),
    E("E","."),
    F("F","..-."),
    G("G","--."),
    H("H","...."),
    I("I",".."),
    J("J",".---"),
    K("K","-.-"),
    L("L",".-.."),
    M("M","--"),
    N("N","-."),
    O("O","---"),
    P("P",".--."),
    Q("Q","--.-"),
    R("R",".-."),
    S("S","..."),
    T("T","-"),
    U("U","..-"),
    V("V","...-"),
    W("W",".--"),
    X("X","-..-"),
    Y("Y","-.--"),
    Z("Z","--.."),
    M0("0", "-----"),
    M1("1", ".----"),
    M2("2", "..---"),
    M3("3", "...--"),
    M4("4", "....-"),
    M5("5", "....."),
    M6("6", "-...."),
    M7("7", "--..."),
    M8("8", "---.."),
    M9("9", "----.");

	//Letra no es del tipo char debido a que java8.stream() tiene una mala compatibilidad con tipos primitivos de datos
	private String letra;

	private String codigo;

	private GameEnum(String letra, String codigo) {
		this.letra = letra;
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public static String byCodigo(String codigo) throws IllegalArgumentException, NoSuchElementException 
	{
		if (!codigo.isEmpty()) {
			return Stream.of(GameEnum.values()).filter(tc -> tc.codigo.equals(codigo)).findFirst().get().getLetra();
		}
		return null;
	}
	
	public static String byLetra(String letra) throws IllegalArgumentException, NoSuchElementException 
	{
		if (!letra.isEmpty()) {
			return Stream.of(GameEnum.values()).filter(tc -> tc.letra.equals(letra)).findFirst().get().getCodigo();
		}
		return null;
	}
		
}
