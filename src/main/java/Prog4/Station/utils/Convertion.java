package Prog4.Station.utils;

import Prog4.Station.model.Stock;

public class Convertion {
   public static double QuantityToLiter(double QantityTypeMoney , Stock stock){
       double priceOfProduct = Price.getPriceById(stock.getStockId());
       return Math.floor(QantityTypeMoney/priceOfProduct);
   }
}
