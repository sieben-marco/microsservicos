package ifrs.orders.resource;

import ifrs.orders.entity.Order;

import java.net.URI;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/order")
public class OrderResource {

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
    Order.persist(newOrder);
    return Response.created(URI.create("/order/" + newOrder.id))
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
