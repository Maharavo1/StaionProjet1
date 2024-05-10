package Prog4.Station.model;

public class ProductTemplate {
    private  int productTemplateId;
    private double price;
    private String name;


    public ProductTemplate(int productTemplateId, double price ,String name) {
        this.productTemplateId = productTemplateId;
        this.price = price;
        this.name = name;
    }


    public int getProductTemplateId() {
        return productTemplateId;
    }



    public void setProductTemplateId(int productTemplateId) {
        this.productTemplateId = productTemplateId;
    }

    public void setIdProductTemplate(int idProductTemplate) {
        this.productTemplateId = idProductTemplate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductTemplate{" +
                "productTemplateId=" + productTemplateId +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
