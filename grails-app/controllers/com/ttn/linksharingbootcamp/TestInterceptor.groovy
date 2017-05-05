package com.ttn.linksharingbootcamp


class TestInterceptor {

    int order = HIGHEST_PRECEDENCE + 100

    public TestInterceptor() {
/*match all requests to the demo controller*/
// match controller: 'demo', action: 'index'

/*match all requests except requests
to the test controller*/
//matchAll().excludes(controller:'test')

//To pass the tests
        match controller: 'test', action: 'index'

    }

    boolean before() {
        params.firstInterceptorRan = 'yes'
        println("Accessing TestInterceptor ${controllerName}/${actionName}")
        true

/*redirect(controller: "demo", action: "index")
false*/
    }

    boolean after() {
        println "after ${actionName} completes"
        true
    }

    void afterView() {
        println("after view is generated for ${controllerName}/${actionName}")
// no-op
    }
}