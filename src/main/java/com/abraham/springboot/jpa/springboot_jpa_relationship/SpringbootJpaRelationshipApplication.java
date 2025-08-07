package com.abraham.springboot.jpa.springboot_jpa_relationship;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.abraham.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.abraham.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.abraham.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.abraham.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.abraham.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.abraham.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.abraham.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import com.abraham.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.abraham.springboot.jpa.springboot_jpa_relationship.repositories.CourseRepository;
import com.abraham.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import com.abraham.springboot.jpa.springboot_jpa_relationship.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		manyToManyFind();

		manyToManyFindById();

		manyToManyRemove();

		manyToManyFindById();
	}

	@Transactional
	public void manyToManyRemove() {
		Student student1 = studentRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.removeCourse(course2);
		studentRepository.save(student1);
	}

	private void manyToManyFindById() {
		studentRepository.findById(1L).ifPresent(System.out::println);
		studentRepository.findById(2L).ifPresent(System.out::println);
		System.out.println(
				"/////////////////////////////////////////////////////////////////////////////////////////Course 1");
		courseRepository.findById(1L).ifPresent((course) -> {
			course.getStudents().forEach((student) -> {
				System.out.println(student.getName());
			});
		});
		System.out.println(
				"/////////////////////////////////////////////////////////////////////////////////////////Course 2");
		courseRepository.findById(2L).ifPresent((course) -> {
			course.getStudents().forEach((student) -> {
				System.out.println(student.getName());
			});
		});
	}

	@Transactional
	public void manyToManyFind() {

		Student student1 = studentRepository.findById(1L).get();
		Student student2 = studentRepository.findById(2L).get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.addCourse(course1).addCourse(course2);
		student2.addCourse(course1);

		studentRepository.save(student1);
		studentRepository.save(student2);
	}

	@Transactional
	public void manyToMany() {

		Student student1 = new Student("John", "Doe");
		Student student2 = new Student("Jane", "Doe");

		Course course1 = new Course("Math", "David");
		Course course2 = new Course("Science", "Francisco");
		Course course3 = new Course("History", "Maria");

		student1.addCourse(course1).addCourse(course2);
		student2.addCourse(course2).addCourse(course3);

		// studentRepository.save(student1);
		// studentRepository.save(student2);

		studentRepository.saveAll(List.of(student1, student2));

	}

	@Transactional
	public void oneToOneFindById() {
		System.out.println("Client");
		clientRepository.findById(3L).ifPresent(System.out::println);
		clientDetailsRepository.findById(1L).ifPresent(c -> {
			System.out.println("ClientDetails");
			System.out.println(c);
			System.out.println(c.getClient());
		});

		System.out.println("Aquí busca el 3L");
		clientRepository.findById(3L).ifPresent(c -> {
			System.out.println("Aquí le quita el detalle al client");
			c.removeClientDetails();
			System.out.println("Aquí lo guarda");
			clientRepository.save(c);
		});
	}

	@Transactional
	public void oneToOne() {
		Client client = new Client("Carlos", "Santana");
		ClientDetails clientDetails = new ClientDetails(50, true);

		client.setClientDetails(clientDetails);

		clientRepository.save(client);
	}

	@Transactional
	public void removeInvoices() {
		System.out.print("Ingrese el id del cliente: ");
		try (Scanner scanner = new Scanner(System.in)) {
			Long clientId = scanner.nextLong();
			clientRepository.findById(clientId).ifPresent(client -> {
				System.out.println(client);
				client.getInvoices().forEach(System.out::println);

				System.out.print("Ingrese el id de la factura que desea eliminar: ");
				Long invoiceIdToRemove = scanner.nextLong();
				client.removeInvoice(invoiceIdToRemove);

				clientRepository.save(client);
				System.out.println("Factura eliminada.");
				System.out.println("Invoices");
				client.getInvoices().forEach(System.out::println);
			});
		}
	}

	public void oneToManyBidireccionalFindById() {
		clientRepository.findById(1L).ifPresent(client -> {
			System.out.println(client);
			client.getInvoices().forEach(System.out::println);
		});
	}

	@Transactional
	public void oneToManyBidireccional() {
		Client client = Client.builder().name("Rafael").lastName("Orantes").build();
		Invoice invoice1 = new Invoice("Compras 1", 1200L);
		Invoice invoice2 = new Invoice("Compras 2", 1500L);
		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);
		System.out.println(client);
		client.getInvoices().forEach(System.out::println);
	}

	@Transactional
	public void manyToOne() {
		Client client = Client.builder().name("John").lastName("Doe").build();
		clientRepository.save(client);

		Invoice invoice = Invoice.builder().description("MacBook Pro").amount(2000L).build();
		invoice.setClient(client);
		Invoice invoiceDb = invoiceRepository.save(invoice);
		System.out.println(invoiceDb);
	}

	@Transactional
	public void oneToManyFindById() {

		clientRepository.findById(2L).ifPresent((client) -> {
			Address address1 = Address.builder().street("Calle 1").number(123).build();
			Address address2 = Address.builder().street("Calle 2").number(456).build();
			client.setAddresses(Arrays.asList(address1, address2));

			clientRepository.save(client);
		});
		clientRepository.findById(2L).ifPresent(System.out::println);

	}

	@Transactional
	public void removeAddress() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Ingrese el id del cliente: ");
			clientRepository.findById(scanner.nextLong()).ifPresent(client -> {
				System.out.println(client);
				scanner.nextLine();

				client.getAddresses().forEach(System.out::println);

				System.out.print("Ingrese el id de la dirección que desea eliminar: ");
				Long addressIdToRemove = scanner.nextLong();
				client.getAddresses().removeIf(address -> address.getId().equals(addressIdToRemove));

				clientRepository.save(client);
				System.out.println(client);
			});
		}
	}

	@Transactional
	public void manyToOneFindByIdClient() {
		clientRepository.findById(1L).ifPresentOrElse(client -> {
			Invoice invoice = new Invoice("MacBook Pro M4", 2500L);
			invoice.setClient(client);
			Invoice invoiceDb = invoiceRepository.save(invoice);
			System.out.println(invoiceDb);
		}, () -> {
			System.out.println("Cliente no encontrado");
		});
	}

}
