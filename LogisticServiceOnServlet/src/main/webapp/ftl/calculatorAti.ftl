<!DOCTYPE html>
<html>
<head>

    <style>

        @import url('https://fonts.googleapis.com/css?family=Comfortaa:300,400,700|Merriweather&subset=cyrillic');

        .body {
            font-family: 'Comfortaa', cursive;
        }

    </style>

    <title>Calculator ATI</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="../css/fakeLoader.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <script>

        function exit() {
            var date = new Date(0);
            document.cookie = "auth=; path=/; expires=" + date.toUTCString();
            document.cookie = "baskId=; path=/; expires=" + date.toUTCString();
            window.location.replace("/home")
        }
    </script>


</head>

<body class="body">

<#include "header.ftl">

<FORM id="Form1" name="frmTraceDetect" action="http://ati.su/Trace/" method="post" target="_blank">
    <TABLE cellSpacing="0" cellPadding="4">
        <TR>
            <TD colSpan="2">Расчет расстояния между городами</TD>
        </TR>
        <TR>
            <TD>От:</TD>
            <TD> <INPUT type="text" size="20" name="City1"></TD>
        </TR>
        <TR>
            <TD>До:</TD>
            <TD> <INPUT type="text" size="20" name="City5"></TD>
        </TR>
        <TR>
            <TD>Через:</TD>
            <TD> <INPUT type="text" name="City2" size="20"></TD>
        </TR>
        <TR>
            <TD colSpan="2" align="center"><INPUT type="submit" value="Определить" name="Submit1"> 
                <FONT size="2">(на <a href="http://ati.su">ATI.su</a>)</FONT></TD>
        </TR>
    </TABLE>
</FORM>

<!-- Bootstrap core JavaScript ================================================== -->
<script>window.jQuery || document.write('<script src="../js/jquery-1.10.2.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</body>
</html>