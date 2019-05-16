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

    <title>Profile</title>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

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

            <li class="nav-item active dropdown">
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

            <a class="nav-link" id="showGreetingMessage" style="color: ghostwhite; padding-top: 2%">
                ${greetingMessage}
            </a>

            <img class="img-fluid" onclick="exit()" src="../img/logoutUser.jpg"
                 style=" padding-left: 1%; width: 32px; height: 50px; object-fit: cover;"
                 alt="Exit">
        </ul>

    </div>
</nav>

<div class="container" style="padding-top: 5%">

    <div class="row m-y-2">

        <div class="col-lg-8 push-lg-4">

            <ul class="nav nav-tabs">

                <li class="nav-item active">
                    <a href="#profile" data-target="#profile" data-toggle="tab" class="nav-link active">Profile</a>
                </li>

                <li class="nav-item">
                    <a href="#company" data-target="#company" data-toggle="tab" class="nav-link">Company</a>
                </li>

                <li class="nav-item">
                    <a href="#application" data-target="#application" data-toggle="tab" class="nav-link">Application</a>
                </li>

            </ul>

            <div class="tab-content p-b-3" style="padding-top: 5%">

                <div class="tab-pane" id="profile">

                    <h4 class="m-y-2">User Profile</h4>

                    <div class="row">

                        <div id="userProfile">

                            <div class="form-group row">
                                <label class="col-lg-2 col-form-label form-control-label">Email:</label>
                                <div class="col-lg-10">
                                    <div class="form-control-plaintext" name="email" type="email">${profile.email}</div>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-lg-2 col-form-label form-control-label">Phone number:</label>
                                <div class="col-lg-10">
                                    <div class="form-control-plaintext" name="phoneNumber"
                                         type="text">${profile.phoneNumber}</div>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-lg-4 col-form-label form-control-label">Username:</label>
                                <div class="col-lg-8">
                                    <div class="form-control-plaintext" name="login" type="text">${profile.login}</div>
                                </div>
                            </div>

                            <div class="form-group row">

                                <label class="col-lg-3 col-form-label form-control-label">First name:</label>

                                <div class="col-lg-3">
                                    <div class="form-control-plaintext" name="firstName"
                                         type="text">${profile.firstName}</div>
                                </div>

                                <label class="col-lg-3 col-form-label form-control-label">Last name:</label>
                                <div class="col-lg-3">
                                    <div class="form-control-plaintext" name="lastName"
                                         type="text">${profile.lastName}</div>
                                </div>

                            </div>

                            <div class="form-group row">
                                <label class="col-lg-4 col-form-label form-control-label">Address:</label>
                                <div class="col-lg-8">
                                    <div class="form-control-plaintext" name="address"
                                         type="text">${profile.address}</div>
                                </div>
                            </div>

                            <hr>

                            <div class="form-group row">
                                <div class="col-lg-6">
                                    <button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#EditUserModal">
                                        Edit user
                                    </button>
                                </div>

                                <div class="col-lg-6">
                                    <button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#EditUserPasswordModal">
                                        Edit password
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="tab-pane" id="company">

                    <div id="companyProfile">
                        <#if company??>
                            <h4 class="m-y-2">Company profile( role
                                - ${company.companyRole} )</h4>

                            <div class="form-group row">
                                <label class="col-lg-4 col-form-label form-control-label" for="viewCompanyName">Company
                                    name:</label>

                                <div class="col-lg-8">
                                    <div type="text" name="viewCompanyName" class="form-control-plaintext"
                                         id="viewCompanyName">${company.companyName}</div>
                                </div>

                            </div>

                            <div class="form-group row">

                                <label class="col-lg-4 col-form-label form-control-label" for="viewDirectorName">Director
                                    name:</label>

                                <div class="col-lg-8">
                                    <div type="text" name="viewDirectorName" class="form-control-plaintext"
                                         id="viewDirectorName">${company.directorName}
                                    </div>
                                </div>

                            </div>

                            <div class="form-group row">

                                <label class="col-lg-4 col-form-label form-control-label" for="editPhoneNumber">Phone
                                    number:</label>

                                <div class="col-lg-8">
                                    <div type="text" name="viewPhoneNumber" class="form-control-plaintext"
                                         id="viewPhoneNumber"> ${company.phoneNumber}</div>
                                </div>

                            </div>
                        <#else>
                            <h2>Please, add you company</h2>
                        </#if>
                    </div>

                    <hr>

                    <div class="row">

                        <#if company??>
                            <div class="col-lg-6">

                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#EditCompanyModal">
                                    Edit company
                                </button>

                            </div>
                        <#else>
                            <div class="col-lg-6">

                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#AddCompanyModal">
                                    Add company
                                </button>

                            </div>
                        </#if>
                    </div>
                    <#--<button type="button" class="btn btn-primary" data-toggle="modal"-->
                    <#--data-target="#AddCompanyModal">-->
                    <#--Add company-->
                    <#--</button>-->

                    <#--</div>-->
                    <#--</#if>-->

                    <#---->
                    <#--<div class="col-lg-6">-->

                    <#--<button type="button" class="btn btn-primary" data-toggle="modal"-->
                    <#--data-target="#AddCompanyModal">-->
                    <#--Add company-->
                    <#--</button>-->

                    <#--</div>-->

                    <#--<div class="col-lg-6">-->

                    <#--<button type="button" class="btn btn-primary" data-toggle="modal"-->
                    <#--data-target="#EditCompanyModal">-->
                    <#--Edit company-->
                    <#--</button>-->

                    <#--</div>-->

                </div>

                <div class="tab-pane" id="application">

                    <div class="col-lg-12" style="padding-top: 5%">

                        <#if company??>

                            <#if company.companyRole == "Customer" >
                                <h2>Table with all added application</h2>

                                <strong><p>${messageCustomer}</p></strong>

                                <div class="table-responsive" id="allAddedApplicationTable">
                                    <table class="table table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Loading address</th>
                                            <th scope="col">Unloading address</th>
                                            <th scope="col">Loading type</th>
                                            <th scope="col">Weight(KG)</th>
                                            <th scope="col">Payment(RUB)</th>
                                            <#--<th scope="col"></th>-->
                                            <th scope="col"></th>
                                        </tr>

                                        </thead>

                                        <tbody>

                                        <#list addedApplications as addedApplication>

                                            <tr>

                                                <th scope="row">
                                                    <#--<%-->
                                                    <#--rowCounterApp++;-->
                                                    <#--out.print(rowCounterApp);-->
                                                    <#--%>-->
                                                </th>

                                                <td>${addedApplication.date}</td>
                                                <td>${addedApplication.loadingAddress}</td>
                                                <td>${addedApplication.unloadingAddress}</td>
                                                <td>${addedApplication.loadingType}</td>
                                                <td>${addedApplication.weight}</td>
                                                <td>${addedApplication.payment}</td>

                                                <#--<%-- редактировать заявку --%>-->
                                                <#--<td>-->
                                                <#--<button type="button" class="btn btn-outline-success"-->
                                                <#--data-target="#EditApplicationModal"-->
                                                <#--data-toggle="modal"-->
                                                <#--onclick="setAppId(${addedApplication.id})">-->
                                                <#--Edit-->
                                                <#--</button>-->
                                                <#--</td>-->

                                                <#--<%-- удалить заявку --%>-->
                                                <td>
                                                    <button type="button" class="btn btn-outline-danger"
                                                            onclick="deleteApplication(${addedApplication.id})">
                                                        Delete
                                                    </button>
                                                </td>

                                            </tr>

                                        </#list>

                                        </tbody>
                                    </table>
                                </div>
                                <br>

                                <hr class="featurette-divider">

                                <div class="row">

                                    <div class="col-lg-6">

                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#AddApplicationModal">
                                            Add application
                                        </button>

                                    </div>

                                </div>

                                <hr class="featurette-divider">

                                <h2>The table with all applications that have accepted</h2>

                                <div class="table-responsive" id="allAddedAndApplyedApplicationTable">
                                    <table class="table table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Loading address</th>
                                            <th scope="col">Unloading address</th>
                                            <th scope="col">Loading type</th>
                                            <th scope="col">Weight(KG)</th>
                                            <th scope="col">Payment(RUB)</th>
                                            <th scope="col">Company name</th>
                                            <th scope="col">Director name</th>
                                            <th scope="col">Phone number</th>
                                            <th scope="col">Email</th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                        </tr>

                                        </thead>

                                        <tbody>

                                        <#list applyApplications as applyApplication>

                                            <tr>

                                                <th scope="row">
                                                    <#--<%-->
                                                    <#--rowCounterApp++;-->
                                                    <#--out.print(rowCounterApp);-->
                                                    <#--%>-->
                                                </th>
                                                <td>${applyApplication.date}</td>
                                                <td>${applyApplication.loadingAddress}</td>
                                                <td>${applyApplication.unloadingAddress}</td>
                                                <td>${applyApplication.loadingType}</td>
                                                <td>${applyApplication.weight}</td>
                                                <td>${applyApplication.payment}</td>
                                                <td>${applyApplication.companyName}</td>
                                                <td>${applyApplication.firstName} ${applyApplication.lastName}</td>
                                                <td>${applyApplication.phoneNumber}</td>
                                                <td>${applyApplication.email}</td>

                                                <td>
                                                    <button type="button" class="btn btn-outline-info" data-toggle="modal"
                                                            data-target="#SendEmailToCarrierModal">Send email
                                                    </button>
                                                </td>

                                                <#--<%-- удалить заявку --%>-->
                                                <td>
                                                    <button type="button" class="btn btn-outline-danger"
                                                            onclick="deleteApplication(${applyApplication.id})">
                                                        Delete
                                                    </button>
                                                </td>


                                            </tr>

                                        </#list>

                                        </tbody>
                                    </table>
                                </div>

                            </#if>

                            <#if company.companyRole == "Carrier" >

                                <h2>Table with all apply application</h2>

                                <div class="table-responsive" id="allApplyedApplicationTable">
                                    <table class="table table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Loading address</th>
                                            <th scope="col">Unloading address</th>
                                            <th scope="col">Loading type</th>
                                            <th scope="col">Weight(KG)</th>
                                            <th scope="col">Payment(RUB)</th>
                                            <th scope="col">Company name</th>
                                            <th scope="col">Director name</th>
                                            <th scope="col">Phone number</th>
                                            <th scope="col">Email</th>
                                            <th scope="col"></th>
                                        </tr>

                                        </thead>

                                        <tbody>
                                        <#if applyApplications??>

                                            <#list applyApplications as applyApplication>

                                                <tr>

                                                    <th scope="row">
                                                        <#--<%-->
                                                        <#--rowCounterApp++;-->
                                                        <#--out.print(rowCounterApp);-->
                                                        <#--%>-->
                                                    </th>
                                                    <td>${applyApplication.date}</td>
                                                    <td>${applyApplication.loadingAddress}</td>
                                                    <td>${applyApplication.unloadingAddress}</td>
                                                    <td>${applyApplication.loadingType}</td>
                                                    <td>${applyApplication.weight}</td>
                                                    <td>${applyApplication.payment}</td>
                                                    <td>${applyApplication.companyName}</td>
                                                    <td>${applyApplication.firstName} ${applyApplication.lastName}</td>
                                                    <td>${applyApplication.phoneNumber}</td>
                                                    <td>${applyApplication.email}</td>
                                                    <td>
                                                        <button type="button" class="btn btn-outline-info" data-toggle="modal"
                                                                data-target="#SendEmailToCustomerModal">Send email
                                                        </button>
                                                    </td>
                                                </tr>

                                            </#list>
                                        <#else>
                                            <th scope="row">
                                                <#--<%-->
                                                <#--rowCounterApp++;-->
                                                <#--out.print(rowCounterApp);-->
                                                <#--%>-->
                                            </th>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </#if>

                                        </tbody>
                                    </table>
                                </div>

                                <hr class="featurette-divider">

                                <div class="row">

                                    <div class="col-lg-6">

                                        <a href="/home">
                                            <button type="button" class="btn btn-primary"
                                                    href="/home">
                                                View free applications
                                            </button>
                                        </a>

                                    </div>

                                </div>

                            </#if>

                        <#else>
                            <h3>At the begining, add you first company!</h3>
                        </#if>
                    </div>

                </div>

            </div>

        </div>

        <div class="col-lg-4 pull-lg-8 text-xs-center" style="padding: 5%">

            <img src="${profile.imgPath}" class="rounded-circle m-x-auto img-fluid " alt="avatar">
            <h6 class="m-t-2">Upload a different photo</h6>

            <label class="custom-file">

                <form action="/uploadAvatar" onclick="" method="post" enctype="multipart/form-data">
                    <input name="file" type="file"><br>
                    <input type="submit" class="btn btn-primary" value="Upload" "><br>
                </form>

                <#--onclick="sendProfileImg()-->

                <#--<%---->
                <#--<div class="mr-2" style="float: right;">--%>-->
                <#--<%--&lt;%&ndash;<input type="text" name="id" value="${product.id}" style="display: none">&ndash;%&gt;--%>-->
                <#--<%---->
                <#--<button type="button" class="btn btn-primary" -->
                <#--style=" border-color: darkslategrey; background-color: lightgreen; color: white"-->
                <#--onclick="sendProfileImg(${profile.id})"-->
                <#--name="add">-->
                <#--Upload-->
                <#--</button>-->
                <#--</div>-->
                <#----%>-->

            </label>

        </div>

    </div>

