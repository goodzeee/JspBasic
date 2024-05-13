package com.jsp.chap04;


import com.jsp.entity.Dancer;
import com.jsp.repository.DancerJdbcRepo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/chap04/remove")
public class DancerRemoveServlet extends HttpServlet {

    // 데베 사용하기 위해 해당 클래스 필드로 가져오기
    private final DancerJdbcRepo repo = DancerJdbcRepo.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("삭제 요청 서버에 들어옴.");

        // 삭제를 하려면 데이터베이스에서 해당 데이터 지워야 함.
        // 지우려면 뭘 지울지 클라이언트가 알려줘야 함.
        // 클라이언트에서 보낸 url 에 붙은 id값 읽어오기
        String id = req.getParameter("id");
        System.out.println("삭제대상 id : " + id);

        // 1. DB에 삭제 명령
        repo.delete(id); // id 줄테니 지워줘

        // 2. /chap04/show-list 요청을 자동으로 보냄 (리다이렉션) sendRedirect
        // (삭제를 하고 댄서 리스트를 보여줘 재사용하는 것 !)
        resp.sendRedirect("/chap04/show-list"); // 삭제하고 돌아와서 댄서 리스트 다루는 url 재사용 !

//        // retrieve = select 조회하는 기능 !
//        // 2. 삭제한 후 댄서 리스트 조회
//        List<Dancer> dancerList = repo.retrieve();
//        req.setAttribute("dancers", dancerList);
//
//        // 3. 적절한 화면 이동 -> 삭제를 하고 삭제된 걸 다시 조회해서 화면에 보여줘 ! 삭제된 댄서 수송 필요 !
//        RequestDispatcher dp = req.getRequestDispatcher("/WEB-INF/chap04/dancer-list.jsp");
//        dp.forward(req, resp);

    }
}
