package ifrs.products;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse;

import ifrs.products.models.Product;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/products")
public class ProductResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public RestResponse<List<Product>> list() {
    return RestResponse.ok(Product.listAll());
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public RestResponse<Product> get(Long id) {
    Product entity = Product.findById(id);
    if (entity == null) {
      return RestResponse.notFound();
    }
    return RestResponse.ok(entity);
  }
}
