package com.javamsdt.clientdeal;

import java.text.ParseException;

import com.javamsdt.clientdeal.model.parser.CSVParser;

public class Runner {

	public static void main(final String[] args) throws ParseException {

		String filePath = "files/clientDeal.csv";
		CSVParser parser = new CSVParser();
		parser.getClientDeals(filePath).forEach(System.out::println);

	}

}
