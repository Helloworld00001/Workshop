package changcheng.lin.java.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author greatwall
 */
public class StreamExample {

	private List<Transaction> transactions = new ArrayList<>();

	/**
	 * 在 Java 7 中，如果要发现 type 为 grocery 的所有交易，然后返回以交易值降序排序好的交易 ID 集合
	 * 的实现方式
	 */
	private List<Integer> doInJava7() {
		List<Transaction> groceryTransactions = new ArrayList<>();
		for(Transaction t: transactions){
			if(Transaction.GROCERY.equals(t.getType())){
				groceryTransactions.add(t);
			}
		}
		Collections.sort(groceryTransactions, new Comparator<Transaction>(){
			public int compare(Transaction t1, Transaction t2){
				return t2.getValue().compareTo(t1.getValue());
			}
		});
		List<Integer> transactionIds = new ArrayList<>();
		for(Transaction t: groceryTransactions){
			transactionIds.add(t.getId());
		}

		return transactionIds;
	}

	/**
	 * 用Java8 的 stream接口实现 type 为 grocery 的所有交易，然后返回以交易值降序排序好的交易 ID 集合
	 * @return
	 */
	private List<Integer> doInJava8() {
		return transactions.stream().
				filter(t -> Transaction.GROCERY.equals(t.getType())).
				sorted(Comparator.comparing(Transaction::getValue).reversed()).
				map(Transaction::getId).
				collect(Collectors.toList());
	}

}
