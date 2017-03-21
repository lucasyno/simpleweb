<%--
  Created by IntelliJ IDEA.
  User: lukaszgodlewski
  Date: 12.03.2017
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Harmonogram spłat</title>
    <meta http-equiv="content-type" content="text/html" charset="ISO-8859-1">
</head>
<body>
    <form action="raty" method="post" id="kredyt">
       <label>kwota kredytu:<input type="number" id="kwota" name="kwota"/></label><br>
        <label>ilość rat:<input type="number" id="ilosc_rat" name="ilosc_rat"/></label><br>
        <label>Oprocentowanie:<input type="number" id="oprocentowanie" name="oprocentowanie"/></label><br>
        <label>Opłata stała:<input type="number" id="oplata_stala" name="oplata_stala"/></label><br>
        <label>Rodzaj rat:
            <select name="rodzaj" form="kredyt">
                <option value="malejaca">Malejąca</option>
                <option value="stala">Stała</option>
            </select>
        </label><br>
        <input type="submit" value="wyslij"/>
    </form>
</body>
</html>
