<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="admin_style/modal.css?var=12022" type="text/css" charset="UTF-8" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/gh/stove99/jquery-modal-sample@v1.4/css/animate.min.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/gh/stove99/jquery-modal-sample@v1.4/css/jquery.modal.css" />
<script src="//cdn.jsdelivr.net/gh/stove99/jquery-modal-sample@v1.4/js/jquery.modal.js"></script>
<script src="//cdn.jsdelivr.net/gh/stove99/jquery-modal-sample@v1.4/js/modal.js"></script>

<style>
</style>
</head>
<body>
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div id="test_page">
    <p>
       승인하시겠습니까?
    </p>

    <div class="text-center">
        <button id="close_btn" type="button" class="btn btn-danger btn-lg">닫기</button>
    </div>
</div>
    <script>
        var base = $('#test_page').parents('.modal_popup');

        $('#close_btn', base).on('click', function() {
            // 창 닫을때 부모창으로 값 넘기기
            $.modal.getCurrent().close({ x: '333333' });
        });
    </script>
</div>



</body>
</html>