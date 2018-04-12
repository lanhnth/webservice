<%-- 
    Document   : 404
    Created on : Dec 10, 2017, 1:36:27 AM
    Author     : ducvu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    .nb-error {
        margin: 0 auto;
        text-align: center;
        max-width: 480px;
        padding: 60px 30px;
    }

    .nb-error .error-code {
        color: #2d353c;
        font-size: 96px;
        line-height: 100px;
    }

    .nb-error .error-desc {
        font-size: 12px;
        color: #647788;
    }
    .nb-error .input-group{
        margin: 30px 0;
    }
</style>

<div class="nb-error">
    <div class="error-code">404</div>
    <h3 class="font-bold">We couldn't find the page..</h3>

    <div class="error-desc">
        Sorry, but the page you are looking for was either not found or does not exist. <br/>
        Try refreshing the page or click the button below to go back to the Homepage.
        <div class="input-group">
            <input type="text" placeholder="Try with a search" class="form-control">
            <span class="input-group-btn">
                <button type="button" class="btn btn-default">
                    <em class="fa fa-search"></em>
                </button>
            </span>
        </div>
        <ul class="list-inline text-center text-sm">
            <li class="list-inline-item"><a href="home" class="text-muted">Go to Home</a>
            </li>
        </ul>
        <div class="text-center">
            <span>©</span>
            <span>2017</span>
            <span>-</span>
            <span>DGC DNN</span>
            <br>
        </div>
    </div>
</div>
