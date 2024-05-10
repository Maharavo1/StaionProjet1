CREATE TABLE StockMovements (
    stockMovementId INT PRIMARY KEY,
    productId INT,
    stationId INT,
    type VARCHAR(50),
    quantity INT,
    datetime TIMESTAMP,
    FOREIGN KEY (productId) REFERENCES Product(productId),
    FOREIGN KEY (stationId) REFERENCES Station(stationId)
);
