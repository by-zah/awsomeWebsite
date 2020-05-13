<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">
<%@ page isELIgnored="false" %>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" id="bootstrap-css" rel="stylesheet">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css"
          rel="stylesheet">
    <meta charset="utf-8">
    <title>Главная</title>
    <link rel="icon" href="img/icon.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<a class="back-to-top-button" id="back-to-top-button" href="#">↑</a>
<%@ include file="jspf/header.jspf" %>

<content class="content">
    <div class="content-container">
        <h2 id="content-title">каталог</h2>
        <div class="sort-buttons-container">
            <div class="catalog-menu-panel">
                <form class="catalog-menu-panel-form" method="GET" action="catalog">

                    <div class="checkbox-full-container">

                        <div class="checkbox-container">
                            <input class="search-checkbox" type='checkbox' name='tshirt' id='Tshirt' value='Tshirt'>
                            <p>одежда</p>
                        </div>
                        <div class="checkbox-container">
                            <input class="search-checkbox" type='checkbox' name='toy' id='Toy' value='Toy'>
                            <p>игрушки</p>
                        </div>
                        <div class="checkbox-container">
                            <input class="search-checkbox" type='checkbox' name='figure' id='Figure' value='Figure'>
                            <p>фигурки</p>
                        </div>
                        <div class="checkbox-container">
                            <input class="search-checkbox" type='checkbox' name='accessory' id='Accessory'
                                   value='Accessory'>
                            <p>аксессуары</p>
                        </div>

                    </div>

                    <div class="form-checkbox-attributes">

                        <label for="custom-select" class="attribute-label">цвет:</label>
                        <select class="selectpicker  form-control" data-selected-text-format="count > 3"
                                data-width="auto" id="color" multiple="multiple">
                            <option class="color-option" name="color" value="red">красный</option>
                            <option class="color-option" name="color" value="black">черный</option>
                            <option class="color-option" name="color" value="grey">серый</option>
                        </select>
                        <label for="custom-select" class="attribute-label">размер:</label>
                        <select class="selectpicker  form-control" data-selected-text-format="count > 3"
                                data-width="auto"
                                id="size" class="custom-select" multiple="multiple">
                            <option class="size-option" name="size" value="m">m</option>
                            <option class="size-option" name="size" value="l">l</option>
                            <option class="size-option" name="size" value="xl">xl</option>
                        </select>
                        <label for="custom-select" class="sort-label">сортировать по:</label>
                        <select id="sortType" class="custom-select">
                            <option
                                    <c:if test="${userRequestParameter.sortType==1}">selected</c:if>
                                    value="PRICE_UP">по возрастанию
                            </option>
                            <option
                                    <c:if test="${userRequestParameter.sortType==2}">selected</c:if>
                                    value="PRICE_DOWN">по убыванию
                            </option>
                            <option
                                    <c:if test="${userRequestParameter.sortType==3}">selected</c:if>
                                    value="ALPHABET_UP">по алфавиту (а-я)
                            </option>
                            <option
                                    <c:if test="${userRequestParameter.sortType==4}">selected</c:if>
                                    value="ALPHABET_DOWN">по алфавиту (я-а)
                            </option>
                        </select>
                        <button class="checkbox-attributes-button" name="button" type="button"
                                id="submit-search-button" onclick="getAll()">поиск
                        </button>

                    </div>
                    <div class="sort-buttons">

                    </div>
                </form>
            </div>

        </div>

        <div class="content-divider" style="width: 100%;"></div>

        <div class="content-card-holder">
            <h4 class="content-card-title"></h4>
            <div class="content-card-holder-box" id="content-card-holder-box0"></div>
        </div>

        <div class="content-divider" style="width: 100%;"></div>

        <div class="pages-container">
            <div class="pages-holder">
                <div class="page">
                    <button type="button" class="page-link" id="more" more="2" name="more" value="2"
                            onClick="setMore();">загрузить еще
                    </button>
                </div>
            </div>
        </div>

    </div>

</content>

<%@ include file="jspf/footer.jspf" %>
<script src="js/jQuery.min.js"></script>
<script src="js/banner.js"></script>
<script src="js/back-to-top.js"></script>
<script src="js/catalog.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script crossorigin="anonymous"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
<script crossorigin="anonymous"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>