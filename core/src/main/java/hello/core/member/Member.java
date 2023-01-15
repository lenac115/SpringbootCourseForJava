package hello.core.member;

public class Member {
    private Long ID;
    private String name;
    private Grade grade;

    public Member(Long id, String name, Grade grade){
        this.ID = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId(){
        return ID;
    }
    public void setId(Long ID){
        this.ID = ID;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Grade getGrade(){
        return grade;
    }
    public void setGrade(){
        this.grade = grade;
    }
}
