package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String desc;
	private int releaseYear;
	private int langId;
	private int rentDur;
	private double rate;
	private int length;
	private double repCost;
	private String rating;
	private String features;
	private String language;
	private String category;
	private String condition;
	private List<Film> inventory;
	private List<Actor> actors;

	public Film() {
	}
	
	
	public Film(String title, String condition) {
		this.title = title;
		this.condition = condition;
	}

	public Film(int id, String title, String desc, int releaseYear, int langId, int rentDur, double rate, int length,
			double repCost, String rating, String features) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDur = rentDur;
		this.rate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
	}

	public Film(int id, String title, String desc, int releaseYear, int langId, int rentDur, double rate, int length,
			double repCost, String rating, String features, List<Actor> actors) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDur = rentDur;
		this.rate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
		this.actors = actors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public int getRentDur() {
		return rentDur;
	}

	public void setRentDur(int rentDur) {
		this.rentDur = rentDur;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getRepCost() {
		return repCost;
	}

	public void setRepCost(double repCost) {
		this.repCost = repCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	

	public List<Film> getInventory() {
		return inventory;
	}

	public void setInventory(List<Film> inventory) {
		this.inventory = inventory;
	}

	public String simpleDisplay() {
		return "| TITLE: " + title + " | YEAR: " + releaseYear + " | RATING: " + rating + " | LANGUAGE: " + language + " | CATEGORY: " + category +" |\n"
				+ "| DESCRIPTION: " + desc + " |\nACTORS:\n" + actorList();

	}

	public String actorList() {
		String actorNames = "";
		for (Actor actor : actors) {
			actorNames += actor.getFirstName() + " " + actor.getLastName() + "\n";
		}
		return actorNames;
	}

	public String fullDetails() {
		return "| FILM ID: " + id + " | TITLE: " + title + " | RELEASE YEAR: " + releaseYear + " | LANGUAGE ID: " + langId
				+ " | RENTAL DURATION: " + rentDur + " | RENTAL RATE: " + rate + " | LENGTH: " + length
				+ " |\n| REPLACEMENT COST: " + repCost + " | RATING: " + rating + " | SPECIAL FEATURES: " + features
				+ " |\n| DESCRIPTION: " + desc + " |\nINVENTORY:\n" + inventoryList();

	}
	
	public String inventoryList() {
		String inventoryItems = "";
		int counter = 1;
		for (Film film : inventory) {
			inventoryItems += counter +". " + film.getTitle() + " " + film.getCondition() + "\n";
			counter++;
		}
		return inventoryItems;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return id == other.id;
	}

}
