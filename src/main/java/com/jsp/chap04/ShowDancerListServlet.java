package com.jsp.chap04;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerJdbcRepo;
import com.jsp.repository.DancerMemoryRepo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 역할 : 댄서 목록 조회 요청을 받아서 데이터베이스에 있는 댄서 정보를
// 가져온 후 적당한 HTML을 찾아서 forwarding !!
@WebServlet("/chap04/show-list")
public class ShowDancerListServlet extends HttpServlet {

    // 해당 클래스에서 데이터베이스에서 댄서 리스트 가져오는 처리 해주니 가져와서 사용
    private DancerJdbcRepo repo = DancerJdbcRepo.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse reps) throws IOException, ServletException {

        // 데이터베이스에 접근하여 댄서 목록 가져오기 ->
        List<Dancer> dancerList = repo.retrieve();
        // System.out.println("가져와 !! = " + dancerList);

        // 댄서 목록을 jsp파일에게 보낼 데이터 수송 객체에 담기
        req.setAttribute("dancers", dancerList);
        // 요 밑에 forward 파라미터로 들어가서 댄서 목록 보여줄 수 있어짐
        // jsp 파일 열기 -> 리스트 목록 보여줄 html
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/chap04/dancer-list.jsp");
        rd.forward(req, reps);
    }
}
