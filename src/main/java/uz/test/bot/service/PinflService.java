package uz.test.bot.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class PinflService {

    public File getCaptchaImage(){
        try {
            URL url = new URL("https://my3.soliq.uz/api/auth/captcha/generate");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            httpConn.setRequestMethod("GET");

            httpConn.setRequestProperty("User-Agent", "insomnia/9.2.0");

            int responseCode = httpConn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
                 BufferedReader br = new BufferedReader(isr)) {
                String responseLine;
                StringBuilder response = new StringBuilder();
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine);
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                String captchaImageBase64 = jsonResponse.getString("captchaImageBase64");

                System.out.println("Captcha Image Base64: " + captchaImageBase64);

                byte[] imageBytes = Base64.getDecoder().decode(captchaImageBase64.split(",")[1]);
                File imageFile = new File("captcha.png");
                try (OutputStream os = new FileOutputStream(imageFile)) {
                    os.write(imageBytes);
                }
                return imageFile;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getPinfl(String pasInfo, String dateOfBirth, Integer captchaResult){
        String pasSer = "";
        String pasNum = "";
        if(pasInfo != null){
            pasSer = pasInfo.substring(0,2);
            pasNum = pasInfo.substring(3,9);
        }
        try {
            // Define the URL
            URL url = new URL("https://my3.soliq.uz/api/search-tin-api/individual/search-by-passport-data");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            httpConn.setRequestMethod("POST");

            // Set the request headers
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Language", "uz_cyrl");
            httpConn.setRequestProperty("User-Agent", "insomnia/9.2.0");
            httpConn.setRequestProperty("X-Captcha-Id", "E3B9BDEE31E54B03ABDE324EF1A61458");
            httpConn.setRequestProperty("X-Captcha-Value", captchaResult.toString());

            // Enable input and output streams
            httpConn.setDoOutput(true);

            // Define the JSON data
            String jsonInputString = "{"
                + "\"pasSer\": \""+ pasSer + "\","
                + "\"pasNum\": \"" + pasNum + "\","
                + "\"pasDob\": \""+ dateOfBirth +"\","
                + "\"docCode\": \"01\""
                + "}";

            // Write the JSON data to the output stream
            try (OutputStream os = httpConn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check the response code
            int responseCode = httpConn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            try (InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
                 BufferedReader br = new BufferedReader(isr)) {
                String responseLine;
                StringBuilder response = new StringBuilder();
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine);
                }
                System.out.println("Response: " + response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
