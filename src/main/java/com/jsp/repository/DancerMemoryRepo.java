package com.jsp.repository;


import com.jsp.entity.Dancer;

import java.util.ArrayList;
import java.util.List;

// 역할 : 메모리 데이터베이스에 댄서들을 CRUD, DB 저장만 역할.
// MVC = Model (댄서 정보 데베에 저장하기 위해 필요한)
public class DancerMemoryRepo {

    // 싱글톤 구현 - 객체 생성 하나만 구현 !
    private static DancerMemoryRepo repo = new DancerMemoryRepo();
    // 생성자에도 private 부여 only 1 !
    private DancerMemoryRepo() {
    }
    // 싱글 객체를 리턴하는 메서드
    public static DancerMemoryRepo getInstance() {
        return repo;
    }

    // 데이터베이스 역할을 할 자료구조
    private List<Dancer> dancerList = new ArrayList<>();

    // 댄서를 데이터베이스에 저장하는 기능
    public boolean save(Dancer dancer) {
        if (dancer == null) return false;
        dancerList.add(dancer);
        System.out.println(dancerList);
        return true;
    }

    // 댄서 리스트를 반환하는 기능
    public List<Dancer> retrieve() {
        return dancerList;
    }
}
