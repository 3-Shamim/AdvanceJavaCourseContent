package bd.ac.seu.aj.vaadindemo.ui;

import bd.ac.seu.aj.vaadindemo.model.Student;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class StudentForm extends FormLayout {

    private TextField id = new TextField("Id");
    private TextField name = new TextField("Name");
    private TextField cgpa = new TextField("Cgpa");

    private Button save = new Button("Save");
    private Button update = new Button("Update");

    private Student student = new Student();
    private Binder<Student> binder = new Binder<>(Student.class);

    private HelloUI helloUI;

    public StudentForm(HelloUI helloUI) {
        this.helloUI = helloUI;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, update);
        addComponents(id, name, cgpa, buttons);

        binder.forField(id)
                .withConverter( new StringToLongConverter("Please enter a number !"))
                .bind(Student::getId, Student::setId);
        binder.forField(cgpa)
                .withConverter(new StringToDoubleConverter("Please enter a number !"))
                .bind(Student::getCgpa, Student::setCgpa);

        binder.bindInstanceFields(this);
//        binder.readBean(student);
//        binder.setBean(student);
        save.addClickListener(clickEvent -> saveStudent());
        update.addClickListener(clickEvent -> updateStudent());
    }


    public void saveStudent() {
        try {
            binder.writeBean(student);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/student/create";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Student> entity = new HttpEntity<>(student, httpHeaders);

        Student student1 = restTemplate.postForObject(url, entity, Student.class);
        System.out.println(student1);

        helloUI.updateTable();

    }

    public void updateStudent(){

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/student/update";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Student> entity = new HttpEntity<>(student, httpHeaders);

        Student student1 = restTemplate.postForObject(url, entity, Student.class);
        System.out.println(student1);

        helloUI.updateTable();

    }

    public void fillForm(Student student) {
        id.setValue(student.getId()+"");
        name.setValue(student.getName());
        cgpa.setValue(student.getCgpa() + "");
    }




}
