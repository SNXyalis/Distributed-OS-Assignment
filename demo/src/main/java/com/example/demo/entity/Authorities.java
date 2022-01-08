package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
 
    @Column(name = "username")
    private String username;
    
   
    @Column(name = "authority")
    private String authority;

    public Authorities() {
    }

    public Authorities(int id,String username,String authority) {
        super();
        this.id=id;
        this.username=username;
        this.authority=authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
    public String toString() {
        return "User [id=" + id + ",username=" + username +" ,authority = " +authority+ "]";
    }
    
}