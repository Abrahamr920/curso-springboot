package com.springboot.jpa.springboot_jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.jpa.springboot_jpa.entities.Person;
import com.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create();
		findAll();
		update();

	}

	public void findPersonsByProgrammingLanguaje() {
		List<String> namesList = Arrays.asList("Java", "Python");
		personRepository.findPersonsByProgrammingLanguaje(namesList).forEach(System.out::println);
	}

	public void printLastPersonRegistration() {
		Optional<Person> lastPerson = personRepository.findLastPersonRegistration();
		lastPerson.ifPresentOrElse(
				person -> System.out.println("Última persona registrada: " + person),
				() -> System.out.println("No se encontró ninguna persona registrada."));
	}

	public void printShortestName() {
		System.out.println("===========================Nombre mas corto y su tamaño===========================");
		personRepository.findShortestName().forEach(System.out::println);
	}

	public void printLongestName() {
		System.out.println("===========================Nombre mas largo y su tamaño===========================");
		System.out.println(personRepository.findLongestName().get(0));

	}

	public void printLongestName1() {
		System.out.println("===========================Nombre mas largo y su tamaño===========================");
		List<Object[]> register = personRepository.findLongestName1();
		String name = (String) register.get(0)[0];
		int length = (int) register.get(0)[1];
		System.out.println("Nombre: " + name + "\nTamaño: " + length);

	}

	public void printAggregateFunctions() {
		Object[] results = (Object[]) personRepository.findAggregateFunctions();

		// Acceder a los valores por índice
		Long maxId = (Long) results[0];
		Long minId = (Long) results[1];
		Double avgId = (Double) results[2];
		Long sumId = (Long) results[3];

		// Imprimir los resultados
		System.out.println("Max ID: " + maxId);
		System.out.println("Min ID: " + minId);
		System.out.println("Avg ID: " + avgId);
		System.out.println("Sum ID: " + sumId);
	}

	public void findPersonsByNameRange() {
		personRepository.findByNameBetween("A", "D").forEach(System.out::println);
	}

	public void findAllLowerNames() {
		System.out.println("===========================Nombres en minúsculas===========================");
		personRepository.findAllLowerNames().forEach(System.out::println);
	}

	public void findAllUpperNames() {
		System.out.println("===========================Nombres en mayúsculas===========================");
		personRepository.findAllUpperNames().forEach(System.out::println);
	}

	public void findDistinctProgrammingLanguage() {
		personRepository.findDistinctProgrammingLanguage().forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findAllFullNames() {
		System.out.println("===========================Nombres completos de las personas===========================");
		personRepository.findAllDistinctNames().forEach(System.out::println);
	}

	public void findTwoProgrammingLanguaje() {
		personRepository.findTwoProgrammingLanguaje().forEach(row -> {
			Arrays.stream(row).forEach(attribute -> System.out.print(attribute + "\t"));
			System.out.println(); // Para saltar de línea después de cada fila
		});
	}

	public void findAllPersonDto() {
		personRepository.findAllPersonDto().forEach(System.out::println);
	}

	public void findAllFullName() {
		personRepository.findAllFullName().forEach(System.out::println);
	}

	public void printPersonDataList() {
		personRepository.findTwoProgrammingLanguaje().forEach(row -> {
			Arrays.stream(row).forEach(attribute -> System.out.print(attribute + "\t"));
			System.out.println(); // Para saltar de línea después de cada fila
		});

	}

	@Transactional(readOnly = true)
	public void onePersonData() {
		// Object[] personData = (Object[]) personRepository.getFullPersonData(1L);
		// Arrays.stream(personData).forEach(attribute ->
		// System.out.println(attribute));

		Arrays.stream((Object[]) personRepository.getFullPersonData(1L)).forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQuery() {
		System.out.println("====================Ver Nombre Completo====================");
		System.out.print("Ingrese el id: ");
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Nombre: " + personRepository.getFullNamePersonById(scanner.nextLong()));

		}

	}

	@Transactional
	public void create() {

		System.out.println("insertar Persona");

		Person newPerson = new Person();
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Ingrese el nombre: ");
			newPerson.setName(scanner.nextLine());
			System.out.print("Ingrese el apellido: ");
			newPerson.setLastName(scanner.nextLine());
			System.out.print("Ingrese el lenguaje de programación: ");
			newPerson.setProgrammingLanguage(scanner.nextLine());
		}
		System.out.println("Creando persona..." + newPerson);
		personRepository.save(newPerson);
		personRepository.findById(newPerson.getId()).ifPresentOrElse(
				p -> System.out.println("Persona creada: " + p),
				() -> System.out.println("Persona no creada"));

	}

	@Transactional
	public void update() {
		System.out.println("----------------------Actualizar Persona----------------------");

		Person personUpdate;
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Ingrese el ID de la persona a actualizar: ");
			personUpdate = personRepository.findById(scanner.nextLong()).orElse(null);
			if (personUpdate != null) {
				System.out.println("Persona a actualizar: " + personUpdate);

				scanner.nextLine();
				System.out.print("Ingrese el nombre: ");
				personUpdate.setName(scanner.nextLine());
				System.out.print("Ingrese el apellido: ");
				personUpdate.setLastName(scanner.nextLine());
				System.out.print("Ingrese el lenguaje de programación: ");
				personUpdate.setProgrammingLanguage(scanner.nextLine());
			} else {
				System.out.println("Persona no encontrada");
				return;
			}
		}
		System.out.println("Actualizando persona..." + personUpdate);
		personRepository.save(personUpdate);
		personRepository.findById(personUpdate.getId()).ifPresentOrElse(
				p -> System.out.println("Persona actualizada: " + p),
				() -> System.out.println("Persona no actualizada"));
	}

	@Transactional
	public void update1() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Ingrese el ID de la persona a actualizar: ");
			Long id = scanner.hasNextLong() ? scanner.nextLong() : null;
			scanner.nextLine(); // Limpiar buffer

			personRepository.findById(id).ifPresentOrElse(person -> {
				System.out.println("Persona a actualizar: " + person);
				System.out.print("Nuevo nombre: ");
				person.setName(scanner.nextLine());
				System.out.print("Nuevo apellido: ");
				person.setLastName(scanner.nextLine());
				System.out.print("Nuevo lenguaje de programación: ");
				person.setProgrammingLanguage(scanner.nextLine());

				personRepository.save(person);
				System.out.println("Persona actualizada: " + person);
			}, () -> System.out.println("Persona no encontrada."));
		}
	}

	@Transactional
	public void deleteById() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Ingrese el ID de la persona a eliminar: ");
			Long id = scanner.hasNextLong() ? scanner.nextLong() : null;

			if (personRepository.existsById(id)) {
				personRepository.deleteById(id);
				System.out.println("Persona eliminada.");

			}
			System.out.println("Persona no encontrada.");

		}
	}

	@Transactional
	public void deleteByEntity() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Ingrese el ID de la persona a eliminar: ");
			personRepository.findById(scanner.nextLong()).ifPresentOrElse(person -> {
				personRepository.delete(person);
				System.out.println("Persona eliminada.");
			}, () -> System.out.println("Persona no encontrada."));
		}

	}

	@Transactional
	public void deleteValidate() {
		try (Scanner scanner = new Scanner(System.in)) {
			// scanner.nextLine();
			System.out.print("Ingrese el ID de la persona a eliminar: ");
			personRepository.findById(scanner.nextLong()).ifPresentOrElse(person -> {
				System.out.println("Persona a eliminar: " + person);
				System.out.print("¿Está seguro de eliminar la persona? (s/n): ");
				if (scanner.next().equalsIgnoreCase("s")) {

					try {
						personRepository.delete(person);
						System.out.println("Persona eliminada.");
					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage());
					}
				} else {
					System.out.println("Operación cancelada.");
				}
			}, () -> System.out.println("Persona no encontrada."));
		}

	}

	public void headers() {
		System.out.println("ID\tName\tLastName\tProgramming Language");
		System.out.println("----------------------------------------------------");
	}

	public void findOne() {
		personRepository.findFirstByNameContaining("l").ifPresent(System.out::println);
	}

	public void listInitialFilter(String initial) {
		personRepository.findByNameStartingWith(initial).stream().forEach(System.out::println);

	}

	public void findAll() {
		personRepository.buscarTodos().forEach(System.out::println);
	}

}
