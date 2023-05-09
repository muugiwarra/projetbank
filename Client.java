
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Client {

        private int id;
        private String name;
        private String address;
        private String phoneNumber;
        private String email;

        public Client(String name, String address, String phoneNumber, String email) throws IOException {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.id = generateId();
        }

    private int lastGeneratedId;

    private int generateId() throws IOException {
        String filename = "c:/clients.txt";

        // Open the file to read the number of lines
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        int id = (int) br.lines().count() + 1; // Increment the ID by 1
        br.close();
        fr.close();

        // Store the generated ID in the instance variable
        lastGeneratedId = id;

        return id;
    }


        public int getId() {
            return id;
        }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}

