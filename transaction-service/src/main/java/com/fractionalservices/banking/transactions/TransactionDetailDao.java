package com.fractionalservices.banking.transactions;

import com.fractionalservices.banking.transactions.TransactionDetails;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TransactionDetailDao {

    private static Logger log = LoggerFactory.getLogger(TransactionDetailDao.class);
    private static Map<String, List<TransactionDetails>> transactionDetailsMap;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Map<String, List<TransactionDetails>> getAccountTransactions() {

        if (!CollectionUtils.isEmpty(transactionDetailsMap)) {
            log.info("Returning data from cache");
            return transactionDetailsMap;
        }
        transactionDetailsMap = new HashMap<>();
        JSONParser parser = new JSONParser();

        try {
            log.info("Reading data from file");

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("transactions.json");
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            JSONArray txns = (JSONArray) parser.parse(contents);


            txns.forEach(txnObj -> {
                TransactionDetails details = parseToTransactionDetails((JSONObject) txnObj);
                List<TransactionDetails> transactionDetails = transactionDetailsMap.get(details.getAcctNumber());
                if (transactionDetails == null) {
                    transactionDetails = new ArrayList<>();
                }
                transactionDetails.add(details);
                transactionDetailsMap.put(details.getAcctNumber(), transactionDetails);
            });
        } catch (ParseException e) {
            log.error("Error getting the transaction details : ", e);
        }
        return transactionDetailsMap;
    }

    private TransactionDetails parseToTransactionDetails(JSONObject txnObj) {
        log.debug("Preparing to parse the transaction elements");
        JSONObject txn = txnObj;
        String transactionId = (String) txn.get("transactionId");
        String amountAndCurrency = (String) txn.get("amount");
        String acctNumber = (String) txn.get("acctNumber");
        String date = (String) txn.get("valueDate");
        String description = (String) txn.get("description");

        // Amount
        String[] split = amountAndCurrency.split(" ");
        boolean isNegative = split[1].indexOf("-") != -1;
        String anountStr = split[1].replace("-", "");
        Double amount = Double.parseDouble(anountStr);
        if (isNegative) {
            amount = amount * -1;
        }

        // Value Date
        String currency = split[0];
        LocalDate valueDate = LocalDate.parse(date, formatter);
        TransactionDetails details = new TransactionDetails(transactionId, amount, currency, acctNumber, valueDate, description);
        return details;
    }
}
