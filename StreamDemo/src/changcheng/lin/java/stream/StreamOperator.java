package changcheng.lin.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author greatwall
 */
public class StreamOperator {

	/**
	 * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
	 * peek、 limit、 skip、 parallel、 sequential、 unordered
	 */
	private class IntermediateOperator {

		private void demoMap() {
			List output = Arrays.asList("a", "b", "c").stream().
					map(String::toUpperCase).
					collect(Collectors.toList());

			List<Integer> nums = Arrays.asList(1, 2, 3, 4);
			List<Integer> squareNums = nums.stream().
					map(n -> n * n).
					collect(Collectors.toList());
		}

		private void demoFlatMap() {
			Stream<List<Integer>> inputStream = Stream.of(
					Arrays.asList(1),
					Arrays.asList(2, 3),
					Arrays.asList(4, 5, 6)
			);
			Stream<Integer> outputStream = inputStream.
					flatMap(childList -> childList.stream());

			outputStream.mapToInt(n -> n).sum();
		}

		private void demoFilter() {
			Integer[] sixNums = {1, 2, 3, 4, 5, 6};
			Integer[] evens =
					Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
		}

		private void demoPeek() {
			Stream.of("one", "two", "three", "four")
					.filter(e -> e.length() > 3)
					.peek(e -> System.out.println("Filtered value: " + e))
					.map(String::toUpperCase)
					.peek(e -> System.out.println("Mapped value: " + e))
					.collect(Collectors.toList());
		}
	}

	/**
	 * forEach、 forEachOrdered、 toArray、 reduce、 collect、
	 * min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
	 */
	private class TerminalOperator {

		private void demoForeach() {
			Arrays.asList("yongjun", "changcheng").stream().forEach(System.out::println);
		}

		private void demoFindAny() {
			Optional<Integer> result = Stream.of(1, 2, 3, 4, 5).filter(ele -> (ele & 1) == 1).findAny();

			result.ifPresent(n -> {
				System.out.print(n);
			});
		}

		private void demoReduce() {
			Stream.of(1, 2, 3, 4, 5).mapToInt(n -> n).sum();
			Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);
			Stream.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> a + b);

		}
	}

}
