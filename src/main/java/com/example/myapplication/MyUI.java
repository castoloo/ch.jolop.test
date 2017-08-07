package com.example.myapplication;
import com.example.myapplication.Student;

import java.util.Set;

import javax.management.Notification;
import javax.servlet.annotation.WebServlet;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	VerticalLayout root = new VerticalLayout();
    	
       
       
       final Label l1 = new Label("HALLO");
       
       
       HorizontalLayout h1 = new HorizontalLayout();
       
       //TEXTFIELD
       TextField t1 = new TextField("Character counter");
       t1.setValue("This is the value...");
     final  Label counterLabel = new Label();
    // counterLabel.setImmediate(true);
       
       t1.addTextChangeListener(new TextChangeListener() {
		
		@Override
		public void textChange(TextChangeEvent event) {
			int lengthOfText = event.getText().length();
			counterLabel.setValue("Number of Char: " + lengthOfText);
	
			
		}
	});
       h1.addComponents(t1,counterLabel);
       
       // Counter wird schneller aktualisiert
      t1.setTextChangeEventMode(TextChangeEventMode.EAGER);
       
       //TEXTAREA
      TextArea ta1 = new TextArea("TEXTAREA");
      ta1.setWidth("500px");
      ta1.setHeight("100px");
      //Setzt den Text in mehrere Zeilen default immer auf mehrere Zeile
      ta1.setWordwrap(true);
      ta1.setValue("This is going to be a very long text");
      
      //BUTTON
      
      Label bl = new Label("BUTTON");
      Button b1 = new Button("Save");
      
      b1.addClickListener(new Button.ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			//Text auf Bildschirm
			//root.addComponent(new Label("Save button clicked"));
			
			//Notification
		com.vaadin.ui.Notification.show("Button click");
			
			
		}
	});
      
      //Button Lambda
      Button b2 = new Button("Button 2");
      b2.addClickListener(event -> com.vaadin.ui.Notification.show("Button 2 clicked"));
      b2.addClickListener(event -> root.addComponent(new Label("Yeah..")));
      
      //CheckBox
      Label cl = new Label("Check Box");
      CheckBox cb1 = new CheckBox("Has degree");
      
      cb1.addValueChangeListener(new ValueChangeListener() {
		
		@Override
		public void valueChange(ValueChangeEvent event) {
			System.out.println(cb1.getValue());
			
		}
	});
      
      
      //Check Box Lambda
      CheckBox cb2 = new CheckBox("Lambda");
      cb2.addValueChangeListener(event -> System.out.println(cb2.getValue()));
      
      //OptionGroup
      
      OptionGroup og = new OptionGroup("Students");
      
      og.setMultiSelect(true);
      
      Student s1 = new Student("Patrick Jolo", 25);
      Student s2 = new Student("Sabine Leuenberger", 24);
      Student s3 = new Student("Tim Glatthard", 25);
      Student s4 = new Student("Lea Muster", 22);
     
      
      og.addItem(s1);
      og.addItem(s2);
      og.addItem(s3);
      og.addItem(s4);
      
      
      //og.addValueChangeListener(event -> System.out.println(event.getProperty()));
      og.addValueChangeListener(new ValueChangeListener() {
		
		@Override
		public void valueChange(ValueChangeEvent event) {
			//Einzel Selektion
			//Student student = (Student) event.getProperty().getValue();
			
			//Multi Selektion
			Set<Student> students = (Set<Student>) event.getProperty().getValue();
			
			for(Student s : students) {
			
			System.out.println(s.getAge()+ "  " + s.getName());
			}
			
		}
	});
       
      //ComboBox
      ComboBox comboBox = new ComboBox("Names");
      //Filter CONTAINS = egal welcher buchstaben
      //Filter STARTSWITH = erster Buchstaben
      comboBox.setFilteringMode(FilteringMode.STARTSWITH);
      
      comboBox.addItem("Patrick Jolo");
      comboBox.addItem("Tim Glatthard");
      comboBox.addItem("Sabine Leuenberger");
      comboBox.addItem("Max Muster");
       
      
       
    		   
    		   
       root.addComponents(l1,h1,ta1,bl,b1,b2,cl,cb1,cb2,og,comboBox);
        setContent(root);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
