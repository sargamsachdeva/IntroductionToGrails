package linksharingbootcamp

class UrlMappings {


    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        //"/"(controller: 'topic', action: 'show')
      //  "/test"(controller: "util",action: "noAction")

       // "/redirectTest"(redirect: '/test')
        //"/errorTest"(controller: "test",action: "save")
    }
}
