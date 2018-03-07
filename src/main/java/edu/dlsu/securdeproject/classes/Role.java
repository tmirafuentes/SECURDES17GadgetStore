package edu.dlsu.securdeprojet.classes;

import java.persistence.*;
import java.util.Set;

@Entity
public class Role {
	private Long id;
	private String name;
	private Set<User> users;

	@id
	@GeneratedValue(strategy = GeneratedType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mapped = "roles")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}