package com.example.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "aithsh")
public class Aithsh {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "programma_spoudwn")
    private String programma_spoudwn;

    @Column(name = "grade")
    private int grade;
    
    @Column(name = "epistoles")
    private String epistoles;
    
    @Column(name = "kateuthinsh")
    private String kateuthinsh;
    
    @Column(name = "AM")
    private int AM;
    
    @Column(name = "state")
    private String state;
    
    @Column(name = "seira")
    private int seira;

    
    public Aithsh() {
    }

    public Aithsh(int id,String programma_spoudwn,int grade ,String epistoles,String kateuthinsh,int AM,String state,int seira) {
        super();
        this.id = id;
        this.programma_spoudwn = programma_spoudwn;
        this.grade = grade;
        this.epistoles = epistoles;
        this.kateuthinsh = kateuthinsh;
        this.AM = AM;
        this.state = state;
        this.seira=seira;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getProgramma_spoudwn() {
		return programma_spoudwn;
	}

	public void setProgramma_spoudwn(String programma_spoudwn) {
		this.programma_spoudwn = programma_spoudwn;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getEpistoles() {
		return epistoles;
	}

	public void setEpistoles(String epistoles) {
		this.epistoles = epistoles;
	}

	public String getKateuthinsh() {
		return kateuthinsh;
	}

	public void setKateuthinsh(String kateuthinsh) {
		this.kateuthinsh = kateuthinsh;
	}

	public int getAM() {
		return AM;
	}

	public void setAM(int aM) {
		AM = aM;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	public int getSeira() {
		return seira;
	}

	public void setSeira(int seira) {
		this.seira = seira;
	}
	
	
	@Override
    public String toString() {
        return "Aithsh [id=" + id + ", programma_spoudwn=" + programma_spoudwn + ", grade=" + grade + ",epistoles=" + epistoles+ ",kateuthinsh=" + kateuthinsh + ",AM=" + AM + ",state=" + state + "]";
    }

}