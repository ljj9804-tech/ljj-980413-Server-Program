<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Member Registration</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
  <div class="card shadow-sm" style="width: 100%; max-width: 450px;">
    <div class="card-header bg-dark text-white">
      <h4 class="mb-0">회원가입</h4>
    </div>
    <div class="card-body p-4">
      <form action="/member/register" method="post">
        <div class="mb-3">
          <label class="form-label">아이디</label>
          <input type="text" name="mid" class="form-control" placeholder="아이디를 입력하세요" required>
        </div>
        <div class="mb-3">
          <label class="form-label">비밀번호</label>
          <input type="password" name="mpw" class="form-control" placeholder="비밀번호를 입력하세요" required>
        </div>
        <div class="mb-3">
          <label class="form-label">이름</label>
          <input type="text" name="mname" class="form-control" placeholder="실명을 입력하세요" required>
        </div>

        <div class="d-grid gap-2 mt-4">
          <button type="submit" class="btn btn-primary">가입 신청</button>
          <button type="button" class="btn btn-secondary" onclick="location.href='/login_0209'">취소</button>
        </div>
      </form>
    </div>
    <div class="card-footer text-muted text-center small">
      © 2026 BusanIT 501 Project
    </div>
  </div>
</div>

<script>
  // 가입 실패 시 컨트롤러에서 보낸 에러 파라미터 체크
  const urlParams = new URLSearchParams(window.location.search);
  if(urlParams.get('error') === 'fail') {
    alert("회원가입 처리 중 오류가 발생했습니다.");
  }
</script>

</body>
</html>