import java.io.*;

public class FileHandler implements Serializable {
    public void writeInFile(String fileName, String fileType, FamilyTree list) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(fileName + "." + fileType));
        objectOutputStream.writeObject(list);
        objectOutputStream.close();
    }

    public FamilyTree readFromFile(String fileName, String fileType) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(fileName + "." + fileType));
        /*List<Human> listResult = new ArrayList<Human>();
        while (objectInputStream.available() != -1){
            listResult.add((Human)objectInputStream.readObject());
        }*/
        FamilyTree myTree = (FamilyTree) objectInputStream.readObject();
        objectInputStream.close();
        return myTree;
    }
}
