document.addEventListener('DOMContentLoaded', function () {
    addActionsToNavBar()
})

function addActionsToNavBar() {
    addUserButton()
}

function addUserButton() {
    document.getElementById('addBtn').addEventListener('click', function (event) {
        event.preventDefault();
        let href = this.getAttribute('href');
        fetch(href).then(response => response.text()).then(fragment => {
            document.querySelector("#addUserModal").innerHTML = fragment;
        }).then(() => {
            let model = new bootstrap.Modal(document.getElementById('addUserModal'), {});
            model.show();
            // document.getElementById('add_user')
                // .addEventListener('submit', event => submitNewUserForm(event))
        })
    })
}

function searchOnUserTable(){

}