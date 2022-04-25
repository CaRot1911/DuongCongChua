// console.log("Hello js");

$(document).ready(function () {

    let menuLinks = document.getElementsByClassName('sidebar-link');

    // console.log(menuLinks);

    menuLinks[1].onclick = function () {
        $('.content').load('home.html');
    }
    menuLinks[2].onclick = function () {
        $('.content').load('account.html', function () {
            loadAccountJS();
        });

    }
    menuLinks[3].onclick = function () {
        $('.content').load('department.html', function () {
            loadDepartmentJS();
        });

    }
})

if (localStorage.getItem('isTheFirstLogin')) {//login already
    localStorage.removeItem('isTheFirstLogin')
    setUpInfoLogedIn()
} else {
    if (localStorage.getItem('rememberme')) {
        setUpInfoLogedIn()
    } else {
        if (localStorage.getItem('USERNAME')) {
            setUpInfoLogedIn()
        } else {
            window.location.replace('signin.html')
            // window.location.replace('index.html')
        }

    }
}

function setUpInfoLogedIn() {
    let userName = localStorage.getItem('USERNAME')
    document.getElementById('nav_username').innerHTML = userName
    document.getElementById('sidebar_username').innerHTML = userName

    console.log(localStorage.getItem("ROLE"))
    console.log($(`#nav_username`).val())
    console.log($(`#sidebar_username`).val())
}


function handleSignOut() {
    localStorage.removeItem('USERNAME')
    localStorage.removeItem('ROLE')
    //localStorage.removeItem('rememberme')
    localStorage.removeItem('isTheFirstLogin')
    localStorage.removeItem('TOKEN')

    console.log('user logout')

    document.getElementById('nav_username').innerHTML = ''
    document.getElementById('sidebar_username').innerHTML = ''

    window.location.replace('signin.html')
}

$(`#signOut`).click(function (e) {
    // alert("click!!!!!")
})

