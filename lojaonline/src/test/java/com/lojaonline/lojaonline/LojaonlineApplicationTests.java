package com.lojaonline.lojaonline;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class LojaonlineApplicationTests {

	private static final HttpClient client = HttpClient.newHttpClient();
	private static final Random random = new Random();
	private static final AtomicInteger productCounter = new AtomicInteger(10);

	public static void main(String[] args) {
		int numThreads = 20;
		for (int i = 0; i < numThreads; i++) {
			Thread thread = new Thread(new RequestTask(), "UserThread-" + i);
			thread.start();
		}
	}

	static class RequestTask implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					int op = random.nextInt(5);
					switch (op) {
						case 0:
							sendGet("http://localhost:8080/products");
							break;
						case 1:
							long productId = random.nextInt(20) + 1;
							int quantity = random.nextInt(5) + 1;
							String purchaseJson = "{\"id\":" + productId + ", \"quantity\":" + quantity + "}";
							sendPost("http://localhost:8080/purchase", purchaseJson);
							break;
						case 2:
							long newProductId = random.nextInt(20) + 1;
							int newStock = random.nextInt(101);
							String stockJson = "{\"quantity\":" + newStock + "}";
							sendPut("http://localhost:8080/products/" + newProductId + "/stock", stockJson);
							break;
						case 3:
							sendGet("http://localhost:8080/sales/report");
							break;
						case 4:
							sendCreateProduct();
							break;
					}
					Thread.sleep(random.nextInt(2000) + 500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void sendCreateProduct() throws Exception {
		if (productCounter.get() > 20) {
			return;
		}
		int currentId = productCounter.getAndIncrement();
		String name = "ProdutoCriadoNoTeste" + currentId;
		double price = 10.0 + random.nextInt(90);
		int quantity = 1 + random.nextInt(20);

		String productJson = "{\"id\": " + currentId + ", \"nome\": \"" + name + "\", \"price\": " + price
				+ ", \"quantity\": " + quantity + "}";

		sendPost("http://localhost:8080/products", productJson);
	}

	private static void sendGet(String url) throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.GET()
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(Thread.currentThread().getName() + " GET " + url + " -> " + response.statusCode());
	}

	private static void sendPost(String url, String json) throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(json))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(Thread.currentThread().getName() + " POST " + url + " -> " + response.statusCode());
	}

	private static void sendPut(String url, String json) throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("Content-Type", "application/json")
				.PUT(HttpRequest.BodyPublishers.ofString(json))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(Thread.currentThread().getName() + " PUT " + url + " -> " + response.statusCode());
	}
}
