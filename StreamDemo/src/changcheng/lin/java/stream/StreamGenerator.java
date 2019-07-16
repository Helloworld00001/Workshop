package changcheng.lin.java.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author greatwall
 */
public class StreamGenerator {

	private String[] array = {"abc", "def"};
	private Stream<String> stream = Arrays.stream(array);
	private Stream<String> stream2 = Stream.of("abc", "def");
	private Stream stream3 = Stream.empty();
	private Stream stream4 = Stream.generate(() -> {
		return "abc";
	});
	private Stream stream5 = Stream.iterate(1, a -> a + 1);
	private Stream stream6 = Stream.concat(Stream.empty(), Stream.of("abc"));

	private IntStream intStream = IntStream.range(1, 10);
	private IntStream intStream2 = new Random().ints();
	private IntStream intStream3 = new BitSet(10).stream();

	private void streamTransformer() {
		// 1. Array
		String[] strArray1 = stream.toArray(String[]::new);
		// 2. Collection
		List<String> list1 = stream.collect(Collectors.toList());
		List<String> list2 = stream2.collect(Collectors.toCollection(ArrayList::new));
		Set set1 = stream.collect(Collectors.toSet());
		Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
		Map<String, Integer> map = stream.collect(Collectors.toMap(key -> key, value -> value.length()));
		//高阶函数
		stream.collect(Collectors.reducing("", (a, b) -> a.concat(b)));

		// 3. String
		String str = stream.collect(Collectors.joining());
	}

	public static void main(String[] args) {
		StreamGenerator  generator = new StreamGenerator();
		generator.streamTransformer();
	}
}
