package com.acko.util;

import com.acko.customException.ApplicationException;
import com.acko.entities.DeveloperEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Util {
    private static final Logger LOG = LoggerFactory.getLogger(Util.class.getCanonicalName());

    public static String MakeHttpPostCall(String notifyUrl,DeveloperEntity developer)throws ApplicationException {
       // try {
            return "Skipping send";
          /*  URL url = new URL(notifyUrl);
            LOG.info("Creating POST Rest call to ->{}", url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(generateByteResponse(developer));
            os.flush();
            LOG.info("Send the JSON Object to  Server Successfully");
            if(conn.getResponseCode()!=200){
                throw new ApplicationException("Request Failed->"+ readStream(conn.getErrorStream()));
            }
            else{
                return "Successfully sent the notification";
            }
        } catch (IOException e) {
            LOG.error("unable to communicate to server");
            throw new ApplicationException("Failed to communicate to Server->"+e.getMessage());
        }*/
    }

    private static String readStream(InputStream stream){
        StringBuilder result= new StringBuilder();
        Scanner scanner = new Scanner(stream);
        while(scanner.hasNext()){
            result.append(scanner.nextLine()).append("\n");
        }
        return result.toString();
    }


    private static byte[] generateByteResponse(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = mapper.writeValueAsString(obj);
        return jsonObject.getBytes();
    }
}

