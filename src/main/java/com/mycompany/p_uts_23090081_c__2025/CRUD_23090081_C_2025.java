/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p_uts_23090081_c__2025;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import java.util.Arrays;
import org.bson.Document;
import org.bson.conversions.Bson;
/**
 *
 * @author mfaiz
 */
public class CRUD_23090081_C_2025 {
    public static void main(String[] args) {
        String URL = "mongodb://localhost:27017";

        try (MongoClient mongo = MongoClients.create(URL)) {
            MongoDatabase DB = mongo.getDatabase("uts_23090081_C_2025");
            MongoCollection<Document> collection = DB.getCollection("uts_23090081_C_2025");

            // CREATE
            Document product = new Document("NIM", "23090081")
                    .append("KELAS", "C")
                    .append("TAHUN", "2025")
                    .append("PRODUK", new Document("name", "flash disk")
                        .append("merk", "kingston")
                        .append("size", Arrays.asList("8GB", "16GB", "32GB")));

            collection.insertOne(product);
            System.out.println("CREATE: Data berhasil disimpan!");

            // READ
            System.out.println("\nREAD: Data saat ini:");
            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }

            // UPDATE (ubah merk dari kingston ke sandisk)
            Bson filter = Filters.eq("NIM", "23090081");
            Bson update = new Document("$set", new Document("PRODUK.merk", "sandisk"));
            collection.updateOne(filter, update);
            System.out.println("\nUPDATE: Data berhasil diubah!");

            // READ setelah update
            System.out.println("\nREAD: Data setelah update:");
            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }

            // DELETE
            //collection.deleteOne(filter);
            //System.out.println("\nDELETE: Data berhasil dihapus!");

            // READ setelah delete
            System.out.println("\nREAD: Data setelah delete:");
            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