</div>

<div class="modal fade bd-example-modal-lg" id="EditUserModal" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalUserEditLabel"
     aria-hidden="true">

    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="ModalEditUserTitle">Edit Profile</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>


            <div class="modal-body">

                <div class="form-group row">
                    <label class="col-lg-2 col-form-label form-control-label">Email</label>
                    <div class="col-lg-10">
                        <input class="form-control" name="editUserEmail" id="editUserEmail" type="email"
                               value="${profile.email}">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-lg-2 col-form-label form-control-label">Phone number</label>
                    <div class="col-lg-10">
                        <input class="form-control" name="editUserPhoneNumber" id="editUserPhoneNumber" type="text"
                               value="${profile.phoneNumber}">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-lg-2 col-form-label form-control-label">Username</label>
                    <div class="col-lg-10">
                        <input class="form-control" name="editUserLogin" id="editUserLogin" type="text"
                               value="${profile.login}">
                    </div>
                </div>

                <div class="form-group row">

                    <label class="col-lg-2 col-form-label form-control-label">First name</label>
                    <div class="col-lg-4">
                        <input class="form-control" name="editUserFirstName" id="editUserFirstName" type="text"
                               value="${profile.firstName}">
                    </div>

                    <label class="col-lg-2 col-form-label form-control-label">Last name</label>
                    <div class="col-lg-4">
                        <input class="form-control" name="editUserLastName" id="editUserLastName" type="text"
                               value="${profile.lastName}">
                    </div>

                </div>


                <div class="form-group row">
                    <label class="col-lg-2 col-form-label form-control-label">Address</label>
                    <div class="col-lg-10">
                        <input class="form-control" id="editUserAddress" name="editUserAddress" type="text"
                               value="${profile.address}"
                               placeholder="Address">
                    </div>
                </div>

                <hr>

                <div class="form-group row">
                    <label class="col-lg-2 col-form-label form-control-label"></label>
                    <div class="col-lg-10">
                        <input type="reset" data-dismiss="modal" class="btn btn-secondary" value="Cancel">
                        <input type="button"
                               onclick="editUser($('#editUserEmail').val(),$('#editUserPhoneNumber').val(),$('#editUserLogin').val(),$('#editUserFirstName').val(),$('#editUserLastName').val(),$('#editUserAddress').val())"
                               class="btn btn-primary" value="Save Changes">
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="modal fade bd-example-modal-lg" id="EditUserPasswordModal" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalCompanyEditLabel"
     aria-hidden="true">

    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="ModalEditPasswordTitle">Edit user password</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">

                <div class="form-group row">
                    <label class="col-lg-2 col-form-label form-control-label">Password</label>
                    <div class="col-lg-10">
                        <label for="editPassword"></label>
                        <input class="form-control" name="editPassword" id="editPassword" type="password"
                               value="">
                    </div>
                </div>

                <#--<%---->
                <#--<div class="form-group row">--%>-->
                <#--<%--<label class="col-lg-2 col-form-label form-control-label">Confirm password</label>--%>-->
                <#--<%---->
                <#--<div class="col-lg-10">--%>-->
                <#--<%--<input class="form-control" type="password" value="">--%>-->
                <#--<%---->
                <#--</div>-->
                <#----%>-->
                <#--<%---->
                <#--</div>-->
                <#----%>-->

                <hr>

                <div class="form-group row">
                    <label class="col-lg-2 col-form-label form-control-label"></label>

                    <div class="col-lg-10">
                        <input type="reset" data-dismiss="modal" class="btn btn-secondary" value="Cancel">
                        <input type="button" onclick="editPassword($('#editPassword').val())"
                               class="btn btn-primary"
                               value="Edit password"/>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade bd-example-modal-lg" id="AddCompanyModal" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalCompanyAddLabel"
     aria-hidden="true">

    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="ModalAddCompanyTitle">Add Company</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <#--<form role="form" method="post" action="/addcompany">-->

            <div class="modal-body">

                <div class="form-group row">
                    <label class="col-lg-4 col-form-label form-control-label" for="companyName">Company name</label>
                    <div class="col-lg-8">
                        <input type="text" name="companyName" class="form-control" id="addCompanyName"
                               placeholder="OOO 'Example Company'">
                    </div>

                </div>


                <div class="form-group row">

                    <label class="col-lg-4 col-form-label form-control-label" for="directorName">Director
                        name</label>

                    <div class="col-lg-8">
                        <input type="text" name="directorName" class="form-control" id="addDirectorName"
                               placeholder="Vasya Pupkin">
                    </div>

                </div>


                <div class="form-group row">
                    <label class="col-lg-4 col-form-label form-control-label" for="phoneNumber">Phone number</label>
                    <div class="col-lg-8">
                        <input type="text" name="phoneNumber" class="form-control" id="addCompanyPhoneNumber"
                               placeholder="+7(666)-777-77-77">
                    </div>
                </div>


                <div class="form-group row">

                    <label class="col-lg-6 col-form-label form-control-label"
                           for="companyRole">Select you company role</label>
                    <div class="col-lg-6">
                        <select name="companyRole" class="form-control"
                                id="addCompanyRole">
                            <option>Carrier</option>
                            <option>Customer</option>
                        </select>

                    </div>
                </div>

                <hr>

                <div class="form-group row">

                    <div class="col-lg-6">
                        <input type="reset" data-dismiss="modal" class="btn btn-secondary" value="Cancel">
                    </div>

                    <div class="col-lg-6">
                        <input type="button"
                               onclick="addCompany($('#addCompanyName').val(),$('#addDirectorName').val(),$('#addCompanyPhoneNumber').val(),$('#addCompanyRole').val())"
                               class="btn btn-primary"
                               value="Add company"/>
                    </div>

                </div>

            </div>

            <#--</form>-->
        </div>
    </div>
