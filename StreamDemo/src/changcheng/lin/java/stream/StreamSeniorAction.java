package changcheng.lin.java.stream;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author greatwall
 */
public class StreamSeniorAction {

	private class Person {
		public int no;
		private String name;

		private int age;

		public Person (int no, String name, int age) {
			this.no = no;
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}

		public int getAge() {
			return this.age;
		}
	}

	private class PersonSupplier implements Supplier<Person> {
		private int index = 0;
		private Random random = new Random();
		@Override
		public Person get() {
			return new Person(index++, "StormTestUser" + index, random.nextInt(100));
		}
	}

	private void generateInfiniteStream() {
		Random seed = new Random();
		Supplier<Integer> random = seed::nextInt;
		Stream.generate(random).limit(10).forEach(System.out::println);
		//Another way
		IntStream.generate(() -> (int) (System.nanoTime() % 100)).
				limit(10).forEach(System.out::println);
	}

	private void iterateInfiniteStream() {
		Stream.iterate(0, n -> n + 3).limit(10). forEach(x -> System.out.print(x + " "));
	}

	/**
	 * groupby and partitionby
	 */
	private void collectWithGroupBy() {
		Map<Integer, List<Person>> personGroups = Stream.generate(new PersonSupplier()).
				limit(100).
				collect(Collectors.groupingBy(Person::getAge));
		Iterator it = personGroups.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, List<Person>> persons = (Map.Entry) it.next();
			System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
		}
	}

	private void collectWithPartitionby() {
		Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier()).
				limit(100).
				collect(Collectors.partitioningBy(p -> p.getAge() < 18));
		System.out.println("Children number: " + children.get(true).size());
		System.out.println("Adult number: " + children.get(false).size());
	}
}
