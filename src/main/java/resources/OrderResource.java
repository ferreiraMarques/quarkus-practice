package resources;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Orders;
import services.OrderService;

@Path(value = "orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OrderResource {

	OrderService orderService;
	
	@Inject
	public OrderResource(OrderService orderService) {
		this.orderService = orderService;
	}

	@GET
	public List<Orders> getAll(@QueryParam("customerId") long customerId) {
		return orderService.getAll(customerId);
	}
	
	@GET
	@Path("{order}")
	public Orders getById(@QueryParam("order") long orderId) {
		return orderService.getById(orderId);
	}
	
	@POST
	@Path("/{customer}")
	public Response create(Orders order, @PathParam("customer") Long customerId) {
		orderService.create(order, customerId);
		return Response.status(204).build();
	}
	
	@PUT
	public Response update(Orders order) {
		orderService.update(order);
		return Response.status(204).build();
	}

	@DELETE
	@Path("{order}")
	public Response delete(@PathParam("order") Long orderId) {
		orderService.delete(orderId);
		return Response.status(204).build();
	}
}
