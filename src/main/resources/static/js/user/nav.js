document.addEventListener('DOMContentLoaded', function () {
    clickListenerNavBar()
})

function clickListenerNavBar() {
    document.querySelectorAll('a').forEach(a => {
        a.addEventListener('click', function (event) {
            event.preventDefault()
            let href = this.getAttribute('href')
            fetch(href).then(response => response.text()).then(fragment => {
                document.getElementById('userTable').innerHTML = fragment
                addActionsToNavBar()
                addEventsToTableUsers()
            })
        })
    })
}

function addActionsToNavBar() {
    addUserButton()
}

function addUserButton() {
    document.getElementById("addBtn").addEventListener('click', function (event) {
        event.preventDefault();
        let href = this.getAttribute('href');
        fetch(href).then(response => response.text()).then(fragment => {
            document.querySelector("#addUserModal").innerHTML = fragment;
        }).then(() => {
            let model = new bootstrap.Modal(document.getElementById('addUserModal'), {});
            model.show();
            document.getElementById('add_user').addEventListener('submit', event => submitNewUserForm(event))
        })
    })
}

async function submitNewUserForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    saveUser(request)
}

function saveUser(request) {
    fetch(request).then(response => response.text()).then(fr => {
        let modal = bootstrap.Modal.getInstance(document.getElementById('addUserModal'))
        modal.hide();
        document.getElementById('userTable').innerHTML = fr;
        addUserButton()
        addEventsToTableUsers()
    });
}


function addEventsToTableUsers(){

    document.querySelectorAll('.table .editBtn').forEach(editBtn => editEvent(editBtn))

    document.querySelectorAll('.table .deleteBtn')
        .forEach(deleteBtn => deleteBtn.addEventListener('click', function (event) {
            let href = this.getAttribute('href')
            event.preventDefault()
            document.getElementById('delUser').setAttribute('href', href)
            let modal = new bootstrap.Modal(document.getElementById('deleteUserModal'), {});
            modal.show()
            let delUser = document.getElementById('delUser')
            delUser.addEventListener('click', ev => deleteUser(ev))
        }))
}

async function deleteUser(event) {
    event.preventDefault();
    let el = event.target;
    let href = el.getAttribute('href');
    fetch(href).then(response => response.text()).then(fragment => {
        let modal = bootstrap.Modal.getInstance(document.getElementById('deleteUserModal'))
        modal.hide();
        document.getElementById('userTable').innerHTML = fragment;
        addEventsToTableUsers()
    })
}

function editEvent(element){
    element.addEventListener('click', function (event){
        event.preventDefault()
        let href = this.getAttribute('href')
        editAsyncFetch(href)
    })
}

function editAsyncFetch(href) {
    fetch(href).then(response => response.text()).then(fragment => {
        document.querySelector("#editUserModal").innerHTML = fragment;
    }).then(() => {
        let modal = new bootstrap.Modal(document.getElementById('editUserModal'), {});
        modal.show();
        document.getElementById("edit_user")
            .addEventListener('submit', event => submitEditUserForm(event))
    });
}

async function submitEditUserForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    let response = await fetch(request);
    let userTable = await response.text();
    //console.dir(userTable)

    let modal = bootstrap.Modal.getInstance(document.getElementById('editUserModal'))
    modal.hide();
    document.getElementById('userTable').innerHTML = userTable;
    addEventsToTableUsers()
    addActionsToNavBar()
}

// function searchOnUserTable() {
//
// }