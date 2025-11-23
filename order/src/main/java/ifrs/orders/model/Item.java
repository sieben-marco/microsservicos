package ifrs.orders.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item extends PanacheEntity {
  public Long productId;
  public int quantity;
}
