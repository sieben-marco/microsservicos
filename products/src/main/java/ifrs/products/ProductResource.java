package ifrs.products;

import ifrs.products.models.Product;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


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

  
}
