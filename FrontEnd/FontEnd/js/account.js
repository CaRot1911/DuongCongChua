function loadAccountJS() {

    var accountIndex = null;
    var accountIdDelete = null;
    var accountIdUpdate = null;
    var checkAccountUpdate = false;
    var checkAccountDeleteAll = false;

    function getListDepartments() {
        let url = 'http://localhost:8080/departments/v1'
        $.ajax({
            url: url,
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('TOKEN')
            },
            success: function (response) {
                console.log(response)

                $.each(response, function (index, item) {
                    $('#select_department_account')
                        .append(
                            $('<option></option>')
                                .attr("value", item.id)
                                .text(item.name)
                        )
                    $(`#account_department`)
                        .append(
                            $('<option></option>')
                                .attr("value", item.id)
                                .text(item.name)
                        )
                    $(`#account_department_create`)
                        .append(
                            $('<option></option>')
                                .attr("value", item.id)
                                .text(item.name)
                        )
                })


            },
            error: function (error) {
                console.log(error)
            }

        })
    }

    getListDepartments()
    var page = 0
    var size = 50
    var sort = 'id,asc'

    function getListAccounts(roleInput, departmentIdInput, searchInput, pageInput, sizeInput, sortInput) {
        let url = `http://localhost:8080/accounts/v1/paging`;
        $.ajax({
            url: url,
            type: `GET`,
            contentType: `application/json`,
            dataType: `json`,
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('TOKEN')
            },
            data: {
                role: roleInput,
                departmentId: departmentIdInput,
                search: searchInput,
                page: pageInput,
                size: sizeInput,
                sort: sortInput
            },
            success: function (response) {
                console.log("List Account")
                console.log(response.content);
                createDataTableAccount(response.content);
            },

            error: function (error) {
                console.log(error)
            }
        })
    }


    function createDataTableAccount(listAccounts) {
        let accounts = [];
        // console.log("list account dsad s")

        for (let i = 0; i < listAccounts.length; i++) {
            let temp = [];
            temp[0] = i + 1
            temp[1] = listAccounts[i].id
            temp[2] = listAccounts[i].firstName
            temp[3] = listAccounts[i].lastName
            temp[4] = listAccounts[i].fullName
            temp[5] = listAccounts[i].userName;
            temp[6] = listAccounts[i].role;
            temp[7] = listAccounts[i].departmentId;
            temp[8] = listAccounts[i].departmentName;
            temp[9] = '';
            temp[10] = '';
            accounts.push(temp);
        }
        console.log(accounts);


        dataTable = $(`#datatables_accounts`).DataTable({
            data: accounts,

            columns: [
                { title: "" },
                { title: "" },
                { title: "" },
                { title: "" },
                { title: "Full Name" },
                { title: "Use Name" },
                { title: "Role" },
                { title: "" },
                { title: "Department" },
                { title: "Actions" },
                { title: "Check" }
            ],

            "columnDefs": [
                {
                    "targets": 1,
                    "visible": false
                },
                {
                    "targets": 2,
                    "visible": false
                },
                {
                    "targets": 3,
                    "visible": false
                },
                {
                    "targets": 7,
                    "visible": false
                },
                {
                    "targets": 9,
                    "width": 100,
                    "render": function () {
                        return '<td>' +
                            '<button class = "btn-actions edit" data-toggle="modal" data-target="#defaultModalWarning"><i class="material-icons icon-actions">&#xe3c9</i></button>' +
                            '<button class = "btn-actions delete-account" data-toggle="modal" data-target="#defaultModalDanger"><i class="material-icons icon-actions">&#xe872</i></button>' +
                            '</td>'
                    }
                },
                {
                    orderable: true,
                    className: 'select-checkbox',
                    targets: 10,
                    with: 40
                }

            ],
            'select': {
                'style': 'multi'
            }
        });
    }
    // CAll API
    getListAccounts('', 0, null, page, size, sort);

    // Click in close
    function clickClose() {
        $(`#close_role`).click(function () {
            let check = $(`select_role_account`).val()
            if (check !== 'none') {
                $(`#select_role_account`).val('none')
            }
        })

        $(`#close_department`).click(function () {
            let check = $(`#select_department_account`).val()
            if (check !== 0) {
                $(`#select_department_account`).val(0)
            }
        })

        $(`#close_input`).click(function () {
            let check = $(`#search_input_account`).val()
            if (check !== null) {
                $(`#search_input_account`).val(null)
            }
        })

    }
    // click close
    clickClose()

    // Refect page
    function refectPage() {
        $(`#btn_refech`).click(function () {
            // alert(`on clickS`)

            // check refect
            clickClose();

            // return value filter
            $(`#select_role_account`).val('none')
            $(`#select_department_account`).val(0)
            $(`#search_input_account`).val(null)

            dataTable.destroy()

            getListAccounts('', 0, null, page, size, sort)
        })
    }

    refectPage();

    // Click filter
    $('#icon_search_account').click(function () {
        let role = $('#select_role_account').val()
        let departmentId = $('#select_department_account').val()
        let search = $('#search_input_account').val()

        // console.log(search)
        // console.log(role)
        // console.log(departmentId)

        //Note
        if (departmentId === 0) departmentId = 0
        else {
            departmentId = parseInt(departmentId)
        }
        if (search === '') search = null
        else {
            search = search.trim().replace(' ', '%');
        }
        if (role === 'none') role = null

        dataTable.destroy()

        getListAccounts(role, departmentId, search, page, size, sort)

    })

    // Click form delete , insert and update

    $('#btn_close_form_account').click(function (e) {
        e.preventDefault()
        dataTable.$('tr.selected').removeClass('selected');
    })

    $('#btn_dismiss_form_account').click(function (e) {
        e.preventDefault()
        dataTable.$('tr.selected').removeClass('selected');
    })

    $('#btn_close_delete_account').click(function (e) {
        e.preventDefault()
        dataTable.$('tr.selected').removeClass('selected');
    })

    $('#btn_no_account').click(function (e) {
        e.preventDefault()
        dataTable.$('tr.selected').removeClass('selected');
    })




    // Delete one account
    // Lay account id
    $(`#datatables_accounts`).on(`click`, `td .delete-account`, function (e) {
        e.preventDefault();
        accountIndex = parseInt($(this).closest('tr').find('td').html()) - 1;
        let rowData = dataTable.row(accountIndex).data()

        accountIdDelete = rowData[1];
        console.log(accountIdDelete);

    })

    var ids = [];
    //Clear arrAccountId after delete multiple
    const clearArrAccountId = () => {
        while (ids.length > 0) {
            ids.pop()
        }
    }
    //btn delete mutiple accounts
    $(`#btn_delete_all`).click(function () {
        checkAccountDeleteAll = true
        //Check rows selected
        if (dataTable.rows('.selected').data().length == 0) {
            alert('Mời bạn chọn account muốn xóa')
        } else {
            checkAccountDeleteAll = true
            $('#defaultModalDanger').modal()
        }
    })

    // Delete account
    $(`#btn_confirm_delete_account`).click(function () {
        if (checkAccountDeleteAll == true) {
            //Select multil account for deleting
            let accountsSelected = dataTable.rows('.selected').data();

            if (accountsSelected.length > 0) {
                for (let i = 0; i < accountsSelected.length; i++) {
                    ids.push(accountsSelected[i][1])
                    // console.log(accountsSelected[i])
                }
            }

            $.ajax({
                url: `http://localhost:8080/accounts/v1/deleteAllAccount?ids=` + ids,
                method: 'DELETE',
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('TOKEN')
                },
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(ids),
                success: function (response) {
                    $(`#btn_close_delete_account`).click()//close modal

                    //Refresh table
                    dataTable.destroy()
                    getListAccounts('', 0, null, page, size, sort);

                    //Reset arr accounts after delete
                    clearArrAccountId()
                },
                error: function (request, status, error) {
                    console.log(error)
                    //Reset arr accounts after delete
                    clearArrAccountId()

                    //Deselect all on the data table
                    dataTable.$('tr.selected').removeClass('selected');
                }
            })
        } else {
            $.ajax({
                url: `http://localhost:8080/accounts/v1/` + accountIdDelete,
                method: 'DELETE',
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('TOKEN')
                },
                success: function (response) {
                    $(`#btn_close_delete_account`).click()

                    dataTable.row(currentRowIndex).remove().draw()
                    getListAccounts('', 0, null, page, size, sort)
                },
                error: function (request, status, error) {
                    console.log(error)
                }
            })
        }
    })


    var accountIdEdit = null;
    // Edit account
    $(`#datatables_accounts`).on(`click`, `td .edit`, function (e) {
        e.preventDefault();
        checkAccountUpdate = true;
        accountIdEdit = parseInt($(this).closest('tr').find('td').html()) - 1;
        let rowData = dataTable.row(accountIdEdit).data()

        accountIdEdit = rowData[1];
        // console.log(rowData)
        // console.log(accountIdEdit)

        let userName = rowData[5];
        let firstName = rowData[2];
        let lastName = rowData[3];
        let role = rowData[6];
        let departmentId = rowData[7];

        $('#user_name').val(userName)
        $('#first_name').val(firstName)
        $('#last_name').val(lastName)
        $('#role').val(role)
        $('#account_department').val(departmentId)
    })

    $('#btn_save_account').click(function (e) {
        e.preventDefault()

        let data = {
            role: $('#role').val(),
            departmentId: $('#account_department').val()
        }

        // console.log(data)
        // console.log(accountIdEdit)

        $.ajax({
            url: `http://localhost:8080/accounts/v1/` + accountIdEdit,
            method: `PUT`,
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('TOKEN')
            },
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (response) {
                //Close modal
                $(`#btn_close_form_account`).click()
                //update row data
                let tempUpdate = [];
                tempUpdate[1] = data.departmentId.val()
                tempUpdate[3] = data.role

                dataTable.row(accountIdEdit).data(temp).draw(false)
            },
            error: function (request, status, error) {
                console.log(error)
            }
        })
    })

    // Insert account
    $(`#btn_save_account_create`).click(function (e) {
        e.preventDefault()
        let data = {
            userName: $(`#user_name_create`).val(),
            firstName: $(`#first_name_create`).val(),
            lastName: $(`#last_name_create`).val(),
            passWord: $(`#password_create`).val(),
            passwordEnter: $(`#password_enter_create`).val(),
            role: $(`#role_create`).val(),
            departmentId: $(`#account_department_create`).val()
        }

        // Check null

        if (data.userName == null || data.firstName == null || data.lastName == null || data.passWord == null || data.passwordEnter == null || data.role == `none` || data.departmentId == 0) {
            alert("Có trường để rỗng!!!!");
        } else {
            // Check passwprd trung nhau
            if (data.passWord === data.passwordEnter) {
                let accountInsert = {
                    userName: data.userName,
                    firstName: data.firstName,
                    lastName: data.lastName,
                    passWord: data.passWord,
                    role: data.role,
                    departmentId: data.departmentId
                }
                $.ajax({
                    url: `http://localhost:8080/accounts/v1`,
                    method: `POST`,
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem('TOKEN')
                    },
                    contentType: 'application/json',
                    dataType: 'json',
                    data: JSON.stringify(accountInsert),
                    success: function (response) {
                        //Close modal
                        $('#btn_close_form_account').click()


                        //Reset table
                        dataTable.destroy()
                        getListAccounts('', 0, null, page, size, sort);
                    },
                    error: function (request, status, error) {
                        console.log(error)
                    }
                })
            } else {
                alert(`Passwords do not match`)
            }
        }


    })

}