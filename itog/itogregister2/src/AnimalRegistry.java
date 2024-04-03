import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

private static Map<String, Animal> animalRegistry = new HashMap<>();

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int animalCount = 0;

    while (true) {
        System.out.println("Выберите тип животного (собака, кошка, хомяк, лошадь, верблюд, ослик):");
        String type = scanner.nextLine();

        System.out.println("Введите имя животного:");
        String name = scanner.nextLine();

        System.out.println("Введите дату рождения животного:");
        String date = scanner.nextLine();

        System.out.println("Введите команды, которые умеет выполнять животное (через запятую):");
        String[] commands = scanner.nextLine().split(",");

        Animal animal = createAnimal(type, name, date, commands);
        animalRegistry.put(name, animal);

        System.out.println("Животное успешно добавлено в реестр.");

        System.out.println("Желаете добавить еще одно животное? (да/нет)");
        String answer = scanner.nextLine();
        if (!answer.equalsIgnoreCase("да")) {
            break;
        }
    }

    try (FileWriter writer = new FileWriter("animal_registry.txt")) {
        for (Animal animal : animalRegistry.values()) {
            writer.write("Имя: " + animal.name + ", Дата рождения: " + animal.date + ", Команды: ");
            for (String command : animal.commands) {
                writer.write(command + " ");
            }
            writer.write("\n");
        }
    } catch (IOException e) {
        System.out.println("Ошибка при записи в файл.");
    }

    System.out.println("Реестр животных сохранен в файле animal_registry.txt.");
}

private static Animal createAnimal(String type, String name, String date, String[] commands) {
    switch (type) {
        case "собака":
        case "кошка":
        case "хомяк":
            return new Pet(name, date, commands);
        case "лошадь":
        case "верблюд":
        case "ослик":
            return new PackAnimal(name, date, commands);
        default:
            System.out.println("Неверно указан тип животного.");
            return null;
    }

    Animal animal = createAnimal(type, name, date, commands);
    if (animal != null) {
        animalRegistry.put(name, animal);
         int animalCount++;
        System.out.println("Всего животных: " + animalCount);
    }
}
