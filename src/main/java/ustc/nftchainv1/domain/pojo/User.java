package ustc.nftchainv1.domain.pojo;

import lombok.Data;

@Data
public class User {
    private long id;
    private String name;
    private  Integer age;
    public User(String name,int age){
        this.name = name;
        this.age = age;
    }
}
