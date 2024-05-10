package Prog4.Station.model;

import java.time.Instant;

public class Stock {

    private int stockId;
    private int stationId;
   private int productTemplateId;
   private Instant datetime;
   private int quantity;

    public Stock(int stockId, int stationId, int productTemplateId, Instant datetime, int quantity) {
        this.stockId = stockId;
        this.stationId = stationId;
        this.productTemplateId = productTemplateId;
        this.datetime = datetime;
        this.quantity = quantity;
    }

    public Instant getDatetime() {
        return datetime;
    }

    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getProductTemplateId() {
        return productTemplateId;
    }

    public void setProductTemplateId(int productTemplateId) {
        this.productTemplateId = productTemplateId;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", stationId=" + stationId +
                ", productTemplateId=" + productTemplateId +
                ", datetime=" + datetime +
                ", quantity=" + quantity +
                '}';
    }
}
