package ifrs.orders.resource;

import ifrs.orders.client.ProductClient;
import ifrs.orders.model.Item;
import ifrs.orders.model.Order;
import ifrs.orders.model.Product;

import java.net.URI;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orders")
public class OrderResource {

  @Inject
  @RestClient
  private ProductClient _productClient;

  // ===============
  // Pedidos
  // ===============

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Order> list() {
    return Order.listAll();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Order get(Long id) {
    Order entity = Order.findById(id);
    if (entity == null) {
      throw new NotFoundException();
    }
    return entity;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response create(Order newOrder) {
    if (newOrder.id != null) {
      throw new WebApplicationException("O ID deve ser nulo ao criar um pedido.", 422);
    }

    if (newOrder.items == null || newOrder.items.isEmpty()) {
      throw new WebApplicationException("O pedido deve conter ao menos um item.", 422);
    }

    List<Product> products = _productClient.list();

    newOrder.status = "ABERTO";
    newOrder.total = newOrder.items.stream()
        .mapToDouble(item -> {
          Product product = products.stream().
              filter(p -> p.getId().equals(item.productId))
              .findFirst()
              .orElseThrow(() -> new NotFoundException("Produto com ID " + item.productId + " não encontrado."));
          return product.getPrice() * item.quantity;
        })
        .sum();  
    Order.persist(newOrder);
    return Response.created(URI.create("/orders/" + newOrder.id))
        .entity(newOrder)
        .build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void delete(Long id) {
    Order entity = Order.findById(id);
    if (entity == null) {
      throw new NotFoundException();
    }
    entity.delete();
  }
}
