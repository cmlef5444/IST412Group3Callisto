<%-- 
    Document   : login
    Created on : Jul 31, 2020, 12:58:39 PM
    Author     : cjani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><table border="1">
        <thead>
            <tr>
                <th>IFPWAFCAD offers expert counseling in a wide range of fields</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>To view the contact details of an IFPWAFCAD certified former
                    professional wrestler in your area, select a subject below:</td>
            </tr>
            <tr>
                <td>
                    <form action="Navigation">
                        <strong> Select a subject:
                            <select name="customerFirstName">
                                <option></option>
                                <option></option>
                            </select>
                        <input type="submit" value="submit" name="submit"/>
                    </form>
                </td>
            </tr>
        </tbody>
    </table>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>

    <div class="container">
        <label for="uname"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="uname" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit">Login</button>
        <label>
            <input type="checkbox" checked="checked" name="remember"> Remember me
        </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn">Cancel</button>
        <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
</html>
