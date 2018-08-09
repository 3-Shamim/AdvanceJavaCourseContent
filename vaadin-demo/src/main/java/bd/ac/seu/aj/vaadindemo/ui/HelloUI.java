package bd.ac.seu.aj.vaadindemo.ui;

import bd.ac.seu.aj.vaadindemo.model.Student;
import bd.ac.seu.aj.vaadindemo.service.StudentService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@SpringUI(path = "home")
@Theme("valo")
public class HelloUI extends UI {

    private StudentService studentService;

    public HelloUI(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    private StudentForm studentForm = new StudentForm(this);
    private Grid<Student> studentGrid = new Grid<>();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Label label = new Label("Hello World");
        Button button = new Button("Click Me");

        studentGrid.addColumn(Student::getId).setCaption("Student ID");
        studentGrid.addColumn(Student::getName).setCaption("Student Name");
        studentGrid.addColumn(Student::getCgpa).setCaption("CGPA");
        studentGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        updateTable();

        button.addClickListener(clickEvent -> {
            label.setValue("AJ");
            Notification.show("Hello");
        });

        Button waiverButton = new Button("Waive Tuition");
        waiverButton.addClickListener(clickEvent -> {
            System.out.println("Selected student");
            Set<Student> selectedStudentSet = studentGrid.getSelectedItems();
            selectedStudentSet.stream().forEach(System.out::println);
        });

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button deleteStudentButton = new Button("Delete");
        Button updateStudentButton = new Button("Update");



        deleteStudentButton.addClickListener(clickEvent -> {

            Set<Student> selectedStudentSet = studentGrid.getSelectedItems();
            
            if (selectedStudentSet.size() > 0) {

                selectedStudentSet.forEach(student -> studentService.deleteStudent(student.getId()));
                updateTable();

            } else {
                Notification.show("Select student for delete");
            }

        });

        updateStudentButton.addClickListener(clickEvent -> {
            Set<Student> selectedStudentSet = studentGrid.getSelectedItems();

            if (selectedStudentSet.size() == 1) {
                Student student = selectedStudentSet.stream().findFirst().get();
                studentForm.fillForm(student);
            } else {
                Notification.show("Select one row for update");
            }

        });


        horizontalLayout.addComponents(label, button, waiverButton,updateStudentButton,deleteStudentButton);
        horizontalLayout.setSizeFull();
        studentGrid.setSizeFull();

        verticalLayout.addComponents(horizontalLayout, studentGrid, studentForm);

        setContent(verticalLayout);
    }

    public void updateTable() {
        studentGrid.setItems(studentService.getAllStudents());
    }
}
