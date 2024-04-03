class Animal {
    String name;
    String date;
    String[] commands;

    public Animal(String name, String date, String[] commands) {
        this.name = name;
        this.date = date;
        this.commands = commands;
    }

    public void displayCommands() {
        System.out.print("Команды, которые умеет выполнять " + this.name + ": ");
        for (String command : commands) {
            System.out.print(command + " ");
        }
        System.out.println();
    }
}

