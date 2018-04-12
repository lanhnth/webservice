<%-- 
    Document   : index
    Created on : Dec 3, 2017, 12:06:23 AM
    Author     : ducvu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN DNN CGV</title>
        <link rel="stylesheet" href="admin/css/bootstrap.css">
        <link rel="stylesheet" href="admin/css/dataTables.bootstrap4.css">
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
        <style>
            .tb {
                position: fixed;
                top: 90px;
            }
        </style>
    </head>
    <body>
        <!--alert success-->
        <%
            if (request.getParameter("alert") != null && request.getParameter("alert").equals("success")) {
        %>
        <div class="tb alert alert-success" role="alert">
            Success!!!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>  
        <%   }%>
        <!--alert fail-->
        <%
            if (request.getParameter("alert") != null && request.getParameter("alert").equals("fail")) {
        %>
        <div class="tb alert alert-danger" role="alert">
            Fail!!!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>  
        <%   }%>
        <jsp:include page='view/header.jsp' />

        <%
            try {
                if (request.getParameter("controller") != null && session.getAttribute("admin_login") != null) {
                    String t = "view/partial/" + request.getParameter("controller") + ".jsp";%>
        <jsp:include page='<%=t%>' /> 
        <%      } else { %>
        <jsp:include page='view/partial/login.jsp' /> 
        <%      }
        } catch (Exception e) {
            System.out.println("Loi404");%>
        <jsp:include page='view/partial/404.jsp' /> 
        <%    }%>


        <footer>

        </footer> <!-- /footer -->
        <!-- script -->
        <script type="text/javascript" src="admin/js/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="admin/js/jquery-slim.min.js"></script>
        <script type="text/javascript" src="admin/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="admin/js/vendor/popper.min.js"></script>
        <script type="text/javascript" src="admin/js/bootstrap.js"></script>
        <script type="text/javascript" src="admin/js/dataTables.bootstrap4.js"></script>
        <script type="text/javascript" src="admin/js/schedule/stepbystep.js"></script>
        <script type="text/javascript" src="admin/js/sale.js"></script>
        <script>
            (function () {
                window.addEventListener('load', function () {
                    var form = document.getElementById('needs-validation');
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                }, false);
            })();

            $(document).ready(function () {
//                $(".tb").fadeTo(2000, 500).slideUp(500, function () {
//                    $(".tb").slideUp(500);
//                });
            });

        </script>
    </body>
</html>
