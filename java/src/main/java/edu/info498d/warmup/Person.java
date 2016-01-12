package edu.info498d.warmup;

import java.beans.*;
import java.lang.Object;
import java.util.*;
import java.util.Objects;

public class Person implements Comparable<Person> {
   private int age;
   private String name;
   private double salary;
   private String ssn;
   private boolean propertyChangeFired;
  
  //This is a constructor when no parameters are passed to the People class
   public Person() {
      this("", 0, 0.0);
   }
  
  //This is a constructor when parameters are passed to the People class
   public Person(String n, int a, double s) {
      name = n;
      age = a;
      salary = s;
      ssn = "";
      propertyChangeFired = false;
   }

    //This creates a readable String from the person class that involves name, age and salary.
    public String toString() {
        String newString = "Person[name:" + this.name + ";age:" + this.age + ";salary:" + this.salary + "]";
        return newString;
    }

    //Returns the current name of the Person
   public String getName() {
      return this.name;
   }

    //Returns the current SSN of the Person
   public String getSSN() {
      return this.ssn;
   }


    //Returns the current age of the Person.
   public int getAge() {
      return this.age;
   }

    //Returns the current salary of the Person
   public double getSalary() {
      return salary;
   }


   //Changes the property of name of a Person
   public void setName(String newName){
      if (newName == null) {
         throw new IllegalArgumentException();
      }
       String oldName = name;
       name = newName;

       this.pcs.firePropertyChange("name", oldName, newName);
       propertyChangeFired = true;
   }

    //Changes the property of age of a Person
   public void setAge(int newAge) {
      if (newAge < 0) {
         throw new IllegalArgumentException();
      }
      int oldAge = age;
      age = newAge;

       this.pcs.firePropertyChange("age", oldAge, newAge);
       propertyChangeFired = true;
   }

    //Changes the proprety of salary of a Person
   public void setSalary(double newSalary) {
       Double oldSalary = salary;
       salary = newSalary;

       this.pcs.firePropertyChange("salary", oldSalary, newSalary);
       propertyChangeFired = true;
   }

    //Changes the property of SSN of a Person
   public void setSSN(String value) {
      String old = ssn;
      ssn = value;
    
      this.pcs.firePropertyChange("ssn", old, value);
      propertyChangeFired = true;
   }

    //Determines that a property has been changed
   public boolean getPropertyChangeFired() {
      return propertyChangeFired;
   }

    //Calculates the bonus based upon the person's salary
   public double calculateBonus() {
      return this.salary * 1.10;
   }

    //Displays the persons name if they were to become a judge
   public String becomeJudge() {
      return "The Honorable " + this.name;
   }

    //Calculates the person's age 10 years into the future
   public int timeWarp() {
       this.setAge(this.age + 10);
       return this.age + 10;
   }

   //Compares two different People to see if they are the same people through their age and name
    public boolean equals(Object other){
      if (other != null && other instanceof Person) {
          Person p = (Person) other;
          return (name == p.getName() && age == p.getAge());
      }
      return false;
   }

   //Compare two different People. It then arranges the people in reverse-order based upon age. If the ages are the same, then it sorts them alphabetically
    public int compareTo(Person p) {
      if (age == p.getAge()) {
          return this.name.compareTo(p.getName());
      } else {
          if (age > p.getAge()) {
              return -1;
          } else { //age < p.getAge()
              return 1;
          }
      }
   }

   //Compares the salary of two People and then arranges them based upon salary.
   public static class SalaryComparator implements Comparator<Person>{
       public int compare(Person p1, Person p2) {
           if (p1.salary > p2.salary) {
               return 1;
           } else if (p1.salary < p2.salary) {
               return -1;
           } else {
               return 0;
           }
       }
   }

   //Creates a test family consisting of different People
   public static ArrayList<Person> createFamily() {
      Person Anakin = new Person("Anakin", 41, 75000);
      Person Padme = new Person("Padme", 46, 1000000);
      Person Luke = new Person("Luke", 19, 0);
      Person Leia = new Person("Leia", 19, 10000);
      ArrayList<Person> results = new ArrayList<Person>();
      results.add(Anakin);
      results.add(Padme);
      results.add(Luke);
      results.add(Leia);
      return results;	
   }


  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
   public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
   }
   public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
   }
}
