import java.io.IOException;

public interface Readable {
    FamilyTree readFromFile() throws IOException, ClassNotFoundException;
}