</div>

<div class="modal fade bd-example-modal-lg" id="EditCompanyModal" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalCompanyEditLabel"
     aria-hidden="true">

    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="ModalEditCompanyTitle">Edit Company</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">

                <#if company??>
                    <div class="form-group row">
                        <label class="col-lg-4 col-form-label form-control-label" for="editCompanyName">Company
                            name</label>

                        <div class="col-lg-8">
                            <input type="text" id="editCompanyName" name="editCompanyName" class="form-control"
                                   id="editCompanyName"
                                   placeholder="OOO 'Example Edit Company'" value="${company.companyName}">
                        </div>

                    </div>


                    <div class="form-group row">

                        <label class="col-lg-4 col-form-label form-control-label" for="editDirectorName">Director
                            name</label>

                        <div class="col-lg-8">
                            <input type="text" id="editDirectorName" name="editDirectorName" class="form-control"
                                   id="editDirectorName"
                                   placeholder="Azinos Domingos" value="${company.directorName}">
                        </div>

                    </div>

                    <div class="form-group row">

                        <label class="col-lg-4 col-form-label form-control-label" for="editPhoneNumber">Phone
                            number</label>

                        <div class="col-lg-8">
                            <input type="text" id="editPhoneNumber" name="editPhoneNumber" class="form-control"
                                   id="editPhoneNumber"
                                   placeholder="+7(666)-777-77-77" value="${company.phoneNumber}">
                        </div>

                    </div>
                <#else>
                    <p>At the begininig add you company</p>
                </#if>

                <hr>

                <div class="form-group row">
                    <label class="col-lg-2 col-form-label form-control-label"></label>

                    <div class="col-lg-10">
                        <input type="reset" data-dismiss="modal" class="btn btn-secondary" value="Cancel">
                        <input type="button" class="btn btn-primary"
                               onclick="editCompany($('#editCompanyName').val(),$('#editDirectorName').val(),$('#editPhoneNumber').val())"
                               value="Edit company"/>
                    </div>

                </div>

            </div>

        </div>
    </div>
