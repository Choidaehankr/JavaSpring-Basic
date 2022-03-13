package hello.hellospring.controller;

public class MemberForm {
    private String name;  // template/members/createMemberForm.html에서 받은 name과 매칭이됨.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
