package abstract_factory;

abstract class Button {
    protected String text;

    public Button(String text) {
        this.text = text;
    }

    public abstract void display();

    public void setText(String text) {
        this.text = text;
    }
}

abstract class TextField {
    protected String text;

    public TextField(String text) {
        this.text = text;
    }

    public abstract void display();

    public void setText(String text) {
        this.text = text;
    }
}

abstract class Checkbox {
    protected String text;
    protected boolean checked;

    public Checkbox(String text) {
        this.text = text;
        this.checked = false;
    }

    public abstract void display();

    public void setText(String text) {
        this.text = text;
    }

    public void toggle() {
        this.checked = !this.checked;
    }
}

class ButtonA extends Button {
    public ButtonA(String text) {
        super(text);
    }

    @Override
    public void display() {
        String border = "+" + "-".repeat(text.length() + 4) + "+";
        System.out.println(border);
        System.out.println("| " + text + " |");
        System.out.println(border);
    }
}

class TextFieldA extends TextField {
    public TextFieldA(String text) {
        super(text);
    }

    @Override
    public void display() {
        String border = "[" + "=".repeat(text.length() + 2) + "]";
        System.out.println(border);
        System.out.println("| " + text + " |");
        System.out.println(border);
    }
}

class CheckboxA extends Checkbox {
    public CheckboxA(String text) {
        super(text);
    }

    @Override
    public void display() {
        String mark = checked ? "[X]" : "[ ]";
        System.out.println("+-------------+");
        System.out.println("| " + mark + " " + text + " |");
        System.out.println("+-------------+");
    }
}

class ButtonB extends Button {
    public ButtonB(String text) {
        super(text);
    }

    @Override
    public void display() {
        String border = "╔" + "═".repeat(text.length() + 4) + "╗";
        System.out.println(border);
        System.out.println("║ " + text + " ║");
        System.out.println("╚" + "═".repeat(text.length() + 4) + "╝");
    }
}

class TextFieldB extends TextField {
    public TextFieldB(String text) {
        super(text);
    }

    @Override
    public void display() {
        String border = "┌" + "─".repeat(text.length() + 2) + "┐";
        System.out.println(border);
        System.out.println("│ " + text + " │");
        System.out.println("└" + "─".repeat(text.length() + 2) + "┘");
    }
}

class CheckboxB extends Checkbox {
    public CheckboxB(String text) {
        super(text);
    }

    @Override
    public void display() {
        String mark = checked ? "☒" : "☐";
        System.out.println("╔═════════════╗");
        System.out.println("║ " + mark + " " + text + " ║");
        System.out.println("╚═════════════╝");
    }
}

interface UIFactory {
    Button createButton(String text);
    TextField createTextField(String text);
    Checkbox createCheckbox(String text);
}

class StyleAFactory implements UIFactory {
    @Override
    public Button createButton(String text) {
        return new ButtonA(text);
    }

    @Override
    public TextField createTextField(String text) {
        return new TextFieldA(text);
    }

    @Override
    public Checkbox createCheckbox(String text) {
        return new CheckboxA(text);
    }
}

class StyleBFactory implements UIFactory {
    @Override
    public Button createButton(String text) {
        return new ButtonB(text);
    }

    @Override
    public TextField createTextField(String text) {
        return new TextFieldB(text);
    }

    @Override
    public Checkbox createCheckbox(String text) {
        return new CheckboxB(text);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Style A UI ===");
        UIFactory factoryA = new StyleAFactory();
        createAndDisplayUI(factoryA);

        System.out.println("\n=== Style B UI ===");
        UIFactory factoryB = new StyleBFactory();
        createAndDisplayUI(factoryB);
    }

    public static void createAndDisplayUI(UIFactory factory) {
        Button button = factory.createButton("Click Me");
        TextField textField = factory.createTextField("Enter Text");
        Checkbox checkbox = factory.createCheckbox("Accept Terms");

        button.display();
        textField.display();
        checkbox.display();

        System.out.println("--- After dynamic changes ---");
        button.setText("Submit");
        textField.setText("New Input");
        checkbox.toggle();

        button.display();
        textField.display();
        checkbox.display();
    }
}