</div>

<div class="modal fade bd-example-modal-lg" id="AddApplicationModal" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalApplicationAddLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="ModalAddApplicationTitle">Add aplication</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">

                <div class="form-group row">

                    <label class="col-lg-4 col-form-label form-control-label" for="addApplicationLoadingAddress">Loading
                        address</label>

                    <div class="col-lg-8">
                        <input type="text" name="loadingAddress" class="form-control"
                               id="addApplicationLoadingAddress"
                               placeholder="From">
                    </div>

                </div>

                <div class="form-group row">
                    <label class="col-lg-4 col-form-label form-control-label" for="addApplicationUnloadingAddress">Unloading
                        address</label>

                    <div class="col-lg-8">
                        <input type="text" name="unloadingAddress" class="form-control"
                               id="addApplicationUnloadingAddress"
                               placeholder="To">
                    </div>

                </div>

                <div class="form-group row">
                    <label class="col-lg-4 col-form-label form-control-label"
                           for="addApplicationFormControlSelectLoadingType">Select
                        loading type</label>

                    <div class="col-lg-6">
                        <select name="loadingType" class="form-control"
                                id="addApplicationFormControlSelectLoadingType">
                            <#list loadingTypes as loadingType>
                                <option>${loadingType}</option>
                            </#list>
                        </select>
                    </div>
                </div>

                <div class="form-group row">

                    <label class="col-lg-3 col-form-label form-control-label"
                           for="addApplicationWeight">Weight(kg)</label>
                    <div class="col-lg-3">
                        <input type="number" name="weight" class="form-control" id="addApplicationWeight"
                               placeholder="0">
                    </div>

                    <label class="col-lg-3 col-form-label form-control-label"
                           for="addApplicationPayment">Payment(rub)</label>

                    <div class="col-lg-3">
                        <input type="number" name="payment" class="form-control" id="addApplicationPayment"
                               placeholder="0">

                    </div>

                </div>

                <hr>

                <div class="form-group row">

                    <div class="col-lg-6">

                        <input type="reset" data-dismiss="modal" class="btn btn-secondary" value="Cancel">

                    </div>

                    <div class="col-lg-6">
                        <input type="button" class="btn btn-primary" data-dismiss="modal"
                               onclick="addApplication($('#addApplicationLoadingAddress').val(),$('#addApplicationUnloadingAddress').val(),$('#addApplicationFormControlSelectLoadingType').val(),
                               $('#addApplicationWeight').val(), $('#addApplicationPayment').val())"
                               value="Add application"/>
                    </div>

                </div>
            </div>

        </div>
    </div>
</div>

<div class="modal fade bd-example-modal-lg"
     id="EditApplicationModal" tabindex="-1"
     role="dialog"
     aria-labelledby="myLargeModalApplicationEditLabel"
     aria-hidden="true">

    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title"
                    id="ModalEditApplicationTitle">
                    Edit aplication</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">

                <div class="form-group row">

                    <label class="col-lg-4 col-form-label form-control-label"
                           for="editApplicationLoadingAddress">Loading
                        address</label>

                    <div class="col-lg-8">
                        <input type="text" name="loadingAddress"
                               class="form-control"
                               id="editApplicationLoadingAddress"
                               placeholder="From">
                    </div>

                </div>

                <div class="form-group row">
                    <label class="col-lg-4 col-form-label form-control-label"
                           for="editApplicationUnloadingAddress">Unloading
                        address</label>

                    <div class="col-lg-8">
                        <input type="text" name="unloadingAddress"
                               class="form-control"
                               id="editApplicationUnloadingAddress"
                               placeholder="To">
                    </div>

                </div>

                <div class="form-group row">
                    <label class="col-lg-4 col-form-label form-control-label"
                           for="editApplicationFormControlSelectLoadingType">Select
                        loading type</label>

                    <div class="col-lg-6">
                        <select name="loadingType" class="form-control"
                                id="editApplicationFormControlSelectLoadingType">
                            <#list loadingTypes as loadingType>
                                <option>${loadingType}</option>
                            </#list>
                        </select>
                    </div>
                </div>

                <div class="form-group row">

                    <label class="col-lg-3 col-form-label form-control-label"
                           for="editApplicationWeight">Weight</label>
                    <div class="col-lg-3">
                        <input type="number" name="weight"
                               class="form-control"
                               id="editApplicationWeight"
                               placeholder="0">
                    </div>

                    <label class="col-lg-3 col-form-label form-control-label"
                           for="editApplicationPayment">Payment</label>


                    <div class="col-lg-3">
                        <input type="number" name="payment"
                               class="form-control"
                               id="editApplicationPayment"
                               placeholder="0">

                    </div>

                </div>

                <hr>

                <div class="form-group row">

                    <div class="col-lg-6">

                        <input type="reset" data-dismiss="modal"
                               class="btn btn-secondary" value="Cancel">


                    </div>

                    <div class="col-lg-6">
                        <input type="button" class="btn btn-primary"
                               onclick="editApplication(getAppId(),$('#editApplicationLoadingAddress').val(),$('#editApplicationUnloadingAddress').val(),$('#editApplicationFormControlSelectLoadingType').val(),
                                       $('#editApplicationWeight').val(), $('#editApplicationPayment').val())"
                               value="Edit application"/>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

<div class="modal fade bd-example-modal-lg" id="SendEmailToCustomerModal" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalSendEmailToCustomerLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">

        <div class="modal-content">
            <#--style="background-color: #2EA7EB"-->
            <div class="modal-header bg-dark">
                <h5 class="modal-title" id="SendEmailToCustomerTitle" style="color: whitesmoke">Send e-mail message, to Customer</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form role="form" method="POST" action="/sendEmailToCustomer">

                <div class="modal-body">

                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label" for="to">To:</label>
                        <div class="col-lg-10">
                            <input id="to" name="to" type="text"/><br/>
                        </div>
                    </div>
                    <#--<div class="form-group row">-->
                    <#--<label class="col-lg-2 col-form-label form-control-label" for="subject">Subject:</label>-->
                    <#--<div class="col-lg-10">-->
                    <#--<input id="subject" name="subject" type="text"/><br/>-->
                    <#--</div>-->
                    <#--</div>-->
                    <label class="col-lg-2 col-form-label form-control-label" for="mailbody">Email body:</label>
                    <div class="col-lg-10">
                        <textarea id="mailbody" name="body" cols="60" rows="15"></textarea><br/>
                    </div>

                    <hr>

                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label"></label>
                        <div class="col-lg-10">
                            <input type="reset" class="btn btn-secondary" value="Cancel" data-dismiss="modal"
                                   aria-label="Close">
                            <input type="submit" class="btn btn-primary" value="Send email">
                        </div>
                    </div>

                </div>
            </form>

        </div>
    </div>
