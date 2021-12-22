package objects;

public class User {
    int keyUser;
    String name;
    String gender;
    String password;
    int keyCountry;

    public User(int keyUser, String name, String password, String gender, int keyCountry) {
        this.keyUser = keyUser;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.keyCountry = keyCountry;
    }
    
    public User(String name,String password, String gender,int keyCountry) {
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.keyCountry =keyCountry;
    }

    public User(int keyUser, String name, String password) {
        this.keyUser = keyUser;
    	this.name = name;
        this.password = password;
    }

    public int getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(int keyUser) {
        this.keyUser = keyUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getKeyCountry() {
        return keyCountry;
    }

    public void setKeyCountry(int KeyCountry) {
        this.keyCountry = KeyCountry;
    }
 
}
