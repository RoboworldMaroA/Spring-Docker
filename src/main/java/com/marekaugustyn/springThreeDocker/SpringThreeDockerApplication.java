package com.marekaugustyn.springThreeDocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class SpringThreeDockerApplication {

    //get data from Customer Repository, this class allows you to get a data from database
    private final CustomerRepository customerRepository;

	public SpringThreeDockerApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	//main method
	public static void main(String[] args) {

		SpringApplication.run(SpringThreeDockerApplication.class, args);
	}

	@GetMapping
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}

    //????????????????????????????????????????????????????????????????????
	//this record could be separate class with the setters and getters
	//Why we ca not use same class customer that we have implemented????????????
	record NewCustomerRequest(
			String name,
			String email,
			Integer age
	){}

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request){
		//create customer object so we have acces to the elements in the customer class
		Customer customer = new Customer();
		//get a name from customer and save to the name in the customer
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setAge(request.age());
		customerRepository.save(customer);
	}


    @DeleteMapping("/{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerRepository.deleteById(id);

	}


	@PutMapping("/{customerId}")
	public void updateCustomer(@PathVariable("customerId") Integer id,
							   @RequestBody NewCustomerRequest request){
		       customerRepository.findById(id)
				.map(customer -> {
					customer.setName(request.name());
					customer.setEmail(request.email());
					customer.setAge(request.age());
					return customerRepository.save(customer);
				})
				.orElseGet(() -> {
					Customer customer = new Customer();
					customer.setName(request.name());
					customer.setEmail(request.email());
					customer.setAge(request.age());
					return customerRepository.save(customer);
				});
	}




//	@GetMapping("/")
//	public GreetResponse hi(){
//
//		return new GreetResponse(
//				"Hi Marek",
//				List.of("aa","aa"),
//				new Person("Maro",22));
//	}
//
//	record Person(String name, int age){}
//
//	record GreetResponse(
//			String Greet,
//			List<String> favoriteProgramminLanguages,
//			Person person
//	){ }

}
