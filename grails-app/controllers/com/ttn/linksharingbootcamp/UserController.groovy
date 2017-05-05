package com.ttn.linksharingbootcamp

class UserController {

    def index() {

       // render "user dashboard"

     //   def u = User.findByUserName("admin")

//        if (u) {
    //        session.user = u
            render "session ${session.user}"
      //     render  "username ${u.userName}"
  //      }
    }
}
