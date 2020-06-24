package com.tul.utility.Mongoutility;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;

import org.bson.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVWriter;

@Component
public class Scheduler {

	@Resource(name = "mongoClient")
	private MongoClient mongoClient;

	@Scheduled(cron = "0 */2 * ? * *")
	public void cronJobSch() throws IOException {
		System.out.println("cron scheduler printed");
		String fileName = new SimpleDateFormat("yyyyMMddHHmm'.csv'").format(new Date());
		CSVWriter writer = new CSVWriter(new FileWriter("output" + fileName));
		MongoDatabase database = mongoClient.getDatabase("oms-ats");
		MongoCollection<Document> collection = database.getCollection("invreservation_single");
		FindIterable<Document> iterDoc = collection.find();
		Iterator<Document> it = iterDoc.iterator();
		while (it.hasNext()) {
			writer.writeNext(new String[] { it.next().toJson() });
		}
		writer.flush();
		writer.close();
	}
}
