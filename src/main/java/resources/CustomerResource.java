package resources;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Customer;
import services.CustomerService;

@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CustomerResource {

	CustomerService customerService;

	@Inject
	public CustomerResource(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GET
	public List<Customer> findAll() {
		return customerService.findAll();
	}
	
	@GET
	@Path("{id}")
	public Customer findCustomerById(@PathParam("id") Long id) {
		return customerService.findCustomerById(id);
	}
	
	@PUT
	public Response updateCustomer(Customer customer) {
		customerService.updateCustomer(customer);
		return Response.status(204).build();
	}
	
	@POST
	public Response createCustomer(Customer customer) {
		customerService.createCustomer(customer);
		return Response.status(204).build();
	}
	
	public Response deleteCustomer(Long id) {
		customerService.deleteCustomer(id);
		return Response.status(204).build();
	}
	
	
}
