package com.example.website;

import com.example.website.model.Product;
import com.example.website.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

@SpringBootApplication
public class WebsiteApplication {

	static int availableQty = 0;

	public static void main(String[] args) throws InterruptedException {

		BlockingQueue queue = new ArrayBlockingQueue(1024);

		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		new Thread(producer).start();
		new Thread(producer).start();
		new Thread(consumer).start();

		Thread.sleep(4000);
		System.out.println("available qty : "+availableQty);

		SpringApplication.run(WebsiteApplication.class, args);
	}

	public static class Producer implements Runnable{

		protected BlockingQueue queue = null;

		public Producer(BlockingQueue queue) {
			this.queue = queue;
		}

		public void run() {
			try {
				HashSet<String> categorySet= new HashSet<>(2);
				categorySet.add("Fitness");
				categorySet.add("Sports");
				Product product1 = new Product(new Random().nextLong(),"random1",10.00,true,categorySet);
				Product product2 = new Product(new Random().nextLong(),"random2",20.00,true,categorySet);
				Product product3 = new Product(new Random().nextLong(),"random3",30.00,true,categorySet);

				queue.put(product1);
				availableQty++;
				System.out.println("put product1");
				Thread.sleep(1000);

				queue.put(product2);
				availableQty++;
				System.out.println("put product2");
				Thread.sleep(1000);

				queue.put(product3);
				availableQty++;
				System.out.println("put product3");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public static class Consumer implements Runnable{

		protected BlockingQueue queue = null;

		public Consumer(BlockingQueue queue) {
			this.queue = queue;
		}

		public void run() {
			try {
				System.out.println(queue.take());
				availableQty--;
				System.out.println("remove product1");

				System.out.println(queue.take());
				availableQty--;
				System.out.println("remove product2");

				System.out.println(queue.take());
				availableQty--;
				System.out.println("remove product3");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
			}
		};
	}
}
