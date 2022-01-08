package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kateuthinsh")
public class Kateuthinsh {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "perigrafh")
    private String perigrafh;

    @Column(name = "subjects")
    private String subjects;

    
    public Kateuthinsh() {
    }

    public Kateuthinsh(int id,String perigrafh, String subjects) {
        super();
        this.id = id;
        this.perigrafh = perigrafh;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerigrafh() {
		return perigrafh;
	}

	public void setPerigrafh(String perigrafh) {
		this.perigrafh = perigrafh;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	@Override
    public String toString() {
        return "Kateuthinsh [id=" + id + ", perigrafh=" + perigrafh + ", subjects=" + subjects + "]";
    }

}