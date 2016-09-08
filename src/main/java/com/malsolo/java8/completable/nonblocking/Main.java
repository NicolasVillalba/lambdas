package com.malsolo.java8.completable.nonblocking;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		System.out.println(">>>>> AVAILABLE PROCESSORS: " +Runtime.getRuntime().availableProcessors());
		System.out.println("***** NAIVE *****");
		Client client = new NaiveClient();
		main.performClientCall(client);
		System.out.println("***** NAIVE PARALELL*****");
		client = new NaiveParalellClient();
		main.performClientCall(client);
		System.out.println("***** NAIVE COMPLETABLE FUTURE *****");
		client = new NaiveClientCompletableFuture();
		main.performClientCall(client);
		System.out.println("***** COMPLETABLE FUTURE WITH EXECUTOR *****");
		client = new ClientCompletableFutureAndExecutor();
		main.performClientCall(client);
	}
	
	public void performClientCall(Client client) {
		long start = System.nanoTime();
		System.out.println(client.findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}

}
