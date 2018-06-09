/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info;

import entity.Rent;
import entity.RentItem;
import entity.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class rentinfo {
    Rent rent;
    Student student;
    List<RentItem> items;
    public rentinfo() {
        items = new ArrayList<RentItem>();
        rent = new Rent();
        student = new Student();
    }
    public void setRent(Rent rent){
        this.rent =rent;
    }
    public Rent getRent(){
        return rent;
    }

    public void setStudent(Student student){
        this.student =student;
    }
    public Student getStudent(){
        return student;
    }
    public void setitems(List<RentItem> items){
        this.items =items;
    }
    public List<RentItem> getItems(){
        return items;
    }
}
