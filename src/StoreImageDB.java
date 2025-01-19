import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StoreImageDB {
    static String URL = "jdbc:mysql://localhost:3306/atharva_db";
    static String USERNAME = "root";
    static String PASSWORD = "Roger365";
    static String IMAGE_FOLDER_PATH = "src/resources/images";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Successfully Connected to Database!!!");
        } catch (Exception exception) {
            System.err.println("Failed to connect to Database: " + exception.getMessage());
            throw new RuntimeException(exception);
        }

        try {
            String query = "INSERT INTO images(image) VALUES(?)";
            preparedStatement = connection.prepareStatement(query);
            File folder_path = new File(IMAGE_FOLDER_PATH);
            File[] files = folder_path.listFiles();
            for (int i = 0; i < files.length; i++) {
                FileInputStream fileInputStream = new FileInputStream(files[i]);
                byte[] imageArray = new byte[fileInputStream.available()];
                fileInputStream.read(imageArray);
                preparedStatement.setBytes(1, imageArray);
                preparedStatement.executeUpdate();
                System.out.println("Image stored in database successfully!!!");
            }
            connection.close();
        } catch (Exception exception) {
            System.err.println("Failed to insert images into Database: " + exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
