package com.javamsdt.clientdeal.model.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javamsdt.clientdeal.domain.ClientDeal;

public class CSVParser {

	private static final Logger LOGGER = LogManager.getLogger();

	public List<ClientDeal> getClientDeals(final String filePath) {
		List<ClientDeal> clientDeals = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
			bufferedReader.readLine();

			LOGGER.info("Reading from CSV file Starts: ");

			String line = bufferedReader.readLine();
			while (line != null) {
				String[] attributes = line.split(",");

				String[] clientNameAndId = getNameAndId(attributes[0], "@");
				long clientId = Long.valueOf(clientNameAndId[1].trim());
				String clientName = clientNameAndId[0];

				String[] dealNameAndId = getNameAndId(attributes[1], "#");
				long dealId = Long.valueOf(dealNameAndId[1].trim());
				String dealName = dealNameAndId[0];

				LocalDateTime date = dateParser(attributes[2]);
				int accepted = Integer.valueOf(attributes[3]);
				int refused = Integer.valueOf(attributes[4]);

				ClientDeal clientDeal = new ClientDeal(clientId, clientName, dealId, dealName, date, accepted, refused);
				clientDeals.add(clientDeal);
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			LOGGER.error("Exception in coordinatesList parser in  CSVReader class......", e);
		}

		LOGGER.info("Reading from CSV file Done Succseffully ;) You have Stored " + clientDeals.size() + ", Objects");
		return clientDeals;
	}

	/**
	 * 
	 * @param toBeSplited String which we need to split it
	 * @param regexSplit  character which will be using it for the splitting
	 *                    proccess
	 * @return String Array contains of name and id
	 */
	private String[] getNameAndId(final String toBeSplited, final String regexSplit) {
		return toBeSplited.split(regexSplit);
	}

	/**
	 * 
	 * @param dateString to be parsed to LocalDateTime
	 * @return LocalDateTime instance after parsing
	 */
	private LocalDateTime dateParser(final String dateString) {

		String[] patterns = { "u-M-d HH:mm", "u-M-d H:mm" };
		boolean checkDateTimePattern = checkDateTimePattern(patterns, dateString);
		if (checkDateTimePattern) {
			return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(patterns[0]));
		} else {
			return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(patterns[1]));

		}

	}

	/**
	 * 
	 * @param patterns   array of patterns to be used for the DateTime check process
	 * @param dateString To be used for the check process
	 * @return true if the dateString date pattern equal the first pattern in the
	 *         array false if the dateString date pattern equal the second pattern
	 *         in the array
	 */
	private boolean checkDateTimePattern(final String[] patterns, final String dateString) {
		boolean formatCheck = true;
		for (String pattern : patterns) {
			try {
				DateTimeFormatter.ofPattern(pattern).parse(dateString);
				break;
			} catch (Exception e) {
				formatCheck = false;
				break;
			}
		}
		return formatCheck;
	}

}
