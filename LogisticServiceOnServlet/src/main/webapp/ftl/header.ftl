<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>

        @import url('https://fonts.googleapis.com/css?family=Comfortaa:300,400,700|Merriweather&subset=cyrillic');

        .body {
            font-family: 'Comfortaa', cursive;
        }

    </style>

    <title>Header</title>


    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>

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

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <a class="navbar-brand" href="/home">LogistLog</a>

    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbar"
            aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="navbar-collapse collapse" id="navbar" style="">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="/signIn">Sign in</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/signUp">Sign up</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/home">Home<span class="sr-only">(current)</span> </a>
            </li>

            <li class="nav-item ">
                <a class="nav-link" href="/calculator">Distance ATI calculator<span
                        class="sr-only">(current)</span> </a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="/profile" id="dropdown05" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Profile</a>
                <div class="dropdown-menu" aria-labelledby="dropdown05">
                    <a class="dropdown-item" href="/profile">User</a>
                    <a class="dropdown-item" href="/profile#company" data-target="#company"
                       data-toggle="tab">Company</a>
                    <a class="dropdown-item" href="/profile" data-target="#application"
                       data-toggle="tab">Application</a>
                </div>

            </li>

        <#--<li class="nav-item">-->
        <#--<a class="nav-link" href="/shop">Shop<span class="sr-only">(current)</span> </a>-->
        <#--</li>-->

            <a class="nav-link" id="showGreetingMessage" style="color: ghostwhite; padding-top: 2%">
            ${greetingMessage}
            </a>

            <img class="img-fluid" onclick="exit()" src="../img/logoutUser.jpg"
                 style=" padding-left: 1%; width: 32px; height: 50px; object-fit: cover;"
                 alt="Exit">

        </ul>

    <#--<form class="form-inline my-2 my-md-0">-->
    <#--<input class="form-control" type="text" placeholder="Search">-->
    <#--</form>-->
    <#---->

    </div>
</nav>

</body>
</html>