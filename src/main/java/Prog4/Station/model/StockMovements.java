package Prog4.Station.model;

import java.time.Instant;

public class StockMovements {
    private int stockMovementId;
    private int stockId;
    private int stationId;
    private String type;
    private int quantity;
    private Instant datetime;

    public StockMovements(int stockMovementId, int stockId, int stationId, String type, int quantity, Instant datetime) {
        this.stockMovementId = stockMovementId;


       this.stockId=stockId;
        this.stationId=stationId;
        this.type = type;
        this.quantity = quantity;
        this.datetime = datetime;
    }

    public int getStockMovementId() {
        return stockMovementId;
    }

    public void setStockMovementId(int stockMovementId) {
        this.stockMovementId = stockMovementId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Instant getDatetime() {
        return datetime;
    }

    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "StockMovements{" +
                "stockMovementId=" + stockMovementId +
                ", stockId=" + stockId +
                ", stationId=" + stationId +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", datetime=" + datetime +
                '}';
    }
}
