package derekentringer.com.arch_mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Repository implements Parcelable {

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

    public Repository() {
    }

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

    protected Repository(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.description = in.readString();
        this.forks = in.readInt();
        this.watchers = in.readInt();
        this.stars = in.readInt();
        this.language = in.readString();
        this.homepage = in.readString();
        this.owner = in.readParcelable(User.class.getClassLoader());
        this.fork = in.readByte() != 0;
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        public Repository createFromParcel(Parcel source) {
            return new Repository(source);
        }

        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.forks);
        dest.writeInt(this.watchers);
        dest.writeInt(this.stars);
        dest.writeString(this.language);
        dest.writeString(this.homepage);
        dest.writeParcelable(this.owner, 0);
        dest.writeByte(fork ? (byte) 1 : (byte) 0);
    }

}