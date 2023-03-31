import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static void printInvitationForUser() {
        String str = "Выберите операцию и введите соответствующую цифру:\n" + " 1 - для создания списка родственников генеалогического древа \n" +
                " 2 - для записи и сохранения списка родственников \n" +
                " 3 - для чтения и получения списка родственников из файла \n" +
                " 0 - для выхода из меню \n";
        System.out.println(str);
    }

    private static FamilyTree createFamilyTree() {
        Human h_1 = new Human("Кузнецов Иван Иванович", Gender.male, 1874);
        Human h_2 = new Human("Кузнецова Евдокия Петровна", Gender.female, 1881);
        Human h_3 = new Human("Грищенко Мария Ивановна", Gender.female, h_1, h_2, 1910);
        Human h_4 = new Human("Кузнецов Петр Иванович", Gender.male, h_1, h_2, 1924);
        Human h_5 = new Human("Грищенко Афонасий Ермолаевич", Gender.male, 1915);
        Human h_6 = new Human("Богданова Зоя Афонасьевна", Gender.female, h_5, h_3, 1937);
        Human h_7 = new Human("Грищенко Александр Афонасьевич", Gender.male, h_5, h_3, 1940);
        ArrayList<Human> humans = new ArrayList<Human>(Arrays.asList(h_1, h_2, h_3, h_4, h_5, h_6, h_7));

        FamilyTree familyTree = new FamilyTree();
        for (Human human : humans) {
            familyTree.addHuman(human);
        }
        return familyTree;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileHandler fh = new FileHandler();
        FamilyTree familyTree = new FamilyTree();
        printInvitationForUser();
        Scanner scn = new Scanner(System.in);
        try {
            while (scn.hasNextInt()) {
                int operationNumber = scn.nextInt();
                switch (operationNumber) {
                    case 1:
                        familyTree = createFamilyTree();
                        break;
                    case 2:
                        if (familyTree != null) {
                            fh.writeInFile("familyTree", "txt", familyTree);
                            System.out.println("Вы успешно сохранили список генеалогического древа!");
                            System.out.println(familyTree);
                        }
                        break;
                    case 3:
                        familyTree = fh.readFromFile("familyTree", "out");
                        System.out.println(familyTree);
                        familyTree.printChildren();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Вы ввели некорректный номер операции!");
                }
                if (operationNumber == 0){
                    System.out.println("Всего доброго!");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
