package ifrs.products.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Product extends PanacheEntity {

  @Column(nullable = false)
  public String name;
  
  public double price;
}
