package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int nPersons = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    nPersons++;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException();
    }
    this.age = age;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
  }
  
  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }
  
  public String getSSN() {
    return ssn;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  public int count() {
    return nPersons;
  }
  
  public boolean equals(Object other) {
    if (other instanceof Person) {
      return (this.name.equals(((Person)other).name) && this.age == ((Person)other).age);
    } else {
      return false;
    }
  }

  public String toString() {
    return "[Person name:" + name + " age:" + age + " salary:" + salary + "]";
  }

  public int compareTo(Object other) {
    return (int)(((Person)other).salary - this.salary);
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> newardFamily = new ArrayList<Person>();
    newardFamily.add(new Person("Ted", 41, 250000));
    newardFamily.add(new Person("Charlotte", 43, 150000));
    newardFamily.add(new Person("Michael", 22, 10000));
    newardFamily.add(new Person("Matthew", 15, 0));
    return newardFamily;
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


  public static class AgeComparator implements Comparator<Person> {
    
    public int compare(Person person, Person other) {
      return person.age - other.age;
    }

  }

}