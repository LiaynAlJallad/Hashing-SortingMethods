package LA3Q1;

public class LiyanValueEntry {

//    field
    private Integer key;

//    constructor with no args, setting key = -1
    public LiyanValueEntry (){
        key=-1;
    }

//    constructor with args
    public LiyanValueEntry(Integer key){
        this.key=key;
    }

//    Setter
    public void setKey (Integer key){
        this.key=key;
    }

//    Getter
    public Integer getKey (){
        return key;
    }
}
