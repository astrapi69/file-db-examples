package io.github.astrapi69.jsondb;

import io.jsondb.JsonDBTemplate;
import io.jsondb.crypto.Default1Cipher;
import io.jsondb.crypto.ICipher;

import java.security.GeneralSecurityException;
import java.util.List;

public class JsondbExtensions {

    public static void testJsondb() throws GeneralSecurityException {
        //Actual location on disk for database files, process should have read-write permissions to this folder
        String dbFilesLocation = "src/main/resources";

//Java package name where POJO's are present
        String baseScanPackage = "io.github.astrapi69.jsondb";

//Optionally a Cipher object if you need Encryption
        ICipher cipher = new Default1Cipher("1r8+24pibarAWgS85/Heeg==");

        JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage, cipher);
        List<Instance> collection;
        String collectionName = jsonDBTemplate.getCollectionName(Instance.class);
        boolean collectionExists = jsonDBTemplate.collectionExists(Instance.class);
        if(!collectionExists) {
            jsonDBTemplate.createCollection(Instance.class);
            collection = jsonDBTemplate.getCollection(Instance.class);
            Instance instance = new Instance();
            instance.setId("11");
            instance.setHostname("ec2-54-191-11");
            instance.setPrivateKey("b87eb02f5dd7e5232d7b0fc30a5015e4");
            jsonDBTemplate.insert(instance);

            instance = new Instance();
            instance.setId("000015");
            jsonDBTemplate.insert(instance);
            jsonDBTemplate.save(instance, Instance.class);

            instance = new Instance();
            instance.setId("07");
            instance.setHostname("ec2-54-191-07");
            instance.setPrivateKey("PrivateRyanSaved");
            jsonDBTemplate.upsert(instance);
            Instance byId = jsonDBTemplate.findById("07", Instance.class);
            System.out.println(byId);
        } else {
            collection = jsonDBTemplate.getCollection(Instance.class);
        }
    }
}
