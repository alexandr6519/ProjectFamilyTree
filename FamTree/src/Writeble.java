import java.io.IOException;
import java.io.Serializable;

interface Writable extends Serializable {
    void writeTreeInFile(FamilyTree object) throws IOException;
}


