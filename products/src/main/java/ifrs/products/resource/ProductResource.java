package ifrs.products.resource;

import ifrs.products.entity.Product;

import java.net.URI;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/products")
public class ProductResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Product> list() {
    return Product.listAll();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Product get(Long id) {
    Product entity = Product.findById(id);
    if (entity == null) {
      throw new NotFoundException();
    }
    return entity;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response create(Product newProduct) {
    if (newProduct.id != null) {
      throw new WebApplicationException("O ID deve ser nulo ao criar um produto.", 422);
    }
    Product.persist(newProduct);
    return Response.created(URI.create("/products/" + newProduct.id))
        .entity(newProduct)
        .build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Product update(Long id, Product updatedProduct) {
    Product entity = Product.findById(id);
    if (entity == null) {
      throw new NotFoundException();
    }
    if (updatedProduct.name == null) {
      throw new WebApplicationException("O nome do produto não pode ser nulo.", 422);
    }
    entity.name = updatedProduct.name;
    entity.price = updatedProduct.price;
    return entity;
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response delete(Long id) {
    Product entity = Product.findById(id);
    if (entity == null) {
      throw new NotFoundException();
    }
    entity.delete();
    return Response.noContent()
        .build();
  }
}