</div>

<div class="modal fade bd-example-modal-lg" id="SendEmailToCarrierModal" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalSendEmailToCarrierLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">

        <div class="modal-content">
            <#--style="background-color: #2EA7EB"-->
            <div class="modal-header bg-dark">
                <h5 class="modal-title" id="ModalSendEmailToCarrierTitle" style="color: whitesmoke">Send e-mail
                    message,to carrier</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form role="form" method="POST" action="/sendEmailToCarrier">

                <div class="modal-body">

                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label" for="to">To:</label>
                        <div class="col-lg-10">
                            <input id="to" name="to" type="text"/><br/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label" for="subject">Subject:</label>
                        <div class="col-lg-10">
                            <input id="subject" name="subject" type="text"/><br/>
                        </div>
                    </div>

                    <label class="col-lg-2 col-form-label form-control-label" for="mailbody">Email body:</label>
                    <div class="col-lg-10">
                        <textarea id="mailbody" name="body" cols="60" rows="15"></textarea><br/>
                    </div>

                    <hr>

                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label"></label>
                        <div class="col-lg-10">
                            <input type="reset" class="btn btn-secondary" value="Cancel" data-dismiss="modal"
                                   aria-label="Close">
                            <input type="submit" class="btn btn-primary" value="Send email">
                        </div>
                    </div>

                </div>
            </form>

        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript ================================================== -->

