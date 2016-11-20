<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form:form method="POST" action="/converter/login">
    <table>

        <tr>
            <td><label for="username">Email</label></td>
            <td><input id="username" name="username"/>&nbsp;*</td>
            <td></td>
        </tr>

        <tr>
            <td><label for="password">Password</label></td>
            <td><input type="password" id="password" name="password"/>&nbsp;*</td>
            <td></td>
        </tr>

        <tr>
            <td>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </td>
            <td class="submit_button">
                <input type="submit" value="Login"/>
            </td>
            <td/>
        </tr>
    </table>
</form:form>
</body>
</html>
