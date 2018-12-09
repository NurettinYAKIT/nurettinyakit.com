package com.nurettin.bestockspringboot.domain;

public class StockBuilder {

    public static Stock createStock(String symbol){
        return new Stock(symbol,getPrice(symbol));
    }

    private static String getPrice(String symbol){
        String price;

        switch (symbol) {
            case "APPL":
                price = "178.58";
                break;
            case "GOOGL":
                price = "1,094.43";
                break;
            case "NVDA":
                price= "163.42";
                break;
            default:
                price="unknown";
        }

        return price;
    }
}
