<%@taglib uri="/tlds/usertaglib.tld" prefix="user"%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><user:locale value="login.title"/></h1>
        <center><form action="login" method="post">
                <table><tr>
                        <td><user:locale value="login.login"/></td><td><input type="text" name="login"/></td>
                    </tr>
                    <tr>
                        <td><user:locale value="login.pass"/></td><td><input type="password" name="pass"/></td>
                    </tr>
                    <tr>
                        <td></td><td><input type="submit" name="submit" value="<user:locale value="login.submit"/>" style="float:right"/></td>
                    </tr>
                </table>
        </form></center>
    </body>
</html>
