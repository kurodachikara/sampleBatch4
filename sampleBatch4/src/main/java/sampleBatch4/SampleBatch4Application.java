package sampleBatch4;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SampleBatch4Application {

	public static void main(String[] args) {
		SpringApplication.run(SampleBatch4Application.class, args);
	}

}
