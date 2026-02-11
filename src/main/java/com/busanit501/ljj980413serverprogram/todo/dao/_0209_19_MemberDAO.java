package com.busanit501.ljj980413serverprogram.todo.dao;


import com.busanit501.ljj980413serverprogram.todo.domain._0209_17_MemberVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class _0209_19_MemberDAO {

    public _0209_17_MemberVO getWithPassword(String mid, String mpw) throws Exception {
        // SQL 문장 선언,
        String sql = "select mid, mpw, mname from tbl_member where mid = ? and mpw = ?";

        _0209_17_MemberVO memberVO = null;

        @Cleanup Connection connection = _0209_7_ConnectionUtil.INSTANCE.getConnection();

        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, mid);
        preparedStatement.setString(2, mpw);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        memberVO = _0209_17_MemberVO.builder()
                .mid(resultSet.getString(1))
                .mpw(resultSet.getString(2))
                .mname(resultSet.getString(3))
                .build();
        return memberVO;
    }


    public void updateUuid(String mid, String uuid) throws Exception {
        String sql = "update tbl_member set uuid =? where mid = ?";

        @Cleanup Connection connection = _0209_7_ConnectionUtil.INSTANCE.getConnection();

        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, uuid);
        preparedStatement.setString(2, mid);

        preparedStatement.executeUpdate();
    }

    public _0209_17_MemberVO selectUUID(String uuid) throws Exception {

        String sql = "select mid,mpw,mname,uuid from tbl_member where uuid = ?";

        @Cleanup Connection connection = _0209_7_ConnectionUtil.INSTANCE.getConnection();

        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, uuid);

        @Cleanup ResultSet resultSet =  preparedStatement.executeQuery();

        resultSet.next();

        _0209_17_MemberVO memberVO = _0209_17_MemberVO.builder()
                .mid(resultSet.getString(1))
                .mpw(resultSet.getString(2))
                .mname(resultSet.getString(3))
                .uuid(resultSet.getString(4))
                .build();
        return memberVO;
    }

    //회원가입 기능 추가
    public static final _0209_19_MemberDAO INSTANCE = new _0209_19_MemberDAO();

    private _0209_19_MemberDAO() {}

    public void insert(_0209_17_MemberVO vo) throws Exception {
        String sql = "insert into tbl_member (mid, mpw, mname) values (?, ?, ?)";

        @Cleanup Connection connection = _0209_7_ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, vo.getMid());
        preparedStatement.setString(2, vo.getMpw());
        preparedStatement.setString(3, vo.getMname());

        preparedStatement.executeUpdate();
    }

}
