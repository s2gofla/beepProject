<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!--*******  경로맞춰서 css style 부여 -->
        <link rel="stylesheet" href="admin_style/admin_sidemenu_style.css?var=1322" type="text/css" charset="UTF-8" />
        <link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
        <link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
        <title>PPIYONPPIYONG</title>

    </head>

    <body>
       

       
            <div id="floatdiv">
                <ul>
                    <a href="admin_notice_wirte.jsp">공지사항 작성</a>
                </ul>
                <ul>
                    <a href="admin_toptip_wirte.jsp">꿀팁게시글 작성</a>
                </ul>
                <ul>
                    <a href="admin_faq_wirte.jsp">FAQ 작성</a>
                </ul>
                <ul>
                    <a href="admin_confirm.jsp">의사,병원,의약품 승인</a>
                </ul>
                <ul>
                    <a href="admin_report.jsp">신고내역확인</a>
                </ul>
                <ul>
                    <a href="admin_statics.jsp">통계</a>
                </ul>
            </div>
   

        <script type="text/javascript">
            var stmnLEFT = 10; // 오른쪽 여백 
            var stmnGAP1 = 0; // 위쪽 여백 
            var stmnGAP2 = 150; // 스크롤시 브라우저 위쪽과 떨어지는 거리 
            var stmnBASE = 150; // 스크롤 시작위치 
            var stmnActivateSpeed = 35; //스크롤을 인식하는 딜레이 (숫자가 클수록 느리게 인식) 
            var stmnScrollSpeed = 20; //스크롤 속도 (클수록 느림)
            var stmnTimer;

            function RefreshStaticMenu() {
                var stmnStartPoint, stmnEndPoint;
                stmnStartPoint = parseInt(document.getElementById('STATICMENU').style.top, 10);
                stmnEndPoint = Math.max(document.documentElement.scrollTop, document.body.scrollTop) + stmnGAP2;
                if (stmnEndPoint <
                    stmnGAP1) stmnEndPoint = stmnGAP1;
                if (stmnStartPoint != stmnEndPoint) {
                    stmnScrollAmount = Math.ceil(Math.abs(stmnEndPoint - stmnStartPoint) / 15);
                    document.getElementById('STATICMENU').style.top = parseInt(document.getElementById(
                        'STATICMENU').style.top, 10) + ((stmnEndPoint < stmnStartPoint) ? -stmnScrollAmount : stmnScrollAmount) + 'px';
                    stmnRefreshTimer = stmnScrollSpeed;
                }
                stmnTimer = setTimeout("RefreshStaticMenu();", stmnActivateSpeed);
            }

            function InitializeStaticMenu() {
                document.getElementById('STATICMENU').style.right = stmnLEFT + 'px'; //처음에 오른쪽에 위치. left로 바꿔도. 
                document.getElementById('STATICMENU').style.top = document.body.scrollTop + stmnBASE + 'px';
                RefreshStaticMenu();
            }
        </script>

        <footer></footer>
        <script type="text/javascript" src="layout-js.js"></script>

        <!-- js파일연결 body태그 하단에 해줘야함 -->
    </body>

    </html>