package com.ttn.linksharingbootcamp


class LoginController {

    def index() {


        if (session?.user) {

            forward(controller: 'user', action: 'index')
        } else {

            response.sendError(404)
        }

    }

    def logout() {
        session.invalidate()
        redirect(action: 'index')
    }

    def login(String userName, String password) {

        User user = User.findByUserNameAndPassword(userName, password)

        if (user) {

            if (user.active) {
                session.user = user
                redirect(controller: 'login', action: 'index')

            } else {

                render flash.error = "User is not active"

            }

        } else {

            render flash.error = "User not found"
        }
    }

    def register(String fname,String lname, String email, String pass, String confirm,String uname) {

        User user = new User(firstName: fname, lastName: lname, email: email, userName: uname,
                password: pass,confirmPassword: confirm)

        if(!user.save(flush:true))
            render flash.message='Cannot be registered'

        else
            render flash.message='Successfully registered!'
    }

}