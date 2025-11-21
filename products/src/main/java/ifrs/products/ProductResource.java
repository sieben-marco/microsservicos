package ifrs.products;

import ifrs.products.models.Product;

import java.net.URI;
import java.util.Map;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;

@Path("/products")
public class ProductResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response list() {
    return Response.ok(Product.listAll())
        .build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(Long id) {
    Product entity = Product.findById(id);
    if (entity == null) {
      return Response.status(Status.NOT_FOUND)
          .build();
    }
    return Response.ok(entity)
        .build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response create(Product newProduct) {
    if (newProduct.id != null) {
      final int UNPROCESSABLE_ENTITY = 422;
      return Response.status(UNPROCESSABLE_ENTITY)
          .entity(Map.of("message", "O ID deve ser nulo ao criar um novo produto."))
          .build();
    }
    Product.persist(newProduct);
    URI uri = UriBuilder.fromPath("/products/{id}")
        .build(newProduct.id);
    return Response.created(uri)
        .entity(newProduct)
        .build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response update(Long id, Product updatedProduct) {
    Product entity = Product.findById(id);
    if (entity == null) {
      return Response.status(Status.NOT_FOUND)
          .build();
    }
    entity.name = updatedProduct.name;
    entity.description = updatedProduct.description;
    entity.price = updatedProduct.price;
    return Response.ok(entity)
        .build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response delete(Long id) {
    Product entity = Product.findById(id);
    if (entity == null) {
      return Response.status(Status.NOT_FOUND)
          .build();
    }
    entity.delete();
    return Response.noContent()
        .build();
  }
}