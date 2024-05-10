CREATE TABLE Product (
    productId SERIAL PRIMARY KEY,
    stationId INT,
    productTemplateId INT,
    FOREIGN KEY (stationId) REFERENCES Station(stationId),
    FOREIGN KEY (productTemplateId) REFERENCES ProductTemplate(productTemplateId)
);
