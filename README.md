javac -d bin -cp junit-4.10.jar src/ascenseur/traitement/util/*.java src/ascenseur/traitement/strategie/*.java src/ascenseur/traitement/requete/*.java src/ascenseur/traitement/ascenseur/*.java src/ascenseur/affichage/*.java src/ascenseur/traitement/fabrique/*.java src/ascenseur/traitement/Controleur.java src/ascenseur/Application.java


# ProjectJavaYou could make use of the observer pattern. Let your TreeSet implement Observer and let your Student extend Observable. The only change you need to make is to hide the age field by encapsulation so that you have more internal control over the change.

Here's a kickoff example:

public class ObservableTreeSet<O extends Observable> extends TreeSet<O> implements Observer {

    public ObservableTreeSet(Comparator<O> comparator) {
        super(comparator);
    }

    @Override
    public boolean add(O element) {
        element.addObserver(this);
        return super.add(element);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(Observable element, Object arg) {
        remove(element);
        add((O) element);
    }

}
and

public class Student extends Observable {

    private int age;

    Student(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age != age) {
            setChanged();
        }

        this.age = age;

        if (hasChanged()) {
            notifyObservers();
        }
    }

    @Override
    public String toString() {
        return "Student [age=" + age + "]";
    }
}
Now do a new ObservableTreeSet instead of new TreeSet.

static TreeSet<Student> ts = new ObservableTreeSet<Student>(new Comparator<Student>() {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getAge() - o2.getAge();
    }
});
It's ugly at first sight, but you end up with no changes in the main code. Just do a s.setAge(24) and the TreeSet will "reorder" itself.
