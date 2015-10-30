package derekentringer.com.arch_mvp.model;

import com.google.gson.annotations.SerializedName;

public class Repository {

	@SerializedName("id")
	private long id;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	public String description;

	@SerializedName("forks")
	public int forks;

	@SerializedName("watchers")
	public int watchers;

	@SerializedName("stargazers_count")
	public int stars;

	@SerializedName("language")
	public String language;

	@SerializedName("homepage")
	public String homepage;

	@SerializedName("owner")
	public User owner;

	@SerializedName("fork")
	public boolean fork;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getForks() {
		return forks;
	}

	public void setForks(int forks) {
		this.forks = forks;
	}

	public int getWatchers() {
		return watchers;
	}

	public void setWatchers(int watchers) {
		this.watchers = watchers;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isFork() {
		return fork;
	}

	public void setFork(boolean fork) {
		this.fork = fork;
	}

}