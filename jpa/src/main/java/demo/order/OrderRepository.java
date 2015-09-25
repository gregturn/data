package demo.order;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("cloud-native-java-mongodb")
@RibbonClient()
public interface OrderRepository {

	@RequestMapping(method = RequestMethod.GET, value = "/orders")
	PagedResources<Resource<Order>> findAll();

	@RequestMapping(method = RequestMethod.POST, value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
	<S extends Order> S save(S entity);

	@RequestMapping(method = RequestMethod.PUT, value = "/orders")
	<S extends Order> Iterable<S> save(Iterable<S> entities);

	@RequestMapping(method = RequestMethod.GET, value = "/orders/{id}")
	Order findOne(String s);

	@RequestMapping(method = RequestMethod.DELETE, value = "/orders")
	void delete(String s);

}
