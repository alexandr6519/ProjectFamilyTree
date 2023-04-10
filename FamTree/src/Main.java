import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static void printInvitationForUser() {
        System.out.println();
        String str = "Выберите операцию и введите соответствующую цифру:\n" +
                " 1 - для создания генеалогического древа из заданного списка \n" +
                " 2 - для записи и сохранения списка родственников древа \n" +
                " 3 - для чтения и получения списка родственников из файла \n" +
                " 4 - для сортировки списка родственников по выбранному параметру \n" +
                " 0 - для выхода из меню \n";
        System.out.println(str);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FamilyTree familyTree = new FamilyTree();
       
        FileHandler fh = new FileHandler("familyTree", "out");
        
        Scanner scn = new Scanner(System.in);
        printInvitationForUser();
        while (scn.hasNextInt()) {
            int operationNumber = scn.nextInt();
            if (operationNumber == 0) {
                System.out.println("Всего доброго!");
                break;
            }
            if (operationNumber == 1) {
                familyTree.createInitialFamilyTree();
                familyTree.getInfoAboutFamilyTree();
                familyTree.printChildren();
                printInvitationForUser();
            } else if (operationNumber == 2) {
                fh.writeTreeInFile(familyTree);
                System.out.println("Вы успешно сохранили в файл список генеалогического древа!");
                familyTree.getInfoAboutFamilyTree();
                printInvitationForUser();
            } else if (operationNumber == 3) {
                System.out.println("Генеалогическое древо, прочитанное из файла:");
                familyTree.getInfoAboutFamilyTree();
                familyTree.printChildren();
                printInvitationForUser();
            } else if (operationNumber == 4) {
                System.out.println("Для сортировки по имени введите цифру 1:\n" +
                        "для сортировки по году рождения введите цифру 2: \n" +
                        "для сортировки по id введите цифру 3:");
                int sortNumber = scn.nextInt();

                switch (sortNumber) {
                    case 1:
                        System.out.println("Генеалогическое древо, отсортированное по имени:");
                        familyTree.sortByName();
                        break;
                    case 2:
                        System.out.println("Генеалогическое древо, отсортированное по году рождения:");
                        familyTree.sortByBirthday();
                        break;
                    case 3:
                        System.out.println("Генеалогическое древо, отсортированное по Id:");
                        familyTree.sortById();
                        break;
                    default:
                        System.out.println("Вы ввели некорректный номер сортировки!");
                }
                familyTree.getInfoAboutFamilyTree();
                printInvitationForUser();
            }  else {
                System.out.println("Вы ввели некорректный номер операции!");
                printInvitationForUser();
            }
        }
    }
}
