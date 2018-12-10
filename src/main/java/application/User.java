package application;

public class User {
   private String nickName=new String();
    public User(String nickName){
        this.nickName=nickName;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
