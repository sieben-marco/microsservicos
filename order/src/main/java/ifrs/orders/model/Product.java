package ifrs.orders.model;

public record Product(Long id, String name, Double price) {
  public Long getId() {
    return id;
  }

  public Double getPrice() {
    return price;
  }
}