<script>window.jQuery || document.write('<script src="../js/jquery-1.10.2.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<#--<script src="../js/profile.js"></script>-->

<script type="text/javascript">

    var applicationId = 0;

    function setAppId(id) {
        applicationId = id;
    }

    function getAppId() {
        return applicationId;
    }

    //
    // let loadingAddress = "";
    //
    // function setLoadingAddress(address) {
    //     loadingAddress = address;
    // }
    //
    // function getLoadingAddress() {
    //     return loadingAddress;
    // }
    //
    // let unloadingAddress = "";
    //
    // function setUnloadingAddress(addres) {
    //     unloadingAddress = address;
    // }
    //
    // function getUnloadingAddress() {
    //     return unloadingAddress;
    // }
    //
    // let loadingType = "";
    //
    // function setLoadingType(LoadingType) {
    //     loadingType = LoadingType;
    // }
    //
    // function getLoadingType() {
    //     return loadingType;
    // }
    //
    // var appWeight = 0;
    //
    // function setAppWeight(weight) {
    //     appWeight = weight;
    // }
    //
    // function getAppWeight() {
    //     return appWeight;
    // }
    //
    // var appPayment = 0;
    //
    // function setAppPayment(payment) {
    //     appPayment = payment;
    // }
    //
    // function getAppPayment() {
    //     return appPayment;
    // }

    function editUser(email, phoneNumber, login, firstName, lastName, address) {
        $.ajax({
            type: 'post',
            url: '/edituser',
            data: {
                email: email,
                login: login,
                firstName: firstName,
                lastName: lastName,
                address: address,
                phoneNumber: phoneNumber
            }
        }).done(function (data) {
            alert("Профиль успешно отредактирован");
            let contentUserProfileTabDiv = document.getElementById("userProfile");
            contentUserProfileTabDiv.innerHTML = " ";

            let contentUserProfileHTML = "<div class=\"form-group row\">\n" +
                "                                <label class=\"col-lg-2 col-form-label form-control-label\">Email:</label>\n" +
                "                                <div class=\"col-lg-10\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"email\" type=\"email\">" + data.email + "</div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <label class=\"col-lg-2 col-form-label form-control-label\">Phone number:</label>\n" +
                "                                <div class=\"col-lg-10\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"phoneNumber\"\n" +
                "                                         type=\"text\">" + data.phoneNumber + "</div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <label class=\"col-lg-4 col-form-label form-control-label\">Username:</label>\n" +
                "                                <div class=\"col-lg-8\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"login\" type=\"text\">" + data.login + "</div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"form-group row\">\n" +
                "\n" +
                "                                <label class=\"col-lg-2 col-form-label form-control-label\">First name:</label>\n" +
                "\n" +
                "                                <div class=\"col-lg-4\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"firstName\"\n" +
                "                                         type=\"text\">" + data.firstName + "</div>\n" +
                "                                </div>\n" +
                "\n" +
                "                                <label class=\"col-lg-2 col-form-label form-control-label\">Last name:</label>\n" +
                "                                <div class=\"col-lg-4\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"lastName\"\n" +
                "                                         type=\"text\">" + data.lastName + "</div>\n" +
                "                                </div>\n" +
                "\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <label class=\"col-lg-4 col-form-label form-control-label\">Address:</label>\n" +
                "                                <div class=\"col-lg-8\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"address\" type=\"text\"\n" +
                "                                    \">" + data.address + "</div>\n" +
                "                            </div>\n" +
                "                    </div>\n" +
                " <hr>\n" + "\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <div class=\"col-lg-6\">\n" +
                "                                <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"\n" +
                "                                        data-target=\"#EditUserModal\">\n" +
                "                                    Edit user\n" +
                "                                </button>\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"col-lg-6\">\n" +
                "                                <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"\n" +
                "                                        data-target=\"#EditUserPasswordModal\">\n" +
                "                                    Edit password\n" +
                "                                </button>\n" +
                "                            </div>\n" +
                "\n" +
                "                        </div>";
            contentUserProfileTabDiv.innerHTML = contentUserProfileHTML

            // showUser();
        }).fail(function () {
            alert("При изменении профиля что-то пошло не так");
        })
    }

    function editPassword(password) {
        $.ajax({
            type: 'post',
            url: '/editpassword',
            data: {
                password: password
            }
        }).done(function () {
            alert("Пароль успешно изменен")
        }).fail(function () {
            alert("При изменении пароля что-то пошло не так");
        })
    }

    function addCompany(companyName, directorName, phoneNumber, companyRole) {
        $.ajax({
            type: 'post',
            url: '/addcompany',
            data: {
                companyName: companyName,
                directorName: directorName,
                phoneNumber: phoneNumber,
                companyRole: companyRole
            }
        }).done(function (data) {
            // showUser();
            let contentCompanyTabDiv = document.getElementById("companyProfile");
            contentCompanyTabDiv.innerHTML = " ";

            // if (data !== null) {
            //     alert("Пришли данные");
            // }

            let contentCompanyTabHTML = "   <h4 class=\"m-y-2\">Company profile( role - " + data.companyRole + ")</h4>\n" +
                "\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewCompanyName\">Company\n" +
                "                                name:</label>\n" +
                "\n" +
                "                            <div class=\"col-lg-8\">\n" +
                "                                <div type=\"text\" name=\"viewCompanyName\" class=\"form-control-plaintext\"\n" +
                "                                     id=\"viewCompanyName\">" + data.companyName + "</div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "\n" +
                "                        <div class=\"form-group row\">\n" +
                "\n" +
                "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewDirectorName\">Director\n" +
                "                                name:</label>\n" +
                "\n" +
                "                            <div class=\"col-lg-8\">\n" +
                "                                <div type=\"text\" name=\"viewDirectorName\" class=\"form-control-plaintext\"\n" +
                "                                     id=\"viewDirectorName\">" + data.directorName + "\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "                        </div>\n" +
                "\n" +
                "                        <div class=\"form-group row\">\n" +
                "\n" +
                "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"editPhoneNumber\">Phone\n" +
                "                                number:</label>\n" +
                "\n" +
                "                            <div class=\"col-lg-8\">\n" +
                "                                <div type=\"text\" name=\"viewPhoneNumber\" class=\"form-control-plaintext\"\n" +
                "                                     id=\"viewPhoneNumber\">" + data.phoneNumber + "</div>\n" +
                "                            </div>\n" +
                "\n" +
                "                        </div>\n";
            contentCompanyTabDiv.innerHTML = contentCompanyTabHTML;
            alert("Компания" + data.get(companyName) + "отредактирована");
        }).fail(function () {
            alert("При добавлении компании что-то пошло не так");
        });
    }

    function editCompany(companyName, directorName, phoneNumber) {
        $.ajax({
            type: 'post',
            url: '/editcompany',
            data: {
                companyName: companyName,
                directorName: directorName,
                phoneNumber: phoneNumber
            }
        }).done(function (data) {

            let contentCompanyTabDiv = document.getElementById("companyProfile");
            contentCompanyTabDiv.innerHTML = " ";

            // if (data !== null) {
            //     alert("Пришли данные");
            // }

            let contentCompanyTabHTML = "   <h4 class=\"m-y-2\">Company profile( role - " + data.companyRole + ")</h4>\n" +
                "\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewCompanyName\">Company\n" +
                "                                name:</label>\n" +
                "\n" +
                "                            <div class=\"col-lg-8\">\n" +
                "                                <div type=\"text\" name=\"viewCompanyName\" class=\"form-control-plaintext\"\n" +
                "                                     id=\"viewCompanyName\">" + data.companyName + "</div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "\n" +
                "                        <div class=\"form-group row\">\n" +
                "\n" +
                "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewDirectorName\">Director\n" +
                "                                name:</label>\n" +
                "\n" +
                "                            <div class=\"col-lg-8\">\n" +
                "                                <div type=\"text\" name=\"viewDirectorName\" class=\"form-control-plaintext\"\n" +
                "                                     id=\"viewDirectorName\">" + data.directorName + "\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "                        </div>\n" +
                "\n" +
                "                        <div class=\"form-group row\">\n" +
                "\n" +
                "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"editPhoneNumber\">Phone\n" +
                "                                number:</label>\n" +
                "\n" +
                "                            <div class=\"col-lg-8\">\n" +
                "                                <div type=\"text\" name=\"viewPhoneNumber\" class=\"form-control-plaintext\"\n" +
                "                                     id=\"viewPhoneNumber\">" + data.phoneNumber + "</div>\n" +
                "                            </div>\n" +
                "\n" +
                "                        </div>\n";
            contentCompanyTabDiv.innerHTML = contentCompanyTabHTML;
            alert("Компания" + data.get(companyName) + "добавлена");
        }).fail(function () {
            alert("При добавлении компании что-то пошло не так");
        });
    }

    function addApplication(loadingAddress, unloadingAddress, loadingType, weight, payment) {
        $.ajax({
            type: 'post',
            url: '/addapplication',
            data: {
                id: id,
                loadingAddress: loadingAddress,
                unloadingAddress: unloadingAddress,
                loadingType: loadingType,
                weight: weight,
                payment: payment
            }
        }).done(function (data) {

            // showAllAddedApplication();

            let contentApplicationTableDiv = document.getElementById("allAddedApplicationTable");
            contentApplicationTableDiv.innerHTML = " ";

            // if (data !== null) {
            //     alert("Данные пришли")
            // }

            var rowCounterApp = 0;

            let contentAllApplicationTableHTML = "<table\n" +
                "    class=\"table table-striped table-hover\">\n" +
                "\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "             <th scope = \"col\"> # </th>\n" +
                "             <th scope = \"col\"> Date </th>\n" +
                "             <th scope = \"col\"> Loading address </th>\n" +
                "             <th scope = \"col\"> Unloading address </th>\n" +
                "             <th scope = \"col\"> Loading type </th>\n" +
                "             <th scope = \"col\"> Weight(KG) </th>\n" +
                "             <th scope = \"col\"> Payment(RUB) </th>\n" +
                "             <th scope=\"col\"> </th>\n" +
                "             <th scope=\"col\"> </th>\n" +
                "            </tr>\n" +
                "\n" +
                "            </thead>\n" +
                "\n" +
                "            <tbody>\n";

            for (let i = 0; i < data.length; i++) {

                contentAllApplicationTableHTML += "        <tr>\n" +
                    "\n" +
                    "        <th scope = \"row\">\n";
                rowCounterApp++;
                contentAllApplicationTableHTML += rowCounterApp + "</th>\n" +
                    "        <td>" + new Date(data[i].date).toDateString() + " " + new Date(data[i].date).toTimeString() + +"</td>\n" +
                    "        <td>" + data[i].loadingAddress + "</td>\n" +
                    "        <td>" + data[i].unloadingAddress + "</td>\n" +
                    "        <td>" + data[i].loadingType + "</td>\n" +
                    "        <td>" + data[i].weight + "</td>\n" +
                    "        <td>" + data[i].payment + "</td>\n" +
                    "        <td>\n" +
                    "        <button type = \"button\" class=\"btn btn-outline-danger\" onclick=\"deleteApplication(" + data[i].id + ")\"> Delete\n" +
                    "            </button>\n" +
                    "            </td>\n" +
                    "    <td>\n" +
                    "                                                    <button type=\"button\" class=\"btn btn-outline-success\"\n" +
                    "                                                            data-target=\"#EditApplicationModal\"data-toggle=\"modal\"\n" +
                    "                                                        onclick=\"setAppId(" + data[i].id + "}))\">\n" +
                    "                                                        Edit\n" +
                    "                                                    </button>\n" +
                    "                                                </td>" +
                    "\n" +
                    "            </tr>\n" +
                    "\n";
            }

            contentAllApplicationTableHTML += "            </tbody>\n" +
                "            </table>";
            contentApplicationTableDiv.innerHTML = contentAllApplicationTableHTML;
            alert("Заявка добавлена");
        }).fail(function () {
            alert("Что-то пошло не так");
        });
    }

    function editApplication(id, loadingAddress, unloadingAddress, loadingType, weight, payment) {
        $.ajax({
            type: 'post',
            url: '/editapplication',
            data: {
                id: id,
                loadingAddress: loadingAddress,
                unloadingAddress: unloadingAddress,
                loadingType: loadingType,
                weight: weight,
                payment: payment
            }
        }).done(function (data) {

            // showAllAddedApplication();

            let contentApplicationTableDiv = document.getElementById("allAddedApplicationTable");
            contentApplicationTableDiv.innerHTML = " ";

            var rowCounterApp = 0;

            let contentAllApplicationTableHTML = "<table\n" +
                "    class=\"table table-striped table-hover\">\n" +
                "\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "             <th scope = \"col\"> # </th>\n" +
                "             <th scope = \"col\"> Date </th>\n" +
                "             <th scope = \"col\"> Loading address </th>\n" +
                "             <th scope = \"col\"> Unloading address </th>\n" +
                "             <th scope = \"col\"> Loading type </th>\n" +
                "             <th scope = \"col\"> Weight(KG) </th>\n" +
                "             <th scope = \"col\"> Payment(RUB) </th>\n" +
                "             <th scope=\"col\"> </th>\n" +
                "             <th scope=\"col\"> </th>\n" +
                "            </tr>\n" +
                "\n" +
                "            </thead>\n" +
                "\n" +
                "            <tbody>\n";

            for (let i = 0; i < data.length; i++) {

                contentAllApplicationTableHTML += "        <tr>\n" +
                    "\n" +
                    "        <th scope = \"row\">\n";
                rowCounterApp++;
                contentAllApplicationTableHTML += rowCounterApp + "</th>\n" +
                    "        <td>" + new Date(data[i].date).toDateString() + " " + new Date(data[i].date).toTimeString() + +"</td>\n" +
                    "        <td>" + data[i].loadingAddress + "</td>\n" +
                    "        <td>" + data[i].unloadingAddress + "</td>\n" +
                    "        <td>" + data[i].loadingType + "</td>\n" +
                    "        <td>" + data[i].weight + "</td>\n" +
                    "        <td>" + data[i].payment + "</td>\n" +
                    "    <td>\n" +
                    "                                                    <button type=\"button\" class=\"btn btn-outline-success\"\n" +
                    "                                                            data-target=\"#EditApplicationModal\"data-toggle=\"modal\"\n" +
                    "                                                        onclick=\"setAppId(" + data[i].id + "}))\">\n" +
                    "                                                        Edit\n" +
                    "                                                    </button>\n" +
                    "                                                </td>" +
                    "        <td>\n" +
                    "        <button type = \"button\" class=\"btn btn-outline-danger\" onclick=\"deleteApplication(" + data[i].id + ")\"> Delete\n" +
                    "            </button>\n" +
                    "            </td>\n" +
                    // "        <td>\n" +
                    // "                                        <button type=\"button\" class=\"btn btn-outline-success\" onclick=\"\">Edit\n" +
                    // "                                        </button>\n" +
                    // "                                    </td>" +
                    "\n" +
                    "            </tr>\n" +
                    "\n";

            }

            contentAllApplicationTableHTML += "            </tbody>\n" +
                "            </table>";
            contentApplicationTableDiv.innerHTML = contentAllApplicationTableHTML;
            alert("Заявка отредактирована");
        }).fail(function () {
            alert("Что-то пошло не так");
        });
    }

    function deleteApplication(id) {
        $.ajax({
            type: 'post',
            url: '/deleteapplication',
            data: {
                id: id
            }
        }).done(function (data) {

            // showAllAddedApplication();

            let contentApplicationTableDiv = document.getElementById("allAddedApplicationTable");
            contentApplicationTableDiv.innerHTML = " ";

            // if (data !== null) {
            //     alert("Данные пришли")
            // }

            var rowCounterApp = 0;

            let contentAllApplicationTableHTML = "<table\n" +
                "    class=\"table table-striped table-hover\">\n" +
                "\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "             <th scope = \"col\"> # </th>\n" +
                "             <th scope = \"col\"> Date </th>\n" +
                "             <th scope = \"col\"> Loading address </th>\n" +
                "             <th scope = \"col\"> Unloading address </th>\n" +
                "             <th scope = \"col\"> Loading type </th>\n" +
                "             <th scope = \"col\"> Weight(KG) </th>\n" +
                "             <th scope = \"col\"> Payment(RUB) </th>\n" +
                "             <th scope=\"col\"> </th>\n" +
                "             <th scope=\"col\"> </th>\n" +
                "            </tr>\n" +
                "\n" +
                "            </thead>\n" +
                "\n" +
                "            <tbody>\n";

            for (let i = 0; i < data.length; i++) {

                contentAllApplicationTableHTML += "        <tr>\n" +
                    "\n" +
                    "        <th scope = \"row\">\n";
                rowCounterApp++;
                contentAllApplicationTableHTML += rowCounterApp + "</th>\n" +
                    "        <td>" + new Date(data[i].date).toDateString() + " " + new Date(data[i].date).toTimeString() + +"</td>\n" +
                    "        <td>" + data[i].loadingAddress + "</td>\n" +
                    "        <td>" + data[i].unloadingAddress + "</td>\n" +
                    "        <td>" + data[i].loadingType + "</td>\n" +
                    "        <td>" + data[i].weight + "</td>\n" +
                    "        <td>" + data[i].payment + "</td>\n" +
                    "        <td>\n" +
                    "        <button type = \"button\" class=\"btn btn-outline-danger\" onclick=\"deleteApplication(" + data[i].id + ")\"> Delete\n" +
                    "            </button>\n" +
                    "            </td>\n" +
                    "    <td>\n" +
                    "                                                    <button type=\"button\" class=\"btn btn-outline-success\"\n" +
                    "                                                            data-target=\"#EditApplicationModal\"data-toggle=\"modal\"\n" +
                    "                                                        onclick=\"setAppId(" + data[i].id + "}))\">\n" +
                    "                                                        Edit\n" +
                    "                                                    </button>\n" +
                    "                                                </td>" +
                    "\n" +
                    "            </tr>\n" +
                    "\n";
            }

            contentAllApplicationTableHTML += "            </tbody>\n" +
                "            </table>";
            contentApplicationTableDiv.innerHTML = contentAllApplicationTableHTML;
            // alert("Заявка удалена");
        }).fail(function () {
            alert("Что-то пошло не так");
        });
    }


    function showAllProfileData() {
        // showUser();
        // showCompany();
        // showAllAddedApplication();
    }

    function showUser() {
        $.ajax({
            type: 'get',
            url: '/profile'
        }).done(function (data) {
            let contentUserProfileTabDiv = document.getElementById("userProfile");
            contentUserProfileTabDiv.innerHTML = " ";

            let contentUserProfileHTML = "<div class=\"form-group row\">\n" +
                "                                <label class=\"col-lg-2 col-form-label form-control-label\">Email:</label>\n" +
                "                                <div class=\"col-lg-10\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"email\" type=\"email\">" + data.email + "</div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <label class=\"col-lg-2 col-form-label form-control-label\">Phone number:</label>\n" +
                "                                <div class=\"col-lg-10\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"phoneNumber\"\n" +
                "                                         type=\"text\">" + data.phoneNumber + "</div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <label class=\"col-lg-4 col-form-label form-control-label\">Username:</label>\n" +
                "                                <div class=\"col-lg-8\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"login\" type=\"text\">" + data.login + "</div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"form-group row\">\n" +
                "\n" +
                "                                <label class=\"col-lg-2 col-form-label form-control-label\">First name:</label>\n" +
                "\n" +
                "                                <div class=\"col-lg-4\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"firstName\"\n" +
                "                                         type=\"text\">" + data.firstName + "</div>\n" +
                "                                </div>\n" +
                "\n" +
                "                                <label class=\"col-lg-2 col-form-label form-control-label\">Last name:</label>\n" +
                "                                <div class=\"col-lg-4\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"lastName\"\n" +
                "                                         type=\"text\">" + data.lastName + "</div>\n" +
                "                                </div>\n" +
                "\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <label class=\"col-lg-4 col-form-label form-control-label\">Address:</label>\n" +
                "                                <div class=\"col-lg-8\">\n" +
                "                                    <div class=\"form-control-plaintext\" name=\"address\" type=\"text\"\n" +
                "                                    \">" + data.address + "</div>\n" +
                "                            </div>\n" +
                "                    </div>\n" +
                " <hr>\n" + "\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <div class=\"col-lg-6\">\n" +
                "                                <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"\n" +
                "                                        data-target=\"#EditUserModal\">\n" +
                "                                    Edit user\n" +
                "                                </button>\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"col-lg-6\">\n" +
                "                                <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"\n" +
                "                                        data-target=\"#EditUserPasswordModal\">\n" +
                "                                    Edit password\n" +
                "                                </button>\n" +
                "                            </div>\n" +
                "\n" +
                "                        </div>";

            contentUserProfileTabDiv.innerHTML = contentUserProfileHTML
        })
    }

    function showCompany() {
        $.ajax({
            type: 'get',
            url: '/profile'
        }).done(function (data) {

            let contentCompanyTabDiv = document.getElementById("companyProfile");
            contentCompanyTabDiv.innerHTML = " ";

            // if (data !== null) {
            //     alert("Пришли данные");
            // }

            let contentCompanyTabHTML = "   <h4 class=\"m-y-2\">Company profile( role - " + data.company_role + ")</h4>\n" +
                "\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewCompanyName\">Company\n" +
                "                                name:</label>\n" +
                "\n" +
                "                            <div class=\"col-lg-8\">\n" +
                "                                <div type=\"text\" name=\"viewCompanyName\" class=\"form-control-plaintext\"\n" +
                "                                     id=\"viewCompanyName\">" + data.companyName + "</div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "\n" +
                "                        <div class=\"form-group row\">\n" +
                "\n" +
                "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewDirectorName\">Director\n" +
                "                                name:</label>\n" +
                "\n" +
                "                            <div class=\"col-lg-8\">\n" +
                "                                <div type=\"text\" name=\"viewDirectorName\" class=\"form-control-plaintext\"\n" +
                "                                     id=\"viewDirectorName\">" + data.directorName + "\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "                        </div>\n" +
                "\n" +
                "                        <div class=\"form-group row\">\n" +
                "\n" +
                "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"editPhoneNumber\">Phone\n" +
                "                                number:</label>\n" +
                "\n" +
                "                            <div class=\"col-lg-8\">\n" +
                "                                <div type=\"text\" name=\"viewPhoneNumber\" class=\"form-control-plaintext\"\n" +
                "                                     id=\"viewPhoneNumber\">" + data.phoneNumber + "</div>\n" +
                "                            </div>\n" +
                "\n" +
                "                        </div>\n";
            contentCompanyTabDiv.innerHTML = contentCompanyTabHTML;
        }).fail(function () {
            alert("Все плохо!");
        })
    }

    function showAllAddedApplication() {
        $.ajax({
            type: 'get',
            url: '/showAllAddedApplications'
        }).done(function (data) {

                let contentApplicationTableDiv = document.getElementById("allAddedApplicationTable");
                contentApplicationTableDiv.innerHTML = " ";

                // if (data !== null) {
                //     alert("Данные пришли")
                // }

                var rowCounterApp = 0;
                let contentAllApplicationTableHTML = "<table\n" +
                    "    class=\"table table-striped table-hover\">\n" +
                    "\n" +
                    "            <thead>\n" +
                    "            <tr>\n" +
                    "             <th scope = \"col\"> # </th>\n" +
                    "             <th scope = \"col\"> Date </th>\n" +
                    "             <th scope = \"col\"> Loading address </th>\n" +
                    "             <th scope = \"col\"> Unloading address </th>\n" +
                    "             <th scope = \"col\"> Loading type </th>\n" +
                    "             <th scope = \"col\"> Weight(KG) </th>\n" +
                    "             <th scope = \"col\"> Payment(RUB) </th>\n" +
                    "             <th scope=\"col\"> </th>\n" +
                    "             <th scope=\"col\"> </th>\n" +
                    "            </tr>\n" +
                    "\n" +
                    "            </thead>\n" +
                    "\n" +
                    "            <tbody>\n";

                for (let i = 0; i < data.length; i++) {

                    contentAllApplicationTableHTML += "        <tr>\n" +
                        "\n" +
                        "        <th scope = \"row\">\n" + rowCounterApp++ + "</th>\n" +
                        "\n" +
                        "        <td>" + data.date + "</td>\n" +
                        "        <td>" + data.loadingAddress + "</td>\n" +
                        "        <td>" + data.unloadingAddress + "</td>\n" +
                        "        <td>" + data.loadingType + "</td>\n" +
                        "        <td>" + data.weight + "</td>\n" +
                        "        <td>" + data.payment + "</td>\n" +
                        "        <td>\n" +
                        "        <button type = \"button\" class=\"btn btn-outline-danger\" onclick = \" \" > Delete\n" +
                        "            </button>\n" +
                        "            </td>\n" +
                        "<td>\n" +
                        "                                        <button type=\"button\" class=\"btn btn-outline-success\" onclick=\"\">Edit\n" +
                        "                                        </button>\n" +
                        "                                    </td>" +
                        "\n" +
                        "            </tr>\n" +
                        "\n";
                }
                contentAllApplicationTableHTML += "            </tbody>\n" +
                    "            </table>";
                contentApplicationTableDiv.innerHTML = contentAllApplicationTableHTML;
            }
        )
    }

    function sendProfileImg() {
        $.ajax({
            type: 'post',
            enctype: 'multipart/form-data',
            url: '/uploadAvatar'
        }).done(function () {
            alert("Аватар загружен")
        }).fail(function () {
            alert("Что то пошло не так")
        });
    }

    function showProfileImg() {
        $.ajax({
            type: 'get',
            url: '/showAvatar'
        }).done(function (data) {
            alert("Аватар загружен")
            //    выводим изображение
        }).fail(function () {
            alert("Что то пошло не так")
        });
    }

    function exit() {
        var date = new Date(0);
        document.cookie = "auth=; path=/; expires=" + date.toUTCString();
        document.cookie = "baskId=; path=/; expires=" + date.toUTCString();
        window.location.replace("/home")
    }

</script>
</body>
</html>