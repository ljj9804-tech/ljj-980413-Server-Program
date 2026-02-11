package com.busanit501.ljj980413serverprogram.todo.service;

import com.busanit501.ljj980413serverprogram.todo.dao._0209_19_MemberDAO;
import com.busanit501.ljj980413serverprogram.todo.domain._0209_17_MemberVO;
import com.busanit501.ljj980413serverprogram.todo.dto._0209_18_MemberDTO;
import com.busanit501.ljj980413serverprogram.todo.util._0209_4_MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum _0209_21_MemberService {
    INSTANCE;

    private _0209_19_MemberDAO dao;
    private ModelMapper modelMapper;

    _0209_21_MemberService() {
        // DAO 클래스에 추가한 INSTANCE를 사용합니다.
        dao = _0209_19_MemberDAO.INSTANCE;
        modelMapper = _0209_4_MapperUtil.INSTANCE.get();
    }

    // [추가] 회원가입 기능 메서드
    public void register(_0209_18_MemberDTO dto) throws Exception {
        _0209_17_MemberVO vo = modelMapper.map(dto, _0209_17_MemberVO.class);
        log.info("MemberService register vo: " + vo);
        dao.insert(vo);
    }

    // 로그인 기능 메서드
    public _0209_18_MemberDTO login(String mid, String mpw) throws Exception {
        _0209_17_MemberVO vo = dao.getWithPassword(mid, mpw);
        // null 체크 로직 추가 권장 (로그인 실패 시)
        if (vo == null) return null;
        return modelMapper.map(vo, _0209_18_MemberDTO.class);
    }

    // uuid 업데이트 기능 (자동로그인)
    public void updateUuid(String mid, String uuid) throws Exception {
        dao.updateUuid(mid, uuid);
    }

    // uuid로 회원 정보 가져오기 (자동로그인)
    public _0209_18_MemberDTO getByUUID(String uuid) throws Exception {
        _0209_17_MemberVO memberVO = dao.selectUUID(uuid);
        if (memberVO == null) return null;
        return modelMapper.map(memberVO, _0209_18_MemberDTO.class);
    }
}