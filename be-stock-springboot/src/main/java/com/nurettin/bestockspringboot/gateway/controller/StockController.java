package com.nurettin.bestockspringboot.gateway.controller;

import com.nurettin.bestockspringboot.domain.Stock;
import com.nurettin.bestockspringboot.domain.StockBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StockController {

    List<Stock> stockList = new ArrayList<>();

    @CrossOrigin
    @RequestMapping("/stock")
    public List<Stock> getStockPrice(@RequestParam(value = "symbol", required = false) String symbol) {
        //this kind of initializations shouldn't be implemented in the controller.
        //this is just for demonstration.
        initStockList();

        List<Stock> resultList;

        if(symbol!=null){
            resultList = filterStocks(symbol);
        }else{
            resultList = stockList;
        }

        return resultList;
    }

    private void initStockList() {
        if(stockList.size()<1){
            stockList.add(StockBuilder.createStock("APPL"));
            stockList.add(StockBuilder.createStock("GOOGL"));
            stockList.add(StockBuilder.createStock("NVDA") );
        }

    }

    private List<Stock> filterStocks(String symbol) {
        return stockList.stream()
                .filter(stock -> symbol.equals(stock.getSymbol()))
                .collect(Collectors.toList());
    }


}
