function loadDepartmentJS() {

    var page = 0
    var size = 1000000
    var sort = 'totalMember,desc'


    console.log("Load list department")
    // erorr min max create date and type

    function getListDepartments(minDateInput, maxDateInput, typeInput, searchInput, pageInput, sizeInput, sortInput) {
        let url = 'http://localhost:8080/departments/v1/paging'
        $.ajax({
            url: url,
            type: 'GET',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('TOKEN')
            },
            contentType: 'application/json',
            dataType: 'json',
            data: {
                minCreateDate: minDateInput,
                maxCreateDate: maxDateInput,
                type: typeInput,
                search: searchInput,
                pageNumber: pageInput,
                sizePage: sizeInput,
                sortDesc: sortInput
            },

            success: function (response) {
                // console.log("List department")
                // console.log(response.content)
                createDataTableDepartment(response.content)
            },
            error: function (error) {
                console.log(error)
            }

        })
    }

    function createDataTableDepartment(listDepartments) {
        let departments = []

        for (let i = 0; i < listDepartments.length; i++) {
            let temp = []
            temp[0] = i + 1
            temp[1] = listDepartments[i].id
            temp[2] = listDepartments[i].name
            temp[3] = listDepartments[i].totalMember
            temp[4] = listDepartments[i].type
            temp[5] = listDepartments[i].createDate
            temp[6] = ''
            temp[7] = ''
            departments.push(temp)
        }
        // console.log("list Department find to table")
        // console.log(departments)

        dataTable = $('#datatables_departments').DataTable({
            data: departments,

            columns: [
                { title: "" },
                { title: "" },
                { title: "Name" },
                { title: "Total Member" },
                { title: "Type" },
                { title: "Create Date" },
                { title: "Actions" },
                { title: "Check" },
            ],

            "columnDefs": [
                {
                    "targets": 1,
                    "visible": false
                },
                {
                    "targets": 6,
                    "width": 100,
                    "render": function () {
                        return '<td>' +
                            '<button class="btn-actions edit"><i class="material-icons icon-actions" data-toggle="modal" data-target="#defaultModalUpdateDepartment">&#xe3c9</i></button>' +
                            '<button class="btn-actions delete"><i class="material-icons icon-actions" data-toggle="modal" data-target="#defaultModaDeleteDepartment">&#xe872</i></button>' +
                            '<td>'
                    }
                },
                {
                    orderable: false,
                    className: 'select-checkbox',
                    targets: 7,
                    with: 40
                }
            ],
            'select': {
                'style': 'multi'
            }
        })
    }
    getListDepartments(null, null, '', null, page, size, sort)


    // onclick close
    function onClickClose() {

        // chua xu lys duoc date
        $(`#close_mindate`).click(function () {
            let check = $(`#minCreateDate`).val()
            if (check !== null) {
                $(`#minCreateDate`).val(null)
            }
        })

        $(`#close_maxdate`).click(function () {
            let check = $(`#maxCreateDate`).val()
            if (check !== null) {
                $(`#maxCreateDate`).val(null)
            }
        })

        $(`#close_type_department`).click(function () {
            let check = $(`#select_type_department`).val()
            if (check !== 'none') {
                $(`#select_type_department`).val('none')
            }
        })

        $(`#close_search_name`).click(function () {
            let check = $(`#search_input_department`).val()
            if (check !== null) {
                $(`#search_input_department`).val(null)
            }
        })
    }
    onClickClose();

    // Refect page
    $(`#btn_refech`).click(function () {
        // alert(`on clickS`)

        // check refect
        onClickClose();

        // return value filter
        $(`#minCreateDate`).val(null)
        $(`#maxCreateDate`).val(null)
        $(`#select_type_department`).val('none')
        $(`#search_input_department`).val(null)

        dataTable.destroy()

        getListDepartments(null, null, '', null, page, size, sort)
    })

    // click filter
    $(`#icon_search_department`).click(function () {
        let search = $(`#search_input_department`).val();


        // Check
        if (search === '') search = null
        else {
            search = search.trim().replace(' ', '%');
        }
        dataTable.destroy()
        getListDepartments(null, null, '', search, page, size, sort)
    })

    $(`#btn_fiter`).click(function (e) {
        e.preventDefault()
        let type = $(`#select_type_department`).val();
        let minCreateDate = $(`#minCreateDate`).val()
        let maxCreateDate = $(`#maxCreateDate`).val()

        if (type === 'none') type = null

        if (minCreateDate == '') minDate = null

        if (maxCreateDate == '') maxDate = null

        console.log(minCreateDate)
        console.log(maxCreateDate)
        console.log(type)

        dataTable.destroy()
        getListDepartments(minCreateDate, maxCreateDate, type, null, page, size, sort)
    })

    $(`#btn_dismiss_form_department_create`).click(function (e) {
        e.preventDefault()
        dataTable.$('tr.selected').removeClass('selected');
    })

    $(`#btn_close_form_department`).click(function (e) {
        e.preventDefault()
        dataTable.$('tr.selected').removeClass('selected');
    })
    $(`#btn_close_delete_department`).click(function (e) {
        e.preventDefault()
        dataTable.$('tr.selected').removeClass('selected');
    })
    // Create department
    $(`#btn_save_department_create`).click(function (e) {

        e.preventDefault()
        let data = {
            name: $("#departmentName").val(),
            type: $("#create_type_department").val()
        }
        // console.log(data)

        // check null
        if (data.name == null || data.type == 'none') {
            alert("Tồn tại trường rỗng!!!")

        } else {
            let departmentInsert = {
                name: data.name,
                type: data.type
            }
            // console.log(departmentInsert)

            $.ajax({
                url: `http://localhost:8080/departments/v1`,
                method: `POST`,
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('TOKEN')
                },
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(departmentInsert),

                success: function (response) {
                    //Close modal
                    $(`#btn_dismiss_form_account_create`).click()

                    //Reset table
                    dataTable.destroy();
                    getListDepartments(null, null, '', null, page, size, sort)
                },
                error: function (request, status, error) {
                    console.log(error)
                }
            })
        }
    })


    $(`#btn_dismiss_form_department_update`).click(function (e) {
        e.preventDefault()
        dataTable.$('tr.selected').removeClass('selected');
    })
    var departmentUpdateId = null;
    // Update department
    // Lay id 
    $('#datatables_departments').on(`click`, `td .edit`, function (e) {
        e.preventDefault();
        departmentEditId = parseInt($(this).closest('tr').find('td').html()) - 1;
        let departmentRow = dataTable.row(departmentEditId).data()

        console.log(departmentRow)
        departmetUpdateId = departmentRow[1];
        let name = departmentRow[2];
        let type = departmentRow[4];


        $(`#departmentName_update`).val(name)
        $(`#create_type_department_update`).val(type)

        console.log(departmetUpdateId)
    })

    // Thuc hien update 
    $('#btn_save_department_update').click(function (e) {
        e.preventDefault();
        // alert("Click me!!!!")

        let dataUpdate = {
            name: $(`#departmentName_update`).val(),
            type: $(`#create_type_department_update`).val()
        }

        $.ajax({
            url: `http://localhost:8080/departments/v1/` + departmetUpdateId,
            method: `PUT`,
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('TOKEN')
            },
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(dataUpdate),
            success: function (response) {
                //Close modal
                $(`#btn_close_form_department`).click()
                //update row data
                console.log(response)
                let temp = []
                temp[2] = dataUpdate.name
                temp[4] = dataUpdate.type
                dataTable.row(departmetUpdateId).data(temp).draw(false)
            },
            error: function (request, status, error) {
                console.log(error)
            }
        })
    })




    $(`#btn_no_department`).click(function (e) {
        e.preventDefault()
        dataTable.$('tr.selected').removeClass('selected');
    })
    // Dalete mot account
    var departmentIdDelete = null

    $('#datatables_departments').on(`click`, `td .delete`, function (e) {
        e.preventDefault();
        departmentDeletId = parseInt($(this).closest('tr').find('td').html()) - 1;
        let departmentRow = dataTable.row(departmentDeletId).data();

        departmentIdDelete = departmentRow[1];

        console.log(departmentIdDelete)
        console.log(departmentRow)
    })

    $(`#btn_confirm_delete_department`).click(function (e) {
        e.preventDefault();

        $.ajax({
            url: `http://localhost:8080/departments/v1/` + departmentIdDelete,
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('TOKEN')
            },
            method: 'DELETE',
            success: function (response) {
                $(`#btn_no_department`).click()

                // dataTable.row(currentRowIndex).remove().draw()


                dataTable.destroy()
                getListDepartments(null, null, '', null, page, size, sort)
            },
            error: function (request, status, error) {
                console.log(error)
            }
        })
    })

    // Delete all 

    $(`#btn_delete_all_department`).click(function () {
        if (dataTable.rows('.selected').data().length == 0) {
            alert('Mời bạn chọn account muốn xóa')
        } else {
            $('#defaultModalDangerDepartment').modal()
        }
    })

    var ids = [];

    //Clear arrAccountId after delete multiple
    const clearArrDepartmentId = () => {
        while (ids.length > 0) {
            ids.pop()
        }
    }

    $(`#btn_confirm_delete_mutile_department`).click(function (e) {
        e.preventDefault()

        alert("Delete all departments")

        let departmentSelect = dataTable.rows(`.selected`).data();


        if (departmentSelect.length > 0) {
            for (let i = 0; i < departmentSelect.length; i++) {
                ids.push(departmentSelect[i][1])
            }
        }

        console.log(ids)
        $.ajax({
            url: "http://localhost:8080/departments/v1/deleteAllDepartment?ids=" + ids,
            method: 'DELETE',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('TOKEN')
            },
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(ids),
            success: function (response) {
                $(`#btn_close_delete_department`).click()//close modal

                //Refresh table
                dataTable.destroy()

                getListDepartments(null, null, '', null, page, size, sort)

                //Reset arr accounts after delete
                clearArrDepartmentId
                console.log(response)
            },
            error: function (request, status, error) {
                console.log(error)
                //Reset arr accounts after delete
                clearArrDepartmentId()

                //Deselect all on the data table
                dataTable.$('tr.selected').removeClass('selected');
            }
        })
    })

    // add Account

    // create table account departmentid is null
    function getListAccountsIsDepartmentIsNull() {
        let url = `http://localhost:8080/accounts/v1/accountDepartmentIsNull`;
        $.ajax({
            url: url,
            type: `GET`,
            contentType: `application/json`,
            dataType: `json`,
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('TOKEN')
            },
            success: function (response) {
                console.log("List Account")
                console.log(response);
                createDataTableAccount(response);
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
            temp[7] = '';
            accounts.push(temp);
        }
        dataTableAccountUpdate = $(`#datatables_account_add`).DataTable({
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
                    orderable: true,
                    className: 'select-checkbox',
                    targets: 7,
                    with: 40
                }

            ],
            'select': {
                'style': 'multi'
            }
        });
    }

    var departmentIdUpdateForAccount = null;
    var departmentNameUpdateForAccount = null;

    $(`#btn_add_account_in_department`).click(function (e) {
        e.preventDefault();

        getListAccountsIsDepartmentIsNull()
        departmentNameUpdateForAccount = $(`#departmentName`).val();
        $(`#btn_save_department_create`).click();

        console.log(departmentNameUpdateForAccount)
    })


    var idsAccountUpdate = []
    // get dapertmentByName
    function getDepartmentByName() {
        $.ajax({
            url: 'http://localhost:8080/departments/v1/getDepartmentByName?name=' + departmentNameUpdateForAccount,
            type: `GET`,
            contentType: `application/json`,
            dataType: 'json',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('TOKEN')
            },
            success: function (response) {
                departmentIdUpdateForAccount = response.id
                let accountSelect = dataTableAccountUpdate.rows(' .selected').data();
                if (accountSelect.length > 0) {
                    for (let i = 0; i < accountSelect.length; i++) {
                        idsAccountUpdate.push(accountSelect[i][1])
                    }
                }

                $.ajax({
                    url: 'http://localhost:8080/accounts/v1/updateAllAccounts?departmentId=' + departmentIdUpdateForAccount + '&ids=' + idsAccountUpdate,
                    type: `PUT`,
                    contentType: `application/json`,
                    dataType: 'json',
                    data: JSON.stringify(idsAccountUpdate),
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem('TOKEN')
                    },
                    success: function (response) {

                    },

                    error: function (error) {
                        console.log(error)
                    }
                })
            },

            error: function (error) {
                console.log(error)
            }
        })
    }


    $(`#btn_confirm_add_account_in_department`).click(function (e) {
        e.preventDefault()
        getDepartmentByName()
    })
}
