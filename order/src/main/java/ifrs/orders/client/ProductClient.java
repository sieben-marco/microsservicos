package ifrs.orders.client;

import ifrs.orders.model.Product;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/products")
@RegisterRestClient(configKey = "product-api")
@ApplicationScoped
public interface ProductClient {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<Product> list();

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  Product get(Long id);
}
