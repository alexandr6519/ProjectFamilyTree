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
                " 5 - для добавления родственника в список древа \n" +
                " 0 - для выхода из меню \n";
        System.out.println(str);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FamilyTree familyTree = new FamilyTree();
        familyTree.createInitialFamilyTree();        

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
                printInvitationForUser();
            } else if (operationNumber == 2) {
                fh.writeTreeInFile(familyTree);
                System.out.println("Вы успешно сохранили в файл список генеалогического древа!");
                familyTree.getInfoAboutFamilyTree();
                printInvitationForUser();
            } else if (operationNumber == 3) {
                System.out.println("Генеалогическое древо, прочитанное из файла:");
                familyTree.getInfoAboutFamilyTree();
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
            } else if (operationNumber == 5) {
                System.out.println("Для добавления в список введите последовательно через запятую(без пробелов): " +
                        "фамилия, имя, отчество, пол (м или ж), id отца, id матери, год рождения" +
                        "(в случае отсутствия id введите -1");
                String humanForAdding = scn.next();
                String [] arrayDataOfHuman = humanForAdding.split("[,]");

                String nameFull = String.format("%s %s %s",arrayDataOfHuman[0], arrayDataOfHuman[1], arrayDataOfHuman[2]);
                Human father = familyTree.getHumanById(Integer.parseInt(arrayDataOfHuman[4]));
                Human mother = familyTree.getHumanById(Integer.parseInt(arrayDataOfHuman[5]));
                int birthYear = Integer.parseInt(arrayDataOfHuman[6]);
                int id = familyTree.getLastId() + 1;
                Human humanNew = new Human(id, nameFull, familyTree.getGender(arrayDataOfHuman[3]), father, mother, birthYear);
                familyTree.addHuman(humanNew);
                familyTree.getInfoAboutFamilyTree();
                familyTree.printChildren();
            }
            else {
                System.out.println("Вы ввели некорректный номер операции!");
                printInvitationForUser();
            }
        }
